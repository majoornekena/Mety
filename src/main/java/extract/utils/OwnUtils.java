package extract.utils;



import java.time.LocalTime;

import extract.zframework.controlExcept.NegativeValueExcept;

public class OwnUtils {
    public static LocalTime toTime(int min) {
        return LocalTime.of((int) min / 60, min % 60);
    }

    public static void verifNeg(double value, String... message) throws NegativeValueExcept {
        if (value < 0.0) {
            String str = "";
            if (message.length > 0) {
                for (int i = 0; i < message.length; i++) {
                    str += " " + message[i];
                }
            }
            throw new NegativeValueExcept(str);
        }
    }
}
