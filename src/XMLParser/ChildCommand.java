package XMLParser;

/**
 * Команда за извеждане на конкретен child елемент.
 */
public class ChildCommand extends Command {

    public ChildCommand(ProgramData data) {
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

            System.out.println("Използване: child <id> <n>");

            return false;
        }

        String searchedId = parts[1];

        int index = Integer.parseInt(parts[2]) - 1;

        XmlElement element = findElementById(data.currentFile.getRootElement(), searchedId);

        if (element == null) {

            System.out.println("Няма елемент с такова id.");

            return false;
        }

        if (index < 0 || index >= element.getElements().size()) {

            System.out.println("Невалиден номер.");

            return false;
        }

        XmlElement child = element.getElements().get(index);

        System.out.println(child.getTagName() + " = " + child.getText());

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