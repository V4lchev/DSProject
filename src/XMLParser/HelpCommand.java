package XMLParser;

/**
 * Команда за извеждане на помощно меню.
 */
public class HelpCommand extends Command {

    public HelpCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        System.out.println("===== Основни команди =====");
        System.out.println("open <file>");
        System.out.println("close");
        System.out.println("save");
        System.out.println("save as <file>");
        System.out.println("help");
        System.out.println("exit");

        System.out.println();

        System.out.println("===== XML команди =====");
        System.out.println("print");
        System.out.println("select <id> <key>");
        System.out.println("set <id> <key> <value>");
        System.out.println("children <id>");
        System.out.println("child <id> <n>");
        System.out.println("text <id>");
        System.out.println("delete <id> <key>");

        return false;
    }
}