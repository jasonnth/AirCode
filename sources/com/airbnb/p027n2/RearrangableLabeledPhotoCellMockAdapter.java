package com.airbnb.p027n2;

import com.airbnb.p027n2.components.photorearranger.RearrangableLabeledPhotoCell;

/* renamed from: com.airbnb.n2.RearrangableLabeledPhotoCellMockAdapter */
public final class RearrangableLabeledPhotoCellMockAdapter implements DLSMockAdapter<RearrangableLabeledPhotoCell> {
    public int getItemCount() {
        return 2;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 1:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(RearrangableLabeledPhotoCell view, int position) {
        switch (position) {
            case 0:
                RearrangableLabeledPhotoCell.mock(view);
                return;
            case 1:
                RearrangableLabeledPhotoCell.mockWithBackupText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
