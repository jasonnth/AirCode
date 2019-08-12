package com.jumio.p311nv.benchmark;

import android.content.Context;
import com.jumio.commons.log.Log;
import com.jumio.p311nv.benchmark.BenchmarkAlgorithm.DeviceCategory;
import com.jumio.p311nv.benchmark.coremark.CoremarkC;
import com.jumio.p311nv.environment.NVEnvironment;
import com.jumio.persistence.DataAccess;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.jumio.nv.benchmark.Benchmark */
public class Benchmark {
    private static final long MAX_WAIT_TIMEOUT = 2000;
    public static final String SERVICE = "BenchmarkService";
    /* access modifiers changed from: private */
    public static final AtomicBoolean isMeasuring = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static ReentrantLock semaphor = new ReentrantLock();
    /* access modifiers changed from: private */
    public DeviceCategory mCategory;
    private Context mContext;

    public Benchmark(Context context) {
        this.mContext = context;
        NVEnvironment.loadBenchmarkLib();
    }

    private void executeBenchmark() {
        Log.m1924v(SERVICE, "currently measuring: " + isMeasuring.get());
        if (isMeasuring.compareAndSet(false, true) && this.mCategory == null) {
            new Thread(new Runnable() {
                public void run() {
                    Log.m1924v(Benchmark.SERVICE, "running benchmark, waiting for monitor...");
                    try {
                        Benchmark.semaphor.lock();
                        CoremarkC coremarkC = new CoremarkC();
                        Log.m1919i(Benchmark.SERVICE, "  monitor acquired starting benchmark " + coremarkC.getName());
                        long currentTimeMillis = System.currentTimeMillis();
                        coremarkC.execute();
                        Log.m1909d(Benchmark.SERVICE, "  benchmark finished");
                        Benchmark.this.mCategory = coremarkC.getDeviceCategory();
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        Benchmark.this.storeSharedPref(Benchmark.this.mCategory);
                        Log.m1919i(Benchmark.SERVICE, String.format("  Classified device as %s at %.2f %s (%s)", new Object[]{coremarkC.getDeviceCategory().toString(), coremarkC.getResult(), coremarkC.getUnitName(), coremarkC.getName()}));
                        Log.m1924v(Benchmark.SERVICE, "releasing monitor (took " + currentTimeMillis2 + " ms)");
                        Benchmark.isMeasuring.set(false);
                    } finally {
                        Benchmark.semaphor.unlock();
                    }
                }
            }, "BenchmarkThread").start();
        }
    }

    /* access modifiers changed from: private */
    public void storeSharedPref(DeviceCategory deviceCategory) {
        DataAccess.store(this.mContext, DeviceCategory.class, deviceCategory);
    }

    private DeviceCategory loadDeviceCategory() {
        DeviceCategory deviceCategory = (DeviceCategory) DataAccess.load(this.mContext, DeviceCategory.class);
        if (deviceCategory == null) {
            Log.m1919i(SERVICE, "No device category stored!");
        } else {
            Log.m1919i(SERVICE, "Loading previously stored value " + deviceCategory.toString());
        }
        return deviceCategory;
    }

    public void reset() {
        DataAccess.delete(this.mContext, DeviceCategory.class);
    }

    public DeviceCategory getDeviceCategory() {
        this.mCategory = loadDeviceCategory();
        if (this.mCategory == null) {
            if (!isMeasuring.get()) {
                executeBenchmark();
            }
            Log.m1924v(SERVICE, " waiting for category...");
            try {
                if (!semaphor.tryLock(MAX_WAIT_TIMEOUT, TimeUnit.MILLISECONDS)) {
                    Log.m1909d(SERVICE, hashCode() + " timeout expired - assuming FAST");
                    return DeviceCategory.FAST;
                }
                semaphor.unlock();
            } catch (InterruptedException e) {
                Log.printStackTrace(e);
            }
        }
        Log.m1924v(SERVICE, " dev category available!");
        return this.mCategory;
    }

    public void startMeasurement() {
        if (this.mCategory == null && loadDeviceCategory() == null) {
            executeBenchmark();
        }
    }
}
