package org.davidCMs;

import java.math.BigInteger;


/**
 * The {@code ConversionUtils} class is holds methods to manage conversion between different {@code Base}
 * */
public class ConversionUtils {


    /**
     * Returns the result of the conversion of a value from one {@code Base} to another.
     *
     * @param from The base from witch the value should be converted from.
     * @param to The base to witch the value should be converted to.
     * @param value The value witch should be converted.
     *
     * @return The value with its base converted.
     * */
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
