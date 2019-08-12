package com.appboy.models;

import com.appboy.enums.inappmessage.ImageStyle;
import java.util.List;

public interface IInAppMessageImmersive extends IInAppMessage {
    ImageStyle getImageStyle();

    List<MessageButton> getMessageButtons();

    boolean logButtonClick(MessageButton messageButton);
}
