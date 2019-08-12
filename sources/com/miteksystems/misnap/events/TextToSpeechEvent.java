package com.miteksystems.misnap.events;

public class TextToSpeechEvent {
    public static final int USE_STRING_INSTEAD_OF_ID = -1;
    public final int delayMs;
    public final int spokenTextId;
    public final String spokenTextString;

    public TextToSpeechEvent(int spokenTextId2) {
        this(spokenTextId2, 0);
    }

    public TextToSpeechEvent(String spokenTextString2) {
        this(spokenTextString2, 0);
    }

    public TextToSpeechEvent(int spokenTextId2, int delayMs2) {
        this(spokenTextId2, "", delayMs2);
    }

    public TextToSpeechEvent(String spokenTextString2, int delayMs2) {
        this(-1, spokenTextString2, delayMs2);
    }

    private TextToSpeechEvent(int spokenTextId2, String spokenTextString2, int delayMs2) {
        this.spokenTextId = spokenTextId2;
        this.spokenTextString = spokenTextString2;
        this.delayMs = delayMs2;
    }
}
