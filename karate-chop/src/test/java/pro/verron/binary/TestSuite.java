package pro.verron.binary;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pro.verron.utils.MyTimer;
import pro.verron.binary.linear.LinearSearch;
import pro.verron.binary.object.OOSearch;
import pro.verron.binary.procedural.ProceduralSearch;
import pro.verron.binary.recursive.RecursiveSearch;
import pro.verron.binary.sublist.SublistSearch;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSuite {

    public static final LinearSearch<Integer> REFERENCE_SEARCH = new LinearSearch<>();

    static Stream<BinarySearch<Integer>> searches() {
        return Stream.of(
                new ProceduralSearch<>(),
                new RecursiveSearch<>(),
                new SublistSearch<>(),
                new OOSearch<>()
        );
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(15)
    void search_function_is_fast(BinarySearch<Integer> searchFunction) {
        var random = new Random();
        long seed = random.nextLong();
        System.out.printf("seed: %d%n", seed);
        random.setSeed(seed);
        var sarray = random.ints().distinct().limit(100_000).sorted().boxed().toList();
        MyTimer linearTimer = new MyTimer();
        try (linearTimer) {
            for (int i = 0; i < 10_000; i++) {
                REFERENCE_SEARCH.getIndex(random.nextInt(), sarray);
            }
        }

        MyTimer binaryTimer = new MyTimer();
        try (binaryTimer) {
            for (int i = 0; i < 10_000; i++) {
                searchFunction.getIndex(random.nextInt(), sarray);
            }
        }
        long speedup = linearTimer.getDuration().dividedBy(binaryTimer.getDuration());
        assertThat(10L, lessThan(speedup));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_empty_array(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(3, emptyList()));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_singleton_array(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(3, List.of(1)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_in_singleton_array(BinarySearch<Integer> searchFunction) {
        assertEquals(0, searchFunction.getIndex(1, List.of(1)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_first_in_odd_length_array(BinarySearch<Integer> searchFunction) {
        assertEquals(0, searchFunction.getIndex(1, List.of(1, 3, 5)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_middle_in_odd_length_array(BinarySearch<Integer> searchFunction) {
        assertEquals(1, searchFunction.getIndex(3, List.of(1, 3, 5)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_last_in_odd_length_array(BinarySearch<Integer> searchFunction) {
        int result = searchFunction.getIndex(5, List.of(1, 3, 5));
        assertEquals(2, result);
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_odd_length_array_before_first_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(0, List.of(1, 3, 5)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_odd_length_array_after_before_middle_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(2, List.of(1, 3, 5)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_odd_length_array_after_middle_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(4, List.of(1, 3, 5)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_odd_length_array_after_last_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(6, List.of(1, 3, 5)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_first_in_even_length_array(BinarySearch<Integer> searchFunction) {
        assertEquals(0, searchFunction.getIndex(1, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_after_first_in_even_length_array(BinarySearch<Integer> searchFunction) {
        assertEquals(1, searchFunction.getIndex(3, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_before_last_in_even_length_array(BinarySearch<Integer> searchFunction) {
        assertEquals(2, searchFunction.getIndex(5, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void can_find_last_in_even_length_array(BinarySearch<Integer> searchFunction) {
        assertEquals(3, searchFunction.getIndex(7, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_even_length_array_before_first_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(0, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_even_length_array_after_first_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(2, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_even_length_array_at_middle(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(4, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_even_length_array_before_last_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(6, List.of(1, 3, 5, 7)));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void cant_find_in_even_length_array_after_last_element(BinarySearch<Integer> searchFunction) {
        assertEquals(-1, searchFunction.getIndex(8, List.of(1, 3, 5, 7)));
    }
}
