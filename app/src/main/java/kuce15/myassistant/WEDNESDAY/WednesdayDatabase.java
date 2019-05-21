package kuce15.myassistant.WEDNESDAY;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asish on 3/3/17.
 */

public class WednesdayDatabase extends SQLiteOpenHelper {
    public WednesdayDatabase sundayDatabase;
    // Database Version
    private static final int DATABASE_VERSION_WEDNESDAY = 8;

    // Database Name
    private static final String DATABASE_WEDNESDAY = "WednesdayDatabase";

    // Table name
    private static final String TABLE_WEDNESDAY = "Wednesday";

    // Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_COURSE = "course";
    public static final String KEY_COURSE_INSTRUCTOR = "course_instructor";
    public static final String KEY_TIME = "time";
    public static final String KEY_NOTIFICATION = "notification";


    public WednesdayDatabase(Context context) {
        super(context, DATABASE_WEDNESDAY, null, DATABASE_VERSION_WEDNESDAY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WEDNESDAY_TABLE = "CREATE TABLE " + TABLE_WEDNESDAY +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COURSE + " TEXT,"
                + KEY_COURSE_INSTRUCTOR + " TEXT,"
                + KEY_TIME + " INTEGER,"
                + KEY_NOTIFICATION + " BOOLEAN" + ")";
        db.execSQL(CREATE_WEDNESDAY_TABLE);

        //getInformation(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEDNESDAY);

        // Create tables again
        onCreate(db);
    }

    // Adding new Reminder
    public int addReminder(WednesdayReminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_COURSE, reminder.getsCourse());
        values.put(KEY_COURSE_INSTRUCTOR, reminder.getsCourseInstructor());
        values.put(KEY_TIME, reminder.getTime());
        values.put(KEY_NOTIFICATION, reminder.getsNotification());


        // Inserting Row
        long ID = db.insert(TABLE_WEDNESDAY, null, values);
        db.close();
        return (int) ID;
    }

    // Adding new Reminder
    public int addReminders(SQLiteDatabase db, String Course, String CourseInstructor, String Time, String Notification) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_COURSE, Course);
        values.put(KEY_COURSE_INSTRUCTOR, CourseInstructor);
        values.put(KEY_TIME, Time);
        values.put(KEY_NOTIFICATION, Notification);


        // Inserting Row
        long ID = db.insert(TABLE_WEDNESDAY, null, values);
        db.close();
        return (int) ID;
    }

    // Getting single Reminder
    public WednesdayReminder getReminder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WEDNESDAY, new String[]
                        {
                                KEY_ID,
                                KEY_COURSE,
                                KEY_COURSE_INSTRUCTOR,
                                KEY_TIME,
                                KEY_NOTIFICATION,
                        }, KEY_ID + "=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        WednesdayReminder reminder = new WednesdayReminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return reminder;
    }

    public Cursor getInformation(SQLiteDatabase db) {
        String[] projections = {
                KEY_ID, KEY_COURSE, KEY_COURSE_INSTRUCTOR,
                KEY_TIME, KEY_NOTIFICATION
        };

        Cursor cursor = db.query(TABLE_WEDNESDAY, projections,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }


    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_WEDNESDAY, null);
        return data;
    }

    public void deleteWednesday() {
        SQLiteDatabase db = WednesdayDatabase.this.getWritableDatabase();

        db.delete(TABLE_WEDNESDAY, null, null);

    }

}


