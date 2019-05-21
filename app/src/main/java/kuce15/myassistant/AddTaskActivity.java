package kuce15.myassistant;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    // taskdatabase Taskdatabase;
    TextView locationnameinput;
    EditText selectname;
    EditText selectloc;
    Task mTask;
    LatLng latLng;
    int PLACE_PICKER_REQUEST =1;
    String loc;
    String tname;
    static String locname;
    static String tasname;
    String tloc;
    ListView list;
  static  Double latitude, longitude;
    int remindDistance= 75;
    //Location location;
    //Distance distanceclass= new Distance();




    public static class Task {
        String mTaskName;
        String mTaskLocation;
        int mRemindDistance =75;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mTask = new Task();

        // mTask.mRemindDistance = 100;

        selectname=(EditText)findViewById(R.id.task_name_input);
        selectloc=(EditText)findViewById(R.id.locationNameInput);
        loc = selectloc.getText().toString();
        locationnameinput =(TextView)findViewById(R.id.selectlcn);

        locationnameinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder= new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(getApplicationContext());
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });



    }

    public   void onActivityResult(int requestcode,int resultcode,Intent data){
        if(requestcode==PLACE_PICKER_REQUEST)
        {
            if(resultcode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);
                String address= String.format("Place: %s",place.getAddress());
                latLng = place.getLatLng();
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                //String address = String.valueOf(latitude)+String.valueOf(longitude);
                selectloc.setText(address);

            }
        }
    }


    public void Alarm(View view) {
        switch (view.getId())
        {
            case R.id.alarm:
                showDateselectionDialog();
                break;


        }
    }
    public void SaveTask(View view) {
        // createTask();
        mTask.mTaskLocation= loc;
        inserttasknameintodb();
       // getCurrentLocation(this);
        Location setlocation = new Location("");
        setlocation.setLatitude(latLng.latitude);
        setlocation.setLongitude(latLng.longitude);
        Toast.makeText(this,"Lat is "+latitude,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Long is "+longitude,Toast.LENGTH_SHORT).show();


        // Toast.makeText(this,"Long is "+longitude,Toast.LENGTH_SHORT).show();
      //distanceclass.setUsersetlocation(setlocation,AddTaskActivity.this);

        //usersetlocation();


        Intent nintent = new Intent(this,MainActivity.class);
        startActivity(nintent);
        //finish();

    }

     public Location usersetlocation(Location currentLocation) {
        Location usersetlocationn = new Location("");
        usersetlocationn.setLatitude(latLng.latitude);
        usersetlocationn.setLongitude(latLng.longitude);
        Toast.makeText(this,"Lat is "+latitude,Toast.LENGTH_SHORT).show();
         Toast.makeText(this,"Long is "+longitude,Toast.LENGTH_SHORT).show();
         //double requireddist = currentLocation.distanceTo(usersetlocationn);

         return  usersetlocationn;
    }



    private void inserttasknameintodb() {
        tname =selectname.getText().toString();
        tloc = selectloc.getText().toString();
        tasname=tname;
        locname=tloc;
        String noteLocation = tloc;
        String noteText = tname;
        ContentValues values = new ContentValues();
        values.put(TaskDbhelper.NOTE_TEXT,noteText);
        values.put(TaskDbhelper.LOC_NAME,noteLocation);
        Uri noteUri =getContentResolver().insert(TasksProvider.CONTENT_URI,values);

        Log.d("MainActivity","Inserted Note" +noteUri.getLastPathSegment());

    }
        public String gettaskname(){
            return  tname;
        }
    public  String getlocationname(){
        return tloc;
    }


    private void showDateselectionDialog() {
        Date currentDate = Calendar.getInstance().getTime() ;

        DatePickerDialog mDPDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(AddTaskActivity.this, "Date is: " + year + "/" + month + "/" + dayOfMonth,Toast.LENGTH_LONG).show();
            }
        }, 2017,1,14);
        mDPDialog.show();


    }

}
