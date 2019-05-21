package kuce15.myassistant;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import kuce15.myassistant.GPA.GPACalculation;
import kuce15.myassistant.Task.TaskAcitivity;

import static kuce15.myassistant.AddTaskActivity.latitude;
import static kuce15.myassistant.AddTaskActivity.longitude;
import static kuce15.myassistant.TaskDbhelper.NOTE_ID;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //speech recognition variables
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;

    private LocationManager locationmanager;
    private LocationListener locationListener;
    NotificationManager notificationmanager;
    boolean isnotificactive = false;
    int notifyID= 33;
    String taskname, locationname;
    // Distance distanceclass = new Distance();
    AddTaskActivity ata= new AddTaskActivity();
    public static final int COL_TASK_ID = 0;
    static boolean check =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        locationmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                distancefunction(location);

            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent settingintent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(settingintent);

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET
                },10);

                return;

            }
        }
        else
        {
            configure();
        }


        //insertnote("New Task");

        showinlistView();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Toast.makeText(this,"main",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.Deflection) {
            Toast.makeText(this, "Deflectometer", Toast.LENGTH_SHORT).show();
            //routine_fragment routine_fragment=new routine_fragment();
            //FragmentManager manager=getSupportFragmentManager();
            //manager.beginTransaction().replace(R.id.relativelayout_for_fragment,routine_fragment,routine_fragment.getTag()).commit();
            Intent intent = new Intent(this, Deflectometer.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_GPA_Calculator) {
            Toast.makeText(this,"GPA CALCULATOR",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,GPACalculation.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_routine) {
            Toast.makeText(this,"Routine",Toast.LENGTH_SHORT).show();
            //routine_fragment routine_fragment=new routine_fragment();
            //FragmentManager manager=getSupportFragmentManager();
            //manager.beginTransaction().replace(R.id.relativelayout_for_fragment,routine_fragment,routine_fragment.getTag()).commit();
            Intent intent= new Intent(this,routine_tabbed.class);
            startActivity(intent);

        }

        else if (id == R.id.nav_task) {
            Toast.makeText(this, "Task", Toast.LENGTH_SHORT).show();
            //routine_fragment routine_fragment=new routine_fragment();
            //FragmentManager manager=getSupportFragmentManager();
            //manager.beginTransaction().replace(R.id.relativelayout_for_fragment,routine_fragment,routine_fragment.getTag()).commit();
            Intent intent = new Intent(this, TaskAcitivity.class);
            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void distancefunction(Location location) {

        Location userlocation = new Location("");
        Location userloc = new Location("");
        // double requireddist= ata.usersetlocation(location);
        // distanceclass.setCurrentLocation(location, MainActivity.this);
        //userlocation = distanceclass.getUserlocation();
        if (latitude != null && longitude != null) {
            userlocation.setLatitude(latitude);
            userlocation.setLongitude(longitude);
            double requireddist = location.distanceTo(userlocation);
            check=false;
            int requireddistance;
            String tname, locationname;
            tname = ata.gettaskname();
            locationname = ata.getlocationname();
            // requireddistance = distanceclass.getDistance();
            if (requireddist <= 90) {
                //taskname = ata.gettaskname();
                //locationname = ata.getlocationname();
                if (check == false) {
                    createNotification();
                }
            }
            if (check == false) {
                Toast.makeText(this, "Distanceremaining:" + requireddist, Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void createNotification() {
        MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.notification_sound);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);

        NotificationCompat.Builder notificationbuilder =new
                NotificationCompat.Builder(this)
                .setContentTitle("Task Notification")
                .setContentText("Task")
                .setTicker("ALert New Message")
                // .setSound(notification)
                .setSmallIcon(R.mipmap.ic_launcher);
        // .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        //  Notification notification = notificationbuilder.build();
        //notification.defaults |=Notification.DEFAULT_SOUND;
        //notification.defaults|=Notification.DEFAULT_VIBRATE;

        // notification.defaults|=Notification.DEFAULT_LIGHTS;.
        // notificationbuilder.setDefaults(Notification.DEFAULT_SOUND);
        Intent moreinfointent = new Intent(this,NotificationActivity.class);
        TaskStackBuilder tstackbuilder = TaskStackBuilder.create(this);
        tstackbuilder.addParentStack(NotificationActivity.class);
        tstackbuilder.addNextIntent(moreinfointent);
        PendingIntent pendingintent = tstackbuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationbuilder.setContentIntent(pendingintent);
        notificationbuilder.setAutoCancel(true);
        notificationmanager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationmanager.notify(notifyID,notificationbuilder.build());
        mp.start();
//        r.play();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 &&grantResults[0] ==PackageManager.PERMISSION_GRANTED)
                    configure();
                return;
        }
    }

    private void configure() {
        locationmanager.requestLocationUpdates("gps", 5000, 0, locationListener);

    }

    private void showinlistView() {
        final Cursor cursor = getContentResolver().query(TasksProvider.CONTENT_URI, TaskDbhelper.ALL_COLUMNS,null,null,null,null);
        String[] from = {TaskDbhelper.NOTE_TEXT,TaskDbhelper.LOC_NAME};
        int[] to ={android.R.id.text1,android.R.id.text2};
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to,0);
        final ListView list = (ListView) findViewById(android.R.id.list);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> cursorAdapter, View view, final int position, long id) {
                DialogInterface.OnClickListener dialogClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int button) {
                                if (button == DialogInterface.BUTTON_POSITIVE) {
                                    //Insert Data management code here
                                    deletetask(cursor.getString(COL_TASK_ID),true);
                                }
                            }

                            private void deletetask(String COL_TASK_ID, boolean b) {
                                getContentResolver().delete(TasksProvider.CONTENT_URI,NOTE_ID+ "=?",new String[]{COL_TASK_ID});

                                Toast.makeText(MainActivity.this,
                                        getString(R.string.deleted),
                                        Toast.LENGTH_SHORT).show();

                            }
                        };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(getString(R.string.are_you_sure))
                        .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                        .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                        .show();
                return false;
            }
        });
        list.setAdapter(cursorAdapter);

    }

    private void insertnote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(TaskDbhelper.NOTE_TEXT,noteText);
        Uri noteUri =getContentResolver().insert(TasksProvider.CONTENT_URI,values);

        Log.d("MainActivity","Inserted Note" +noteUri.getLastPathSegment());
    }
   /* public void deletetask()
    {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {
                            //Insert Data management code here
                            getContentResolver().delete(
                                    TasksProvider.CONTENT_URI,null,null
                            );
                            Toast.makeText(MainActivity.this,
                                    getString(R.string.deleted),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();
    }*/


    public void addTask(View view) {
        Intent intent= new Intent(this,AddTaskActivity.class);
        startActivity(intent);


    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    mVoiceInputTv.setText(result.get(0));

                    if((result.get(0).equals("deflectometer")) || (result.get(0).equals("open deflectometer") )){
                        Intent intent=new Intent(this,Deflectometer.class);
                        startActivity(intent);
                    }

                    if(result.get(0).equals("routine") ){
                        Intent intent=new Intent(this,routine_tabbed.class);
                        startActivity(intent);
                    }

                    if(result.get(0).equals("GPA calculator") ){
                        Intent intent=new Intent(this,GPACalculation.class);
                        startActivity(intent);
                    }

                    if(result.get(0).equals("task") ){
                        Intent intent=new Intent(this,TaskAcitivity.class);
                        startActivity(intent);
                    }
                }

                break;
            }

        }
    }


}
