package kuce15.myassistant.GPA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shoaib on 3/3/2017.
 */

public class StudentDatabase extends SQLiteOpenHelper {
    public StudentDatabase studentDatabase;
    // Database Version
    private static final int DATABASE_VERSION_STUDENT = 4;

    // Database Name
    private static final String DATABASE_STUDENT = "StudentDatabase";

    // Table name
    private static final String TABLE_STUDENT = "Student";

    // Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ROLL_NO = "rollno";
    public static final String KEY_DEPARTMENT = "department";
    public static final String KEY_GPA = "Gpa";

    public StudentDatabase(Context context) {
        super(context, DATABASE_STUDENT, null, DATABASE_VERSION_STUDENT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_ROLL_NO + " INTEGER,"
                + KEY_DEPARTMENT + " TEXT,"
                + KEY_GPA + " FLOAT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

        //getInformation(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        // Create tables again
        onCreate(db);

    }
    public int addReminder(Student reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME , reminder.getsName());
        values.put(KEY_ROLL_NO , reminder.getsRollNo());
        values.put(KEY_DEPARTMENT , reminder.getsDepartment());
        values.put(KEY_GPA , reminder.getsGPA());


        // Inserting Row
        long ID = db.insert(TABLE_STUDENT, null, values);
        db.close();
        return (int) ID;
    }
    public int addReminders(SQLiteDatabase db, String Name, String RollNo, String Department, String Gpa){
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, Name);
        values.put(KEY_ROLL_NO , RollNo);
        values.put(KEY_DEPARTMENT , Department);
        values.put(KEY_GPA , Gpa);


        // Inserting Row
        long ID = db.insert(TABLE_STUDENT, null, values);
        db.close();
        return (int) ID;
    }

    public Cursor getInformation(SQLiteDatabase db) {
        String[] projections = {
                KEY_ID, KEY_NAME, KEY_ROLL_NO,
                KEY_DEPARTMENT,KEY_GPA
        };

        Cursor cursor = db.query(TABLE_STUDENT, projections,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public void deleteStudent()
    {
        SQLiteDatabase db=StudentDatabase.this.getWritableDatabase();

        db.delete(TABLE_STUDENT,null,null);
        db.close();

    }
}
