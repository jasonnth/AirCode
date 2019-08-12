package com.jumio.p311nv.benchmark;

import com.jumio.commons.PersistWith;
import java.io.Serializable;

/* renamed from: com.jumio.nv.benchmark.BenchmarkAlgorithm */
public abstract class BenchmarkAlgorithm implements Serializable {
    private Double mResult;

    @PersistWith("DeviceCategory")
    /* renamed from: com.jumio.nv.benchmark.BenchmarkAlgorithm$DeviceCategory */
    public enum DeviceCategory {
        SLOW,
        MEDIUM,
        FAST
    }

    public abstract DeviceCategory getDeviceCategory();

    public abstract String getName();

    public abstract String getUnitName();

    public abstract double run();

    public double execute() {
        this.mResult = new Double(run());
        return this.mResult.doubleValue();
    }

    public double run(int i) {
        double d = 0.0d;
        for (int i2 = 0; i2 < i; i2++) {
            d += run();
        }
        this.mResult = new Double(d / ((double) i));
        return d / ((double) i);
    }

    public Double getResult() {
        return this.mResult;
    }
}
