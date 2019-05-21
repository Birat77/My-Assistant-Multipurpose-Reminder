package kuce15.myassistant.GPA;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import kuce15.myassistant.R;

import static kuce15.myassistant.GPA.StudentDatabase.KEY_DEPARTMENT;
import static kuce15.myassistant.GPA.StudentDatabase.KEY_GPA;
import static kuce15.myassistant.GPA.StudentDatabase.KEY_NAME;
import static kuce15.myassistant.GPA.StudentDatabase.KEY_ROLL_NO;

/**
 * Created by Shoaib on 2/28/2017.
 */

class BackGroundTask extends AsyncTask<String,Student,String> {
    Context ctx;
    ListView listView;
    StudentAdapter studentadapter;
    Activity activity;
    BackGroundTask(Context ctx)
    {
        this.ctx=ctx;
        activity =(Activity)ctx;
    }
    @Override
    protected void onPreExecute()
    {super.onPreExecute();}



    @Override
    protected String doInBackground(String... params) {
        String method=params[0];
        StudentDatabase dpOperation=new StudentDatabase(ctx);
        if (method.equals("add_info"))
        {
            String Name=params[1];
            String Rollno=params[2];
            String Department=params[3];
            String GPA=params[4];
            SQLiteDatabase db=dpOperation.getWritableDatabase();

            dpOperation.addReminders(db,Name,Rollno,Department,GPA);
            return "One student record inserted!";
        }
        else if (method.equals("get_info"))
        {
            listView=(ListView)activity.findViewById(R.id.listView1);
            SQLiteDatabase db=dpOperation.getReadableDatabase();
            Cursor cursor =dpOperation.getInformation(db);
            studentadapter=new StudentAdapter(ctx, R.layout.routine);

            String Name,RollNo,Department,GPA;
            while (cursor.moveToNext())
            {
                Name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
                RollNo=cursor.getString(cursor.getColumnIndex(KEY_ROLL_NO));
                Department=cursor.getString(cursor.getColumnIndex(KEY_DEPARTMENT));
                GPA=cursor.getString(cursor.getColumnIndex(KEY_GPA));
                Student student=new Student(Name,RollNo,Department,GPA);
                publishProgress(student);
            }
            return "get_info";

        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Student... values)
    {
        studentadapter.add(values[0]);
    }
    @Override
    protected void onPostExecute(String result)
    {
        if (result.equals("get_info")) {
            listView.setAdapter(studentadapter);
        }
        else {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }

}
