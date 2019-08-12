package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class zzbyg implements Cloneable {
    private Object value;
    private zzbye<?, ?> zzcwI;
    private List<zzbyl> zzcwJ = new ArrayList();

    zzbyg() {
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzu()];
        zza(zzbyc.zzah(bArr));
        return bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbyg)) {
            return false;
        }
        zzbyg zzbyg = (zzbyg) obj;
        if (this.value == null || zzbyg.value == null) {
            if (this.zzcwJ != null && zzbyg.zzcwJ != null) {
                return this.zzcwJ.equals(zzbyg.zzcwJ);
            }
            try {
                return Arrays.equals(toByteArray(), zzbyg.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzcwI == zzbyg.zzcwI) {
            return !this.zzcwI.zzckL.isArray() ? this.value.equals(zzbyg.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzbyg.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzbyg.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzbyg.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzbyg.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzbyg.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzbyg.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzbyg.value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzbyc zzbyc) throws IOException {
        if (this.value != null) {
            this.zzcwI.zza(this.value, zzbyc);
            return;
        }
        for (zzbyl zza : this.zzcwJ) {
            zza.zza(zzbyc);
        }
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzbyl zzbyl) {
        this.zzcwJ.add(zzbyl);
    }

    /* renamed from: zzafs */
    public final zzbyg clone() {
        int i = 0;
        zzbyg zzbyg = new zzbyg();
        try {
            zzbyg.zzcwI = this.zzcwI;
            if (this.zzcwJ == null) {
                zzbyg.zzcwJ = null;
            } else {
                zzbyg.zzcwJ.addAll(this.zzcwJ);
            }
            if (this.value != null) {
                if (this.value instanceof zzbyj) {
                    zzbyg.value = (zzbyj) ((zzbyj) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzbyg.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzbyg.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzbyg.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzbyg.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzbyg.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzbyg.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzbyg.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbyj[]) {
                    zzbyj[] zzbyjArr = (zzbyj[]) this.value;
                    zzbyj[] zzbyjArr2 = new zzbyj[zzbyjArr.length];
                    zzbyg.value = zzbyjArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzbyjArr.length) {
                            break;
                        }
                        zzbyjArr2[i3] = (zzbyj) zzbyjArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzbyg;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public int zzu() {
        int i = 0;
        if (this.value != null) {
            return this.zzcwI.zzaV(this.value);
        }
        Iterator it = this.zzcwJ.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = ((zzbyl) it.next()).zzu() + i2;
        }
    }
}
