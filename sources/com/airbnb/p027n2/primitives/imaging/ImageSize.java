package com.airbnb.p027n2.primitives.imaging;

import com.facebook.imageutils.JfifUtil;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.spongycastle.crypto.tls.CipherSuite;

/* renamed from: com.airbnb.n2.primitives.imaging.ImageSize */
public enum ImageSize {
    PortraitLarge(683, 455, "poster"),
    PortraitXLarge(1280, 853, "xl_poster"),
    LandscapeXSmall(74, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, "x_small"),
    LandscapeSmall(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, JfifUtil.MARKER_SOI, "small"),
    LandscapeMedium(CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, 275, "medium"),
    LandscapeXMedium(300, 450, "x_medium"),
    LandscapeLarge(426, 639, "large"),
    LandscapeXLarge(683, 1024, "x_large"),
    LandscapeXXLarge(960, 1440, "xx_large");
    
    public static final List<ImageSize> ALL_SIZES = null;
    public static final List<ImageSize> LANDSCAPE_SIZES_ORDERED = null;
    public static final List<ImageSize> PORTRAIT_SIZES_ORDERED = null;
    public final String akamaiKey;
    public final int heightPx;
    public final boolean portrait;
    public final int widthPx;

    static {
        ALL_SIZES = ImmutableList.copyOf((E[]) values());
        PORTRAIT_SIZES_ORDERED = FluentIterable.from((Iterable<E>) ALL_SIZES).filter(ImageSize$$Lambda$1.lambdaFactory$()).toSortedList(ImageSize$$Lambda$2.lambdaFactory$());
        LANDSCAPE_SIZES_ORDERED = FluentIterable.from((Iterable<E>) ALL_SIZES).filter(ImageSize$$Lambda$3.lambdaFactory$()).toSortedList(ImageSize$$Lambda$4.lambdaFactory$());
    }

    private ImageSize(int heightPx2, int widthPx2, String akamaiKey2) {
        this.heightPx = heightPx2;
        this.widthPx = widthPx2;
        this.akamaiKey = akamaiKey2;
        this.portrait = heightPx2 > widthPx2;
    }

    public static ImageSize getSizeForDimensions(int widthPx2, int heightPx2) {
        if (heightPx2 > widthPx2) {
            return findBestSizeMatch(PORTRAIT_SIZES_ORDERED, heightPx2);
        }
        return findBestSizeMatch(LANDSCAPE_SIZES_ORDERED, widthPx2);
    }

    private static ImageSize findBestSizeMatch(List<ImageSize> orderedSizes, int largestDimension) {
        for (ImageSize size : orderedSizes) {
            if (size.heightPx >= largestDimension) {
                return size;
            }
            if (size.widthPx >= largestDimension) {
                return size;
            }
        }
        return (ImageSize) orderedSizes.get(orderedSizes.size() - 1);
    }
}
