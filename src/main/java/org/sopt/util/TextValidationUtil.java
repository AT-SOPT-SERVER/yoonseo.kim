package org.sopt.util;

public class TextValidationUtil {
    public static int countEmojiRecognizableCharacters(String title) {
        return title.codePointCount(0, title.length());
    }
}