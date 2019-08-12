package com.airbnb.android.p011p3.models;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_ReviewSummaryItem reason: invalid class name */
abstract class C$AutoValue_ReviewSummaryItem extends ReviewSummaryItem {
    private final String label;
    private final int value;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_ReviewSummaryItem$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.ReviewSummaryItem.Builder {
        private String label;
        private Integer value;

        Builder() {
        }

        public com.airbnb.android.p011p3.models.ReviewSummaryItem.Builder label(String label2) {
            if (label2 == null) {
                throw new NullPointerException("Null label");
            }
            this.label = label2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ReviewSummaryItem.Builder value(int value2) {
            this.value = Integer.valueOf(value2);
            return this;
        }

        public ReviewSummaryItem build() {
            String missing = "";
            if (this.label == null) {
                missing = missing + " label";
            }
            if (this.value == null) {
                missing = missing + " value";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ReviewSummaryItem(this.label, this.value.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ReviewSummaryItem(String label2, int value2) {
        if (label2 == null) {
            throw new NullPointerException("Null label");
        }
        this.label = label2;
        this.value = value2;
    }

    public String label() {
        return this.label;
    }

    public int value() {
        return this.value;
    }

    public String toString() {
        return "ReviewSummaryItem{label=" + this.label + ", value=" + this.value + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReviewSummaryItem)) {
            return false;
        }
        ReviewSummaryItem that = (ReviewSummaryItem) o;
        if (!this.label.equals(that.label()) || this.value != that.value()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.label.hashCode()) * 1000003) ^ this.value;
    }
}
