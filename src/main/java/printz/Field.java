package printz;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Field {

    @XmlEnum
    public enum HorizontalAlign{
        left,right,center
    }

    @XmlEnum
    public enum VerticalAlign{
        top,bottom,center
    }

    @XmlValue
    private String name;

    @XmlAttribute
    private int width;

    @XmlAttribute
    private int padding = 0;

    @XmlAttribute(name="horizontal-align")
    private HorizontalAlign horizontalAlign = HorizontalAlign.center;


    @XmlAttribute(name="vertical-align")
    private VerticalAlign verticalAlign = VerticalAlign.center;

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getPadding() {
        return padding;
    }

    public HorizontalAlign getHorizontalAlign() {
        return horizontalAlign;
    }
    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    public String toString(){
        return "name: "+getName()+ "\n"+
                "width: "+getWidth()+"\n"+
                "padding: "+getPadding()+"\n"+
                "horizontal-align: "+getHorizontalAlign().toString()+"\n"+
                "vertical-align: "+getVerticalAlign().toString()+"\n";
    }
}
