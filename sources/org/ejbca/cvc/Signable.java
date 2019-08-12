package org.ejbca.cvc;

import org.ejbca.cvc.exception.ConstructionException;

public interface Signable {
    byte[] getTBS() throws ConstructionException;
}
