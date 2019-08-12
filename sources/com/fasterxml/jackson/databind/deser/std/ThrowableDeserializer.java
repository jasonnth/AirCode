package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class ThrowableDeserializer extends BeanDeserializer {
    public ThrowableDeserializer(BeanDeserializer baseDeserializer) {
        super(baseDeserializer);
        this._vanillaProcessing = false;
    }

    protected ThrowableDeserializer(BeanDeserializer src, NameTransformer unwrapper) {
        super((BeanDeserializerBase) src, unwrapper);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer unwrapper) {
        return getClass() != ThrowableDeserializer.class ? this : new ThrowableDeserializer(this, unwrapper);
    }

    public Object deserializeFromObject(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object throwable;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(p, ctxt);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(p, ctxt));
        }
        if (this._beanType.isAbstract()) {
            return ctxt.handleMissingInstantiator(handledType(), p, "abstract type (need to add/enable type information?)", new Object[0]);
        }
        boolean hasStringCreator = this._valueInstantiator.canCreateFromString();
        boolean hasDefaultCtor = this._valueInstantiator.canCreateUsingDefault();
        if (!hasStringCreator && !hasDefaultCtor) {
            return ctxt.handleMissingInstantiator(handledType(), p, "Throwable needs a default contructor, a single-String-arg constructor; or explicit @JsonCreator", new Object[0]);
        }
        Object throwable2 = null;
        Object[] pending = null;
        int pendingIx = 0;
        while (p.getCurrentToken() != JsonToken.END_OBJECT) {
            String propName = p.getCurrentName();
            SettableBeanProperty prop = this._beanProperties.find(propName);
            p.nextToken();
            if (prop != null) {
                if (throwable2 != null) {
                    prop.deserializeAndSet(p, ctxt, throwable2);
                } else {
                    if (pending == null) {
                        int len = this._beanProperties.size();
                        pending = new Object[(len + len)];
                    }
                    int pendingIx2 = pendingIx + 1;
                    pending[pendingIx] = prop;
                    pendingIx = pendingIx2 + 1;
                    pending[pendingIx2] = prop.deserialize(p, ctxt);
                }
            } else if ("message".equals(propName) && hasStringCreator) {
                throwable2 = this._valueInstantiator.createFromString(ctxt, p.getText());
                if (pending != null) {
                    int len2 = pendingIx;
                    for (int i = 0; i < len2; i += 2) {
                        ((SettableBeanProperty) pending[i]).set(throwable2, pending[i + 1]);
                    }
                    pending = null;
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(propName)) {
                p.skipChildren();
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(p, ctxt, throwable2, propName);
            } else {
                handleUnknownProperty(p, ctxt, throwable2, propName);
            }
            p.nextToken();
        }
        if (throwable2 != null) {
            return throwable2;
        }
        if (hasStringCreator) {
            throwable = this._valueInstantiator.createFromString(ctxt, null);
        } else {
            throwable = this._valueInstantiator.createUsingDefault(ctxt);
        }
        if (pending == null) {
            return throwable;
        }
        int len3 = pendingIx;
        for (int i2 = 0; i2 < len3; i2 += 2) {
            ((SettableBeanProperty) pending[i2]).set(throwable, pending[i2 + 1]);
        }
        return throwable;
    }
}
