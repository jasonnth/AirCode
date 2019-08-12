package p030in.uncod.android.bypass;

/* renamed from: in.uncod.android.bypass.Document */
public class Document {
    Element[] elements;

    public Document(Element[] elements2) {
        this.elements = elements2;
    }

    public int getElementCount() {
        return this.elements.length;
    }

    public Element getElement(int pos) {
        return this.elements[pos];
    }
}
