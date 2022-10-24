package pro.verron;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.verron.Main.chop;

public class TestSuite {

    @Test
    void empty_array(){
        assertEquals(-1, chop(3, new int[0]));
    }

    /*assert_equal(-1, chop(3, []))
    /*assert_equal(-1, chop(3, [1]))
    /*assert_equal(0,  chop(1, [1]))
            #
    /*assert_equal(0,  chop(1, [1, 3, 5]))
    /*assert_equal(1,  chop(3, [1, 3, 5]))
    /*assert_equal(2,  chop(5, [1, 3, 5]))
    /*assert_equal(-1, chop(0, [1, 3, 5]))
    /*assert_equal(-1, chop(2, [1, 3, 5]))
    /*assert_equal(-1, chop(4, [1, 3, 5]))
    /*assert_equal(-1, chop(6, [1, 3, 5]))
            #
    /*assert_equal(0,  chop(1, [1, 3, 5, 7]))
    /*assert_equal(1,  chop(3, [1, 3, 5, 7]))
    /*assert_equal(2,  chop(5, [1, 3, 5, 7]))
    /*assert_equal(3,  chop(7, [1, 3, 5, 7]))
    /*assert_equal(-1, chop(0, [1, 3, 5, 7]))
    /*assert_equal(-1, chop(2, [1, 3, 5, 7]))
    /*assert_equal(-1, chop(4, [1, 3, 5, 7]))
    /*assert_equal(-1, chop(6, [1, 3, 5, 7]))
    /*assert_equal(-1, chop(8, [1, 3, 5, 7]))
    */
}
