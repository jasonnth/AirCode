package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import org.spongycastle.asn1.eac.EACTags;

public final class PasswordSpecification extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
    public static final Creator<PasswordSpecification> CREATOR = new zzf();
    public static final PasswordSpecification zzajC = new zza().zzm(12, 16).zzcs("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zzf("abcdefghijkmnopqrstxyz", 1).zzf("ABCDEFGHJKLMNPQRSTXY", 1).zzf("3456789", 1).zzqZ();
    public static final PasswordSpecification zzajD = new zza().zzm(12, 16).zzcs("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zzf("abcdefghijklmnopqrstuvwxyz", 1).zzf("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zzf("1234567890", 1).zzqZ();
    final int zzaiI;
    final String zzajE;
    final List<String> zzajF;
    final List<Integer> zzajG;
    final int zzajH;
    final int zzajI;
    private final int[] zzajJ = zzqY();
    private final Random zzzW = new SecureRandom();

    public static class zza {
        private final List<String> zzajF = new ArrayList();
        private final List<Integer> zzajG = new ArrayList();
        private int zzajH = 12;
        private int zzajI = 16;
        private final TreeSet<Character> zzajK = new TreeSet<>();

        private void zzra() {
            int i;
            int i2 = 0;
            Iterator it = this.zzajG.iterator();
            while (true) {
                i = i2;
                if (!it.hasNext()) {
                    break;
                }
                i2 = ((Integer) it.next()).intValue() + i;
            }
            if (i > this.zzajI) {
                throw new zzb("required character count cannot be greater than the max password size");
            }
        }

        private void zzrb() {
            boolean[] zArr = new boolean[95];
            for (String charArray : this.zzajF) {
                char[] charArray2 = charArray.toCharArray();
                int length = charArray2.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        char c = charArray2[i];
                        if (zArr[c - ' ']) {
                            throw new zzb("character " + c + " occurs in more than one required character set");
                        }
                        zArr[c - ' '] = true;
                        i++;
                    }
                }
            }
        }

        private TreeSet<Character> zzw(String str, String str2) {
            char[] charArray;
            if (TextUtils.isEmpty(str)) {
                throw new zzb(String.valueOf(str2).concat(" cannot be null or empty"));
            }
            TreeSet<Character> treeSet = new TreeSet<>();
            for (char c : str.toCharArray()) {
                if (PasswordSpecification.zzc(c, 32, EACTags.NON_INTERINDUSTRY_DATA_OBJECT_NESTING_TEMPLATE)) {
                    throw new zzb(String.valueOf(str2).concat(" must only contain ASCII printable characters"));
                }
                treeSet.add(Character.valueOf(c));
            }
            return treeSet;
        }

        public zza zzcs(String str) {
            this.zzajK.addAll(zzw(str, "allowedChars"));
            return this;
        }

        public zza zzf(String str, int i) {
            if (i < 1) {
                throw new zzb("count must be at least 1");
            }
            this.zzajF.add(PasswordSpecification.zzb(zzw(str, "requiredChars")));
            this.zzajG.add(Integer.valueOf(i));
            return this;
        }

        public zza zzm(int i, int i2) {
            if (i < 1) {
                throw new zzb("minimumSize must be at least 1");
            } else if (i > i2) {
                throw new zzb("maximumSize must be greater than or equal to minimumSize");
            } else {
                this.zzajH = i;
                this.zzajI = i2;
                return this;
            }
        }

        public PasswordSpecification zzqZ() {
            if (this.zzajK.isEmpty()) {
                throw new zzb("no allowed characters specified");
            }
            zzra();
            zzrb();
            return new PasswordSpecification(1, PasswordSpecification.zzb(this.zzajK), this.zzajF, this.zzajG, this.zzajH, this.zzajI);
        }
    }

    public static class zzb extends Error {
        public zzb(String str) {
            super(str);
        }
    }

    PasswordSpecification(int i, String str, List<String> list, List<Integer> list2, int i2, int i3) {
        this.zzaiI = i;
        this.zzajE = str;
        this.zzajF = Collections.unmodifiableList(list);
        this.zzajG = Collections.unmodifiableList(list2);
        this.zzajH = i2;
        this.zzajI = i3;
    }

    private int zza(char c) {
        return c - ' ';
    }

    /* access modifiers changed from: private */
    public static String zzb(Collection<Character> collection) {
        char[] cArr = new char[collection.size()];
        int i = 0;
        Iterator it = collection.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return new String(cArr);
            }
            i = i2 + 1;
            cArr[i2] = ((Character) it.next()).charValue();
        }
    }

    /* access modifiers changed from: private */
    public static boolean zzc(int i, int i2, int i3) {
        return i < i2 || i > i3;
    }

    private int[] zzqY() {
        int[] iArr = new int[95];
        Arrays.fill(iArr, -1);
        int i = 0;
        for (String charArray : this.zzajF) {
            for (char zza2 : charArray.toCharArray()) {
                iArr[zza(zza2)] = i;
            }
            i++;
        }
        return iArr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }
}
