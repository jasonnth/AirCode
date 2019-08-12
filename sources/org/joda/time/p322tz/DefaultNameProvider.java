package org.joda.time.p322tz;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.DateTimeUtils;

/* renamed from: org.joda.time.tz.DefaultNameProvider */
public class DefaultNameProvider implements NameProvider {
    private HashMap<Locale, Map<String, Map<String, Object>>> iByLocaleCache = createCache();
    private HashMap<Locale, Map<String, Map<Boolean, Object>>> iByLocaleCache2 = createCache();

    public String getShortName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        if (nameSet == null) {
            return null;
        }
        return nameSet[0];
    }

    public String getName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        if (nameSet == null) {
            return null;
        }
        return nameSet[1];
    }

    private synchronized String[] getNameSet(Locale locale, String str, String str2) {
        String[] strArr;
        Map map;
        String[] strArr2;
        String[] strArr3 = null;
        synchronized (this) {
            if (locale == null || str == null || str2 == null) {
                strArr = null;
            } else {
                Map map2 = (Map) this.iByLocaleCache.get(locale);
                if (map2 == null) {
                    HashMap<Locale, Map<String, Map<String, Object>>> hashMap = this.iByLocaleCache;
                    HashMap createCache = createCache();
                    hashMap.put(locale, createCache);
                    map = createCache;
                } else {
                    map = map2;
                }
                Map map3 = (Map) map.get(str);
                if (map3 == null) {
                    map3 = createCache();
                    map.put(str, map3);
                    String[][] zoneStrings = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
                    int length = zoneStrings.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            strArr2 = null;
                            break;
                        }
                        String[] strArr4 = zoneStrings[i];
                        if (strArr4 != null && strArr4.length >= 5 && str.equals(strArr4[0])) {
                            strArr2 = strArr4;
                            break;
                        }
                        i++;
                    }
                    String[][] zoneStrings2 = DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
                    int length2 = zoneStrings2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        String[] strArr5 = zoneStrings2[i2];
                        if (strArr5 != null && strArr5.length >= 5 && str.equals(strArr5[0])) {
                            strArr3 = strArr5;
                            break;
                        }
                        i2++;
                    }
                    if (!(strArr2 == null || strArr3 == null)) {
                        map3.put(strArr2[2], new String[]{strArr3[2], strArr3[1]});
                        if (strArr2[2].equals(strArr2[4])) {
                            map3.put(strArr2[4] + "-Summer", new String[]{strArr3[4], strArr3[3]});
                        } else {
                            map3.put(strArr2[4], new String[]{strArr3[4], strArr3[3]});
                        }
                    }
                }
                strArr = (String[]) map3.get(str2);
            }
        }
        return strArr;
    }

    public String getShortName(Locale locale, String str, String str2, boolean z) {
        String[] nameSet = getNameSet(locale, str, str2, z);
        if (nameSet == null) {
            return null;
        }
        return nameSet[0];
    }

    public String getName(Locale locale, String str, String str2, boolean z) {
        String[] nameSet = getNameSet(locale, str, str2, z);
        if (nameSet == null) {
            return null;
        }
        return nameSet[1];
    }

    private synchronized String[] getNameSet(Locale locale, String str, String str2, boolean z) {
        String[] strArr;
        Map map;
        String[] strArr2;
        String[] strArr3 = null;
        synchronized (this) {
            if (locale == null || str == null || str2 == null) {
                strArr = null;
            } else {
                if (str.startsWith("Etc/")) {
                    str = str.substring(4);
                }
                Map map2 = (Map) this.iByLocaleCache2.get(locale);
                if (map2 == null) {
                    HashMap<Locale, Map<String, Map<Boolean, Object>>> hashMap = this.iByLocaleCache2;
                    HashMap createCache = createCache();
                    hashMap.put(locale, createCache);
                    map = createCache;
                } else {
                    map = map2;
                }
                Map map3 = (Map) map.get(str);
                if (map3 == null) {
                    map3 = createCache();
                    map.put(str, map3);
                    String[][] zoneStrings = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
                    int length = zoneStrings.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            strArr2 = null;
                            break;
                        }
                        String[] strArr4 = zoneStrings[i];
                        if (strArr4 != null && strArr4.length >= 5 && str.equals(strArr4[0])) {
                            strArr2 = strArr4;
                            break;
                        }
                        i++;
                    }
                    String[][] zoneStrings2 = DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
                    int length2 = zoneStrings2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        String[] strArr5 = zoneStrings2[i2];
                        if (strArr5 != null && strArr5.length >= 5 && str.equals(strArr5[0])) {
                            strArr3 = strArr5;
                            break;
                        }
                        i2++;
                    }
                    if (!(strArr2 == null || strArr3 == null)) {
                        map3.put(Boolean.TRUE, new String[]{strArr3[2], strArr3[1]});
                        map3.put(Boolean.FALSE, new String[]{strArr3[4], strArr3[3]});
                    }
                }
                strArr = (String[]) map3.get(Boolean.valueOf(z));
            }
        }
        return strArr;
    }

    private HashMap createCache() {
        return new HashMap(7);
    }
}
