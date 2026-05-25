package XMLParser;

/**
 * Команда за изход от програмата.
 */
public class ExitCommand extends Command {

    public ExitCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        System.out.println("Край на програмата.");

        return true;
    }
}