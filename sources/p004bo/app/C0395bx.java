package p004bo.app;

import com.appboy.models.IPutIntoJson;
import java.util.UUID;

/* renamed from: bo.app.bx */
public final class C0395bx implements IPutIntoJson<String> {

    /* renamed from: a */
    private final UUID f225a;

    public C0395bx(UUID uuid) {
        this.f225a = uuid;
    }

    /* renamed from: a */
    public static C0395bx m273a() {
        return new C0395bx(UUID.randomUUID());
    }

    /* renamed from: a */
    public static C0395bx m274a(String str) {
        return new C0395bx(UUID.fromString(str));
    }

    public String toString() {
        return this.f225a.toString();
    }

    /* renamed from: b */
    public String forJsonPut() {
        return this.f225a.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return this.f225a.equals(((C0395bx) other).f225a);
    }

    public int hashCode() {
        return this.f225a.hashCode();
    }
}
