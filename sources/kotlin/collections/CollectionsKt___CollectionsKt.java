package kotlin.collections;

import com.facebook.share.internal.ShareConstants;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: _Collections.kt */
class CollectionsKt___CollectionsKt extends CollectionsKt__ReversedViewsKt {
    public static final <T, C extends Collection<? super T>> C toCollection(Iterable<? extends T> $receiver, C destination) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, ShareConstants.DESTINATION);
        for (Object item : $receiver) {
            destination.add(item);
        }
        return destination;
    }

    public static final <T> Set<T> toMutableSet(Iterable<? extends T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ($receiver instanceof Collection) {
            return new LinkedHashSet<>((Collection) $receiver);
        }
        return (Set) CollectionsKt.toCollection($receiver, new LinkedHashSet());
    }
}
