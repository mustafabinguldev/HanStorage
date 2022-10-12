import dev.bingulhan.hanstorage.HanData;
import dev.bingulhan.hanstorage.HanStorage;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.runtime.Context;
import jdk.nashorn.internal.runtime.JSONFunctions;

public class Test2 {

    public static void main(String[] args) {

        HanStorage students = new HanStorage("C:\\Users\\BingulHan\\Desktop\\HanStorage\\src\\main\\resources", "students");

        System.out.println(students.updateValue("2", "BingulHan"));
    }
}
