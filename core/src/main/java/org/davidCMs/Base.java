package org.davidCMs;

import java.lang.reflect.Field;
import java.util.*;

/**
 * The {@code Base} class is a data class to make handling conversion and naming of different bases easier
 *
 * @author davidCMs
 * @see ConversionUtils
 * @since 1.0
 * */
public class Base {

    public static final Base BINARY = new Base("Bin", 2);
    public static final Base TERNARY = new Base("Ter", 3);
    public static final Base QUATERNARY = new Base("Qua", 4);
    public static final Base QUINARY = new Base("Qui", 5);
    public static final Base SENARY = new Base("Sen", 6);
    public static final Base SEPTENARY = new Base("Sep", 7);
    public static final Base OCTAL = new Base("Oct", 8);
    public static final Base NONARY = new Base("Non", 9);
    public static final Base DECIMAL = new Base("Dec", 10);
    public static final Base UNDECIMAL = new Base("Und", 11);
    public static final Base DUODECIMAL = new Base("Duo", 12);
    public static final Base TRIDECIMAL = new Base("Tri", 13);
    public static final Base TETRADECIMAL = new Base("Tet", 14);
    public static final Base PENTADECIMAL = new Base("Pen", 15);
    public static final Base HEXADECIMAL = new Base("Hex", 16);
    public static final Base HEPTADECIMAL = new Base("Hep", 17);
    public static final Base OCTODECIMAL = new Base("Octo", 18);
    public static final Base ENNEADECIMAL = new Base("Enn", 19);
    public static final Base VIGESIMAL = new Base("Vig", 20);
    public static final Base UNVIGESIMAL = new Base("UVig", 21);
    public static final Base DUOVIGESIMAL = new Base("DVig", 22);
    public static final Base TRIVIGESIMAL = new Base("TVig", 23);
    public static final Base TETRAVIGESIMAL = new Base("TeVig", 24);
    public static final Base PENTAVIGESIMAL = new Base("PVig", 25);
    public static final Base HEXAVIGESIMAL = new Base("HVig", 26);
    public static final Base HEPTAVIGESIMAL = new Base("HeVig", 27);
    public static final Base OCTOVIGESIMAL = new Base("OVig", 28);
    public static final Base ENNEAVIGESIMAL = new Base("EVig", 29);
    public static final Base TRIGESIMAL = new Base("Trig", 30);
    public static final Base UNTRIGESIMAL = new Base("UTri", 31);
    public static final Base DUOTRIGESIMAL = new Base("DTri", 32);

    private static final List<Base> bases = new ArrayList<>();
    /**
     * The static initializer uses java reflect to populate the bases list*/
    static {
        Field[] fields = Base.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(Base.class)) {
                try {
                    Base base = (Base) field.get(null);
                    bases.add(base);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        bases.sort(Comparator.comparingInt(Base::getBase));
    }

    public static List<Base> getBases() {
        return bases;
    }

    private final String name;
    private final int base;

    private Base(String name, int base) {
        this.name = name;
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public int getBase() {
        return base;
    }

    @Override
    public String toString() {
        return name + " (base: " + base + ")";
    }
}
