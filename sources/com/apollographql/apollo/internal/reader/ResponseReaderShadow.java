package com.apollographql.apollo.internal.reader;

import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.internal.Optional;
import java.util.List;

public interface ResponseReaderShadow<R> {
    void didParseElement(int i);

    void didParseList(List list);

    void didParseNull();

    void didParseObject(Field field, Optional<R> optional);

    void didParseScalar(Object obj);

    void didResolve(Field field, Variables variables);

    void willParseElement(int i);

    void willParseObject(Field field, Optional<R> optional);

    void willResolve(Field field, Variables variables);
}
