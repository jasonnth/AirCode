package com.airbnb.android.contentframework;

public final class ArticleReadPercentageHelper {
    private final long articleId;
    private boolean fiftyTracked = false;
    private boolean oneHundredTracked = false;
    private boolean seventyFiveTracked = false;
    private final String templateType;
    private boolean twentyFiveTracked = false;

    public ArticleReadPercentageHelper(long articleId2, String templateType2) {
        this.articleId = articleId2;
        this.templateType = templateType2;
    }

    public void handlePercentage(double percentageViewed, long elapsedTimeSinceImpression) {
        if (percentageViewed > 0.2d) {
            trackTwentyFive(elapsedTimeSinceImpression);
        }
        if (percentageViewed > 0.45d) {
            trackFifty(elapsedTimeSinceImpression);
        }
        if (percentageViewed > 0.7d) {
            trackSeventyFive(elapsedTimeSinceImpression);
        }
        if (percentageViewed > 0.9d) {
            trackOneHundred(elapsedTimeSinceImpression);
        }
    }

    private void trackTwentyFive(long elapsedTimeSinceImpression) {
        if (!this.twentyFiveTracked) {
            this.twentyFiveTracked = true;
            track(25, elapsedTimeSinceImpression);
        }
    }

    private void trackFifty(long elapsedTimeSinceImpression) {
        if (!this.fiftyTracked) {
            this.fiftyTracked = true;
            track(50, elapsedTimeSinceImpression);
        }
    }

    private void trackSeventyFive(long elapsedTimeSinceImpression) {
        if (!this.seventyFiveTracked) {
            this.seventyFiveTracked = true;
            track(75, elapsedTimeSinceImpression);
        }
    }

    private void trackOneHundred(long elapsedTimeSinceImpression) {
        if (!this.oneHundredTracked) {
            this.oneHundredTracked = true;
            track(100, elapsedTimeSinceImpression);
        }
    }

    private void track(int viewedPercentage, long elapsedTimeSinceImpression) {
        ContentFrameworkAnalytics.trackArticleReadProgress(this.articleId, viewedPercentage, elapsedTimeSinceImpression, this.templateType);
    }
}
