package printz.table;

import printz.Entry;
import printz.Header;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TableRow {

    private List<TableCell> cellList;

    private void setCellList(Collection<TableCell> cells){
        cellList = Collections.unmodifiableList(List.copyOf(cells));
    }

    public Collection<TableCell> getCells(){
        return cellList;
    }
    public TableRow(Collection<TableCell> cells){
        setCellList(cells);
    }
}
