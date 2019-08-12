package com.mparticle.commerce;

public class Promotion {
    public static final String CLICK = "click";
    public static final String VIEW = "view";
    private String mCreative = null;
    private String mId = null;
    private String mName = null;
    private String mPosition = null;

    public Promotion() {
    }

    public Promotion(Promotion promotion) {
        if (promotion != null) {
            this.mCreative = promotion.getCreative();
            this.mId = promotion.getId();
            this.mName = promotion.getName();
            this.mPosition = promotion.getPosition();
        }
    }

    public String getCreative() {
        return this.mCreative;
    }

    public Promotion setCreative(String creative) {
        this.mCreative = creative;
        return this;
    }

    public String getId() {
        return this.mId;
    }

    public Promotion setId(String id) {
        this.mId = id;
        return this;
    }

    public String getName() {
        return this.mName;
    }

    public Promotion setName(String name) {
        this.mName = name;
        return this;
    }

    public String getPosition() {
        return this.mPosition;
    }

    public Promotion setPosition(String position) {
        this.mPosition = position;
        return this;
    }
}
