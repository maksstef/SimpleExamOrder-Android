package com.example.examorder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private LayoutInflater inflater;
    private int layout;
    private List<Student> students;

    public StudentAdapter(Context context, int resource, List<Student> students) {
        super(context, resource, students);
        this.students = students;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public StudentAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView positionView = (TextView) view.findViewById(R.id.position);

        Student student = students.get(position);

        nameView.setText(student.getName());
        positionView.setText(String.valueOf(student.getPosition()));
        return view;
    }
}
