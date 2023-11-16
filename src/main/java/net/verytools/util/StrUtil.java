package net.verytools.util;

import net.verytools.util.model.SplitResult;

public class StrUtil {

    /**
     * Split without worrying about OutOfIndexException.
     *
     * @param str       string to split
     * @param delimiter the delimiter that separates each element
     * @return instance of SplitResult
     */
    public static SplitResult split(String str, String delimiter) {
        return split(str, delimiter, false);
    }

    public static SplitResult split(String str, String delimiter, boolean skipBlank) {
        if (str == null) {
            return new SplitResult(new String[0], skipBlank);
        }
        return new SplitResult(str.split(delimiter), skipBlank);
    }

    public static boolean isBlank(final CharSequence str) {
        if (str == null || (str.length()) == 0) {
            return true;
        }
        for (int i = 0, n = str.length(); i < n; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

}
