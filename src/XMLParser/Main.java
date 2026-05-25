package XMLParser;

import java.util.Scanner;

/**
 * Главен клас на програмата.
 * Стартира XML Parser приложението.
 */
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        CommandProcessor processor = new CommandProcessor();

        System.out.println("XML Parser");
        System.out.println("Поддържани команди:");
        System.out.println("open <file>");
        System.out.println("close");
        System.out.println("save");
        System.out.println("save as <file>");
        System.out.println("print");
        System.out.println("select <id> <key>");
        System.out.println("set <id> <key> <value>");
        System.out.println("children <id>");
        System.out.println("child <id> <n>");
        System.out.println("text <id>");
        System.out.println("delete <id> <key>");
        System.out.println("help");
        System.out.println("exit");

        while (true) {

            System.out.print("> ");

            String command = input.nextLine();

            boolean exit = processor.executeCommand(command);

            if (exit) {
                break;
            }
        }

        input.close();
    }
}