package com.example.herocomics.enumerations;

public enum Role {
    LEADER, COEQUIPIER, SUPPORT;

    public static boolean isValidRole(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }
}
