package com.airbnb.android.core.enums;

public enum FlagContent {
    User("User", "user_id"),
    Post("Question2Post", "thread_id"),
    Review("Review", "review_id"),
    Listing("Hosting", "listing_id"),
    Story("ContentFramework::Article", "content_framework_article_id");
    
    private final String serverIdKey;
    private final String serverKey;

    private FlagContent(String serverKey2, String serverIdKey2) {
        this.serverKey = serverKey2;
        this.serverIdKey = serverIdKey2;
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public String getServerIdKey() {
        return this.serverIdKey;
    }
}
