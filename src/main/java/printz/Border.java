package printz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Border {

    @XmlElement(name="horizontal")
    private char horizontalBorder ='-';

    @XmlElement(name="vertical")
    private char verticalBorder = '|';

    @XmlElement(name="cross")
    private char crossBorder = '+';

    public char getHorizontalBorder() {
        return horizontalBorder;
    }

    public char getVerticalBorder() {
        return verticalBorder;
    }

    public char getCrossBorder() {
        return crossBorder;
    }

    public String toString(){
        return "vertical-border: "+ (int)getVerticalBorder()+"\n"+
                "horizontal-border: "+ (int)getHorizontalBorder()+"\n"+
                "cross-border: "+ (int)getCrossBorder();
    }
}
