package com.google.common.base;

public final class Preconditions {
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void checkArgument(boolean b, String errorMessageTemplate, int p1) {
        if (!b) {
            throw new IllegalArgumentException(format(errorMessageTemplate, Integer.valueOf(p1)));
        }
    }

    public static void checkArgument(boolean b, String errorMessageTemplate, long p1) {
        if (!b) {
            throw new IllegalArgumentException(format(errorMessageTemplate, Long.valueOf(p1)));
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void checkState(boolean b, String errorMessageTemplate, Object p1) {
        if (!b) {
            throw new IllegalStateException(format(errorMessageTemplate, p1));
        }
    }

    public static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    public static int checkElementIndex(int index, int size) {
        return checkElementIndex(index, size, "index");
    }

    public static int checkElementIndex(int index, int size, String desc) {
        if (index >= 0 && index < size) {
            return index;
        }
        throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
    }

    private static String badElementIndex(int index, int size, String desc) {
        if (index < 0) {
            return format("%s (%s) must not be negative", desc, Integer.valueOf(index));
        } else if (size < 0) {
            throw new IllegalArgumentException("negative size: " + size);
        } else {
            return format("%s (%s) must be less than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
    }

    public static int checkPositionIndex(int index, int size) {
        return checkPositionIndex(index, size, "index");
    }

    public static int checkPositionIndex(int index, int size, String desc) {
        if (index >= 0 && index <= size) {
            return index;
        }
        throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
    }

    private static String badPositionIndex(int index, int size, String desc) {
        if (index < 0) {
            return format("%s (%s) must not be negative", desc, Integer.valueOf(index));
        } else if (size < 0) {
            throw new IllegalArgumentException("negative size: " + size);
        } else {
            return format("%s (%s) must not be greater than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
    }

    public static void checkPositionIndexes(int start, int end, int size) {
        if (start < 0 || end < start || end > size) {
            throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
        }
    }

    private static String badPositionIndexes(int start, int end, int size) {
        if (start < 0 || start > size) {
            return badPositionIndex(start, size, "start index");
        }
        if (end < 0 || end > size) {
            return badPositionIndex(end, size, "end index");
        }
        return format("end index (%s) must not be less than start index (%s)", Integer.valueOf(end), Integer.valueOf(start));
    }

    static String format(String template, Object... args) {
        String template2 = String.valueOf(template);
        StringBuilder builder = new StringBuilder(template2.length() + (args.length * 16));
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template2.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template2, templateStart, placeholderStart);
            int i2 = i + 1;
            builder.append(args[i]);
            templateStart = placeholderStart + 2;
            i = i2;
        }
        builder.append(template2, templateStart, template2.length());
        if (i < args.length) {
            builder.append(" [");
            int i3 = i + 1;
            builder.append(args[i]);
            while (true) {
                int i4 = i3;
                if (i4 >= args.length) {
                    break;
                }
                builder.append(", ");
                i3 = i4 + 1;
                builder.append(args[i4]);
            }
            builder.append(']');
        }
        return builder.toString();
    }
}
