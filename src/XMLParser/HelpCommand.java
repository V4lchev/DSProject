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

        System.out.println("========== HELP MENU ==========");

        System.out.println();

        System.out.println("open <file>          - отваря файл");
        System.out.println("close                - затваря файла");
        System.out.println("save                 - записва промените");
        System.out.println("save as <file>       - запис под ново име");
        System.out.println("print                - извежда XML съдържанието");
        System.out.println("select <id> <key>    - извежда стойност");
        System.out.println("set <id> <key> <value> - променя стойност");
        System.out.println("children <id>        - показва вложени елементи");
        System.out.println("child <id> <n>       - показва конкретен child");
        System.out.println("text <id>            - показва текст");
        System.out.println("delete <id> <key>    - изтрива стойност");
        System.out.println("help                 - показва help меню");
        System.out.println("exit                 - изход от програмата");

        System.out.println();

        System.out.println("================================");

        return false;
    }
}