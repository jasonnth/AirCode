package com.airbnb.p027n2.internal;

import com.airbnb.p027n2.DLSComponentType;

/* renamed from: com.airbnb.n2.internal.AutoValue_Component */
final class AutoValue_Component extends Component {
    private final String name;
    private final DLSComponentType type;

    AutoValue_Component(String name2, DLSComponentType type2) {
        if (name2 == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name2;
        this.type = type2;
    }

    public String name() {
        return this.name;
    }

    public DLSComponentType type() {
        return this.type;
    }

    public String toString() {
        return "Component{name=" + this.name + ", type=" + this.type + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Component)) {
            return false;
        }
        Component that = (Component) o;
        if (this.name.equals(that.name())) {
            if (this.type == null) {
                if (that.type() == null) {
                    return true;
                }
            } else if (this.type.equals(that.type())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.name.hashCode()) * 1000003) ^ (this.type == null ? 0 : this.type.hashCode());
    }
}
