package com.appboy.support;

import java.util.Random;

public final class IntentUtils {

    /* renamed from: a */
    private static final Random f2912a = new Random();

    public static int getRequestCode() {
        return f2912a.nextInt();
    }
}
