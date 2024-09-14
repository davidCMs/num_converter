package org.davidCMs;

import java.math.BigInteger;

public class ConversionUtils {

    public static String convert(Base from, Base to, String value) {
        try {
            int baseIn = from.getBase();
            int baseOut = to.getBase();
            BigInteger decimal = new BigInteger(value, baseIn);
            return decimal.toString(baseOut).toUpperCase();
        } catch (Exception e) {
            return "ERROR: Invalid input";
        }
    }

}
