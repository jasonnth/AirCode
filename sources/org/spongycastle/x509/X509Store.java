package org.spongycastle.x509;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Collection;
import org.spongycastle.util.Selector;
import org.spongycastle.util.Store;

public class X509Store implements Store {
    private Provider _provider;
    private X509StoreSpi _spi;

    public static X509Store getInstance(String type, X509StoreParameters parameters) throws NoSuchStoreException {
        try {
            return createStore(X509Util.getImplementation("X509Store", type), parameters);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchStoreException(e.getMessage());
        }
    }

    public static X509Store getInstance(String type, X509StoreParameters parameters, String provider) throws NoSuchStoreException, NoSuchProviderException {
        return getInstance(type, parameters, X509Util.getProvider(provider));
    }

    public static X509Store getInstance(String type, X509StoreParameters parameters, Provider provider) throws NoSuchStoreException {
        try {
            return createStore(X509Util.getImplementation("X509Store", type, provider), parameters);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchStoreException(e.getMessage());
        }
    }

    private static X509Store createStore(Implementation impl, X509StoreParameters parameters) {
        X509StoreSpi spi = (X509StoreSpi) impl.getEngine();
        spi.engineInit(parameters);
        return new X509Store(impl.getProvider(), spi);
    }

    private X509Store(Provider provider, X509StoreSpi spi) {
        this._provider = provider;
        this._spi = spi;
    }

    public Provider getProvider() {
        return this._provider;
    }

    public Collection getMatches(Selector selector) {
        return this._spi.engineGetMatches(selector);
    }
}
