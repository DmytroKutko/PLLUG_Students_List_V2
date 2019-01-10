package com.example.studentlistv2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.studentlistv2.adapter.StudentAdapter;
import com.example.studentlistv2.db.MyDatabase;
import com.example.studentlistv2.model.Student;

import java.util.ArrayList;

public class StudentsList extends AppCompatActivity {

    FloatingActionButton btnPlus;
    RecyclerView rvStudents;
    StudentAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Student> studentsList;
    Context context;
    Cursor cursor;
    Student student;
    MyDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        setInitialData();
    }

    private void setInitialData() {
        initView();
        initListener();
    }

    private void initListener() {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentsList.this, AddStudent.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnPlus = findViewById(R.id.btnPlus);
        studentsList = new ArrayList<>();
        myDB = new MyDatabase(StudentsList.this);
        cursor = myDB.getData();
        context = this;
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvStudents = findViewById(R.id.rvStudents);
        rvStudents.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvStudents.setLayoutManager(layoutManager);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String group = cursor.getString(3);
                String university = cursor.getString(4);
                student = new Student(id, name, surname, group, university);
                studentsList.add(student);
            }
        }
        adapter = new StudentAdapter(this, studentsList);
        adapter.notifyDataSetChanged();
        rvStudents.setAdapter(adapter);
    }
}
