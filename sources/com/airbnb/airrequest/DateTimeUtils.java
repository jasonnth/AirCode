package com.airbnb.airrequest;

import java.security.BasicPermission;

class DateTimeUtils {
    private static final MillisProvider SYSTEM_MILLIS_PROVIDER = new SystemMillisProvider();
    private static volatile MillisProvider cMillisProvider = SYSTEM_MILLIS_PROVIDER;

    static class FixedMillisProvider implements MillisProvider {
        private final long iMillis;

        FixedMillisProvider(long fixedMillis) {
            this.iMillis = fixedMillis;
        }

        public long getMillis() {
            return this.iMillis;
        }
    }

    public static class JodaTimePermission extends BasicPermission {
        private static final long serialVersionUID = 1408944367355875472L;

        JodaTimePermission(String name) {
            super(name);
        }
    }

    public interface MillisProvider {
        long getMillis();
    }

    static class SystemMillisProvider implements MillisProvider {
        SystemMillisProvider() {
        }

        public long getMillis() {
            return System.currentTimeMillis();
        }
    }

    DateTimeUtils() {
    }

    static long currentTimeMillis() {
        return cMillisProvider.getMillis();
    }

    static void setCurrentMillisFixed(long fixedMillis) throws SecurityException {
        checkPermission();
        cMillisProvider = new FixedMillisProvider(fixedMillis);
    }

    private static void checkPermission() throws SecurityException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new JodaTimePermission("CurrentTime.setProvider"));
        }
    }
}
