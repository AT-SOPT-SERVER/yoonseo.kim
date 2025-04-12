package org.sopt.util;

public class IdGenerator {
    private static int currentId = 1;

    public static int generatePostId() {
        return currentId++;
    }
}