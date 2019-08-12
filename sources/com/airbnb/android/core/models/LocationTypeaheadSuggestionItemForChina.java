package com.airbnb.android.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationTypeaheadSuggestionItemForChina {
    @JsonProperty("city")
    Word city;
    @JsonProperty("country")
    Word country;
    @JsonProperty("place_id")
    String placeId;
    @JsonProperty("p")
    int rank;
    @JsonProperty("state")
    Word state;

    private static class Word {
        String acronym;
        @JsonProperty("zh")
        String chinese;
        @JsonProperty("en")
        String english;
        boolean isInitialized;
        @JsonProperty("py")
        String pinyin;

        private Word() {
        }

        /* access modifiers changed from: 0000 */
        public boolean matchesQuery(String queryInLowerCase) {
            if (!this.isInitialized) {
                init();
            }
            if (this.english != null) {
                return this.english.toLowerCase().startsWith(queryInLowerCase);
            }
            return this.chinese.toLowerCase().startsWith(queryInLowerCase) || this.pinyin.toLowerCase().startsWith(queryInLowerCase) || this.acronym.toLowerCase().startsWith(queryInLowerCase);
        }

        /* access modifiers changed from: 0000 */
        public String getText() {
            if (this.english != null) {
                return this.english;
            }
            return this.chinese;
        }

        private void init() {
            this.isInitialized = true;
            if (this.chinese != null) {
                String[] pinyinWords = this.pinyin.split(" ");
                StringBuilder pinyinBuilder = new StringBuilder();
                StringBuilder acronymBuilder = new StringBuilder(pinyinWords.length);
                for (String word : pinyinWords) {
                    pinyinBuilder.append(word);
                    acronymBuilder.append(word.substring(0, 1));
                }
                this.pinyin = pinyinBuilder.toString();
                this.acronym = acronymBuilder.toString();
            }
        }
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public boolean matchesQuery(String queryInLowerCase) {
        return this.city.matchesQuery(queryInLowerCase) || (this.state != null && this.state.matchesQuery(queryInLowerCase)) || (this.country != null && this.country.matchesQuery(queryInLowerCase));
    }

    public String getTitle() {
        return this.city.getText();
    }

    public String getDescription() {
        if (this.state == null && this.country == null) {
            return null;
        }
        if (this.state == null || this.country == null) {
            return this.state != null ? this.state.getText() : this.country.getText();
        }
        return this.state.getText() + ", " + this.country.getText();
    }

    public String getTextForSearch() {
        StringBuilder stringBuilder = new StringBuilder(this.city.getText());
        if (this.state != null) {
            stringBuilder.append(", " + this.state.getText());
        }
        if (this.country != null) {
            stringBuilder.append(", " + this.country.getText());
        }
        return stringBuilder.toString();
    }

    public int getRank() {
        return this.rank;
    }

    public boolean shouldShowInChineseLocale() {
        if (this.country == null || !"China".equals(this.country.english)) {
            return true;
        }
        return false;
    }
}
