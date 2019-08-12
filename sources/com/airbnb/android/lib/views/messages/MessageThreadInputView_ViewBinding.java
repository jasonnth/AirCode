package com.airbnb.android.lib.views.messages;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.MessageInputOneRow;
import com.airbnb.p027n2.components.MessageInputTwoRows;

public class MessageThreadInputView_ViewBinding implements Unbinder {
    private MessageThreadInputView target;

    public MessageThreadInputView_ViewBinding(MessageThreadInputView target2) {
        this(target2, target2);
    }

    public MessageThreadInputView_ViewBinding(MessageThreadInputView target2, View source) {
        this.target = target2;
        target2.oneRowView = (MessageInputOneRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.message_input_one_row, "field 'oneRowView'", MessageInputOneRow.class);
        target2.twoRowsView = (MessageInputTwoRows) Utils.findRequiredViewAsType(source, C0880R.C0882id.message_input_two_rows, "field 'twoRowsView'", MessageInputTwoRows.class);
    }

    public void unbind() {
        MessageThreadInputView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.oneRowView = null;
        target2.twoRowsView = null;
    }
}
