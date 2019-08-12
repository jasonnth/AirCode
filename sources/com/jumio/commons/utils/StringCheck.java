package com.jumio.commons.utils;

import java.util.Locale;

public class StringCheck {
    private static final String DELIMITER = " ";
    private String stringToCompare;
    private String trueValue;

    public StringCheck(String _trueValue, String _stringToCompare) {
        this.trueValue = trim(_trueValue);
        this.stringToCompare = _stringToCompare;
    }

    private String trim(String _trueValue) {
        String[] tokens = _trueValue.trim().split(DELIMITER);
        if (tokens.length > 1) {
            return tokens[0].trim() + DELIMITER + tokens[tokens.length - 1].trim();
        }
        return null;
    }

    public int getLevenshteinDistance() {
        if (this.trueValue == null || this.stringToCompare == null) {
            return -1;
        }
        this.trueValue = this.trueValue.toLowerCase(Locale.getDefault());
        this.stringToCompare = this.stringToCompare.toLowerCase(Locale.getDefault());
        int[] costs = new int[(this.stringToCompare.length() + 1)];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= this.trueValue.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j2 = 1; j2 <= this.stringToCompare.length(); j2++) {
                int min = Math.min(costs[j2], costs[j2 - 1]) + 1;
                if (this.trueValue.charAt(i - 1) != this.stringToCompare.charAt(j2 - 1)) {
                    nw++;
                }
                int cj = Math.min(min, nw);
                nw = costs[j2];
                costs[j2] = cj;
            }
        }
        return costs[this.stringToCompare.length()];
    }

    public boolean isSimilar() {
        if (this.trueValue == null || this.stringToCompare == null || Math.round(((float) Math.min(this.trueValue.length(), this.stringToCompare.length())) * 0.2f) < getLevenshteinDistance()) {
            return false;
        }
        return true;
    }
}
