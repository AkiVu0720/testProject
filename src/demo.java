import java.time.Year;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.stream.Collectors;

public class demo {
    public static void main(String[] args) {
        String string1 = null;
        String string2 = "jdnvdnvdv";
        if(isStringEmpty(string1)){
            System.out.println("string1 is Empty!");
        }
        if(isStringEmpty(string2)){
            System.out.println("string2 is Empty!");
        }else{
            System.out.println("Khong rong");
        }

    }
    static boolean isStringEmpty(String string){
        return string==null || string.isEmpty();
    }
}
