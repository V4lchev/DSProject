package Elements;
/**
 * Представя XML attribute или data.
 */
public class XmlData {

    private String Name;
    private String Value;

    public XmlData(String Name, String Value) {

        this.Name = Name;
        this.Value = Value;
    }

    public String getName() {

        return Name;
    }

    public String getValue() {

        return Value;
    }

    public void setValue(String Value) {

        this.Value = Value;
    }
}