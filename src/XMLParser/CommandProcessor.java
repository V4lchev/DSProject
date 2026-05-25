package XMLParser;

/**
 * Клас за обработка на командите от потребителя.
 */
public class CommandProcessor {

    private ProgramData data;

    public CommandProcessor() {
        data = new ProgramData();
    }

    /**
     * Изпълнява команда от конзолата.
     *
     * @param command команда от потребителя
     * @return true при изход от програмата
     */
    public boolean executeCommand(String command) {

        command = command.trim();

        Command currentCommand = null;

        if (command.equals("help")) {
            currentCommand = new HelpCommand(data);
        }
        else if (command.startsWith("open ")) {
            currentCommand = new OpenCommand(data);
        }
        else if (command.equals("close")) {
            currentCommand = new CloseCommand(data);
        }
        else if (command.equals("save")) {
            currentCommand = new SaveCommand(data);
        }
        else if (command.startsWith("save as ")) {
            currentCommand = new SaveAsCommand(data);
        }
        else if (command.equals("print")) {
            currentCommand = new PrintCommand(data);
        }
        else if (command.startsWith("select ")) {
            currentCommand = new SelectCommand(data);
        }
        else if (command.startsWith("set ")) {
            currentCommand = new SetCommand(data);
        }
        else if (command.startsWith("children ")) {
            currentCommand = new ChildrenCommand(data);
        }
        else if (command.startsWith("child ")) {
            currentCommand = new ChildCommand(data);
        }
        else if (command.startsWith("text ")) {
            currentCommand = new TextCommand(data);
        }
        else if (command.startsWith("delete ")) {
            currentCommand = new DeleteCommand(data);
        }
        else if (command.equals("exit")) {
            currentCommand = new ExitCommand(data);
        }
        else {
            System.out.println("Непозната команда.");
            return false;
        }

        return currentCommand.execute(command);
    }
}