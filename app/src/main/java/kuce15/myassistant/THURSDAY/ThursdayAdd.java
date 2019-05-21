package kuce15.myassistant.THURSDAY;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import kuce15.myassistant.R;


public class ThursdayAdd extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {




    Context ctx;

    public ThursdayAdd()
    {
    }

    ThursdayAdd(Context ctx)
    {
        this.ctx=ctx;
    }


    ThursdayDatabase sundayDatabase;
    //    SundayAdapter sundayAdapter;
    private int mReceivedID;
    private Calendar sCalendar;
    private Toolbar mToolbar;
    private EditText sCourseText, sCourseInstructorText;
    private TextView sTimeText, sNotificationText;
    private long sRepeatTime;
    private String sCourse;
    private String sCourseInstructuor;
    private String sTime;
    private String sON;
    private int sYear, sMonth, sHour, sMinute, sDayOfWeek;
    String sRepeatNo = "1";


    // Values for orientation change

    private static final String KEY_Course = "course_key";
    private static final String KEY_Course_instructor = "course_instructor_key";
    private static final String KEY_time = "time_key";
    private static final String KEY_on = "on_key";

    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thursday_add);

        // Initialize Views
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        sCourseText = (EditText) findViewById(R.id.reminder_course);
        sCourseInstructorText = (EditText) findViewById(R.id.reminder_course_instructor);
        sTimeText = (TextView) findViewById(R.id.set_time);
        sNotificationText = (TextView) findViewById(R.id.set_notification);


        // Setup Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Routine");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Initialize default values
        sON = "true";
        //mRepeatNo = Integer.toString(1);
        //mRepeatType = "Hour";

        sCalendar = Calendar.getInstance();
        sHour = sCalendar.get(Calendar.HOUR_OF_DAY);
        sMinute = sCalendar.get(Calendar.MINUTE);
        sDayOfWeek = sCalendar.get(Calendar.DAY_OF_WEEK);
        // mYear = mCalendar.get(Calendar.YEAR);
        // mMonth = mCalendar.get(Calendar.MONTH) + 1;
        // mDay = mCalendar.get(Calendar.DATE);

        //mDate = mDay + "/" + mMonth + "/" + mYear;
        sTime = sHour + ":" + sMinute;

        // Setup Course EditText
        sCourseText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sCourse = s.toString().trim();
                sCourseText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        sCourseInstructorText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sCourseInstructuor = s.toString().trim();
                sCourseInstructorText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Setup TextViews using reminder values
        //  sDateText.setText(mDate);
        sTimeText.setText(sTime);
        sNotificationText.setText(sON);
        //   mRepeatTypeText.setText(mRepeatType);
        // mRepeatText.setText("Every " + mRepeatNo + " " + mRepeatType + "(s)");

        // To save state on device rotation
        if (savedInstanceState != null) {
            String savedCourse = savedInstanceState.getString(KEY_Course);
            sCourseText.setText(savedCourse);
            sCourse = savedCourse;


            String savedCourseInstructor = savedInstanceState.getString(KEY_Course_instructor);
            sCourseInstructorText.setText(savedCourseInstructor);
            sCourseInstructuor = savedCourseInstructor;


            // String savedDate = savedInstanceState.getString(KEY_DATE);
            //mDateText.setText(savedDate);
            //mDate = savedDate;

            String savedsTime = savedInstanceState.getString(KEY_time);
            sTimeText.setText(savedsTime);
            sON = savedsTime;

            String savedsON = savedInstanceState.getString(KEY_on);
            sNotificationText.setText(savedsON);
            sON = savedsON;


            // String savedRepeatNo = savedInstanceState.getString(KEY_REPEAT_NO);
            // mRepeatNoText.setText(savedRepeatNo);
            //mRepeatNo = savedRepeatNo;

            //String savedRepeatType = savedInstanceState.getString(KEY_REPEAT_TYPE);
            //mRepeatTypeText.setText(savedRepeatType);
            //mRepeatType = savedRepeatType;

            //  mActive = savedInstanceState.getString(KEY_ACTIVE);
        }


    }

    // To save state on device rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(KEY_Course, sCourseText.getText());
        outState.putCharSequence(KEY_Course_instructor, sCourseInstructorText.getText());
        outState.putCharSequence(KEY_time, sTimeText.getText());
        outState.putCharSequence(KEY_on, sNotificationText.getText());
    }

    // On clicking Time picker
    public void setTime(View v) {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.setThemeDark(false);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }


    // Obtain time from time picker
    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        sHour = hourOfDay;
        sMinute = minute;
        if (minute < 10) {
            sTime = hourOfDay + ":" + "0" + minute;
        } else {
            sTime = hourOfDay + ":" + minute;
        }
        sTimeText.setText(sTime);
    }


    // On clicking the notification switch
    public void onSwitchRepeat(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            sON = "true";
            sNotificationText.setText("Notification is ON");
        } else {
            sON = "false";
            sNotificationText.setText("Notification is OFF");
        }
    }

    public void saveReminder() {
        // BackGroundTask backGroundTask=new BackGroundTask(this);
        // backGroundTask.execute("add_info",sCourse, sCourseInstructuor, sTime, sON);
        ThursdayDatabase rb = new ThursdayDatabase(this);

        // Creating Reminder
        int ID = rb.addReminder(new ThursdayReminder(sCourse, sCourseInstructuor, sTime, sON));
        // int ID = rb.addReminders(sCourse, sCourseInstructuor, sTime, sON);

        // Set up calender for creating the notification
        //sCalendar.set(Calendar.DAY_OF_WEEK,3);
        //mCalendar.set(Calendar.YEAR, mYear);
        //mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
        sCalendar.set(Calendar.HOUR_OF_DAY, sHour);
        sCalendar.set(Calendar.MINUTE, sMinute);
        sCalendar.set(Calendar.SECOND, 0);

        // REPEATING EVERY WEEK
        // if (mRepeatType.equals("Minute")) {
        //    mRepeatTime = Integer.parseInt(mRepeatNo) * milMinute;
        // } else if (mRepeatType.equals("Hour")) {
        //     mRepeatTime = Integer.parseInt(mRepeatNo) * milHour;
        // } else if (mRepeatType.equals("Day")) {
        //     mRepeatTime = Integer.parseInt(mRepeatNo) * milDay;

//            sRepeatTime = Integer.parseInt(sRepeatNo) * milWeek;
        //  } else if (mRepeatType.equals("Month")) {
        //      mRepeatTime = Integer.parseInt(mRepeatNo) * milMonth;
        //  }

        // Create a new notification

//          if (sON.equals("true")) {

        //   new SundayAlarmReceiver().setAlarm(getApplicationContext(), sCalendar, ID);
        //}


        // Create toast to confirm new reminder
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();

        onBackPressed();
    }


    // On pressing the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // Creating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_sunday, menu);
        return true;
    }

    // On clicking menu buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // On clicking the back arrow
            // Discard any changes
            case android.R.id.home:
                onBackPressed();
                return true;

            // On clicking save reminder button
            // Update reminder
            case R.id.save_sunday:
                sCourseText.setText(sCourse);

                if (sCourseText.getText().toString().length() == 0)
                    sCourseText.setError("Course Title cannot be blank!");

                else {
                    saveReminder();
                    onBackPressed();
                    Toast.makeText(getApplicationContext(), "Saved",
                            Toast.LENGTH_SHORT).show();
                }
                return true;

            // On clicking discard reminder button
            // Discard any changes
            case R.id.discard_sunday:
                Toast.makeText(getApplicationContext(), "Discarded",
                        Toast.LENGTH_SHORT).show();

                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}