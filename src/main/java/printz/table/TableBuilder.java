package printz.table;

import printz.Entry;
import printz.Field;
import printz.TableObject;
import printz.Value;

import java.util.*;
import java.util.stream.Collectors;

public final class TableBuilder {
    private TableObject tobj;

    public TableObject getTableObject(){
        return tobj;
    }

    public TableBuilder(TableObject tobj){
        this.tobj = tobj;
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    private static String align(String string,int n,Field.HorizontalAlign align){
        int diff = n-string.length();
        if(diff ==0)return string;

        if(align == Field.HorizontalAlign.left){

            return padRight(string,n);

        }else if( align == Field.HorizontalAlign.center){
            int diff1 = diff/2;
            return padLeft(padRight(string,string.length()+diff-diff1),n);

        }else if(align == Field.HorizontalAlign.right){
            return padLeft(string,n);
        }

        return string;
    }


    private TableCell createTableColumn(String string,int field){
        Field f = getTableObject().getHeader().getField(field);

        final String lineBreaker=String.valueOf(getTableObject().getHeader().getPage().getLineBreaker());
        String[] strings = string.trim().split(lineBreaker);
        ArrayList<String> readyStrings = new ArrayList<>();
        String cstring = "";
        for(String cs : strings){
            if(cstring.equals("")){
                if((cstring+cs).length() > f.getWidth())
                    throw  new IndexOutOfBoundsException("Cannot fit string to column !");
                else cstring += cs;
            }else{

                if((cstring+cs).length() +1 > f.getWidth())
                {
                    readyStrings.add(align(cstring,f.getWidth(),f.getHorizontalAlign()));
                    cstring = "";

                    if((cstring+cs).length() > f.getWidth())
                        throw  new IndexOutOfBoundsException("Cannot fit string to column !");
                    else cstring += cs;
                }
                else cstring += lineBreaker+cs;
            }
        }

        readyStrings.add(align(cstring,f.getWidth(),f.getHorizontalAlign()));

        return new TableCell(readyStrings);
    }
    private TableRow createTableRow(ArrayList<String> strings) {
        ArrayList<TableCell> columns = new ArrayList<>();
        ArrayList<TableCell> columns2 = new ArrayList<>();

        //Add regularly
        for(int i=0;i<strings.size();i++)
            columns.add(createTableColumn(strings.get(i),i));

        //Modify height to align
        int max = columns.stream().map(a-> a.getContent().size()).max((a,b)->a-b).orElse(0);

        for(int i=0;i<columns.size();i++) {
            ArrayList<String> str = new ArrayList<>(columns.get(i).getContent());
            int diff = max - str.size();
            if (diff > 0) {
                Field f = getTableObject().getHeader().getField(i);
                switch (f.getVerticalAlign()) {
                    case top:
                        for (int j = 0; j < diff; j++) str.add(" ".repeat(f.getWidth()));

                        break;
                    case bottom:
                        for (int j = 0; j < diff; j++) str.add(0, " ".repeat(f.getWidth()));
                        break;
                    case center:
                        int diff1 = diff / 2;

                        for (int j = 0; j < diff1; j++) str.add(0, " ".repeat(f.getWidth()));

                        for (int j = 0; j < diff - diff1; j++) str.add(" ".repeat(f.getWidth()));
                        break;
                }
            }
            columns2.add(new TableCell(str));
        }

        return new TableRow(columns2);
    }

    public Table build() {

        ArrayList<TablePage> tablePages = new ArrayList<>();
        ArrayList<TableRow>  pageRows = new ArrayList<>();

        final int maxPageHeight = tobj.getHeader().getPage().getHeight();
        int currentPageHeight = 0;
        int lastID = -1;
        Entry[] entries = getTableObject().getData().toArray(new Entry[getTableObject().getData().size()]);
        for(int i=0;i<entries.length;i++){
            Entry entry = entries[i];
            if(currentPageHeight == 0){
                TableRow row = createTableRow(new ArrayList<>
                        (getTableObject().getHeader().getFields().stream().map(Field::getName)
                        .collect(Collectors.toList())));

                int h = row.getHeight();
                if(h+2 > maxPageHeight) throw new IndexOutOfBoundsException("Cannot fit header into table !");
                pageRows.add(row);
                currentPageHeight += h+2;

            }

            TableRow row = createTableRow(new ArrayList<>
                    (entry.getValues().stream().map(Value::getValue)
                            .collect(Collectors.toList())));
                int h = row.getHeight();
                if(h +1 +currentPageHeight > maxPageHeight){
                    if(lastID == i) throw  new IndexOutOfBoundsException("Cannot fit row !");
                    lastID = i--;
                    tablePages.add(new TablePage(pageRows));
                    pageRows = new ArrayList<>();
                    currentPageHeight = 0;
                }else {
                    currentPageHeight += h+1;
                    pageRows.add(row);
                }

        }

        tablePages.add(new TablePage(pageRows));

        return new Table(tablePages,getTableObject().getHeader());
    }
}
