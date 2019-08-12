package com.kakao;

public class AppActionBuilder {

    public enum DEVICE_TYPE {
        PHONE("phone"),
        PAD("pad");
        
        private final String value;

        private DEVICE_TYPE(String value2) {
            this.value = value2;
        }

        public String getValue() {
            return this.value;
        }
    }
}
