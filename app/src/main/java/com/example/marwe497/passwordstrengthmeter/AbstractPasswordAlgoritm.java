package com.example.marwe497.passwordstrengthmeter;

public abstract class AbstractPasswordAlgoritm {
    /**
     * Returns the strength of the passord
     * @param password
     * the password
     * @return
     * the strength of the password
     */
    public abstract int getStrength(String password);

    public static final int MAXVALUE= 100;
    public static final int MINVALUE = 0;
}
