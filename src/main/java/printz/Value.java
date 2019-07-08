package printz;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Value {

    @XmlValue
    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
