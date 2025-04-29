package org.rajesh.jpaspecification.service;

public class UserAuditorContext {
    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

    public static String setCurrentUser(String userId) {
        currentUser.set(userId);
        return String.valueOf(userId);
    }

    public static String getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
