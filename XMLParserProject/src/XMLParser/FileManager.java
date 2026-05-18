package XMLParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Клас за четене и записване на файлове.
 */
public class FileManager {

    public String readFile(String fileName) {

        String text = "";

        try {
            File file = new File(fileName);

            if (file.exists() == false) {
                file.createNewFile();
                System.out.println("НЕ същестува такъв файл и беше създаден нов.");
                return "";
            }

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                text = text + reader.nextLine() + "\n";
            }

            reader.close();
        }
        catch (IOException e) {
            System.out.println("Грешка при четене на файла.");
        }

        return text;
    }

    public void writeFile(String fileName, String text) {

        try {
            FileWriter writer = new FileWriter(fileName);

            writer.write(text);

            writer.close();

            System.out.println("Файлът е записан успешно.");
        }
        catch (IOException e) {
            System.out.println("Грешка при запис на файла.");
        }
    }
}