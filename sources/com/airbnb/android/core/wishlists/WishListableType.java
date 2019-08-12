package com.airbnb.android.core.wishlists;

public enum WishListableType {
    Place("place"),
    Trip("trip"),
    Home("home"),
    PlaceActivity("activity"),
    StoryArticle("article");
    
    private final String rnKey;

    private WishListableType(String rnKey2) {
        this.rnKey = rnKey2;
    }

    public String getRnKey() {
        return this.rnKey;
    }
}
