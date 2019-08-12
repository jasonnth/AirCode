package jumio.p317nv.nfc;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Hashtable;

/* renamed from: jumio.nv.nfc.br */
/* compiled from: ModuleSpec */
public class C5013br implements Cloneable {

    /* renamed from: b */
    protected int f5143b;

    /* renamed from: c */
    protected int f5144c = 0;

    /* renamed from: d */
    protected int f5145d = 0;

    /* renamed from: e */
    protected byte[][] f5146e;

    /* renamed from: f */
    protected Object f5147f = null;

    /* renamed from: g */
    protected Object[] f5148g = null;

    /* renamed from: h */
    protected Object[] f5149h = null;

    /* renamed from: i */
    protected Hashtable f5150i;

    /* access modifiers changed from: protected */
    public Object clone() {
        try {
            C5013br brVar = (C5013br) super.clone();
            brVar.f5146e = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.f5144c, this.f5145d});
            for (int i = 0; i < this.f5144c; i++) {
                for (int i2 = 0; i2 < this.f5145d; i2++) {
                    brVar.f5146e[i][i2] = this.f5146e[i][i2];
                }
            }
            if (this.f5149h != null) {
                brVar.f5149h = new Object[this.f5144c];
                for (int i3 = 0; i3 < this.f5144c; i3++) {
                    brVar.f5149h[i3] = this.f5149h[i3];
                }
            }
            if (this.f5150i != null) {
                brVar.f5150i = new Hashtable();
                Enumeration keys = this.f5150i.keys();
                while (keys.hasMoreElements()) {
                    String str = (String) keys.nextElement();
                    brVar.f5150i.put(str, this.f5150i.get(str));
                }
            }
            return brVar;
        } catch (CloneNotSupportedException e) {
            throw new Error("Error when cloning ModuleSpec instance");
        }
    }

    public C5013br(int i, int i2, byte b) {
        this.f5144c = i;
        this.f5145d = i2;
        this.f5146e = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{i, i2});
        switch (b) {
            case 0:
                this.f5143b = 0;
                return;
            case 1:
                this.f5143b = 1;
                return;
            case 2:
                this.f5143b = 2;
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void mo46973a(Object obj) {
        this.f5147f = obj;
    }

    /* renamed from: b */
    public Object mo46974b() {
        return this.f5147f;
    }

    /* renamed from: a */
    public void mo46972a(int i, Object obj) {
        if (this.f5143b == 1) {
            throw new Error("Option whose value is '" + obj + "' cannot be " + "specified for components as it is a 'tile only' specific " + "option");
        }
        if (this.f5148g == null) {
            this.f5148g = new Object[this.f5145d];
        }
        for (int i2 = 0; i2 < this.f5144c; i2++) {
            if (this.f5146e[i2][i] < 1) {
                this.f5146e[i2][i] = 1;
            }
        }
        this.f5148g[i] = obj;
    }

    /* renamed from: c */
    public Object mo46977c(int i) {
        if (this.f5143b == 1) {
            throw new Error("Illegal use of ModuleSpec class");
        } else if (this.f5148g == null || this.f5148g[i] == null) {
            return mo46974b();
        } else {
            return this.f5148g[i];
        }
    }

    /* renamed from: b */
    public void mo46976b(int i, Object obj) {
        if (this.f5143b == 0) {
            throw new Error("Option whose value is '" + obj + "' cannot be " + "specified for tiles as it is a 'component only' specific " + "option");
        }
        if (this.f5149h == null) {
            this.f5149h = new Object[this.f5144c];
        }
        for (int i2 = 0; i2 < this.f5145d; i2++) {
            if (this.f5146e[i][i2] < 2) {
                this.f5146e[i][i2] = 2;
            }
        }
        this.f5149h[i] = obj;
    }

    /* renamed from: d */
    public Object mo46979d(int i) {
        if (this.f5143b == 0) {
            throw new Error("Illegal use of ModuleSpec class");
        } else if (this.f5149h == null || this.f5149h[i] == null) {
            return mo46974b();
        } else {
            return this.f5149h[i];
        }
    }

    /* renamed from: a */
    public void mo46971a(int i, int i2, Object obj) {
        if (this.f5143b != 2) {
            String str = "Option whose value is '" + obj + "' cannot be " + "specified for ";
            switch (this.f5143b) {
                case 0:
                    str = str + "tiles as it is a 'component only' specific option";
                    break;
                case 1:
                    str = str + "components as it is a 'tile only' specific option";
                    break;
            }
            throw new Error(str);
        }
        if (this.f5150i == null) {
            this.f5150i = new Hashtable();
        }
        this.f5146e[i][i2] = 3;
        this.f5150i.put("t" + i + "c" + i2, obj);
    }

    /* renamed from: a */
    public Object mo46970a(int i, int i2) {
        if (this.f5143b == 2) {
            return mo46975b(i, i2);
        }
        throw new Error("Illegal use of ModuleSpec class");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Object mo46975b(int i, int i2) {
        switch (this.f5146e[i][i2]) {
            case 0:
                return mo46974b();
            case 1:
                return mo46977c(i2);
            case 2:
                return mo46979d(i);
            case 3:
                return this.f5150i.get("t" + i + "c" + i2);
            default:
                throw new IllegalArgumentException("Not recognized spec type");
        }
    }
}
