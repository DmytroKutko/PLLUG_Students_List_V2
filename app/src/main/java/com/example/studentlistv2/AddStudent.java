package com.example.studentlistv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentlistv2.db.MyDatabase;
import com.example.studentlistv2.model.Student;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity {

    EditText etName, etSurname, etGroup, etUniversity;
    Button btnAdd;

    MyDatabase mydb;
    ArrayList<Student> studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        setInitialData();
    }

    private void setInitialData() {
        initView();
        initListener();
    }


    void initView(){
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etGroup = findViewById(R.id.etGroup);
        etUniversity = findViewById(R.id.etUniversity);
        btnAdd = findViewById(R.id.btnAdd);

        mydb = new MyDatabase(AddStudent.this);
        studentsList = new ArrayList<>();
    }

    void initListener(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().toString().trim().equals("") ||
                        etSurname.getText().toString().trim().equals("") ||
                        etGroup.getText().toString().trim().equals("") ||
                        etUniversity.getText().toString().trim().equals("")) {

                    Toast.makeText(AddStudent.this, "Enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    Student student = new Student();
                    student.setName(etName.getText().toString().trim());
                    student.setSurname(etSurname.getText().toString().trim());
                    student.setGroup(etGroup.getText().toString().trim());
                    student.setUniversity(etUniversity.getText().toString().trim());

                    mydb.insertData(student);

                    etName.setText("");
                    etSurname.setText("");
                    etGroup.setText("");
                    etUniversity.setText("");

                    Toast.makeText(AddStudent.this, "Student " + student.getName() +" added", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(AddStudent.this, StudentsList.class);
                startActivity(intent);

            }
        });
    }
}
