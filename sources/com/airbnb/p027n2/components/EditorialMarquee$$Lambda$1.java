package com.airbnb.p027n2.components;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;

/* renamed from: com.airbnb.n2.components.EditorialMarquee$$Lambda$1 */
final /* synthetic */ class EditorialMarquee$$Lambda$1 implements OnPreparedListener {
    private final EditorialMarquee arg$1;
    private final int arg$2;

    private EditorialMarquee$$Lambda$1(EditorialMarquee editorialMarquee, int i) {
        this.arg$1 = editorialMarquee;
        this.arg$2 = i;
    }

    public static OnPreparedListener lambdaFactory$(EditorialMarquee editorialMarquee, int i) {
        return new EditorialMarquee$$Lambda$1(editorialMarquee, i);
    }

    public void onPrepared() {
        EditorialMarquee.lambda$setVideoUrlWithPosition$0(this.arg$1, this.arg$2);
    }
}
