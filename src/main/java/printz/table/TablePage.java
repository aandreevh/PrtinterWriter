package printz.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TablePage {


    private ArrayList<TableRow> rowList;

    private void setRowList(Collection<TableRow> rowList){
        this.rowList =new ArrayList<>(rowList);
    }

    public ArrayList<TableRow> getRowList(){
        return rowList;
    }

    public TablePage(Collection<TableRow> rowList){
        setRowList(rowList);
    }
}
