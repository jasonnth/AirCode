package org.joda.time.convert;

import org.joda.time.Chronology;

public interface InstantConverter extends Converter {
    Chronology getChronology(Object obj, Chronology chronology);

    long getInstantMillis(Object obj, Chronology chronology);
}
