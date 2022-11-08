package pro.verron.datamunging;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Weather {
    public static void main(String[] args) throws IOException {
        String filepath = "pro/verron/datamunging/weather.dat";
        InputStream stream = ClassLoader.getSystemResourceAsStream(filepath);
        assert stream != null : "Couldn't find the data set file at position: %s".formatted(filepath);
        System.out.println(new String(new BufferedInputStream(stream).readAllBytes()));
    }
}
