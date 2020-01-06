package com.example.examorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeDeleteForm extends AppCompatActivity {

    String ID = "";
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_delete_form);

        Intent intent = getIntent();
        ID = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        EditText name = (EditText) findViewById(R.id.name);
        EditText position = (EditText) findViewById(R.id.position);
        EditText mark = (EditText) findViewById(R.id.mark);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM students where ID = "+Integer.valueOf(ID)+";", null);

        if(query.moveToFirst()){
            do{
                name.setText(query.getString(1));
                position.setText(query.getString(4));
                mark.setText(query.getString(5));
            }
            while(query.moveToNext());
        }

        query.close();
        db.close();
    }

    public void Overwrite(View view){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        EditText name = (EditText)findViewById(R.id.name);
        String name_str = name.getText().toString();
        EditText position = (EditText) findViewById(R.id.position);
        String position_str = position.getText().toString();
        EditText mark = (EditText) findViewById(R.id.mark);
        String mark_str = mark.getText().toString();

        db.execSQL("UPDATE students SET name = '"+name_str+"'," +
                "position = "+Integer.valueOf(position_str)+"," +
                "mark = "+Integer.valueOf(mark_str)+" WHERE ID = "+Integer.valueOf(ID)+";");

        Toast.makeText(this, "updated successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Writeout(View view){
        EditText name = (EditText) findViewById(R.id.name);
        EditText position = (EditText) findViewById(R.id.position);
        EditText mark = (EditText) findViewById(R.id.mark);

        name.setText("");
        position.setText("");
        mark.setText("");

        sqLiteHelper = new SQLiteHelper(this, "app.db", null, 1);
        sqLiteHelper.deleteData(Integer.valueOf(ID));

        Toast.makeText(this, "deleted successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
