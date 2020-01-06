package com.example.examorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    private List<Student> students = new ArrayList();
    ListView studentsList;

    String[] subjects = {"СТПМС", "БЖЧ", "ITECHART", "СЕТИ", "БД"};
    String[] groups = {"1","2","3","4","5","6","7","8","9","10"};
    String group = "";
    String subject = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.subjects);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
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

        OnItemSelectedListener itemSelectedListener2 = new OnItemSelectedListener() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.sort_medium_mark :
                String exec = "SELECT * FROM students where sgroup = '"+Integer.valueOf(group)+"' and subject = '"+subject+"'  order by mark desc;";
                SortBy(exec);
                Log.d("log_5","sort by medium mark");
                return true;
            case R.id.sort_medium_mark_desc :
                String exec2 = "SELECT * FROM students where sgroup = '"+Integer.valueOf(group)+"' and subject = '"+subject+"'  order by mark;";
                SortBy(exec2);
                Log.d("log_5","sort by medium mark desc");
                return true;
            case R.id.sort_order:
                String exec3 = "SELECT * FROM students where sgroup = '"+Integer.valueOf(group)+"' and subject = '"+subject+"'  order by position;";
                SortBy(exec3);
                Log.d("log_5","sort by order");
                return true;
            case R.id.sort_order_desc:
                String exec4 = "SELECT * FROM students where sgroup = '"+Integer.valueOf(group)+"' and subject = '"+subject+"'  order by position desc;";
                SortBy(exec4);
                Log.d("log_5","sort by order desc");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OpenSignUpForm(View view){
        Intent intent = new Intent(this, SignUpForm.class);
        startActivity(intent);
    }

    public void Display(final View view){
        students.clear();

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM students where sgroup = '"+Integer.valueOf(group)+"' and subject = '"+subject+"'  order by position;", null);

        if(query.moveToFirst()){
            do{
                Student student = new Student(query.getInt(0),
                                                query.getString(1),
                                                query.getInt(2),
                                                query.getString(3),
                                                query.getInt(4),
                                                query.getInt(5));
                students.add(student);
            }
            while(query.moveToNext());
        }

        query.close();
        db.close();

        studentsList = (ListView) findViewById(R.id.list_students);
        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.list_item, students);
        studentsList.setAdapter(studentAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Student selectedState = (Student) parent.getItemAtPosition(position);
                ID = String.valueOf(selectedState.getID());
                GoToChangeDeleteForm(view);
            }
        };
        studentsList.setOnItemClickListener(itemListener);
    }

    String ID = "";
    public void GoToChangeDeleteForm(View view){
        Intent intent = new Intent(this, ChangeDeleteForm.class);
        intent.putExtra(EXTRA_MESSAGE,ID);
        startActivity(intent);
    }

    public void SortBy(String exec){
        final View view = new View(this);
        students.clear();

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        Cursor query = db.rawQuery(exec,null);

        if(query.moveToFirst()){
            do{
                Student student = new Student(query.getInt(0),
                        query.getString(1),
                        query.getInt(2),
                        query.getString(3),
                        query.getInt(4),
                        query.getInt(5));
                students.add(student);
            }
            while(query.moveToNext());
        }

        query.close();
        db.close();

        studentsList = (ListView) findViewById(R.id.list_students);
        StudentAdapter studentAdapter = new StudentAdapter(this, R.layout.list_item, students);
        studentsList.setAdapter(studentAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Student selectedState = (Student) parent.getItemAtPosition(position);
                ID = String.valueOf(selectedState.getID());
                GoToChangeDeleteForm(view);
            }
        };
        studentsList.setOnItemClickListener(itemListener);
    }
}
