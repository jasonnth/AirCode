package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.p307io.SerializedString;
import com.fasterxml.jackson.core.util.InternCache;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

public class PropertyName implements Serializable {
    public static final PropertyName NO_NAME = new PropertyName(new String(""), null);
    public static final PropertyName USE_DEFAULT = new PropertyName("", null);
    protected SerializableString _encodedSimple;
    protected final String _namespace;
    protected final String _simpleName;

    public PropertyName(String simpleName) {
        this(simpleName, null);
    }

    public PropertyName(String simpleName, String namespace) {
        if (simpleName == null) {
            simpleName = "";
        }
        this._simpleName = simpleName;
        this._namespace = namespace;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    /* access modifiers changed from: protected */
    public Object readResolve() {
        if (this._simpleName == null || "".equals(this._simpleName)) {
            return USE_DEFAULT;
        }
        if (!this._simpleName.equals("") || this._namespace != null) {
            return this;
        }
        return NO_NAME;
    }

    public static PropertyName construct(String simpleName) {
        if (simpleName == null || simpleName.length() == 0) {
            return USE_DEFAULT;
        }
        return new PropertyName(InternCache.instance.intern(simpleName), null);
    }

    public static PropertyName construct(String simpleName, String ns) {
        if (simpleName == null) {
            simpleName = "";
        }
        if (ns == null && simpleName.length() == 0) {
            return USE_DEFAULT;
        }
        return new PropertyName(InternCache.instance.intern(simpleName), ns);
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public PropertyName internSimpleName() {
        if (this._simpleName.length() == 0) {
            return this;
        }
        String interned = InternCache.instance.intern(this._simpleName);
        return interned != this._simpleName ? new PropertyName(interned, this._namespace) : this;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public PropertyName withSimpleName(String simpleName) {
        if (simpleName == null) {
            simpleName = "";
        }
        return simpleName.equals(this._simpleName) ? this : new PropertyName(simpleName, this._namespace);
    }

    public String getSimpleName() {
        return this._simpleName;
    }

    public SerializableString simpleAsEncoded(MapperConfig<?> config) {
        SerializableString sstr = this._encodedSimple;
        if (sstr == null) {
            if (config == null) {
                sstr = new SerializedString(this._simpleName);
            } else {
                sstr = config.compileString(this._simpleName);
            }
            this._encodedSimple = sstr;
        }
        return sstr;
    }

    public boolean hasSimpleName() {
        return this._simpleName.length() > 0;
    }

    public boolean hasSimpleName(String str) {
        if (str == null) {
            return this._simpleName == null;
        }
        return str.equals(this._simpleName);
    }

    public boolean hasNamespace() {
        return this._namespace != null;
    }

    public boolean isEmpty() {
        return this._namespace == null && this._simpleName.isEmpty();
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        PropertyName other = (PropertyName) o;
        if (this._simpleName == null) {
            if (other._simpleName != null) {
                return false;
            }
        } else if (!this._simpleName.equals(other._simpleName)) {
            return false;
        }
        if (this._namespace != null) {
            return this._namespace.equals(other._namespace);
        }
        if (other._namespace != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this._namespace == null) {
            return this._simpleName.hashCode();
        }
        return this._namespace.hashCode() ^ this._simpleName.hashCode();
    }

    public String toString() {
        if (this._namespace == null) {
            return this._simpleName;
        }
        return "{" + this._namespace + "}" + this._simpleName;
    }
}