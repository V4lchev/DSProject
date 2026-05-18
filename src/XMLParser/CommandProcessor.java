package XMLParser;

import Elements.XmlData;
import Elements.XmlElement;
import Files.XmlFile;
import Parser.XmlReader;
import Writer.XmlWriter;
/**
 * Клас за обработка на командите от потребителя.
 */
public class CommandProcessor {

    private boolean isFileOpen;
    private String openedFileName;
    private String fileText;
    private FileManager fileManager;

    private XmlFile CurrentFile;
    private XmlReader Reader;
    private XmlWriter Writer;

    public CommandProcessor() {

        isFileOpen = false;
        openedFileName = "";
        fileText = "";
        fileManager = new FileManager();

        CurrentFile = null;
        Reader = new XmlReader();
        Writer = new XmlWriter();
    }
    /**
     * Изпълнява команда от конзолата.
     *
     * @param command команда от потребителя
     * @return true при изход от програмата
     */
    public boolean executeCommand(String command) {

        command = command.trim();

        if (command.equals("help")) {
            help();
        }
        else if (command.startsWith("open ")) {
            open(command);
        }
        else if (command.equals("close")) {
            close();
        }
        else if (command.equals("save")) {
            save();
        }
        else if (command.startsWith("save as ")) {
            saveAs(command);
        }
        else if (command.equals("print")) {
            print();
        }
        else if (command.startsWith("select ")) {
            select(command);
        }
        else if (command.startsWith("set ")) {
            set(command);
        }
        else if (command.startsWith("children ")) {
            children(command);
        }
        else if (command.startsWith("child ")) {
            child(command);
        }
        else if (command.startsWith("text ")) {
            text(command);
        }
        else if (command.startsWith("delete ")) {
            delete(command);
        }
        else if (command.equals("exit")) {

            System.out.println("Край на програмата.");

            return true;
        }
        else {
            System.out.println("Непозната команда.");
        }

        return false;
    }
    /**
     * Отваря XML файл.
     *
     * @param command команда за отваряне
     */
    private void open(String command) {

        openedFileName = command.substring(5);

        fileText = fileManager.readFile(openedFileName);

        CurrentFile = Reader.parse(fileText);

        isFileOpen = true;

        System.out.println("Successfully opened " + openedFileName);
    }
    /**
     * Затваря текущо отворения XML файл.
     */
    private void close() {

        if (isFileOpen == false) {

            System.out.println("Няма отворен файл.");

            return;
        }

        System.out.println("Successfully closed " + openedFileName);

        openedFileName = "";
        fileText = "";
        CurrentFile = null;
        isFileOpen = false;
    }
    /**
     * Записва текущия XML файл.
     */
    private void save() {

        if (isFileOpen == false) {

            System.out.println("Няма отворен файл.");

            return;
        }

        fileText = Writer.build(CurrentFile);

        fileManager.writeFile(openedFileName, fileText);

        System.out.println("Successfully saved " + openedFileName);
    }
    /**
     * Записва XML файла под ново име.
     *
     * @param command команда save as
     */
    private void saveAs(String command) {

        if (isFileOpen == false) {

            System.out.println("Няма отворен файл.");

            return;
        }

        String newFileName = command.substring(8);

        fileText = Writer.build(CurrentFile);

        fileManager.writeFile(newFileName, fileText);

        System.out.println("Successfully saved as " + newFileName);
    }

    private XmlElement findPersonById(String searchedId) {

        if (CurrentFile == null || CurrentFile.getRootElement() == null) {

            return null;
        }

        for (int i = 0; i < CurrentFile.getRootElement().getElements().size(); i++) {

            XmlElement person = CurrentFile.getRootElement().getElements().get(i);

            for (int j = 0; j < person.getData().size(); j++) {

                XmlData data = person.getData().get(j);

                if (data.getName().equals("id") && data.getValue().equals(searchedId)) {

                    return person;
                }
            }
        }

        return null;
    }
    /**
     * Извежда XML информацията.
     */
    private void print() {

        if (CurrentFile == null || CurrentFile.getRootElement() == null) {

            System.out.println("Няма зареден XML файл.");

            return;
        }

        System.out.println("========== XML ==========");

        for (int i = 0; i < CurrentFile.getRootElement().getElements().size(); i++) {

            XmlElement person = CurrentFile.getRootElement().getElements().get(i);

            System.out.println();
            System.out.println("Person " + (i + 1));

            for (int j = 0; j < person.getData().size(); j++) {

                XmlData data = person.getData().get(j);

                System.out.println(data.getName() + " = " + data.getValue());
            }

            for (int j = 0; j < person.getElements().size(); j++) {

                XmlElement element = person.getElements().get(j);

                System.out.println(element.getTagName() + " = " + element.getText());
            }
        }

        System.out.println();
        System.out.println("=========================");
    }
    /**
     * Извежда стойност по id и key.
     *
     * @param command команда select
     */
    private void select(String command) {

        String[] parts = command.split(" ");

        if (parts.length < 3) {

            System.out.println("Използване: select <id> <key>");

            return;
        }

        String searchedId = parts[1];
        String searchedKey = parts[2];

        XmlElement person = findPersonById(searchedId);

        if (person == null) {

            System.out.println("Няма елемент с такова id.");

            return;
        }

        for (int i = 0; i < person.getData().size(); i++) {

            XmlData data = person.getData().get(i);

            if (data.getName().equals(searchedKey)) {

                System.out.println(data.getValue());

                return;
            }
        }

        for (int i = 0; i < person.getElements().size(); i++) {

            XmlElement element = person.getElements().get(i);

            if (element.getTagName().equals(searchedKey)) {

                System.out.println(element.getText());

                return;
            }
        }

        System.out.println("Няма такава data за този id.");
    }
    /**
     * Променя стойност в XML елемент.
     *
     * @param command команда set
     */
    private void set(String command) {

        String[] parts = command.split(" ");

        if (parts.length < 4) {

            System.out.println("Използване: set <id> <key> <value>");

            return;
        }

        String searchedId = parts[1];
        String searchedKey = parts[2];
        String newValue = parts[3];

        XmlElement person = findPersonById(searchedId);

        if (person == null) {

            System.out.println("Няма елемент с такова id.");

            return;
        }

        for (int i = 0; i < person.getData().size(); i++) {

            XmlData data = person.getData().get(i);

            if (data.getName().equals(searchedKey)) {

                data.setValue(newValue);

                System.out.println("Успешна промяна.");

                return;
            }
        }

        for (int i = 0; i < person.getElements().size(); i++) {

            XmlElement element = person.getElements().get(i);

            if (element.getTagName().equals(searchedKey)) {

                element.setText(newValue);

                System.out.println("Успешна промяна.");

                return;
            }
        }

        System.out.println("Няма такова поле.");
    }
    /**
     * Извежда всички children елементи.
     *
     * @param command команда children
     */
    private void children(String command) {

        String[] parts = command.split(" ");

        if (parts.length < 2) {

            System.out.println("Използване: children <id>");

            return;
        }

        XmlElement person = findPersonById(parts[1]);

        if (person == null) {

            System.out.println("Няма елемент с такова id.");

            return;
        }

        System.out.println("Children на id " + parts[1] + ":");

        for (int i = 0; i < person.getElements().size(); i++) {

            XmlElement element = person.getElements().get(i);

            System.out.println((i + 1) + ". " + element.getTagName());
        }
    }
    /**
     * Извежда конкретен child елемент.
     *
     * @param command команда child
     */
    private void child(String command) {

        String[] parts = command.split(" ");

        if (parts.length < 3) {

            System.out.println("Използване: child <id> <n>");

            return;
        }

        XmlElement person = findPersonById(parts[1]);

        if (person == null) {

            System.out.println("Няма елемент с такова id.");

            return;
        }

        int number = Integer.parseInt(parts[2]);

        if (number < 1 || number > person.getElements().size()) {

            System.out.println("Няма child с такъв номер.");

            return;
        }

        XmlElement element = person.getElements().get(number - 1);

        System.out.println(element.getTagName() + " = " + element.getText());
    }
    /**
     * Извежда текста на XML елемент.
     *
     * @param command команда text
     */
    private void text(String command) {

        String[] parts = command.split(" ");

        if (parts.length < 2) {
            System.out.println("Използване: text <id>");
            return;
        }

        XmlElement person = findPersonById(parts[1]);

        if (person == null) {
            System.out.println("Няма елемент с такова id.");
            return;
        }

        System.out.println("========== TEXT ==========");

        for (int i = 0; i < person.getData().size(); i++) {
            XmlData data = person.getData().get(i);
            System.out.println(data.getName() + " : " + data.getValue());
        }

        for (int i = 0; i < person.getElements().size(); i++) {
            XmlElement element = person.getElements().get(i);
            System.out.println(element.getTagName() + " : " + element.getText());
        }

        System.out.println("==========================");
    }
    /**
     * Изтрива XML елемент по key.
     *
     * @param command команда delete
     */
    private void delete(String command) {

        String[] parts = command.split(" ");

        if (parts.length < 3) {

            System.out.println("Използване: delete <id> <key>");

            return;
        }

        String searchedId = parts[1];

        String searchedKey = parts[2];

        XmlElement person = findPersonById(searchedId);

        if (person == null) {

            System.out.println("Няма елемент с такова id.");

            return;
        }

        for (int i = 0; i < person.getData().size(); i++) {

            XmlData data = person.getData().get(i);

            if (data.getName().equals(searchedKey)) {

                person.getData().remove(i);

                System.out.println("Успешно изтриване.");

                return;
            }
        }

        for (int i = 0; i < person.getElements().size(); i++) {

            XmlElement element = person.getElements().get(i);

            if (element.getTagName().equals(searchedKey)) {

                person.getElements().remove(i);

                System.out.println("Успешно изтриване.");

                return;
            }
        }

        System.out.println("Няма такъв key.");
    }
    /**
     * Извежда списък с всички поддържани команди.
     */
    private void help() {

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
    }
}