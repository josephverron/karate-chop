package pro.verron;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static pro.verron.Main.binarySearch;
import static pro.verron.Main.linearSearch;

public class TestSuite {

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 15, unit = TimeUnit.SECONDS)
    void search_function_is_fast(){
        var random = new Random();
        var sarray = random.ints().distinct().limit(100_000).sorted().toArray();
        MyTimer timer = new MyTimer();
        try(timer){
            for (int i = 0; i < 10_000; i++) {
                linearSearch(random.nextInt(), sarray);
            }
        }

        MyTimer timer2 = new MyTimer();
        try(timer2){
            for (int i = 0; i < 10_000; i++) {
                binarySearch(random.nextInt(), sarray);
            }
        }
        long speedup = timer.getDuration().dividedBy(timer2.getDuration());
        assertThat(2L, lessThan(speedup));
    }
    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void empty_array(){
        assertEquals(-1, binarySearch(3, new int[0]));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfoundable_value_in_singleton_array(){
        assertEquals(-1, binarySearch(3, new int[]{1}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_only_value_in_array(){
        assertEquals(0, binarySearch(1, new int[]{1}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_first_value_in_array(){
        assertEquals(0,  binarySearch(1, new int[]{1, 3, 5}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_second_value_in_array(){
        assertEquals(1,  binarySearch(3, new int[]{1, 3, 5}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_last_value_in_array(){
        assertEquals(2,  binarySearch(5, new int[]{1, 3, 5}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfoundable_value_in_array_before(){
        assertEquals(-1, binarySearch(0, new int[]{1, 3, 5}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfoundable_value_in_array_after_start(){
        assertEquals(-1, binarySearch(2, new int[]{1, 3, 5}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfoundable_value_in_array_before_end(){
        assertEquals(-1, binarySearch(4, new int[]{1, 3, 5}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfoundable_value_in_array_after(){
        assertEquals(-1, binarySearch(6, new int[]{1, 3, 5}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_first_value_in_even_length_array(){
        assertEquals(0,  binarySearch(1, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_second_value_in_even_length_array(){
        assertEquals(1,  binarySearch(3, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_third_value_in_even_length_array(){
        assertEquals(2,  binarySearch(5, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void find_last_value_in_even_length_array(){
        assertEquals(3,  binarySearch(7, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfindable_value_in_even_length_array_before(){
        assertEquals(-1, binarySearch(0, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfindable_value_in_even_length_array_after_first(){
        assertEquals(-1, binarySearch(2, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfindable_value_in_even_length_array_middle(){
        assertEquals(-1, binarySearch(4, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfindable_value_in_even_length_before_last(){
        assertEquals(-1, binarySearch(6, new int[]{1, 3, 5, 7}));
    }

    @Test
    @Timeout(threadMode = SEPARATE_THREAD, value = 1, unit = TimeUnit.SECONDS)
    void unfindable_value_in_even_length_after(){
        assertEquals(-1, binarySearch(8, new int[]{1, 3, 5, 7}));
    }
}
