package pro.verron.datamunging;

public interface Parser<T> {
    T parse(String t);
}
