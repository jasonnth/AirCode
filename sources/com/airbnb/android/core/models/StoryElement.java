package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.models.generated.GenStoryElement;

public class StoryElement extends GenStoryElement {
    public static final Creator<StoryElement> CREATOR = new Creator<StoryElement>() {
        public StoryElement[] newArray(int size) {
            return new StoryElement[size];
        }

        public StoryElement createFromParcel(Parcel source) {
            StoryElement object = new StoryElement();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum Type {
        SectionHeader("section_header"),
        Text("text"),
        Image(ContentFrameworkAnalytics.IMAGE),
        ProductLink(ContentFrameworkAnalytics.PRODUCT_LINK);
        
        private final String serverKey;

        private Type(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public String getServerKey() {
            return this.serverKey;
        }

        public static Type forKey(String typeString) {
            Type[] values;
            for (Type type : values()) {
                if (type.serverKey.equalsIgnoreCase(typeString)) {
                    return type;
                }
            }
            return null;
        }
    }

    public Type getType() {
        return Type.forKey(getTypeString());
    }

    public boolean hasSubElement() {
        return getSubElements() != null && !getSubElements().isEmpty();
    }
}
