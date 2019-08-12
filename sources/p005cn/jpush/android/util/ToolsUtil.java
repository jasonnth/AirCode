package p005cn.jpush.android.util;

import android.graphics.Rect;

/* renamed from: cn.jpush.android.util.ToolsUtil */
public class ToolsUtil {
    public static final int DIRECTION_CENTER = 0;
    public static final int DIRECTION_DOWN = 7;
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_LEFT_DOWN = 8;
    public static final int DIRECTION_LEFT_UP = 2;
    public static final int DIRECTION_RIGHT = 5;
    public static final int DIRECTION_RIGHT_DOWN = 6;
    public static final int DIRECTION_RIGHT_UP = 4;
    public static final int DIRECTION_UP = 3;

    public static Rect createRect(int positionX, int positionY, int width, int height) {
        return new Rect(positionX - (width / 2), positionY - (height / 2), positionX + (width / 2), positionY + (height / 2));
    }

    public static Rect getImageRect(int imageIndex, int bigImgWidth, int bigImgHeight, int smalImgWidth, int smalImgHeight, int widthOffset, int heightOffset) {
        if (imageIndex == 0) {
            return new Rect(0, 0, smalImgWidth, smalImgHeight);
        }
        int countX = bigImgWidth / smalImgWidth;
        int countY = bigImgHeight / smalImgHeight;
        if (imageIndex > countX * countY) {
            imageIndex -= ((countX * countY) * (imageIndex / (countX * countY))) + 1;
        }
        int linkX = 1;
        int linkY = 1;
        int index = 1;
        boolean isFound = false;
        for (int i = 1; i <= countY; i++) {
            linkY = i;
            int j = 1;
            while (true) {
                if (j > countX) {
                    break;
                }
                linkX = j;
                if (imageIndex == index) {
                    isFound = true;
                    break;
                }
                index++;
                j++;
            }
            if (isFound) {
                break;
            }
        }
        return new Rect(((linkX - 1) * smalImgWidth) + widthOffset, ((linkY - 1) * smalImgHeight) + heightOffset, (linkX * smalImgWidth) + widthOffset, (linkY * smalImgHeight) + heightOffset);
    }

    public static Rect getImageRect(int imageIndexX, int imageIndexY, int bigImgWidth, int bigImgHeight, int smalImgWidth, int smalImgHeight, int widthOffset, int heightOffset) {
        int countX = bigImgWidth / smalImgWidth;
        int countY = bigImgHeight / smalImgHeight;
        if (imageIndexX > countX) {
            imageIndexX -= countX * (imageIndexX / countX);
            if (imageIndexX == 0) {
                imageIndexX = countX;
            }
        }
        if (imageIndexX == 0) {
            imageIndexX = 1;
        }
        if (imageIndexY > countY || imageIndexY == 0) {
            imageIndexY = 1;
        }
        return new Rect(((imageIndexX - 1) * smalImgWidth) + widthOffset, ((imageIndexY - 1) * smalImgHeight) + heightOffset, (imageIndexX * smalImgWidth) + widthOffset, (imageIndexY * smalImgHeight) + heightOffset);
    }

    public static int getMoveDirection(int x, int y) {
        if (y == 0 && x == y) {
            return 0;
        }
        if (y != 0 || x == 0) {
            if (x != 0 || y == 0) {
                if (x < 0 && y < 0) {
                    return 2;
                }
                if (x > 0 && y < 0) {
                    return 4;
                }
                if (x > 0 && y > 0) {
                    return 6;
                }
                if (x >= 0 || y <= 0) {
                    return 0;
                }
                return 8;
            } else if (y > 0) {
                return 7;
            } else {
                return 3;
            }
        } else if (x > 0) {
            return 5;
        } else {
            return 1;
        }
    }
}
