package kuce15.myassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static kuce15.myassistant.AddTaskActivity.latitude;
import static kuce15.myassistant.AddTaskActivity.locname;
import static kuce15.myassistant.AddTaskActivity.longitude;
import static kuce15.myassistant.AddTaskActivity.tasname;
import static kuce15.myassistant.MainActivity.check;

public class NotificationActivity extends AppCompatActivity {
    TextView taskname;
    TextView locationname;
    String nameloc;
    AddTaskActivity att;
    Button donebutton;

    //Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        taskname = (TextView) findViewById(R.id.Taskname);
        locationname=(TextView) findViewById(R.id.Locationname);
        donebutton=(Button)findViewById(R.id.donebut);
       // nameloc =locationname.getText().toString();
      //  nameloc = att.getlocationname();

       // Context context = null;
      //  c= this.getContentResolver().query(TasksProvider.CONTENT_URI,new String[]{NOTE_TEXT,LOC_NAME,NOTE_CREATED},
           //     NOTE_CREATED +"=?",new String[]{nameloc},null);
        //if(c.moveToNext()){
        Toast.makeText(this,"YOU REACHED THE DESTINATION",Toast.LENGTH_LONG).show();
            taskname.setText(tasname);
            locationname.setText(locname);
        tasname=null;
        locname=null;
        check=true;

    }


    public void markascomplete(View view) {
        //final Cursor cursor = getContentResolver().query(TasksProvider.CONTENT_URI, TaskDbhelper.ALL_COLUMNS,null,null,null,null);
        latitude =null;
        longitude=null;
       // taskdelete(cursor.getString(COL_TASK_ID),true);
        finish();

    }

   /* private void taskdelete(String COL_TASK_ID, boolean b) {

        getContentResolver().delete(TasksProvider.CONTENT_URI,NOTE_ID+ "=?",new String[]{COL_TASK_ID});

            Toast.makeText(NotificationActivity.this,
                    getString(R.string.deleted),
                    Toast.LENGTH_SHORT).show();



    }
    */

    //c.close();

    }

