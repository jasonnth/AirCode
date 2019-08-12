package net.p318sf.scuba.data;

/* renamed from: net.sf.scuba.data.Gender */
public enum Gender {
    MALE {
        public int toInt() {
            return 1;
        }
    },
    FEMALE {
        public int toInt() {
            return 2;
        }
    },
    UNKNOWN {
        public int toInt() {
            return 3;
        }
    },
    UNSPECIFIED {
        public int toInt() {
            return 0;
        }
    };

    public static Gender getInstance(int i) {
        Gender[] values;
        for (Gender gender : values()) {
            if (gender.toInt() == i) {
                return gender;
            }
        }
        return null;
    }

    public abstract int toInt();
}
