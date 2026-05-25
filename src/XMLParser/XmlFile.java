package XMLParser;

/**
 * Представя XML файл.
 */
public class XmlFile {

    private XmlElement RootElement;

    public XmlFile() {

        RootElement = null;
    }

    public void setRootElement(XmlElement RootElement) {

        this.RootElement = RootElement;
    }

    public XmlElement getRootElement() {

        return RootElement;
    }
}
