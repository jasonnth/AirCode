package org.ejbca.cvc;

public interface AccessRights {
    byte[] getEncoded();

    String name();
}
