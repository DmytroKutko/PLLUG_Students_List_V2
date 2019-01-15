package com.example.studentlistv2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentlistv2.db.MyDatabase;
import com.example.studentlistv2.model.Student;

public class UpdateStudent extends AppCompatActivity {

    EditText etNameUpdate, etSurnameUpdate, etGroupUpdate, etUniversityUpdate;
    Button btnUpdate;
    MyDatabase myDB;
    Cursor cursor;
    int id;
    Intent intent;
    Student student, updatedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        setInitialData();
    }

    private void setInitialData() {
        initView();
        initListener();
    }

    private void initListener() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updateId = id;
                String name = etNameUpdate.getText().toString();
                String surname = etSurnameUpdate.getText().toString();
                String group = etGroupUpdate.getText().toString();
                String university = etUniversityUpdate.getText().toString();

                updatedStudent = new Student(updateId, name, surname, group, university);

                myDB.updateStudent(updatedStudent);

                Intent intentList = new Intent(UpdateStudent.this, StudentsList.class);
                startActivity(intentList);
            }
        });
    }

    private void initView() {
        etNameUpdate = findViewById(R.id.etNameUpdate);
        etSurnameUpdate = findViewById(R.id.etSurnameUpdate);
        etGroupUpdate = findViewById(R.id.etGroupUpdate);
        etUniversityUpdate = findViewById(R.id.etUniversityUpdate);

        btnUpdate = findViewById(R.id.btnUpdate);

        intent = getIntent();
        id = intent.getIntExtra("id", -1);
        myDB = new MyDatabase(UpdateStudent.this);
        cursor = myDB.getDataById(id);

        initEditText();
    }

    void initEditText(){

        if (cursor.getCount() == 0 || id == -1) {
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String group = cursor.getString(3);
                String university = cursor.getString(4);
                student = new Student(id, name, surname, group, university);
            }
        }

        etNameUpdate.setText(student.getName());
        etSurnameUpdate.setText(student.getSurname());
        etGroupUpdate.setText(student.getGroup());
        etUniversityUpdate.setText(student.getUniversity());
    }
}
