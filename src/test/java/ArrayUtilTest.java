import de.goldmensch.commanddispatcher.util.ArrayUtil;

public class ArrayUtilTest {

    public void test() {
        String[] args = new String[]{"test", "test1", "test2"};
        String[] command = new String[]{"test", "test1"};
        String[] invalid = new String[]{"vogel"};

        System.out.println(ArrayUtil.startWith(args, command));
        System.out.println(ArrayUtil.startWith(args, invalid));

        String[] upperCase = new String[]{"Vögel", "KÖNNEN", "FLIGEN"};

        System.out.println(java.util.Arrays.stream(ArrayUtil.toLowerCase(upperCase)).toList());
    }

}
