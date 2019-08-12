package com.squareup.moshi;

final class JsonScope {
    static String getPath(int stackSize, int[] stack, String[] pathNames, int[] pathIndices) {
        StringBuilder result = new StringBuilder().append('$');
        for (int i = 0; i < stackSize; i++) {
            switch (stack[i]) {
                case 1:
                case 2:
                    result.append('[').append(pathIndices[i]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    result.append('.');
                    if (pathNames[i] == null) {
                        break;
                    } else {
                        result.append(pathNames[i]);
                        break;
                    }
            }
        }
        return result.toString();
    }
}
