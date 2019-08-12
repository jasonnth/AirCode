package com.airbnb.android.payout.models;

import java.util.List;

/* renamed from: com.airbnb.android.payout.models.$AutoValue_PayoutFormField reason: invalid class name */
abstract class C$AutoValue_PayoutFormField extends PayoutFormField {
    private final boolean confirmField;
    private final String errorText;
    private final String fieldType;
    private final String helperText;
    private final String hintText;
    private final String label;
    private final Integer maxLength;
    private final Integer minLength;
    private final String name;
    private final String regex;
    private final boolean required;
    private final List<String> values;

    /* renamed from: com.airbnb.android.payout.models.$AutoValue_PayoutFormField$Builder */
    static final class Builder extends com.airbnb.android.payout.models.PayoutFormField.Builder {
        private Boolean confirmField;
        private String errorText;
        private String fieldType;
        private String helperText;
        private String hintText;
        private String label;
        private Integer maxLength;
        private Integer minLength;
        private String name;
        private String regex;
        private Boolean required;
        private List<String> values;

        Builder() {
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder confirmField(boolean confirmField2) {
            this.confirmField = Boolean.valueOf(confirmField2);
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder required(boolean required2) {
            this.required = Boolean.valueOf(required2);
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder errorText(String errorText2) {
            this.errorText = errorText2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder fieldType(String fieldType2) {
            this.fieldType = fieldType2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder helperText(String helperText2) {
            this.helperText = helperText2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder hintText(String hintText2) {
            this.hintText = hintText2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder name(String name2) {
            this.name = name2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder label(String label2) {
            if (label2 == null) {
                throw new NullPointerException("Null label");
            }
            this.label = label2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder maxLength(Integer maxLength2) {
            this.maxLength = maxLength2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder minLength(Integer minLength2) {
            this.minLength = minLength2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder values(List<String> values2) {
            this.values = values2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormField.Builder regex(String regex2) {
            this.regex = regex2;
            return this;
        }

        public PayoutFormField build() {
            String missing = "";
            if (this.confirmField == null) {
                missing = missing + " confirmField";
            }
            if (this.required == null) {
                missing = missing + " required";
            }
            if (this.label == null) {
                missing = missing + " label";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PayoutFormField(this.confirmField.booleanValue(), this.required.booleanValue(), this.errorText, this.fieldType, this.helperText, this.hintText, this.name, this.label, this.maxLength, this.minLength, this.values, this.regex);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PayoutFormField(boolean confirmField2, boolean required2, String errorText2, String fieldType2, String helperText2, String hintText2, String name2, String label2, Integer maxLength2, Integer minLength2, List<String> values2, String regex2) {
        this.confirmField = confirmField2;
        this.required = required2;
        this.errorText = errorText2;
        this.fieldType = fieldType2;
        this.helperText = helperText2;
        this.hintText = hintText2;
        this.name = name2;
        if (label2 == null) {
            throw new NullPointerException("Null label");
        }
        this.label = label2;
        this.maxLength = maxLength2;
        this.minLength = minLength2;
        this.values = values2;
        this.regex = regex2;
    }

    public boolean confirmField() {
        return this.confirmField;
    }

    public boolean required() {
        return this.required;
    }

    public String errorText() {
        return this.errorText;
    }

    public String fieldType() {
        return this.fieldType;
    }

    public String helperText() {
        return this.helperText;
    }

    public String hintText() {
        return this.hintText;
    }

    public String name() {
        return this.name;
    }

    public String label() {
        return this.label;
    }

    public Integer maxLength() {
        return this.maxLength;
    }

    public Integer minLength() {
        return this.minLength;
    }

    public List<String> values() {
        return this.values;
    }

    public String regex() {
        return this.regex;
    }

    public String toString() {
        return "PayoutFormField{confirmField=" + this.confirmField + ", required=" + this.required + ", errorText=" + this.errorText + ", fieldType=" + this.fieldType + ", helperText=" + this.helperText + ", hintText=" + this.hintText + ", name=" + this.name + ", label=" + this.label + ", maxLength=" + this.maxLength + ", minLength=" + this.minLength + ", values=" + this.values + ", regex=" + this.regex + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PayoutFormField)) {
            return false;
        }
        PayoutFormField that = (PayoutFormField) o;
        if (this.confirmField == that.confirmField() && this.required == that.required() && (this.errorText != null ? this.errorText.equals(that.errorText()) : that.errorText() == null) && (this.fieldType != null ? this.fieldType.equals(that.fieldType()) : that.fieldType() == null) && (this.helperText != null ? this.helperText.equals(that.helperText()) : that.helperText() == null) && (this.hintText != null ? this.hintText.equals(that.hintText()) : that.hintText() == null) && (this.name != null ? this.name.equals(that.name()) : that.name() == null) && this.label.equals(that.label()) && (this.maxLength != null ? this.maxLength.equals(that.maxLength()) : that.maxLength() == null) && (this.minLength != null ? this.minLength.equals(that.minLength()) : that.minLength() == null) && (this.values != null ? this.values.equals(that.values()) : that.values() == null)) {
            if (this.regex == null) {
                if (that.regex() == null) {
                    return true;
                }
            } else if (this.regex.equals(that.regex())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = 0;
        int h = ((1 * 1000003) ^ (this.confirmField ? 1231 : 1237)) * 1000003;
        if (!this.required) {
            i = 1237;
        }
        int h2 = (((((((((((((((((((h ^ i) * 1000003) ^ (this.errorText == null ? 0 : this.errorText.hashCode())) * 1000003) ^ (this.fieldType == null ? 0 : this.fieldType.hashCode())) * 1000003) ^ (this.helperText == null ? 0 : this.helperText.hashCode())) * 1000003) ^ (this.hintText == null ? 0 : this.hintText.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ this.label.hashCode()) * 1000003) ^ (this.maxLength == null ? 0 : this.maxLength.hashCode())) * 1000003) ^ (this.minLength == null ? 0 : this.minLength.hashCode())) * 1000003) ^ (this.values == null ? 0 : this.values.hashCode())) * 1000003;
        if (this.regex != null) {
            i2 = this.regex.hashCode();
        }
        return h2 ^ i2;
    }
}
