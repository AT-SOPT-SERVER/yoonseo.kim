package org.sopt.util;

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
            throw new IllegalArgumentException("제목이 비어 있습니다.");
        }
        int length = countGraphemeClusters(title.trim());
        if (length > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException("제목은 최대 " + MAX_TITLE_LENGTH + "자까지 가능합니다.");
        }
    }

    private static int countGraphemeClusters(String text) {
        Matcher matcher = GRAPHEME_CLUSTER.matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }
}
