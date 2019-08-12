package com.airbnb.android.utils;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListUtils {

    public interface Condition<T> {
        boolean check(T t);
    }

    public interface PositionalTransformer<T, R> {
        R transform(int i, T t);
    }

    public static <T> boolean trimList(List<T> list, int maxSize) {
        if (list.size() <= maxSize) {
            return false;
        }
        list.subList(maxSize, list.size()).clear();
        return true;
    }

    public static boolean isEmpty(List... lists) {
        for (List<?> list : lists) {
            if (isEmpty((Collection<?>) list)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areSameSizes(List... lists) {
        Integer size = null;
        int length = lists.length;
        for (int i = 0; i < length; i++) {
            List<?> list = lists[i];
            int listSize = list == null ? 0 : list.size();
            if (size == null) {
                size = Integer.valueOf(listSize);
            } else if (size.intValue() != listSize) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static <T> T getLast(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> void removeAllWhere(List<T> list, Condition<T> condition) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (condition.check(iterator.next())) {
                iterator.remove();
            }
        }
    }

    public static <T> T removeFirstWhere(List<T> list, Condition<T> condition) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (condition.check(t)) {
                iterator.remove();
                return t;
            }
        }
        return null;
    }

    public static <T> int firstIndexOf(List<T> list, Condition<T> condition) {
        for (int i = 0; i < list.size(); i++) {
            if (condition.check(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static <T> List<T> ensureNotNull(List<T> list) {
        return list == null ? Lists.newArrayList() : list;
    }

    public static <T> ArrayList<T> newArrayList(List<T> list) {
        return list == null ? Lists.newArrayList() : new ArrayList<>(list);
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T, R> List<R> transformWithPosition(List<T> list, PositionalTransformer<T, R> transformer) {
        List<R> results = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            results.add(transformer.transform(i, list.get(i)));
        }
        return results;
    }

    public static Integer[] range(int start, int end) {
        Integer[] range = new Integer[(Math.abs(end - start) + 1)];
        int delta = start < end ? 1 : -1;
        for (int i = 0; i < range.length; i++) {
            range[i] = Integer.valueOf((i * delta) + start);
        }
        return range;
    }
}
