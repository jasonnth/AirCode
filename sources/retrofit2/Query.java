package retrofit2;

public class Query {
    private final boolean encoded;
    private final String name;
    private final String value;

    public Query(String name2, String value2) {
        this(name2, value2, false);
    }

    public Query(String name2, String value2, boolean encoded2) {
        this.name = name2;
        this.value = value2;
        this.encoded = encoded2;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    public boolean encoded() {
        return this.encoded;
    }

    public String toString() {
        return "Query{name: " + this.name + ", value: " + this.value + ", encoded: " + this.encoded + "}";
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Query query = (Query) o;
        if (this.encoded != query.encoded) {
            return false;
        }
        if (this.name != null) {
            if (!this.name.equals(query.name)) {
                return false;
            }
        } else if (query.name != null) {
            return false;
        }
        if (this.value == null ? query.value != null : !this.value.equals(query.value)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.name != null) {
            result = this.name.hashCode();
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.value != null) {
            i = this.value.hashCode();
        } else {
            i = 0;
        }
        int i4 = (i3 + i) * 31;
        if (this.encoded) {
            i2 = 1;
        }
        return i4 + i2;
    }
}
