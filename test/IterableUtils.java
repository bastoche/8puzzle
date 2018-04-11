import java.util.Iterator;

public class IterableUtils {
    public static <T> int size(Iterable<T> iterable) {
        int count = 0;
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }
}
