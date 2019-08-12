package com.airbnb.p027n2;

import com.airbnb.p027n2.components.TagsCollectionRow;

/* renamed from: com.airbnb.n2.TagsCollectionRowMockAdapter */
public final class TagsCollectionRowMockAdapter implements DLSMockAdapter<TagsCollectionRow> {
    public int getItemCount() {
        return 5;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
            case 4:
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
            case 2:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 4:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(TagsCollectionRow view, int position) {
        switch (position) {
            case 0:
                TagsCollectionRow.mockOneTag(view);
                return;
            case 1:
                TagsCollectionRow.mockTwoTags(view);
                return;
            case 2:
                TagsCollectionRow.mockListOfTags(view);
                return;
            case 3:
                TagsCollectionRow.mockListOfTagsWithoutImage(view);
                return;
            case 4:
                TagsCollectionRow.mockListOfTagsWithLongText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}
