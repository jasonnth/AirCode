package net.p318sf.scuba.data;

/* renamed from: net.sf.scuba.data.TestCountry */
public class TestCountry extends Country {

    /* renamed from: UT */
    public static final TestCountry f6055UT = new TestCountry(-1, "UT", "UTO", "Utopia", "Utopian");
    private static final TestCountry[] VALUES = {f6055UT};
    private String alpha2Code;
    private String alpha3Code;
    private int code;
    private String name;
    private String nationality;

    private TestCountry() {
    }

    private TestCountry(int i, String str, String str2, String str3, String str4) {
        this.code = i;
        this.alpha2Code = str;
        this.alpha3Code = str2;
        this.name = str3;
        this.nationality = str4;
    }

    public static TestCountry[] values() {
        return VALUES;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        return this.alpha3Code.equals(((TestCountry) obj).alpha3Code);
    }

    public String getName() {
        return this.name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public int hashCode() {
        return (this.alpha3Code.hashCode() * 2) + 31;
    }

    public String toAlpha2Code() {
        return this.alpha2Code;
    }

    public String toAlpha3Code() {
        return this.alpha3Code;
    }

    public String toString() {
        return this.alpha2Code;
    }

    public int valueOf() {
        return this.code;
    }
}
