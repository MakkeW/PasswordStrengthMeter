package com.example.marwe497.passwordstrengthmeter;

public abstract class AbstractCondStage {

    private int Color;

    private String text;

    private int textColor;

    public AbstractCondStage() {
        init();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    private int length;

    private  boolean allowed;

    public abstract boolean isSatisfied(int strength);

    public abstract void init();

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}