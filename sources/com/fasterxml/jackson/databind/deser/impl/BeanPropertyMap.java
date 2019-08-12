package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BeanPropertyMap implements Serializable, Iterable<SettableBeanProperty> {
    protected final boolean _caseInsensitive;
    private Object[] _hashArea;
    private int _hashMask;
    private SettableBeanProperty[] _propsInOrder;
    private int _size;
    private int _spillCount;

    public BeanPropertyMap(boolean caseInsensitive, Collection<SettableBeanProperty> props) {
        this._caseInsensitive = caseInsensitive;
        this._propsInOrder = (SettableBeanProperty[]) props.toArray(new SettableBeanProperty[props.size()]);
        init(props);
    }

    protected BeanPropertyMap(BeanPropertyMap base, boolean caseInsensitive) {
        this._caseInsensitive = caseInsensitive;
        this._propsInOrder = (SettableBeanProperty[]) Arrays.copyOf(base._propsInOrder, base._propsInOrder.length);
        init(Arrays.asList(this._propsInOrder));
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public BeanPropertyMap withCaseInsensitivity(boolean state) {
        return this._caseInsensitive == state ? this : new BeanPropertyMap(this, state);
    }

    /* access modifiers changed from: protected */
    public void init(Collection<SettableBeanProperty> props) {
        this._size = props.size();
        int hashSize = findSize(this._size);
        this._hashMask = hashSize - 1;
        Object[] hashed = new Object[(((hashSize >> 1) + hashSize) * 2)];
        int spillCount = 0;
        for (SettableBeanProperty prop : props) {
            if (prop != null) {
                String key = getPropertyName(prop);
                int slot = _hashCode(key);
                int ix = slot << 1;
                if (hashed[ix] != null) {
                    ix = ((slot >> 1) + hashSize) << 1;
                    if (hashed[ix] != null) {
                        ix = (((hashSize >> 1) + hashSize) << 1) + spillCount;
                        spillCount += 2;
                        if (ix >= hashed.length) {
                            hashed = Arrays.copyOf(hashed, hashed.length + 4);
                        }
                    }
                }
                hashed[ix] = key;
                hashed[ix + 1] = prop;
            }
        }
        this._hashArea = hashed;
        this._spillCount = spillCount;
    }

    private static final int findSize(int size) {
        if (size <= 5) {
            return 8;
        }
        if (size <= 12) {
            return 16;
        }
        int result = 32;
        while (result < size + (size >> 2)) {
            result += result;
        }
        return result;
    }

    public static BeanPropertyMap construct(Collection<SettableBeanProperty> props, boolean caseInsensitive) {
        return new BeanPropertyMap(caseInsensitive, props);
    }

    public BeanPropertyMap withProperty(SettableBeanProperty newProp) {
        String key = getPropertyName(newProp);
        int i = 1;
        int end = this._hashArea.length;
        while (true) {
            if (i < end) {
                SettableBeanProperty prop = (SettableBeanProperty) this._hashArea[i];
                if (prop != null && prop.getName().equals(key)) {
                    this._hashArea[i] = newProp;
                    this._propsInOrder[_findFromOrdered(prop)] = newProp;
                    break;
                }
                i += 2;
            } else {
                int slot = _hashCode(key);
                int hashSize = this._hashMask + 1;
                int ix = slot << 1;
                if (this._hashArea[ix] != null) {
                    ix = ((slot >> 1) + hashSize) << 1;
                    if (this._hashArea[ix] != null) {
                        ix = (((hashSize >> 1) + hashSize) << 1) + this._spillCount;
                        this._spillCount += 2;
                        if (ix >= this._hashArea.length) {
                            this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length + 4);
                        }
                    }
                }
                this._hashArea[ix] = key;
                this._hashArea[ix + 1] = newProp;
                int last = this._propsInOrder.length;
                this._propsInOrder = (SettableBeanProperty[]) Arrays.copyOf(this._propsInOrder, last + 1);
                this._propsInOrder[last] = newProp;
            }
        }
        return this;
    }

    public BeanPropertyMap assignIndexes() {
        int index;
        int i = 1;
        int end = this._hashArea.length;
        int index2 = 0;
        while (i < end) {
            SettableBeanProperty prop = (SettableBeanProperty) this._hashArea[i];
            if (prop != null) {
                index = index2 + 1;
                prop.assignIndex(index2);
            } else {
                index = index2;
            }
            i += 2;
            index2 = index;
        }
        return this;
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    public BeanPropertyMap renameAll(NameTransformer transformer) {
        if (transformer == null || transformer == NameTransformer.NOP) {
            return this;
        }
        ArrayList<SettableBeanProperty> newProps = new ArrayList<>(len);
        for (SettableBeanProperty prop : this._propsInOrder) {
            if (prop == null) {
                newProps.add(prop);
            } else {
                newProps.add(_rename(prop, transformer));
            }
        }
        return new BeanPropertyMap(this._caseInsensitive, (Collection<SettableBeanProperty>) newProps);
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    public BeanPropertyMap withoutProperties(Collection<String> toExclude) {
        if (toExclude.isEmpty()) {
            return this;
        }
        ArrayList<SettableBeanProperty> newProps = new ArrayList<>(len);
        for (SettableBeanProperty prop : this._propsInOrder) {
            if (prop != null && !toExclude.contains(prop.getName())) {
                newProps.add(prop);
            }
        }
        return new BeanPropertyMap(this._caseInsensitive, (Collection<SettableBeanProperty>) newProps);
    }

    public void replace(SettableBeanProperty newProp) {
        String key = getPropertyName(newProp);
        int ix = _findIndexInHash(key);
        if (ix >= 0) {
            SettableBeanProperty prop = (SettableBeanProperty) this._hashArea[ix];
            this._hashArea[ix] = newProp;
            this._propsInOrder[_findFromOrdered(prop)] = newProp;
            return;
        }
        throw new NoSuchElementException("No entry '" + key + "' found, can't replace");
    }

    private List<SettableBeanProperty> properties() {
        ArrayList<SettableBeanProperty> p = new ArrayList<>(this._size);
        int end = this._hashArea.length;
        for (int i = 1; i < end; i += 2) {
            SettableBeanProperty prop = (SettableBeanProperty) this._hashArea[i];
            if (prop != null) {
                p.add(prop);
            }
        }
        return p;
    }

    public Iterator<SettableBeanProperty> iterator() {
        return properties().iterator();
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        return this._propsInOrder;
    }

    /* access modifiers changed from: protected */
    public final String getPropertyName(SettableBeanProperty prop) {
        return this._caseInsensitive ? prop.getName().toLowerCase() : prop.getName();
    }

    public SettableBeanProperty find(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Can not pass null property name");
        }
        if (this._caseInsensitive) {
            key = key.toLowerCase();
        }
        int slot = key.hashCode() & this._hashMask;
        int ix = slot << 1;
        Object match = this._hashArea[ix];
        if (match == key || key.equals(match)) {
            return (SettableBeanProperty) this._hashArea[ix + 1];
        }
        return _find2(key, slot, match);
    }

    private final SettableBeanProperty _find2(String key, int slot, Object match) {
        if (match == null) {
            return null;
        }
        int hashSize = this._hashMask + 1;
        int ix = ((slot >> 1) + hashSize) << 1;
        Object match2 = this._hashArea[ix];
        if (key.equals(match2)) {
            return (SettableBeanProperty) this._hashArea[ix + 1];
        }
        if (match2 == null) {
            return null;
        }
        int i = ((hashSize >> 1) + hashSize) << 1;
        int end = i + this._spillCount;
        while (i < end) {
            Object match3 = this._hashArea[i];
            if (match3 == key || key.equals(match3)) {
                return (SettableBeanProperty) this._hashArea[i + 1];
            }
            i += 2;
        }
        return null;
    }

    public int size() {
        return this._size;
    }

    public void remove(SettableBeanProperty propToRm) {
        ArrayList<SettableBeanProperty> props = new ArrayList<>(this._size);
        String key = getPropertyName(propToRm);
        boolean found = false;
        int end = this._hashArea.length;
        for (int i = 1; i < end; i += 2) {
            SettableBeanProperty prop = (SettableBeanProperty) this._hashArea[i];
            if (prop != null) {
                if (!found) {
                    found = key.equals(this._hashArea[i - 1]);
                    if (found) {
                        this._propsInOrder[_findFromOrdered(prop)] = null;
                    }
                }
                props.add(prop);
            }
        }
        if (!found) {
            throw new NoSuchElementException("No entry '" + propToRm.getName() + "' found, can't remove");
        }
        init(props);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties=[");
        int count = 0;
        Iterator<SettableBeanProperty> it = iterator();
        while (it.hasNext()) {
            SettableBeanProperty prop = (SettableBeanProperty) it.next();
            int count2 = count + 1;
            if (count > 0) {
                sb.append(", ");
            }
            sb.append(prop.getName());
            sb.append('(');
            sb.append(prop.getType());
            sb.append(')');
            count = count2;
        }
        sb.append(']');
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty _rename(SettableBeanProperty prop, NameTransformer xf) {
        if (prop == null) {
            return prop;
        }
        SettableBeanProperty prop2 = prop.withSimpleName(xf.transform(prop.getName()));
        JsonDeserializer<Object> deser = prop2.getValueDeserializer();
        if (deser != null) {
            JsonDeserializer<Object> newDeser = deser.unwrappingDeserializer(xf);
            if (newDeser != deser) {
                prop2 = prop2.withValueDeserializer(newDeser);
            }
        }
        return prop2;
    }

    private final int _findIndexInHash(String key) {
        int slot = _hashCode(key);
        int ix = slot << 1;
        if (key.equals(this._hashArea[ix])) {
            return ix + 1;
        }
        int hashSize = this._hashMask + 1;
        int ix2 = ((slot >> 1) + hashSize) << 1;
        if (key.equals(this._hashArea[ix2])) {
            return ix2 + 1;
        }
        int i = ((hashSize >> 1) + hashSize) << 1;
        int end = i + this._spillCount;
        while (i < end) {
            if (key.equals(this._hashArea[i])) {
                return i + 1;
            }
            i += 2;
        }
        return -1;
    }

    private final int _findFromOrdered(SettableBeanProperty prop) {
        int end = this._propsInOrder.length;
        for (int i = 0; i < end; i++) {
            if (this._propsInOrder[i] == prop) {
                return i;
            }
        }
        throw new IllegalStateException("Illegal state: property '" + prop.getName() + "' missing from _propsInOrder");
    }

    private final int _hashCode(String key) {
        return key.hashCode() & this._hashMask;
    }
}
