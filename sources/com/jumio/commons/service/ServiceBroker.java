package com.jumio.commons.service;

import android.content.Context;
import java.util.HashMap;

public abstract class ServiceBroker {
    private boolean mInitialized = false;
    private HashMap<String, Service> serviceDirectory;
    private final Object serviceDirectoryLock = new Object();

    public Service getService(Context _context, String _serviceName) {
        Service srv;
        synchronized (this.serviceDirectoryLock) {
            srv = (Service) this.serviceDirectory.get(_serviceName);
            if (!srv.hasContext() && _context != null) {
                srv.setContext(_context);
            }
        }
        return srv;
    }

    public void setContext(Context _c) {
        if (this.serviceDirectory != null) {
            synchronized (this.serviceDirectoryLock) {
                for (Service s : this.serviceDirectory.values()) {
                    s.setContext(_c);
                }
            }
        }
    }

    public synchronized void initialize() {
        if (!this.mInitialized) {
            this.mInitialized = true;
            synchronized (this.serviceDirectoryLock) {
                if (this.serviceDirectory != null) {
                    this.serviceDirectory.clear();
                }
                this.serviceDirectory = new HashMap<>();
            }
        }
    }

    public synchronized boolean isInitialized() {
        return this.mInitialized;
    }

    public synchronized void addService(String name, Service service) {
        if (!this.mInitialized) {
            initialize();
        }
        synchronized (this.serviceDirectoryLock) {
            this.serviceDirectory.put(name, service);
        }
    }

    public synchronized void destroy() {
        synchronized (this.serviceDirectoryLock) {
            this.mInitialized = false;
            if (this.serviceDirectory != null) {
                this.serviceDirectory.clear();
            }
        }
    }
}
