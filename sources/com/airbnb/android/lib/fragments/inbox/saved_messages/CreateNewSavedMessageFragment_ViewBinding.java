package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;

public class CreateNewSavedMessageFragment_ViewBinding implements Unbinder {
    private CreateNewSavedMessageFragment target;

    public CreateNewSavedMessageFragment_ViewBinding(CreateNewSavedMessageFragment target2, View source) {
        this.target = target2;
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.writeMessageBodyButton = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.write_saved_message_body, "field 'writeMessageBodyButton'", TextView.class);
        target2.editTitleText = (AirEditTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_text, "field 'editTitleText'", AirEditTextView.class);
        target2.messagePreviewBubble = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.message_preview, "field 'messagePreviewBubble'", AirTextView.class);
        target2.fullLoader = Utils.findRequiredView(source, C0880R.C0882id.full_loader, "field 'fullLoader'");
    }

    public void unbind() {
        CreateNewSavedMessageFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.documentMarquee = null;
        target2.writeMessageBodyButton = null;
        target2.editTitleText = null;
        target2.messagePreviewBubble = null;
        target2.fullLoader = null;
    }
}
