package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

public final class Route {
    final C5164Address address;
    final InetSocketAddress inetSocketAddress;
    final Proxy proxy;

    public Route(C5164Address address2, Proxy proxy2, InetSocketAddress inetSocketAddress2) {
        if (address2 == null) {
            throw new NullPointerException("address == null");
        } else if (proxy2 == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress2 == null) {
            throw new NullPointerException("inetSocketAddress == null");
        } else {
            this.address = address2;
            this.proxy = proxy2;
            this.inetSocketAddress = inetSocketAddress2;
        }
    }

    public C5164Address address() {
        return this.address;
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public InetSocketAddress socketAddress() {
        return this.inetSocketAddress;
    }

    public boolean requiresTunnel() {
        return this.address.sslSocketFactory != null && this.proxy.type() == Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Route)) {
            return false;
        }
        Route other = (Route) obj;
        if (!this.address.equals(other.address) || !this.proxy.equals(other.proxy) || !this.inetSocketAddress.equals(other.inetSocketAddress)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.address.hashCode() + 527) * 31) + this.proxy.hashCode()) * 31) + this.inetSocketAddress.hashCode();
    }

    public String toString() {
        return "Route{" + this.inetSocketAddress + "}";
    }
}
