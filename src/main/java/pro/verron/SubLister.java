package pro.verron;

import java.util.List;

public interface SubLister<T> {
    List<T> apply(List<T> list, int start, int end);
}
