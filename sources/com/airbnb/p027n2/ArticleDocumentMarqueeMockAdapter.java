package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ArticleDocumentMarquee;

/* renamed from: com.airbnb.n2.ArticleDocumentMarqueeMockAdapter */
public final class ArticleDocumentMarqueeMockAdapter implements DLSMockAdapter<ArticleDocumentMarquee> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "ArticleDocumentMarquee";
            case 1:
                return "ArticleDocumentMarquee without kicker";
            case 2:
                return "ArticleDocumentMarquee without caption";
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
            default:
                return null;
        }
    }

    public void bindView(ArticleDocumentMarquee view, int position) {
        switch (position) {
            case 0:
                ArticleDocumentMarquee.mock(view);
                return;
            case 1:
                ArticleDocumentMarquee.mockWithoutKicker(view);
                return;
            case 2:
                ArticleDocumentMarquee.mockWithoutCaption(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}
