package com.davidcms.decimaltobinary;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    private static final List<Base> bases = new ArrayList<>();

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

        Log.d("Base", "bases: ");
        bases.forEach((base1) -> Log.d("Base","   " + base1.name + " (" + base1.base + ")"));
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
