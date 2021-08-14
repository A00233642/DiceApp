package com.example.diceapp;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.security.Key;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int dice = 0;
    EditText editText;
    TextView textView;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find all views by id
        RadioGroup radiogroup = findViewById(R.id.radio_group);
        TextView textView = findViewById(R.id.textView1);

        RadioButton dr4 = findViewById(R.id.radio_four);
        RadioButton dr6 = findViewById(R.id.radio_six);
        RadioButton dr8 = findViewById(R.id.radio_eight);
        RadioButton dr10 = findViewById(R.id.radio_ten);
        RadioButton dr12 = findViewById(R.id.radio_twelve);
        RadioButton dr20 = findViewById(R.id.radio_twenty);

        editText = findViewById(R.id.edit_Text);

        if(UpdateInt("textValue") % 1 == 0 && UpdateInt("textValue") > 0) {
            editText.setText(Integer.toString(UpdateInt("textValue")));
            dice = UpdateInt("textValue");
        } else if(UpdateBool("d4")){
            dice = 4;
            dr4.setChecked(UpdateBool("d4"));
        } else if(UpdateBool("d6")){
            dice = 6;
            dr6.setChecked(UpdateBool("d6"));

        } else if(UpdateBool("d8") ){
            dice = 8;
            dr8.setChecked(UpdateBool("d8"));

        } else if(UpdateBool("d10")){
            dice = 10;
            dr10.setChecked(UpdateBool("d10"));

        } else if(UpdateBool("d12")){
            dice = 12;
            dr12.setChecked(UpdateBool("d12"));

        } else if(UpdateBool("d20")){
            dice = 20;
            dr20.setChecked(UpdateBool("d20"));

        } else {
            dice = 0;
        };



        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.linear_Layout);




        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                textView.setFocusable(false);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                // Code here executes on main thread after user presses button
                String message = null;
                if(dice > 0) {
                    message = rollDice(dice);


                }
                else if(!editText.getText().toString().equals("")) {
                    if (Integer.parseInt(String.valueOf(editText.getText())) > 0) {

                        SaveIntoSharedPrefs("textValue", Integer.parseInt(String.valueOf(editText.getText())), false);

                        message = rollDice(Integer.parseInt(String.valueOf(editText.getText())));
                    }
                    else message = "Enter number";
                }
                textView.setText(message);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dice = 0;
                textView.setFocusable(true);
                radiogroup.clearCheck();

            }
        });
    }
    public void onRadioButtonClicked(View view)
    {


        //Finding views by id
        RadioButton dr4 =  findViewById(R.id.radio_four);

        RadioButton dr6 =  findViewById(R.id.radio_six);
        RadioButton dr8 =  findViewById(R.id.radio_eight);

        RadioButton dr10 =  findViewById(R.id.radio_ten);
        RadioButton dr12 =  findViewById(R.id.radio_twelve);
        RadioButton dr20 =  findViewById(R.id.radio_twenty);

        editText.setHint("");
        editText.setText("");
        ResetSharedPref();
//            SaveIntoSharedPrefs("textValue", 0, false);
        //require to import the RadioButton class


        //is the current radio button now checked?
        boolean  checked = ((RadioButton) view).isChecked();

        //To check which radio button is selected
        // switch statement
        switch(view.getId()){

            case R.id.radio_four:
                if(checked)

                    //set the checked radio button's text style bold italic
                    dr4.setTypeface(null, Typeface.BOLD_ITALIC);
                //set the other two radio buttons text style to default

                dice = 4;
                SaveIntoSharedPrefs("d4", 0, true);
                SaveIntoSharedPrefs("d6", 0, false);
                SaveIntoSharedPrefs("d8", 0, false);
                SaveIntoSharedPrefs("d10", 0, false);
                SaveIntoSharedPrefs("d12", 0, false);
                SaveIntoSharedPrefs("d20", 0, false);

                dr6.setTypeface(null, Typeface.NORMAL);
                dr8.setTypeface(null, Typeface.NORMAL);
                //set the other two radio buttons text style to default
                dr10.setTypeface(null, Typeface.NORMAL);
                // require to import Typeface class
                dr12.setTypeface(null, Typeface.NORMAL);
                dr20.setTypeface(null, Typeface.NORMAL);
                //set the other two radio buttons text style to default

                break;

            case R.id.radio_six:
                if(checked)

                    //set the checked radio button's text style bold italic
                    dr6.setTypeface(null, Typeface.BOLD_ITALIC);
                dice = 6;

                SaveIntoSharedPrefs("d6", 0, true);
                SaveIntoSharedPrefs("d4", 0, false);
                SaveIntoSharedPrefs("d8", 0, false);
                SaveIntoSharedPrefs("d10", 0, false);
                SaveIntoSharedPrefs("d12", 0, false);
                SaveIntoSharedPrefs("d20", 0, false);
                //set the other two radio buttons text style to default
                dr4.setTypeface(null, Typeface.NORMAL);

                dr8.setTypeface(null, Typeface.NORMAL);
                //set the other two radio buttons text style to default
                dr10.setTypeface(null, Typeface.NORMAL);

                dr12.setTypeface(null, Typeface.NORMAL);
                dr20.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.radio_eight:
                if(checked)

                    //set the checked radio button's text style bold italic
                    dr8.setTypeface(null, Typeface.BOLD_ITALIC);
                dice = 8;


                SaveIntoSharedPrefs("d8", 0, true);

                SaveIntoSharedPrefs("d4", 0, false);
                SaveIntoSharedPrefs("d6", 0, false);
                SaveIntoSharedPrefs("d10", 0, false);
                SaveIntoSharedPrefs("d12", 0, false);
                SaveIntoSharedPrefs("d20", 0, false);

                //set the other two radio buttons text style to default
                dr6.setTypeface(null, Typeface.NORMAL);

                dr4.setTypeface(null, Typeface.NORMAL);
                //set the other two radio buttons text style to default
                dr10.setTypeface(null, Typeface.NORMAL);

                dr12.setTypeface(null, Typeface.NORMAL);
                dr20.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.radio_ten:
                if(checked)

                    //set the checked radio button's text style bold italic
                    dr10.setTypeface(null, Typeface.BOLD_ITALIC);
                //set the other two radio buttons text style to default

                SaveIntoSharedPrefs("d10", 0, true);

                SaveIntoSharedPrefs("d4", 0, false);
                SaveIntoSharedPrefs("d6", 0, false);
                SaveIntoSharedPrefs("d8", 0, false);
                SaveIntoSharedPrefs("d12", 0, false);
                SaveIntoSharedPrefs("d20", 0, false);

                dr6.setTypeface(null, Typeface.NORMAL);
                dice = 10;

                dr4.setTypeface(null, Typeface.NORMAL);

                dr8.setTypeface(null, Typeface.NORMAL);

                dr12.setTypeface(null, Typeface.NORMAL);
                dr20.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.radio_twelve:
                if(checked)

                    //set the checked radio button's text style bold italic
                    dr12.setTypeface(null, Typeface.BOLD_ITALIC);
                //set the other two radio buttons text style to default

                SaveIntoSharedPrefs("d12", 0, true);

                SaveIntoSharedPrefs("d4", 0, false);
                SaveIntoSharedPrefs("d6", 0, false);
                SaveIntoSharedPrefs("d8", 0, false);
                SaveIntoSharedPrefs("d10", 0, false);
                SaveIntoSharedPrefs("d20", 0, false);
                dice = 12;
                dr6.setTypeface(null, Typeface.NORMAL);

                dr4.setTypeface(null, Typeface.NORMAL);
                //set the other two radio buttons text style to default
                dr10.setTypeface(null, Typeface.NORMAL);

                dr8.setTypeface(null, Typeface.NORMAL);
                dr20.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.radio_twenty:
                if(checked)

                    //set the checked radio button's text style bold italic
                    dr12.setTypeface(null, Typeface.BOLD_ITALIC);
                //set the other two radio buttons text style to default
                dice = 20;

                SaveIntoSharedPrefs("d20", 0, true);

                SaveIntoSharedPrefs("d4", 0, false);
                SaveIntoSharedPrefs("d6", 0, false);
                SaveIntoSharedPrefs("d8", 0, false);
                SaveIntoSharedPrefs("d10", 0, false);
                SaveIntoSharedPrefs("d12", 0, false);

                dr6.setTypeface(null, Typeface.NORMAL);

                dr4.setTypeface(null, Typeface.NORMAL);
                //set the other two radio buttons text style to default
                dr10.setTypeface(null, Typeface.NORMAL);
                dr12.setTypeface(null, Typeface.NORMAL);
                dr20.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }


    private static String rollDice(int sides) {
        int number;
        Random rand = new Random();

        do {
            number = rand.nextInt(sides+1);
        } while (number == 0);
        return Integer.toString(number);
    }

    private void SaveIntoSharedPrefs(String key, int diceVal, boolean value){

        SharedPreferences sp = getSharedPreferences("Dice", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(diceVal > 0){
            editor.putInt(key, diceVal);
        }else {
            editor.putBoolean(key, value);

        }
        editor.apply();
    }

    private int UpdateInt(String Key) {
        SharedPreferences sp = getSharedPreferences("Dice", MODE_PRIVATE);
        return sp.getInt(Key, 0);
    }

    private boolean UpdateBool(String key){
        SharedPreferences sp = getSharedPreferences("Dice", MODE_PRIVATE);
        return sp.getBoolean(key, false);

    }
    private void ResetSharedPref(){
        SharedPreferences sp = getSharedPreferences("Dice", MODE_PRIVATE);
        sp.edit().clear().commit();
}

}