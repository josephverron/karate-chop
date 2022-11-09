package pro.verron.binary.sublist;

import java.util.AbstractList;
import java.util.List;

class Sublist<T> extends AbstractList<T> {
    private final int rootLevelIndex;
    private final List<T> list;

    public Sublist(int rootLevelIndex, List<T> list) {
        this.rootLevelIndex = rootLevelIndex;
        this.list = list;
    }

    @Override
    public Sublist<T> subList(int start, int end) {
        return new Sublist<>(
                rootLevelIndex + start,
                list.subList(start, end));
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    public int rootLevelIndex() {
        return rootLevelIndex;
    }
}
