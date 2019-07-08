package printz.table;

import java.util.*;
import java.util.stream.Collectors;

public class TableCell {

    private List<String> content ;

    private void setContent(Collection<String> content){
        this.content = Collections.unmodifiableList(List.copyOf(content));
    }

    public Collection<String> getContent(){
        return content;
    }

    public TableCell(Collection<String> content){
        setContent(content);
    }

    public String toString(){
        return getContent().stream().collect(Collectors.joining("\n"));
    }
}
