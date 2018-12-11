package com.example.marwe497.passwordstrengthmeter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorBar extends LinearLayout {

    private TextView tVDesc = new TextView(getContext());
    private TextView tVResult = new TextView(getContext());

    private ProgressBar bar = new ProgressBar(getContext(),null,android.R.attr.progressBarStyleHorizontal);

    public void setCondList(ArrayList<AbstractCondStage> condList) {
        this.condList = condList;
    }

    private ArrayList<AbstractCondStage> condList = new ArrayList<>();

    public boolean checkStage(int strength){
        for (AbstractCondStage cond : condList) {

            if(cond.isSatisfied(strength)){
                tVResult.setText(cond.getText());
                tVResult.setTextColor(cond.getTextColor());
                drawBar(cond.getColor(), cond.getLength());
                return cond.isAllowed();
            }
        }
        tVResult.setText("Error");
        return false;
    }

    private void drawBar(int color, int length) {
        Drawable drawable = bar.getProgressDrawable();
        drawable.setColorFilter(new LightingColorFilter(0xFF000000, color));
        bar.setProgress(length, true);

    }

    public ColorBar(Context context) {
        super(context);
        init();
    }

    public ColorBar(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorBar(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ColorBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        this.setOrientation(LinearLayout.VERTICAL);

        LinearLayout horLayout = new LinearLayout(getContext());
        horLayout.setOrientation(LinearLayout.HORIZONTAL);

        horLayout.addView(tVDesc);
        horLayout.addView(tVResult);

        this.addView(horLayout);
        this.addView(bar);

        AbstractCondStage tooShort = new AbstractCondStage() {
            @Override
            public boolean isSatisfied(int strength) {
              return strength == -1;
            }

            @Override
            public void init() {
                setText("Too short");
                setTextColor(android.graphics.Color.GRAY);
                setColor(android.graphics.Color.GRAY);
                setLength(0);
                setAllowed(false);
            }
        };
        AbstractCondStage weak = new AbstractCondStage() {
            @Override
            public boolean isSatisfied(int strength) {
                if(strength <= 30 && strength >= 0){
                    return true;
                }
                return false;
            }

            @Override
            public void init() {
                setText("Weak");
                setTextColor(android.graphics.Color.RED);
                setColor(android.graphics.Color.RED);
                setLength(30);
                setAllowed(false);
            }
        };

        AbstractCondStage Fair = new AbstractCondStage() {
            @Override
            public boolean isSatisfied(int strength) {
                if(strength <= 50 && strength > 30){
                    return true;
                }
                return false;
            }

            @Override
            public void init() {
                setText("Fair");
                setTextColor(Color.parseColor("#FF6501"));
                setColor(Color.parseColor("#FF6501"));
                setLength(50);
                setAllowed(true);
            }
        };
        AbstractCondStage Good = new AbstractCondStage() {
            @Override
            public boolean isSatisfied(int strength) {
                if(strength <= 80 && strength > 50){
                    return true;
                }
                return false;
            }

            @Override
            public void init() {
                setText("Good");
                setTextColor(android.graphics.Color.BLUE);
                setColor(android.graphics.Color.BLUE);
                setLength(80);
                setAllowed(true);
            }
        };

        AbstractCondStage Strong = new AbstractCondStage() {
            @Override
            public boolean isSatisfied(int strength) {
                if(strength <= 100 && strength > 80){
                    return true;
                }
                return false;
            }

            @Override
            public void init() {
                setText("Strong");
                setTextColor(android.graphics.Color.GREEN);
                setColor(android.graphics.Color.GREEN);
                setLength(100);
                setAllowed(true);
            }
        };

        condList.add(tooShort);
        condList.add(weak);
        condList.add(Fair);
        condList.add(Good);
        condList.add(Strong);

    }

    public TextView gettVDesc() {
        return tVDesc;
    }

    public void settVDesc(TextView tVDesc) {
        this.tVDesc = tVDesc;
    }

    public TextView gettVResult() {
        return tVResult;
    }

    public void settVResult(TextView tVResult) {
        this.tVResult = tVResult;
    }
}
