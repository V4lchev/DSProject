package XMLParser;

/**
 * Общ базов клас за всички команди.
 */
public abstract class Command {

    protected ProgramData data;

    public Command(ProgramData data) {
        this.data = data;
    }

    public abstract boolean execute(String command);
}