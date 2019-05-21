package kuce15.myassistant.SUNDAY;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shoaib on 2/26/2017.
 */

public class SundayDatabase extends SQLiteOpenHelper {

    public SundayDatabase sundayDatabase;
    // Database Version
    private static final int DATABASE_VERSION_SUNDAY = 6;

    // Database Name
    private static final String DATABASE_SUNDAY = "SundayDatabase";

    // Table name
    private static final String TABLE_SUNDAY = "Sunday";

    // Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_COURSE = "course";
    public static final String KEY_COURSE_INSTRUCTOR = "course_instructor";
    public static final String KEY_TIME = "time";
    public static final String KEY_NOTIFICATION = "notification";



    public SundayDatabase(Context context) {
        super(context, DATABASE_SUNDAY, null, DATABASE_VERSION_SUNDAY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SUNDAY_TABLE = "CREATE TABLE " + TABLE_SUNDAY +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COURSE + " TEXT,"
                + KEY_COURSE_INSTRUCTOR + " TEXT,"
                + KEY_TIME + " INTEGER,"
                + KEY_NOTIFICATION + " BOOLEAN" + ")";
        db.execSQL(CREATE_SUNDAY_TABLE);

        //getInformation(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUNDAY);
        // Create tables again
        onCreate(db);
    }
    // Adding new Reminder
    public int addReminder(SundayReminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_COURSE , reminder.getsCourse());
        values.put(KEY_COURSE_INSTRUCTOR , reminder.getsCourseInstructor());
        values.put(KEY_TIME , reminder.getTime());
        values.put(KEY_NOTIFICATION , reminder.getsNotification());


        // Inserting Row
        long ID = db.insert(TABLE_SUNDAY, null, values);
        db.close();
        return (int) ID;
    }
    // Adding new Reminder
 /*   public int addReminders(SQLiteDatabase db, String Course, String CourseInstructor, String Time, String Notification){
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_COURSE , Course);
        values.put(KEY_COURSE_INSTRUCTOR , CourseInstructor);
        values.put(KEY_TIME , Time);
        values.put(KEY_NOTIFICATION , Notification);


        // Inserting Row
        long ID = db.insert(TABLE_SUNDAY, null, values);
        db.close();
        return (int) ID;
    }*/
    // Getting single Reminder
    public SundayReminder getReminder(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUNDAY, new String[]
                        {
                                KEY_ID,
                                KEY_COURSE,
                                KEY_COURSE_INSTRUCTOR,
                                KEY_TIME,
                                KEY_NOTIFICATION,
                        }, KEY_ID + "=?",

                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        SundayReminder reminder = new SundayReminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return reminder;
    }

    public Cursor getInformation(SQLiteDatabase db) {
        String[] projections = {
                KEY_ID, KEY_COURSE, KEY_COURSE_INSTRUCTOR,
                KEY_TIME,KEY_NOTIFICATION
        };

        Cursor cursor = db.query(TABLE_SUNDAY, projections,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }



    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_SUNDAY, null);
        return data;
    }

    public void deleteSunday()
    {
        SQLiteDatabase db=SundayDatabase.this.getWritableDatabase();

        db.delete(TABLE_SUNDAY,null,null);

    }

}


