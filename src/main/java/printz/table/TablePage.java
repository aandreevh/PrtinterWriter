package printz.table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TablePage {


    private List<TableRow> rowList;

    private void setRowList(Collection<TableRow> rowList){
        this.rowList = Collections.unmodifiableList(List.copyOf(rowList));
    }

    public Collection<TableRow> getRowList(){
        return rowList;
    }

    public TablePage(Collection<TableRow> rowList){
        setRowList(rowList);
    }
}
