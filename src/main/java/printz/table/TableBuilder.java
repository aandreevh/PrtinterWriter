package printz.table;

import printz.Entry;
import printz.Field;
import printz.TableObject;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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


    public TableCell createTableColumn(String string,int field){
        Field f = getTableObject().getHeader().getField(field);

        final String lineBreaker=String.valueOf(getTableObject().getHeader().getPage().getLineBreaker());
        String[] strings = string.trim().split(lineBreaker);
        List<String> readyStrings = new LinkedList<>();
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
    public TableRow createTableRow(String[] strings) {
        List<TableCell> columns = new LinkedList<>();
        List<TableCell> columns2 = new LinkedList<>();

        //Add regularly
        for(int i=0;i<strings.length;i++)
            columns.add(createTableColumn(strings[i],i));

        //Modify height to align
        int max = columns.stream().map(a-> a.getContent().size()).max((a,b)->a-b).orElse(0);

        for(int i=0;i<columns.size();i++) {
            List<String> str = new LinkedList<>(columns.get(i).getContent());
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

    Table build() throws Exception{

        List<TablePage> tablePages = new LinkedList<>();
        List<TableRow>  pageRows = new LinkedList<>();

        final int maxPageHeight = tobj.getHeader().getPage().getHeight();
        int currentPageHeight = 0;

        for(Entry data : tobj.getData()){
            if(currentPageHeight == 0){
            }
        }

        return null;
    }
}
