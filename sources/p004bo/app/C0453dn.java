package p004bo.app;

/* renamed from: bo.app.dn */
public final class C0453dn {
    /* renamed from: a */
    public static <Source, Target> Target m513a(Source source, Class<Target> cls) {
        if (cls.isAssignableFrom(source.getClass())) {
            return cls.cast(source);
        }
        return null;
    }
}
