package com.jumio.sdk.extraction;

import java.util.concurrent.atomic.AtomicInteger;

public class ExtractionUpdateState {

    /* renamed from: id */
    protected static AtomicInteger f3551id = new AtomicInteger(0);
    public static final int notifyFlash = f3551id.getAndIncrement();
    public static final int notifyFocus = f3551id.getAndIncrement();
    public static final int receiveClickListener = f3551id.getAndIncrement();
    public static final int saveExactImage = f3551id.getAndIncrement();
    public static final int saveImage = f3551id.getAndIncrement();
    public static final int shotTaken = f3551id.getAndIncrement();
}
