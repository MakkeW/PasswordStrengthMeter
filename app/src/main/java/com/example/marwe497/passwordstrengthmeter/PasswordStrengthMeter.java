package com.example.marwe497.passwordstrengthmeter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PasswordStrengthMeter extends LinearLayout {

    public PasswordStrengthMeter(Context context) {
        super(context);
        init();
    }

    public PasswordStrengthMeter(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordStrengthMeter(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PasswordStrengthMeter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setFirstText(String text){
        chooseApassword.setText(text);
    }

    public void setInst(String text){
        textInst.setText(text);
    }

    public void setDesc(String text){
        bar.gettVDesc().setText(text);
    }

    public void setAlgoritm(AbstractPasswordAlgoritm algoritm) {
        this.algoritm = algoritm;
    }

    private AbstractPasswordAlgoritm algoritm;


    private TextView chooseApassword = new TextView(getContext());
    private LinearLayout vertLayout = new LinearLayout(getContext());
    private EditText edTxt = new EditText(getContext());
    private TextView textInst = new TextView(getContext());
    private ColorBar bar = new ColorBar(getContext());

    private void init(){
        algoritm = new DefaultAlgoritm();
        this.setOrientation(LinearLayout.VERTICAL);
        chooseApassword.setText("Choose a password: ");
        vertLayout.setOrientation(LinearLayout.VERTICAL);
        edTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textInst.setText("Minimum of 8 characters in length");
        textInst.setTextColor(Color.LTGRAY);
        vertLayout.addView(edTxt);
        vertLayout.addView(textInst);

        edTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bar.checkStage(algoritm.getStrength(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.addView(chooseApassword);
        this.addView(vertLayout);
        this.addView(bar);
    }

}
