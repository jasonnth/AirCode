package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExternalTypeHandler {
    private final HashMap<String, Integer> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    public static class Builder {
        private final HashMap<String, Integer> _nameToPropertyIndex = new HashMap<>();
        private final ArrayList<ExtTypedProperty> _properties = new ArrayList<>();

        public void addExternal(SettableBeanProperty property, TypeDeserializer typeDeser) {
            Integer index = Integer.valueOf(this._properties.size());
            this._properties.add(new ExtTypedProperty(property, typeDeser));
            this._nameToPropertyIndex.put(property.getName(), index);
            this._nameToPropertyIndex.put(typeDeser.getPropertyName(), index);
        }

        public ExternalTypeHandler build(BeanPropertyMap otherProps) {
            int len = this._properties.size();
            ExtTypedProperty[] extProps = new ExtTypedProperty[len];
            for (int i = 0; i < len; i++) {
                ExtTypedProperty extProp = (ExtTypedProperty) this._properties.get(i);
                SettableBeanProperty typeProp = otherProps.find(extProp.getTypePropertyName());
                if (typeProp != null) {
                    extProp.linkTypeProperty(typeProp);
                }
                extProps[i] = extProp;
            }
            return new ExternalTypeHandler(extProps, this._nameToPropertyIndex, null, null);
        }
    }

    private static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private SettableBeanProperty _typeProperty;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty property, TypeDeserializer typeDeser) {
            this._property = property;
            this._typeDeserializer = typeDeser;
            this._typePropertyName = typeDeser.getPropertyName();
        }

        public void linkTypeProperty(SettableBeanProperty p) {
            this._typeProperty = p;
        }

        public boolean hasTypePropertyName(String n) {
            return n.equals(this._typePropertyName);
        }

        public boolean hasDefaultType() {
            return this._typeDeserializer.getDefaultImpl() != null;
        }

        public String getDefaultTypeId() {
            Class<?> defaultType = this._typeDeserializer.getDefaultImpl();
            if (defaultType == null) {
                return null;
            }
            return this._typeDeserializer.getTypeIdResolver().idFromValueAndType(null, defaultType);
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }

        public SettableBeanProperty getTypeProperty() {
            return this._typeProperty;
        }
    }

    protected ExternalTypeHandler(ExtTypedProperty[] properties, HashMap<String, Integer> nameToPropertyIndex, String[] typeIds, TokenBuffer[] tokens) {
        this._properties = properties;
        this._nameToPropertyIndex = nameToPropertyIndex;
        this._typeIds = typeIds;
        this._tokens = tokens;
    }

    protected ExternalTypeHandler(ExternalTypeHandler h) {
        this._properties = h._properties;
        this._nameToPropertyIndex = h._nameToPropertyIndex;
        int len = this._properties.length;
        this._typeIds = new String[len];
        this._tokens = new TokenBuffer[len];
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    public boolean handleTypePropertyValue(JsonParser p, DeserializationContext ctxt, String propName, Object bean) throws IOException {
        boolean canDeserialize;
        Integer I = (Integer) this._nameToPropertyIndex.get(propName);
        if (I == null) {
            return false;
        }
        int index = I.intValue();
        if (!this._properties[index].hasTypePropertyName(propName)) {
            return false;
        }
        String typeId = p.getText();
        if (bean == null || this._tokens[index] == null) {
            canDeserialize = false;
        } else {
            canDeserialize = true;
        }
        if (canDeserialize) {
            _deserializeAndSet(p, ctxt, bean, index, typeId);
            this._tokens[index] = null;
        } else {
            this._typeIds[index] = typeId;
        }
        return true;
    }

    public boolean handlePropertyValue(JsonParser p, DeserializationContext ctxt, String propName, Object bean) throws IOException {
        boolean canDeserialize;
        Integer I = (Integer) this._nameToPropertyIndex.get(propName);
        if (I == null) {
            return false;
        }
        int index = I.intValue();
        if (this._properties[index].hasTypePropertyName(propName)) {
            this._typeIds[index] = p.getText();
            p.skipChildren();
            canDeserialize = (bean == null || this._tokens[index] == null) ? false : true;
        } else {
            TokenBuffer tokens = new TokenBuffer(p, ctxt);
            tokens.copyCurrentStructure(p);
            this._tokens[index] = tokens;
            canDeserialize = (bean == null || this._typeIds[index] == null) ? false : true;
        }
        if (canDeserialize) {
            String typeId = this._typeIds[index];
            this._typeIds[index] = null;
            _deserializeAndSet(p, ctxt, bean, index, typeId);
            this._tokens[index] = null;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0091, code lost:
        if (r16.isEnabled(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY) != false) goto L_0x0093;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object complete(com.fasterxml.jackson.core.JsonParser r15, com.fasterxml.jackson.databind.DeserializationContext r16, java.lang.Object r17) throws java.io.IOException {
        /*
            r14 = this;
            r5 = 0
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r14._properties
            int r9 = r1.length
        L_0x0004:
            if (r5 >= r9) goto L_0x00b0
            java.lang.String[] r1 = r14._typeIds
            r6 = r1[r5]
            if (r6 != 0) goto L_0x0075
            com.fasterxml.jackson.databind.util.TokenBuffer[] r1 = r14._tokens
            r13 = r1[r5]
            if (r13 != 0) goto L_0x0015
        L_0x0012:
            int r5 = r5 + 1
            goto L_0x0004
        L_0x0015:
            com.fasterxml.jackson.core.JsonToken r12 = r13.firstToken()
            if (r12 == 0) goto L_0x0062
            boolean r1 = r12.isScalarValue()
            if (r1 == 0) goto L_0x0062
            com.fasterxml.jackson.core.JsonParser r7 = r13.asParser(r15)
            r7.nextToken()
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r14._properties
            r1 = r1[r5]
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r8 = r1.getProperty()
            com.fasterxml.jackson.databind.JavaType r1 = r8.getType()
            r0 = r16
            java.lang.Object r11 = com.fasterxml.jackson.databind.jsontype.TypeDeserializer.deserializeIfNatural(r7, r0, r1)
            if (r11 == 0) goto L_0x0042
            r0 = r17
            r8.set(r0, r11)
            goto L_0x0012
        L_0x0042:
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r14._properties
            r1 = r1[r5]
            boolean r1 = r1.hasDefaultType()
            if (r1 != 0) goto L_0x006c
            java.lang.String r1 = "Missing external type id property '%s'"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r4 = r14._properties
            r4 = r4[r5]
            java.lang.String r4 = r4.getTypePropertyName()
            r2[r3] = r4
            r0 = r16
            r0.reportMappingException(r1, r2)
        L_0x0062:
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r1._deserializeAndSet(r2, r3, r4, r5, r6)
            goto L_0x0012
        L_0x006c:
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r14._properties
            r1 = r1[r5]
            java.lang.String r6 = r1.getDefaultTypeId()
            goto L_0x0062
        L_0x0075:
            com.fasterxml.jackson.databind.util.TokenBuffer[] r1 = r14._tokens
            r1 = r1[r5]
            if (r1 != 0) goto L_0x0062
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r14._properties
            r1 = r1[r5]
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r10 = r1.getProperty()
            boolean r1 = r10.isRequired()
            if (r1 != 0) goto L_0x0093
            com.fasterxml.jackson.databind.DeserializationFeature r1 = com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY
            r0 = r16
            boolean r1 = r0.isEnabled(r1)
            if (r1 == 0) goto L_0x00b0
        L_0x0093:
            java.lang.String r1 = "Missing property '%s' for external type id '%s'"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            java.lang.String r4 = r10.getName()
            r2[r3] = r4
            r3 = 1
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r4 = r14._properties
            r4 = r4[r5]
            java.lang.String r4 = r4.getTypePropertyName()
            r2[r3] = r4
            r0 = r16
            r0.reportMappingException(r1, r2)
        L_0x00b0:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.complete(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0018 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object complete(com.fasterxml.jackson.core.JsonParser r16, com.fasterxml.jackson.databind.DeserializationContext r17, com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer r18, com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r19) throws java.io.IOException {
        /*
            r15 = this;
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r11 = r15._properties
            int r6 = r11.length
            java.lang.Object[] r10 = new java.lang.Object[r6]
            r5 = 0
        L_0x0006:
            if (r5 >= r6) goto L_0x008d
            java.lang.String[] r11 = r15._typeIds
            r8 = r11[r5]
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r11 = r15._properties
            r4 = r11[r5]
            if (r8 != 0) goto L_0x0065
            com.fasterxml.jackson.databind.util.TokenBuffer[] r11 = r15._tokens
            r11 = r11[r5]
            if (r11 != 0) goto L_0x001b
        L_0x0018:
            int r5 = r5 + 1
            goto L_0x0006
        L_0x001b:
            boolean r11 = r4.hasDefaultType()
            if (r11 != 0) goto L_0x0060
            java.lang.String r11 = "Missing external type id property '%s'"
            r12 = 1
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r13 = 0
            java.lang.String r14 = r4.getTypePropertyName()
            r12[r13] = r14
            r0 = r17
            r0.reportMappingException(r11, r12)
        L_0x0033:
            r0 = r16
            r1 = r17
            java.lang.Object r11 = r15._deserialize(r0, r1, r5, r8)
            r10[r5] = r11
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r7 = r4.getProperty()
            int r11 = r7.getCreatorIndex()
            if (r11 < 0) goto L_0x0018
            r11 = r10[r5]
            r0 = r18
            r0.assignParameter(r7, r11)
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r9 = r4.getTypeProperty()
            if (r9 == 0) goto L_0x0018
            int r11 = r9.getCreatorIndex()
            if (r11 < 0) goto L_0x0018
            r0 = r18
            r0.assignParameter(r9, r8)
            goto L_0x0018
        L_0x0060:
            java.lang.String r8 = r4.getDefaultTypeId()
            goto L_0x0033
        L_0x0065:
            com.fasterxml.jackson.databind.util.TokenBuffer[] r11 = r15._tokens
            r11 = r11[r5]
            if (r11 != 0) goto L_0x0033
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r7 = r4.getProperty()
            java.lang.String r11 = "Missing property '%s' for external type id '%s'"
            r12 = 2
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r13 = 0
            java.lang.String r14 = r7.getName()
            r12[r13] = r14
            r13 = 1
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r14 = r15._properties
            r14 = r14[r5]
            java.lang.String r14 = r14.getTypePropertyName()
            r12[r13] = r14
            r0 = r17
            r0.reportMappingException(r11, r12)
            goto L_0x0033
        L_0x008d:
            r0 = r19
            r1 = r17
            r2 = r18
            java.lang.Object r3 = r0.build(r1, r2)
            r5 = 0
        L_0x0098:
            if (r5 >= r6) goto L_0x00b0
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r11 = r15._properties
            r11 = r11[r5]
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r7 = r11.getProperty()
            int r11 = r7.getCreatorIndex()
            if (r11 >= 0) goto L_0x00ad
            r11 = r10[r5]
            r7.set(r3, r11)
        L_0x00ad:
            int r5 = r5 + 1
            goto L_0x0098
        L_0x00b0:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.complete(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer, com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public final Object _deserialize(JsonParser p, DeserializationContext ctxt, int index, String typeId) throws IOException {
        JsonParser p2 = this._tokens[index].asParser(p);
        if (p2.nextToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        TokenBuffer merged = new TokenBuffer(p, ctxt);
        merged.writeStartArray();
        merged.writeString(typeId);
        merged.copyCurrentStructure(p2);
        merged.writeEndArray();
        JsonParser mp = merged.asParser(p);
        mp.nextToken();
        return this._properties[index].getProperty().deserialize(mp, ctxt);
    }

    /* access modifiers changed from: protected */
    public final void _deserializeAndSet(JsonParser p, DeserializationContext ctxt, Object bean, int index, String typeId) throws IOException {
        JsonParser p2 = this._tokens[index].asParser(p);
        if (p2.nextToken() == JsonToken.VALUE_NULL) {
            this._properties[index].getProperty().set(bean, null);
            return;
        }
        TokenBuffer merged = new TokenBuffer(p, ctxt);
        merged.writeStartArray();
        merged.writeString(typeId);
        merged.copyCurrentStructure(p2);
        merged.writeEndArray();
        JsonParser mp = merged.asParser(p);
        mp.nextToken();
        this._properties[index].getProperty().deserializeAndSet(mp, ctxt, bean);
    }
}
