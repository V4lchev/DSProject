package XMLParser;

/**
 * XML parser клас.
 * Преобразува XML текст в обекти.
 */
public class XmlReader {

    public XmlFile parse(String text) {

        XmlFile file = new XmlFile();

        XmlElement root = null;
        XmlElement currentElement = null;

        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {

            String line = lines[i].trim();

            if (line.equals("")) {
                continue;
            }

            if (line.startsWith("</")) {

                currentElement = null;
            }
            else if (line.startsWith("<") && line.contains("</")) {

                int tagStart = line.indexOf("<") + 1;
                int tagEnd = line.indexOf(">");

                String tagName = line.substring(tagStart, tagEnd);

                int textStart = tagEnd + 1;
                int textEnd = line.indexOf("</");

                String value = line.substring(textStart, textEnd);

                XmlElement smallElement = new XmlElement(tagName);
                smallElement.setText(value);

                if (currentElement != null) {
                    currentElement.addElement(smallElement);
                }
                else if (root != null) {
                    root.addElement(smallElement);
                }
            }
            else if (line.startsWith("<")) {

                int tagStart = line.indexOf("<") + 1;
                int tagEnd = line.indexOf(">");

                String fullTag = line.substring(tagStart, tagEnd);

                String[] parts = fullTag.split(" ");

                XmlElement element = new XmlElement(parts[0]);

                for (int j = 1; j < parts.length; j++) {

                    if (parts[j].contains("=")) {

                        String[] dataParts = parts[j].split("=");

                        String name = dataParts[0];

                        String value = dataParts[1].replace("\"", "");

                        element.addData(new XmlData(name, value));
                    }
                }

                if (root == null) {

                    root = element;
                }
                else {

                    root.addElement(element);

                    currentElement = element;
                }
            }
        }

        file.setRootElement(root);

        return file;
    }
}