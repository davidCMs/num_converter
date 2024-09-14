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

    public static final Base BINARY = new Base("Binary", 2);
    public static final Base TERNARY = new Base("Ternary", 3);
    public static final Base QUATERNARY = new Base("Quaternary", 4);
    public static final Base QUINARY = new Base("Quinary", 5);
    public static final Base SENARY = new Base("Senary", 6);
    public static final Base SEPTENARY = new Base("Septenary", 7);
    public static final Base OCTAL = new Base("Octal", 8);
    public static final Base NONARY = new Base("Nonary", 9);
    public static final Base DECIMAL = new Base("Decimal", 10);
    public static final Base UNDECIMAL = new Base("Undecimal", 11);
    public static final Base DUODECIMAL = new Base("Duodecimal", 12);
    public static final Base TRIDECIMAL = new Base("Tridecimal", 13);
    public static final Base TETRADECIMAL = new Base("Tetradecimal", 14);
    public static final Base PENTADECIMAL = new Base("Pentadecimal", 15);
    public static final Base HEXADECIMAL = new Base("Hexadecimal", 16);
    public static final Base HEPTADECIMAL = new Base("Heptadecimal", 17);
    public static final Base OCTODECIMAL = new Base("Octodecimal", 18);
    public static final Base ENNEADECIMAL = new Base("Enneadecimal", 19);
    public static final Base VIGESIMAL = new Base("Vigesimal", 20);
    public static final Base UNVIGESIMAL = new Base("Unvigesimal", 21);
    public static final Base DUOVIGESIMAL = new Base("Duovigesimal", 22);
    public static final Base TRIVIGESIMAL = new Base("Trivigesimal", 23);
    public static final Base TETRAVIGESIMAL = new Base("Tetravigesimal", 24);
    public static final Base PENTAVIGESIMAL = new Base("Pentavigesimal", 25);
    public static final Base HEXAVIGESIMAL = new Base("Hexavigesimal", 26);
    public static final Base HEPTAVIGESIMAL = new Base("Heptavigesimal", 27);
    public static final Base OCTOVIGESIMAL = new Base("Octovigesimal", 28);
    public static final Base ENNEAVIGESIMAL = new Base("Enneavigesimal", 29);
    public static final Base TRIGESIMAL = new Base("Trigesimal", 30);
    public static final Base UNTRIGESIMAL = new Base("Untrigesimal", 31);
    public static final Base DUOTRIGESIMAL = new Base("Duotrigesimal", 32);

    private static final List<Base> bases = new ArrayList<>();
    /**
     * The static initializer uses java reflect to populate the bases map*/
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
