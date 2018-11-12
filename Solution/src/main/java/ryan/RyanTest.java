package ryan;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RyanTest {
    @Test
    public void main() {
        String s = "4564564";
        System.out.println(s);
        Matcher matcher = Pattern.compile("(^.*)\\\\(.*?\\\\.*?$)").matcher(s);
        while (matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }
}
