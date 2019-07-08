package printz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Page {

    @XmlElement
    private int height;

    @XmlElement
    private Border border = new Border();


    @XmlElement(name="escape-line")
    private char escapeLine='\n';

    @XmlElement(name="escape-page")
    private char escapePage='\n';

    @XmlElement(name="line-breaker")
    private char lineBreaker=' ';

    public int getHeight() {
        return height;
    }

    public char getEscapeLine(){
        return escapeLine;
    }

    public char getEscapePage(){
        return escapePage;
    }

    public char getLineBreaker(){
        return lineBreaker;
    }

    public Border getBorder(){
        return border;
    }


    public String toString(){
        return  "border: \n"+ getBorder().toString()+"\n\n"+
                "height: "+getHeight()+"\n"+
                "line-breaker: "+(int)getLineBreaker()+"\n"+
                "escape-line: "+(int)getEscapeLine()+"\n"+
                "escape-page: "+(int)getEscapePage()+"\n";
    }
}
