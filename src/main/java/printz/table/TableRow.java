package printz.table;

import printz.Entry;
import printz.Header;

import java.util.*;

public class TableRow {

    private ArrayList<TableCell> cellList;

    private void setCellList(Collection<TableCell> cells){
        cellList = new ArrayList<>(cells);
    }

    public ArrayList<TableCell> getCells(){
        return cellList;
    }
    public TableRow(Collection<TableCell> cells){
        setCellList(cells);
    }

    public int getHeight() {
        return  cellList.stream().map(a -> a.getContent().size()).max((a,b)-> a-b).orElse(0);
    }
}
