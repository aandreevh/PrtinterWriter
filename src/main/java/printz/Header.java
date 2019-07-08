package printz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.*;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class Header {

    @XmlElement
    private Page page;

    @XmlElementWrapper(name="fields")
    @XmlElement(name="field")
    private ArrayList<Field> fields = new ArrayList<>();

    public Page getPage(){
        return page;
    }

    public Collection<Field> getFields() {
        return Collections.unmodifiableCollection(fields);
    }

    public Field getField(int index){
        return fields.get(index);
    }

    public String toString(){
        return "page:\n"+ page.toString()+"\n"+
                "fields:"+"\n"+
                fields.stream().map(s -> s.toString()+"\n").collect(Collectors.joining());
    }
}
