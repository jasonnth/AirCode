package org.jmrtd.lds;

public interface MRZFieldTransformer {
    String transliterate(String str);

    String[] truncateNames(String str, String str2, int i);
}
