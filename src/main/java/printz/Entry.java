package printz;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {

    @XmlElement(name = "value")
    private ArrayList<Value> values = new ArrayList<>();


    public Collection<Value> getValues(){
        return Collections.unmodifiableCollection(values);
    }

    public Value getValue(int index){
        return values.get(index);
    }


    public String toString(){
        return values.stream().map(s -> s.toString()).collect(Collectors.joining(", "));
    }
}
