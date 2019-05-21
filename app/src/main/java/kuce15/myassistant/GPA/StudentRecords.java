package kuce15.myassistant.GPA;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kuce15.myassistant.R;


public class StudentRecords extends AppCompatActivity {
    StudentDatabase myDB;
    Button btnStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_records);
        BackGroundTask backGroundTask=new BackGroundTask(this);
        backGroundTask.execute("get_info");

        btnStudent=(Button)findViewById(R.id.buttonSunday);
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteStudent();
            }
        });
    }
}
