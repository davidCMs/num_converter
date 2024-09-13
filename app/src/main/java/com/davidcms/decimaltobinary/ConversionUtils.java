package com.davidcms.decimaltobinary;

import java.math.BigInteger;
import java.util.HashMap;

public class ConversionUtils {

    public static String convert(Base from, Base to, String value) {
        try {
            int baseIn = from.getBase();
            int baseOut = to.getBase();
            BigInteger decimal = new BigInteger(value, baseIn);
            return decimal.toString(baseOut);
        } catch (Exception e) {
            return "ERROR: Invalid input";
        }
    }

}
