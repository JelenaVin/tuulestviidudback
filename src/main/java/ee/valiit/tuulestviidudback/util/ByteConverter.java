package ee.valiit.tuulestviidudback.util;

import java.nio.charset.StandardCharsets;

public class ByteConverter {
    public static String bytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] stringToBytes(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

}
