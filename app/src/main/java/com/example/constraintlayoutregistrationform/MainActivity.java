package com.example.constraintlayoutregistrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText name, age, number;
    private RadioGroup gender;
    private CheckBox french, english, german;
    private TextView op;
    private Button res, sub;
    private Spinner coun ;
    String nameOp, ageOp, numberOp,genderOp, languageOp="",countryOp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            name = findViewById(R.id.person_name);
            age = findViewById(R.id.person_age);
            number = findViewById(R.id.person_phone_no);
            french = findViewById(R.id.fren);
            english = findViewById(R.id.eng);
            german = findViewById(R.id.germ);
            gender = findViewById(R.id.gender);
            op = findViewById(R.id.output);
            coun = findViewById(R.id.spinner);
            res = findViewById(R.id.reset);
            sub = findViewById(R.id.submit);

        //get gender
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.male)
                    genderOp = "Male";
                if(checkedId == R.id.female)
                    genderOp = "Female";
                if(checkedId == R.id.other)
                    genderOp = "Others";
            }
        });

        //get country
        coun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("--SELECT YOUR COUNTRY--"))
                    Toast.makeText(MainActivity.this, "Select a country", 3);
                else
                    countryOp = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

           //on submit
            sub.setOnClickListener(new View.OnClickListener() {

                @SuppressLint("SetTextI18n")
                @Override

                public void onClick(View v) {


                    //get name
                    if(name.length() == 0){
                     name.setError("Name cannot be null");
                    }
                    else {
                        nameOp = name.getText().toString();
                    }

                    //get age
                    if(age.length() == 0){
                        age.setError("Age cannot be null");
                    }
                    else if(Integer.parseInt(age.getText().toString()) < 0){
                        age.setError("Age cannot be less than 0");
                    }
                    else{
                        ageOp = age.getText().toString();
                    }

                    //get ph number
                    if(number.getText().length() < 10){
                        number.setError("Invalid number");
                    }
                    else{
                        numberOp = number.getText().toString();
                    }

//                    checking if gender is selected
                    if (gender.getCheckedRadioButtonId() == -1) {
                        String errorMessage = "Please select a Gender";
                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }

                    //get languages
                    if(!(english.isChecked() || french.isChecked() || german.isChecked())){
                        Toast.makeText(MainActivity.this, "select a language", 2).show();
                    }
                    languageOp="";
                    if(english.isChecked())
                        languageOp += "English ";
                    if(french.isChecked())
                        languageOp += "French ";
                    if(german.isChecked())
                        languageOp +="Germany ";


                    //Displaying output
                    if(nameOp !=null && ageOp != null && numberOp != null && genderOp != null && languageOp != "" && countryOp != null) {
                        op.setText("Name :" + nameOp +
                                "\nAge : " + ageOp +
                                "\nMobile No.:" + numberOp +
                                "\nGender : " + genderOp +
                                "\nLanguages : " + languageOp +
                                "\n Country : " + countryOp);
                    }
                    nameOp=null;ageOp=null;numberOp=null;genderOp=null;languageOp=null;countryOp=null;
                }
            });

            res.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nullifyingEntries();
                }
            });



    }

    private void nullifyingEntries() {
        name.setText("");
        age.setText("");
        number.setText("");
        gender.clearCheck();
        english.setChecked(false);
        french.setChecked(false);
        german.setChecked(false);
        op.setText("");

    }

}