package com.example.studentlistv2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class StudentsList extends AppCompatActivity {

    FloatingActionButton btnPlus;
    RecyclerView rvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        initView();
        initListener();
    }

    private void initListener() {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Test
                Toast.makeText(StudentsList.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        btnPlus = findViewById(R.id.btnPlus);
        rvStudents = findViewById(R.id.rvStudents);
    }
}
