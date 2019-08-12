package com.bugsnag.android;

import java.util.Observer;

public class NativeInterface {
    public static void configureClientObservers(Client client) {
        try {
            client.addObserver((Observer) Class.forName("com.bugsnag.android.ndk.BugsnagObserver").newInstance());
        } catch (ClassNotFoundException e) {
            Logger.info("Bugsnag NDK integration not available");
        } catch (InstantiationException e2) {
            Logger.warn("Failed to instantiate NDK observer", e2);
        } catch (IllegalAccessException e3) {
            Logger.warn("Could not access NDK observer", e3);
        }
        client.notifyBugsnagObservers(NotifyType.ALL);
    }
}
