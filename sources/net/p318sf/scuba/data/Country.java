package net.p318sf.scuba.data;

import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: net.sf.scuba.data.Country */
public abstract class Country {
    private static final Class<?>[] SUB_CLASSES = {UnicodeCountry.class, ISOCountry.class, TestCountry.class};

    private static Country fromAlpha2(String str) {
        Country[] values;
        for (Country country : values()) {
            if (country.toAlpha2Code().equals(str)) {
                return country;
            }
        }
        throw new IllegalArgumentException("Unknown country code " + str);
    }

    private static Country fromAlpha3(String str) {
        Country[] values;
        for (Country country : values()) {
            if (country.toAlpha3Code().equals(str)) {
                return country;
            }
        }
        throw new IllegalArgumentException("Unknown country code " + str);
    }

    public static Country getInstance(int i) {
        Country[] values;
        for (Country country : values()) {
            if (country.valueOf() == i) {
                return country;
            }
        }
        throw new IllegalArgumentException("Illegal country code" + Integer.toHexString(i));
    }

    public static Country getInstance(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Illegal country code");
        }
        String trim = str.trim();
        switch (trim.length()) {
            case 2:
                return fromAlpha2(trim);
            case 3:
                return fromAlpha3(trim);
            default:
                throw new IllegalArgumentException("Illegal country code " + trim);
        }
    }

    public static Country[] values() {
        Class<?>[] clsArr;
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls : SUB_CLASSES) {
            if (Country.class.isAssignableFrom(cls)) {
                try {
                    arrayList.addAll(Arrays.asList((Country[]) cls.getMethod("values", new Class[0]).invoke(null, new Object[0])));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Country[] countryArr = new Country[arrayList.size()];
        arrayList.toArray(countryArr);
        return countryArr;
    }

    public abstract String getName();

    public abstract String getNationality();

    public abstract String toAlpha2Code();

    public abstract String toAlpha3Code();

    public abstract int valueOf();
}
