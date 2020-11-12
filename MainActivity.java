package com.example.polytechimcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.IslamicCalendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edPoids, edTaille;
    RadioButton rdMetre, rdCm;
    Button btnCalculer, btnRaz;
    CheckBox chkMega;
    TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitVar();
    }

    View.OnClickListener ls = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v == btnCalculer) {
                Float vRes = Calculer();
                tvRes.setText(String.valueOf(vRes));

                if(chkMega.isChecked()) {
                    affichage(vRes);
                }
            }

            if (v == btnRaz) {
                vReset();
            }

        }
    };

    View.OnLongClickListener lg = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Float vRes = Calculer();
            affichage(vRes);
            return false;
        }
    };

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tvRes.setText("");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void InitVar(){

        edPoids = findViewById(R.id.editTextPoids);
        edTaille = findViewById(R.id.editTextTaille);
        rdMetre = findViewById(R.id.radioButtonMetre);
        rdCm = findViewById(R.id.radioButtonCMetre);
        btnCalculer = findViewById(R.id.buttonCalcul);
        btnRaz = findViewById(R.id.buttonRaz);
        chkMega = findViewById(R.id.checkBoxMF);
        tvRes = findViewById(R.id.textViewResCal);

        btnCalculer.setOnClickListener(ls);
        btnRaz.setOnClickListener(ls);
        btnCalculer.setOnLongClickListener(lg);

        edPoids.addTextChangedListener(tw);
        edTaille.addTextChangedListener(tw);
    }

    private void vReset(){
        rdMetre.setChecked(true);
        edPoids.setText("");
        edTaille.setText("");
        chkMega.setChecked(true);
    }

    private Float Calculer(){

        Float vPoids = Float.parseFloat(edPoids.getText().toString());
        Float vTaille = Float.parseFloat(edTaille.getText().toString());
        if (rdCm.isChecked()) vTaille = vTaille/100;
        return(vPoids/(vTaille*vTaille));
    }

    private void affichage(Float vRes) {
        String message = "";
        if (vRes < 16.5) {
            message = "Famine !";
        }else if (vRes <= 18.5){
            message = "Maigreur !";
        }else if (vRes <= 25){
            message = "Corpulance normale !";
        }else if (vRes <= 30){
            message = "surpoids !";
        }else if (vRes < 35){
            message = "obésité modéré !";
        }else if (vRes <= 40){
            message = "obésité sévère !";
        }else {
            message = "obésité morbide ou massive !";
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}