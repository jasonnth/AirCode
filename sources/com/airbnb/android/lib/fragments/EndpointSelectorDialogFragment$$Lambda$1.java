package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.List;

final /* synthetic */ class EndpointSelectorDialogFragment$$Lambda$1 implements OnClickListener {
    private final EndpointSelectorDialogFragment arg$1;
    private final List arg$2;

    private EndpointSelectorDialogFragment$$Lambda$1(EndpointSelectorDialogFragment endpointSelectorDialogFragment, List list) {
        this.arg$1 = endpointSelectorDialogFragment;
        this.arg$2 = list;
    }

    public static OnClickListener lambdaFactory$(EndpointSelectorDialogFragment endpointSelectorDialogFragment, List list) {
        return new EndpointSelectorDialogFragment$$Lambda$1(endpointSelectorDialogFragment, list);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        EndpointSelectorDialogFragment.lambda$onCreateDialog$0(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
