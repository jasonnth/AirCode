package okhttp3.internal.http2;

import com.facebook.soloader.MinElf;
import java.util.Arrays;

public final class Settings {
    private int set;
    private final int[] values = new int[10];

    /* access modifiers changed from: 0000 */
    public void clear() {
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    /* access modifiers changed from: 0000 */
    public Settings set(int id, int value) {
        if (id < this.values.length) {
            this.set |= 1 << id;
            this.values[id] = value;
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public boolean isSet(int id) {
        if ((this.set & (1 << id)) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public int get(int id) {
        return this.values[id];
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return Integer.bitCount(this.set);
    }

    /* access modifiers changed from: 0000 */
    public int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxConcurrentStreams(int defaultValue) {
        return (this.set & 16) != 0 ? this.values[4] : defaultValue;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxFrameSize(int defaultValue) {
        return (this.set & 32) != 0 ? this.values[5] : defaultValue;
    }

    /* access modifiers changed from: 0000 */
    public int getInitialWindowSize() {
        return (this.set & 128) != 0 ? this.values[7] : MinElf.PN_XNUM;
    }

    /* access modifiers changed from: 0000 */
    public void merge(Settings other) {
        for (int i = 0; i < 10; i++) {
            if (other.isSet(i)) {
                set(i, other.get(i));
            }
        }
    }
}
