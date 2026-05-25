package XMLParser;

/**
 * XML parser клас.
 * Преобразува XML текст в обекти.
 */
public class XmlReader {

    public XmlFile parse(String text) {

        XmlFile file = new XmlFile();

        XmlElement root = new XmlElement("people");

        String[] lines = text.split("\n");

        XmlElement currentPerson = null;

        for (int i = 0; i < lines.length; i++) {

            String line = lines[i].trim();

            if (line.startsWith("<person")) {

                currentPerson = new XmlElement("person");

                int idStart = line.indexOf("id=\"");

                if (idStart != -1) {

                    int valueStart = idStart + 4;
                    int valueEnd = line.indexOf("\"", valueStart);

                    String idValue = line.substring(valueStart, valueEnd);

                    currentPerson.addData(new XmlData("id", idValue));
                }
            }
            else if (line.startsWith("</person>")) {

                if (currentPerson != null) {

                    root.addElement(currentPerson);

                    currentPerson = null;
                }
            }
            else if (currentPerson != null && line.startsWith("<") && line.contains("</")) {

                int tagStart = line.indexOf("<") + 1;
                int tagEnd = line.indexOf(">");

                String tagName = line.substring(tagStart, tagEnd);

                int textStart = tagEnd + 1;
                int textEnd = line.indexOf("</");

                String value = line.substring(textStart, textEnd);

                XmlElement smallElement = new XmlElement(tagName);

                smallElement.setText(value);

                currentPerson.addElement(smallElement);
            }
        }

        file.setRootElement(root);

        return file;
    }
}