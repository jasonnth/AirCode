package org.apache.sanselan.formats.tiff.constants;

public class TagConstantsUtils implements TiffDirectoryConstants {
    public static TagInfo[] mergeTagLists(TagInfo[][] lists) {
        int count = 0;
        for (TagInfo[] length : lists) {
            count += length.length;
        }
        TagInfo[] result = new TagInfo[count];
        int index = 0;
        for (int i = 0; i < lists.length; i++) {
            System.arraycopy(lists[i], 0, result, index, lists[i].length);
            index += lists[i].length;
        }
        return result;
    }
}
