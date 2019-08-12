package com.airbnb.p027n2.transitions;

import android.text.TextUtils;
import org.spongycastle.asn1.eac.EACTags;

/* renamed from: com.airbnb.n2.transitions.TransitionName */
public abstract class TransitionName {
    /* renamed from: id */
    public abstract long mo26441id();

    public abstract long subId();

    public abstract String subtype();

    public abstract String type();

    public static TransitionName create(String type, long id, String subtype) {
        return create(type, id, subtype, 0);
    }

    public static TransitionName create(String type, long id, String subtype, long subId) {
        return new AutoValue_TransitionName(type, id, subtype, subId);
    }

    public static String toString(String type, long id, String subtype) {
        return toString(type, id, subtype, 0);
    }

    public static String toString(String type, long id, String subtype, long subId) {
        if (type.indexOf(EACTags.DYNAMIC_AUTHENTIFICATION_TEMPLATE) != -1) {
            throw new IllegalArgumentException("Invalid type " + type + ". Delimeter is " + '|');
        } else if (subtype.indexOf(EACTags.DYNAMIC_AUTHENTIFICATION_TEMPLATE) == -1) {
            return type + '|' + id + '|' + subtype + '|' + subId;
        } else {
            throw new IllegalArgumentException("Invalid subtype " + subtype + ". Delimeter is " + '|');
        }
    }

    public static TransitionName parse(String transitionNameString) {
        if (TextUtils.isEmpty(transitionNameString)) {
            return new AutoValue_TransitionName("", 0, "", 0);
        }
        String[] parsed = transitionNameString.split("[|]");
        switch (parsed.length) {
            case 1:
                return new AutoValue_TransitionName(parsed[0], 0, "", 0);
            case 2:
                return new AutoValue_TransitionName(parsed[0], Long.parseLong(parsed[1]), "", 0);
            case 3:
                return new AutoValue_TransitionName(parsed[0], Long.parseLong(parsed[1]), parsed[2], 0);
            case 4:
                return new AutoValue_TransitionName(parsed[0], Long.parseLong(parsed[1]), parsed[2], Long.parseLong(parsed[3]));
            default:
                throw new IllegalArgumentException("Invalid transition name " + transitionNameString + ". Split into " + parsed.length + ". Should be less than 4.");
        }
    }

    public boolean partialEquals(TransitionName other) {
        return type().equals(other.type()) && mo26441id() == other.mo26441id() && subtype().equals(other.subtype());
    }
}
