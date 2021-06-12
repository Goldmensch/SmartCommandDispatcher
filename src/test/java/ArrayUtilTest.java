import de.goldmensch.commanddispatcher.util.ArrayUtils;

import java.util.Arrays;

public class ArrayUtilTest {

    public void test() {
        String[] args = new String[]{"test", "test1", "test2"};
        String[] command = new String[]{"test", "test1"};
        String[] invalid = new String[]{"vogel"};

        System.out.println(ArrayUtils.startWith(args, command));
        System.out.println(ArrayUtils.startWith(args, invalid));

        String[] upperCase = new String[]{"Vögel", "KÖNNEN", "FLIGEN"};

        System.out.println(Arrays.stream(ArrayUtils.toLowerCase(upperCase)).toList());
    }

}
