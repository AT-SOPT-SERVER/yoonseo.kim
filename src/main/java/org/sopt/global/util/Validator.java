package org.sopt.global.util;

import org.sopt.global.common.exception.CustomException;
import org.sopt.global.common.exception.ErrorCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
    private Validator() {}

    private static final int MAX_TITLE_LENGTH = 30;

    private static final Pattern GRAPHEME_CLUSTER = Pattern.compile(
    "(?:(?:\\P{M}\\p{M}*)|(?:\\p{M}+))(?:\\u200D(?:(?:\\P{M}\\p{M}*)|(?:\\p{M}+)))*"
    );

    public static void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_POST_TITLE);
        }
        int length = countGraphemeClusters(title.trim());
        if (length > MAX_TITLE_LENGTH) {
            throw new CustomException(ErrorCode.INVALID_POST_TITLE_LENGTH);
        }
    }

    private static int countGraphemeClusters(String text) {
        Matcher matcher = GRAPHEME_CLUSTER.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
