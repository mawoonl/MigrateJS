package nl.mawoo.wcmscript.cli;


import nl.mawoo.wcmscript.WCMScript;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, ScriptException {
        UUID instanceId = UUID.randomUUID();
        WCMScript wcmScript = new WCMScript(instanceId, new CLILogginConfig());

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int indentation = 0;
        StringBuilder code = new StringBuilder();
        while (true) {
            try {
                System.out.print("> " + StringUtils.repeat("  ", indentation));
                String line = bf.readLine();
                indentation += StringUtils.countMatches(line, '{');
                indentation -= StringUtils.countMatches(line, '}');
                code.append(line).append('\n');

                if (indentation == 0) {
                    eval(wcmScript, code);
                }
            } catch (IOException e) {
                LOGGER.error("IO exception: ", e);
            }
        }
    }

    private static void eval(WCMScript wcmScript, StringBuilder code) {
        try {
            wcmScript.eval(code.toString());
        } catch (ScriptException e) {
            wcmScript.getScriptLogger().error("SCRIPT ERROR", e);
        } catch (Exception e) {
            LOGGER.error("Uncaught exception: ", e);
        } finally {
            code.setLength(0);
        }
    }
}