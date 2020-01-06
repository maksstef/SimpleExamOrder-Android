package com.example.examorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpForm extends AppCompatActivity {

    String[] subjects = {"СТПМС", "БЖЧ", "ITECHART", "СЕТИ", "БД"};
    String[] groups = {"1","2","3","4","5","6","7","8","9","10"};
    String group = "";
    String subject = "";
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        sqLiteHelper = new SQLiteHelper(this, "app.db", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS students " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT," +
                " sgroup INTEGER," +
                " subject TEXT," +
                " position INTEGER," +
                " mark INTEGER)");


        Spinner spinner = findViewById(R.id.subjects);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subject = (String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);


        Spinner spinner2 = findViewById(R.id.groups);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        AdapterView.OnItemSelectedListener itemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                group = (String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner2.setOnItemSelectedListener(itemSelectedListener2);
    }

    public void SignUp(View view){
        EditText name = (EditText)findViewById(R.id.name);
        EditText position = (EditText)findViewById(R.id.position);
        EditText mark = (EditText)findViewById(R.id.mark);
        try{
            sqLiteHelper.insertData(
                    name.getText().toString().trim(),
                    Integer.valueOf(group.trim()),
                    subject,
                    Integer.valueOf(position.getText().toString().trim()),
                    Integer.valueOf(mark.getText().toString().trim())
            );
            Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
