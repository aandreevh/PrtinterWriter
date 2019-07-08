package printz.table;

import printz.Border;
import printz.Field;
import printz.Header;
import printz.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Table {


    private ArrayList<TablePage> tablePageList;
    private Header header;

    private void setTablePageList(Collection<TablePage> tablePages){
        tablePageList = new ArrayList<>(tablePages);
    }

    private void setHeader(Header header){
        this.header = header;
    }

    public ArrayList<TablePage> getTablePages(){
        return tablePageList;
    }

    public Header getHeader(){
        return header;
    }
    Table(List<TablePage> tablePages, Header header){
        setTablePageList(tablePages);
        setHeader(header);
    }

    public String getTablePage(int page){
        TablePage p = tablePageList.get(page-1);
        Border b = getHeader().getPage().getBorder();

        StringBuilder builder = new StringBuilder();

        builder.append(b.getCrossBorder());
        for(Field f : getHeader().getFields()){
        builder.append(String.valueOf(b.getHorizontalBorder()).repeat(f.getWidth()));
            builder.append(b.getCrossBorder());
        }

        for(TableRow row : p.getRowList()){
           int h= row.getHeight();
           for(int j=0;j<h;j++){
               builder.append(getHeader().getPage().getEscapeLine());
               builder.append(b.getVerticalBorder());

               for(TableCell c : row.getCells()){
                    builder.append(c.getContent().get(j));
                   builder.append(b.getVerticalBorder());
               }


           }
            builder.append(getHeader().getPage().getEscapeLine());
            builder.append(b.getCrossBorder());
            for(Field f : getHeader().getFields()){
                builder.append(String.valueOf(b.getHorizontalBorder()).repeat(f.getWidth()));
                builder.append(b.getCrossBorder());
            }
        }

        return builder.toString();
    }

    public String getTableRaw() {
        StringBuilder b = new StringBuilder();
        for(int i=0;i<getTablePages().size();i++){
            b.append(getTablePage(i+1));
            b.append(getHeader().getPage().getEscapePage());
        }

        return b.toString();
    }
}
