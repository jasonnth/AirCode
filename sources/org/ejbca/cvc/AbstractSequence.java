package org.ejbca.cvc;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.ejbca.cvc.exception.ConstructionException;

public abstract class AbstractSequence extends CVCObject {
    private static final long serialVersionUID = 1;
    private final List<CVCTagEnum> allowedFields = Arrays.asList(getAllowedFields());
    private final Map<CVCTagEnum, CVCObject> subfields = new HashMap();

    /* access modifiers changed from: protected */
    public abstract CVCTagEnum[] getAllowedFields();

    AbstractSequence(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
    }

    /* access modifiers changed from: 0000 */
    public void addSubfield(CVCObject cVCObject) throws ConstructionException {
        if (cVCObject == null) {
            return;
        }
        if (!this.allowedFields.contains(cVCObject.getTag())) {
            throw new ConstructionException("Field " + cVCObject.getTag() + " not allowed in " + getClass().getName());
        } else if (this.subfields.containsKey(cVCObject.getTag())) {
            throw new ConstructionException("Field " + cVCObject.getTag() + " has already been added to " + getClass().getName());
        } else {
            cVCObject.setParent(this);
            this.subfields.put(cVCObject.getTag(), cVCObject);
        }
    }

    /* access modifiers changed from: 0000 */
    public CVCObject getSubfield(CVCTagEnum cVCTagEnum) throws NoSuchFieldException {
        CVCObject cVCObject = (CVCObject) this.subfields.get(cVCTagEnum);
        if (cVCObject != null) {
            return cVCObject;
        }
        throw new NoSuchFieldException("Could not find subfield " + cVCTagEnum);
    }

    /* access modifiers changed from: 0000 */
    public CVCObject getOptionalSubfield(CVCTagEnum cVCTagEnum) {
        return (CVCObject) this.subfields.get(cVCTagEnum);
    }

    /* access modifiers changed from: protected */
    public Collection<CVCObject> getSubfields() {
        return this.subfields.values();
    }

    public int encode(DataOutputStream dataOutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
        int i = 0;
        Iterator it = getEncodableFields().iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = ((CVCObject) it.next()).encode(dataOutputStream2) + i2;
            } else {
                dataOutputStream2.close();
                int value = getTag().getValue();
                int size = dataOutputStream.size();
                dataOutputStream.write(toByteArray(Integer.valueOf(value)));
                dataOutputStream.write(encodeLength(i2));
                dataOutputStream.write(byteArrayOutputStream.toByteArray());
                return dataOutputStream.size() - size;
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<CVCObject> getEncodableFields() {
        return getOrderedSubfields();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getDEREncoded() throws java.io.IOException {
        /*
            r3 = this;
            r2 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0018 }
            r1.<init>()     // Catch:{ all -> 0x0018 }
            java.io.DataOutputStream r0 = new java.io.DataOutputStream     // Catch:{ all -> 0x0020 }
            r0.<init>(r1)     // Catch:{ all -> 0x0020 }
            r3.encode(r0)     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0013
            r1.close()
        L_0x0013:
            byte[] r0 = r1.toByteArray()
            return r0
        L_0x0018:
            r0 = move-exception
            r1 = r2
        L_0x001a:
            if (r1 == 0) goto L_0x001f
            r1.close()
        L_0x001f:
            throw r0
        L_0x0020:
            r0 = move-exception
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.ejbca.cvc.AbstractSequence.getDEREncoded():byte[]");
    }

    public String getAsText(String str) {
        return getAsText(str, true);
    }

    public String getAsText(String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.getAsText(str, z));
        for (CVCObject cVCObject : getOrderedSubfields()) {
            stringBuffer.append(NEWLINE);
            stringBuffer.append(cVCObject.getAsText(str + "   ", z));
        }
        return stringBuffer.toString();
    }

    private List<CVCObject> getOrderedSubfields() {
        ArrayList arrayList = new ArrayList();
        for (CVCTagEnum cVCTagEnum : this.allowedFields) {
            CVCObject cVCObject = (CVCObject) this.subfields.get(cVCTagEnum);
            if (cVCObject != null) {
                arrayList.add(cVCObject);
            }
        }
        return arrayList;
    }
}
