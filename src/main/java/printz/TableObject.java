package printz;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class TableObject {

    @XmlElement
    private Header header;

    @XmlElementWrapper
    @XmlElement(name="entry")
    private List<Entry> data = new LinkedList<>();



    public Header getHeader(){
        return header;
    }

    public Collection<Entry> getData(){
        return data;
    }

    public String toString(){
        return "header: \n"+getHeader().toString()+"\n"
                +"data: \n"+getData().stream().map(s -> s.toString()).collect(Collectors.joining("\n"));
    }


    public static TableObject loadObject(String filename) throws Exception{

        JAXBContext jc = JAXBContext.newInstance(TableObject.class);
        Schema s = SchemaFactory.newDefaultInstance().newSchema(ClassLoader.getSystemResource("schema.xsd"));
        Unmarshaller u = jc.createUnmarshaller();
        u.setSchema(s);

        return (TableObject)u.unmarshal(new File(filename));

    }
}
