package nl.mawoo.wcmmanager.controller;

import nl.mawoo.wcmmanager.services.ExecutionResult;
import nl.mawoo.wcmmanager.services.WCMScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;

/**
 * WCMS Fiddle
 * This class is responsible for running the editor for testing code
 * Also it views the WCMScript output in the console.
 *
 * @author Bob van der Valk
 */
@Controller
@RequestMapping("/fiddle")
public class Fiddle {
    private final WCMScriptService scriptService;

    @Autowired
    public Fiddle(WCMScriptService scriptService) {
        this.scriptService = scriptService;
    }

    /**
     * Run the fiddle editor
     *
     * @return editor.html in template folder
     */
    @RequestMapping("")
    public String editor() {
        return "editor";
    }

    /**
     * Handle the run command. To run a WCMScript into the Fiddle.
     *
     * @param content javascript input for the WCMScript
     * @return nothing
     */
    @ResponseBody
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public ExecutionResult run(@RequestParam("code") String content) throws ScriptException {
        return scriptService.run(content);
    }

    /**
     * Responsible to show the console output
     *
     * @return console output page
     */
    @RequestMapping(value = "/console", method = RequestMethod.GET)
    public String consoleOutput() {

        return "console";
    }
}