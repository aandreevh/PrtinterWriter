import printz.TableObject;
import printz.table.TableBuilder;
import printz.table.TableCell;
import printz.table.TableRow;


public class Main {


    public static void main(String... args) throws Exception{

        TableObject obj = TableObject.loadObject("tester.xml");
        System.out.println(obj);


        TableBuilder builder = new TableBuilder(obj);

        TableRow row = builder.createTableRow(new String[]{"12345 6","b","c"});
        row.getCells().stream().forEach(System.out::println);
    }
}
