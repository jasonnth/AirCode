package com.airbnb.android.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.util.LongSparseArray;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class ParcelableUtils {
    private ParcelableUtils() {
    }

    public static void writeParcelableMap(Parcel parcel, Map<?, ? extends Parcelable> map) {
        parcel.writeInt(map.size());
        for (Entry<?, ? extends Parcelable> key : map.entrySet()) {
            parcel.writeValue(key.getKey());
            parcel.writeParcelable((Parcelable) key.getValue(), 0);
        }
    }

    public static <K, V> void readParcelableIntoMap(Parcel parcel, Map<K, V> map, Class<K> keyClass, Class<V> valueClass) {
        int size = parcel.readInt();
        for (int i = 0; i < size; i++) {
            map.put(parcel.readValue(keyClass.getClassLoader()), parcel.readParcelable(valueClass.getClassLoader()));
        }
    }

    public static void writeStringMapToParcel(Parcel dest, Map<String, String> map) {
        dest.writeInt(map.size());
        for (Entry<String, String> key : map.entrySet()) {
            dest.writeString((String) key.getKey());
            dest.writeString((String) key.getValue());
        }
    }

    public static void readStringMapFromParcel(Parcel source, Map<String, String> map) {
        int size = source.readInt();
        for (int i = 0; i < size; i++) {
            map.put(source.readString(), source.readString());
        }
    }

    public static <E> void writeLongSparseArrayToParcel(Parcel dest, LongSparseArray<E> array) {
        int size = array.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            dest.writeLong(array.keyAt(i));
            dest.writeValue(array.valueAt(i));
        }
    }

    public static <E> void readLongSparseArrayFromParcel(Parcel source, LongSparseArray<E> array, Class<E> valueClass) {
        int size = source.readInt();
        for (int i = 0; i < size; i++) {
            array.put(source.readLong(), source.readValue(valueClass.getClassLoader()));
        }
    }

    public static void writeParcelableLongSet(Parcel parcel, Set<Long> set) {
        parcel.writeInt(set.size());
        for (Long l : set) {
            parcel.writeLong(l.longValue());
        }
    }

    public static void readParcelableIntoLongSet(Parcel parcel, Set<Long> set) {
        int size = parcel.readInt();
        for (int i = 0; i < size; i++) {
            set.add(Long.valueOf(parcel.readLong()));
        }
    }

    public static void writeParcelableLongLinkedList(Parcel parcel, LinkedList<Long> linkedList) {
        parcel.writeInt(linkedList.size());
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            parcel.writeLong(((Long) it.next()).longValue());
        }
    }

    public static void readParcelableIntoLongLinkedList(Parcel parcel, LinkedList<Long> linkedList) {
        int size = parcel.readInt();
        for (int i = 0; i < size; i++) {
            linkedList.add(Long.valueOf(parcel.readLong()));
        }
    }

    public static <T extends Parcelable> T cloneParcelable(T object) {
        Parcel p = Parcel.obtain();
        p.writeValue(object);
        p.setDataPosition(0);
        T clone = (Parcelable) p.readValue(object.getClass().getClassLoader());
        p.recycle();
        return clone;
    }
}
