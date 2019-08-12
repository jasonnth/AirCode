package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Collection;
import org.spongycastle.x509.util.StreamParser;
import org.spongycastle.x509.util.StreamParsingException;

public class X509StreamParser implements StreamParser {
    private Provider _provider;
    private X509StreamParserSpi _spi;

    public static X509StreamParser getInstance(String type) throws NoSuchParserException {
        try {
            return createParser(X509Util.getImplementation("X509StreamParser", type));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchParserException(e.getMessage());
        }
    }

    public static X509StreamParser getInstance(String type, String provider) throws NoSuchParserException, NoSuchProviderException {
        return getInstance(type, X509Util.getProvider(provider));
    }

    public static X509StreamParser getInstance(String type, Provider provider) throws NoSuchParserException {
        try {
            return createParser(X509Util.getImplementation("X509StreamParser", type, provider));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchParserException(e.getMessage());
        }
    }

    private static X509StreamParser createParser(Implementation impl) {
        return new X509StreamParser(impl.getProvider(), (X509StreamParserSpi) impl.getEngine());
    }

    private X509StreamParser(Provider provider, X509StreamParserSpi spi) {
        this._provider = provider;
        this._spi = spi;
    }

    public Provider getProvider() {
        return this._provider;
    }

    public void init(InputStream stream) {
        this._spi.engineInit(stream);
    }

    public void init(byte[] data) {
        this._spi.engineInit(new ByteArrayInputStream(data));
    }

    public Object read() throws StreamParsingException {
        return this._spi.engineRead();
    }

    public Collection readAll() throws StreamParsingException {
        return this._spi.engineReadAll();
    }
}
