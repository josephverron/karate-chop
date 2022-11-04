package pro.verron;

public class Range {
    private final int start;
    private final int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    static Range ofSize(int size) {
        return new Range(0, size - 1);
    }

    public int middle() {
        return (start + end) / 2;
    }

    public boolean empty() {
        return start > end;
    }

    public Range endingAt(int newEnd) {
        return new Range(this.start, newEnd);
    }

    public Range startingAt(int newStart) {
        return new Range(newStart, this.end);
    }
}