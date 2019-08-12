package com.airbnb.android.lib.tripassistant;

import com.airbnb.android.core.responses.AttachmentsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AttachmentsProvider$$Lambda$1 implements Action1 {
    private final AttachmentsProvider arg$1;

    private AttachmentsProvider$$Lambda$1(AttachmentsProvider attachmentsProvider) {
        this.arg$1 = attachmentsProvider;
    }

    public static Action1 lambdaFactory$(AttachmentsProvider attachmentsProvider) {
        return new AttachmentsProvider$$Lambda$1(attachmentsProvider);
    }

    public void call(Object obj) {
        AttachmentsProvider.lambda$new$0(this.arg$1, (AttachmentsResponse) obj);
    }
}
