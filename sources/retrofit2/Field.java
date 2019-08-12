package retrofit2;

public class Field {
    private final boolean encoded;
    private final String name;
    private final Object value;

    public Field(String name2, Object value2, boolean encoded2) {
        this.name = name2;
        this.value = value2;
        this.encoded = encoded2;
    }

    public Field(String name2, Object value2) {
        this(name2, value2, false);
    }

    public String name() {
        return this.name;
    }

    public Object value() {
        return this.value;
    }

    public boolean encoded() {
        return this.encoded;
    }
}
