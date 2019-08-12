package p032rx.internal.util.unsafe;

/* renamed from: rx.internal.util.unsafe.Pow2 */
public final class Pow2 {
    public static int roundToPowerOfTwo(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    public static boolean isPowerOfTwo(int value) {
        return ((value + -1) & value) == 0;
    }
}
