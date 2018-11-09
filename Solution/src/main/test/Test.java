import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String s = "G:\\丝袜美腿\\美腿新模第412期\\1326023X4E0-144019.jpg";
        System.out.println(s);
        Matcher matcher = Pattern.compile("(^.*)\\\\(.*?\\\\.*?$)").matcher(s);
        while (matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }
}
