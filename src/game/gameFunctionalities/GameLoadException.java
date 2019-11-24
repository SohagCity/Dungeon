package game.gameFunctionalities;


/**
 * Exceptions when loading the game.
 */
public class GameLoadException extends Exception {
    private String reason;

    public GameLoadException (String reason){
        this.reason=reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
