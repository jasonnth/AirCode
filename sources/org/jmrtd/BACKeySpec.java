package org.jmrtd;

import java.io.Serializable;
import java.security.spec.KeySpec;

public interface BACKeySpec extends Serializable, KeySpec {
    String getDateOfBirth();

    String getDateOfExpiry();

    String getDocumentNumber();
}
