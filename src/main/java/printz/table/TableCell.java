package printz.table;

import java.util.*;
import java.util.stream.Collectors;

public class TableCell {

    private ArrayList<String> content ;

    private void setContent(Collection<String> content){
        this.content = new ArrayList<>(content);
    }

    public ArrayList<String> getContent(){
        return content;
    }

    public TableCell(Collection<String> content){
        setContent(content);
    }

    public String toString(){
        return getContent().stream().collect(Collectors.joining("\n"));
    }
}
