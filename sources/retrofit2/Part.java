package retrofit2;

public class Part {
    private final String encoding;
    private final String filename;
    private final String name;
    private final Object value;

    public Part(String name2, Object value2, String encoding2, String filename2) {
        this.name = name2;
        this.value = value2;
        this.encoding = encoding2;
        this.filename = filename2;
    }

    public Part(String name2, Object value2, String encoding2) {
        this(name2, value2, encoding2, null);
    }

    public Part(String name2, Object value2) {
        this(name2, value2, "binary");
    }

    public String name() {
        return this.name;
    }

    public Object value() {
        return this.value;
    }

    public String encoding() {
        return this.encoding;
    }

    public String filename() {
        return this.filename;
    }
}
