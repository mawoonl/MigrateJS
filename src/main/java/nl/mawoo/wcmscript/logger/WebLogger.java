package nl.mawoo.wcmscript.logger;

/**
 * This class is responsible to log stuff to the web
 *
 * @author Bob van der Valk
 */
public class WebLogger implements LoggerInterface {

    private BroadCaster broadCaster;

    public WebLogger(String broadcastUrl) {
        broadCaster = new BroadCaster(broadcastUrl);
    }

    /**
     * Send a error message to the web manager console
     * @param message String message with the cause
     */
    @Override
    public void error(String message) {
        broadCaster.sendMessage(BroadCaster.ERROR, message);
    }

    /**
     * * Send a error message to the web manager console
     * @param message String message with the cause
     * @param cause throwable cause.
     */
    @Override
    public void error(String message, Throwable cause) {
        broadCaster.sendMessage(BroadCaster.ERROR, message, cause);
    }

    /**
     * Send a info message
     * @param message String with the message
     */
    @Override
    public void info(String message) {
        broadCaster.sendMessage(BroadCaster.INFO, message);
    }

    @Override
    public void info(String message, Throwable cause) {
        broadCaster.sendMessage(BroadCaster.INFO, message, cause);
    }

    @Override
    public void warning(String message) {
        broadCaster.sendMessage(BroadCaster.WARNING, message);
    }

    @Override
    public void warning(String message, Throwable cause) {
        broadCaster.sendMessage(BroadCaster.WARNING, message, cause);
    }
}