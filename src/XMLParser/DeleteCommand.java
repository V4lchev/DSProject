package XMLParser;

/**
 * Команда за изтриване на стойност по id и key.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        if (!data.isFileOpen) {
            System.out.println("Няма отворен файл.");
            return false;
        }

        String[] parts = command.split(" ");

        if (parts.length < 3) {
            System.out.println("Използване: delete <id> <key>");
            return false;
        }

        String searchedId = parts[1];
        String searchedKey = parts[2];

        XmlElement element = findElementById(data.currentFile.getRootElement(), searchedId);

        if (element == null) {
            System.out.println("Няма елемент с такова id.");
            return false;
        }

        for (int i = 0; i < element.getData().size(); i++) {
            XmlData currentData = element.getData().get(i);

            if (currentData.getName().equals(searchedKey)) {
                element.getData().remove(i);
                System.out.println("Успешно изтриване.");
                return false;
            }
        }

        for (int i = 0; i < element.getElements().size(); i++) {
            XmlElement child = element.getElements().get(i);

            if (child.getTagName().equals(searchedKey)) {
                element.getElements().remove(i);
                System.out.println("Успешно изтриване.");
                return false;
            }
        }

        System.out.println("Няма такъв key.");
        return false;
    }

    private XmlElement findElementById(XmlElement element, String id) {

        if (element == null) {
            return null;
        }

        for (int i = 0; i < element.getData().size(); i++) {
            XmlData currentData = element.getData().get(i);

            if (currentData.getName().equals("id") && currentData.getValue().equals(id)) {
                return element;
            }
        }

        for (int i = 0; i < element.getElements().size(); i++) {
            XmlElement found = findElementById(element.getElements().get(i), id);

            if (found != null) {
                return found;
            }
        }

        return null;
    }
}