package org.joda.time.convert;

class ConverterSet {
    private final Converter[] iConverters;
    private Entry[] iSelectEntries = new Entry[16];

    static class Entry {
        final Converter iConverter;
        final Class<?> iType;

        Entry(Class<?> cls, Converter converter) {
            this.iType = cls;
            this.iConverter = converter;
        }
    }

    ConverterSet(Converter[] converterArr) {
        this.iConverters = converterArr;
    }

    /* access modifiers changed from: 0000 */
    public Converter select(Class<?> cls) throws IllegalStateException {
        Entry[] entryArr = this.iSelectEntries;
        int length = entryArr.length;
        int hashCode = cls == null ? 0 : cls.hashCode() & (length - 1);
        while (true) {
            Entry entry = entryArr[hashCode];
            if (entry == null) {
                Converter selectSlow = selectSlow(this, cls);
                Entry[] entryArr2 = (Entry[]) entryArr.clone();
                entryArr2[hashCode] = new Entry(cls, selectSlow);
                for (int i = 0; i < length; i++) {
                    if (entryArr2[i] == null) {
                        this.iSelectEntries = entryArr2;
                        return selectSlow;
                    }
                }
                int i2 = length << 1;
                Entry[] entryArr3 = new Entry[i2];
                for (int i3 = 0; i3 < length; i3++) {
                    Entry entry2 = entryArr2[i3];
                    Class<?> cls2 = entry2.iType;
                    int hashCode2 = cls2 == null ? 0 : cls2.hashCode() & (i2 - 1);
                    while (entryArr3[hashCode2] != null) {
                        int i4 = hashCode2 + 1;
                        if (i4 >= i2) {
                            i4 = 0;
                        }
                    }
                    entryArr3[hashCode2] = entry2;
                }
                this.iSelectEntries = entryArr3;
                return selectSlow;
            } else if (entry.iType == cls) {
                return entry.iConverter;
            } else {
                int i5 = hashCode + 1;
                if (i5 >= length) {
                    hashCode = 0;
                } else {
                    hashCode = i5;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return this.iConverters.length;
    }

    /* access modifiers changed from: 0000 */
    public ConverterSet remove(int i, Converter[] converterArr) {
        int i2;
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        if (i >= length) {
            throw new IndexOutOfBoundsException();
        }
        if (converterArr != null) {
            converterArr[0] = converterArr2[i];
        }
        Converter[] converterArr3 = new Converter[(length - 1)];
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            if (i3 != i) {
                i2 = i4 + 1;
                converterArr3[i4] = converterArr2[i3];
            } else {
                i2 = i4;
            }
            i3++;
            i4 = i2;
        }
        return new ConverterSet(converterArr3);
    }

    private static Converter selectSlow(ConverterSet converterSet, Class<?> cls) {
        Converter[] converterArr = converterSet.iConverters;
        int length = converterArr.length;
        int i = length;
        ConverterSet converterSet2 = converterSet;
        while (true) {
            int i2 = i - 1;
            if (i2 >= 0) {
                Converter converter = converterArr[i2];
                Class<?> supportedType = converter.getSupportedType();
                if (supportedType == cls) {
                    return converter;
                }
                if (supportedType == null || (cls != null && !supportedType.isAssignableFrom(cls))) {
                    converterSet2 = converterSet2.remove(i2, null);
                    converterArr = converterSet2.iConverters;
                    length = converterArr.length;
                }
                i = i2;
            } else if (cls == null || length == 0) {
                return null;
            } else {
                if (length == 1) {
                    return converterArr[0];
                }
                Converter[] converterArr2 = converterArr;
                ConverterSet converterSet3 = converterSet2;
                int i3 = length;
                while (true) {
                    int i4 = length - 1;
                    if (i4 < 0) {
                        break;
                    }
                    Class supportedType2 = converterArr2[i4].getSupportedType();
                    ConverterSet converterSet4 = converterSet3;
                    int i5 = i4;
                    int i6 = i3;
                    while (true) {
                        i6--;
                        if (i6 < 0) {
                            break;
                        } else if (i6 != i5 && converterArr2[i6].getSupportedType().isAssignableFrom(supportedType2)) {
                            converterSet4 = converterSet4.remove(i6, null);
                            converterArr2 = converterSet4.iConverters;
                            i3 = converterArr2.length;
                            i5 = i3 - 1;
                        }
                    }
                    length = i5;
                    converterSet3 = converterSet4;
                }
                if (i3 == 1) {
                    return converterArr2[0];
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to find best converter for type \"");
                sb.append(cls.getName());
                sb.append("\" from remaining set: ");
                for (int i7 = 0; i7 < i3; i7++) {
                    Converter converter2 = converterArr2[i7];
                    Class supportedType3 = converter2.getSupportedType();
                    sb.append(converter2.getClass().getName());
                    sb.append('[');
                    sb.append(supportedType3 == null ? null : supportedType3.getName());
                    sb.append("], ");
                }
                throw new IllegalStateException(sb.toString());
            }
        }
    }
}
