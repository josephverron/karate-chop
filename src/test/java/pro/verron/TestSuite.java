package pro.verron;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.verron.Main.*;

public class TestSuite {


    static Stream<SearchFunction> searches() {
        return Stream.of(
                Main::proceduralBinarySearch,
                Main::recursiveBinarySearch
        );
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(15)
    void search_function_is_fast(SearchFunction searchFunction) {
        var random = new Random();
        long seed = random.nextLong();
        System.out.printf("seed: %d%n", seed);
        random.setSeed(seed);
        var sarray = random.ints().distinct().limit(100_000).sorted().toArray();
        MyTimer linearTimer = new MyTimer();
        try (linearTimer) {
            for (int i = 0; i < 10_000; i++) {
                linearSearch(random.nextInt(), sarray);
            }
        }

        MyTimer binaryTimer = new MyTimer();
        try (binaryTimer) {
            for (int i = 0; i < 10_000; i++) {
                searchFunction.search(random.nextInt(), sarray);
            }
        }
        long speedup = linearTimer.getDuration().dividedBy(binaryTimer.getDuration());
        assertThat(2L, lessThan(speedup));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void empty_array(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(3, new int[0]));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfoundable_value_in_singleton_array(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(3, new int[]{1}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_only_value_in_array(SearchFunction searchFunction) {
        assertEquals(0, searchFunction.search(1, new int[]{1}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_first_value_in_array(SearchFunction searchFunction) {
        assertEquals(0, searchFunction.search(1, new int[]{1, 3, 5}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_second_value_in_array(SearchFunction searchFunction) {
        assertEquals(1, searchFunction.search(3, new int[]{1, 3, 5}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_last_value_in_array(SearchFunction searchFunction) {
        int result = searchFunction.search(5, new int[]{1, 3, 5});
        assertEquals(2, result);
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfoundable_value_in_array_before(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(0, new int[]{1, 3, 5}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfoundable_value_in_array_after_start(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(2, new int[]{1, 3, 5}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfoundable_value_in_array_before_end(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(4, new int[]{1, 3, 5}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfoundable_value_in_array_after(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(6, new int[]{1, 3, 5}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_first_value_in_even_length_array(SearchFunction searchFunction) {
        assertEquals(0, searchFunction.search(1, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_second_value_in_even_length_array(SearchFunction searchFunction) {
        assertEquals(1, searchFunction.search(3, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_third_value_in_even_length_array(SearchFunction searchFunction) {
        assertEquals(2, searchFunction.search(5, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void find_last_value_in_even_length_array(SearchFunction searchFunction) {
        assertEquals(3, searchFunction.search(7, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfindable_value_in_even_length_array_before(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(0, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfindable_value_in_even_length_array_after_first(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(2, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfindable_value_in_even_length_array_middle(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(4, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfindable_value_in_even_length_before_last(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(6, new int[]{1, 3, 5, 7}));
    }

    @ParameterizedTest
    @MethodSource("searches")
    @Timeout(1)
    void unfindable_value_in_even_length_after(SearchFunction searchFunction) {
        assertEquals(VALUE_NOT_FOUND, searchFunction.search(8, new int[]{1, 3, 5, 7}));
    }
}
