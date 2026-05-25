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

        processor.executeCommand("help");

        while (true) {

            System.out.print("> ");

            String command = input.nextLine();

            boolean exit = processor.executeCommand(command);

            if (exit) {
                break;
            }
        }
    }
}