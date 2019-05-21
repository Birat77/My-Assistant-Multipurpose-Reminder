package kuce15.myassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pratik on 2/10/17.
 */

public class TaskDbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notess.db";
    private static final int DATABASE_VERSION = 5;

    //Constants for identifying table and columns
    public static final String TABLE_NOTES = "notes";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TEXT = "taskname";
    public static final String NOTE_CREATED = "timeofcreation";
    public static final String LOC_NAME = "locationname";
    public static final String DATE ="setdate";
    public static final String[] ALL_COLUMNS= {
            NOTE_ID,NOTE_TEXT,NOTE_CREATED,LOC_NAME,DATE};

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTES + " (" +
                    NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTE_TEXT + " TEXT, " +
                    NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    LOC_NAME + " TEXT, "+
                    DATE +" INTEGER" +
                    ")";
    public TaskDbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {

            db.execSQL("ALTER TABLE notes ADD COLUMN latitude REAL");
            db.execSQL("ALTER TABLE notes ADD COLUMN longitude REAL");

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);

            //onCreate(db);
        }
    }
}