package com.example.herocomics.enumerations;

public enum Status {
    ACTIF, COMPLETE, ABANDONNEE, ECHOUE;


    public static boolean isValidStatus(String status) {
        for (Status s : Status.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
