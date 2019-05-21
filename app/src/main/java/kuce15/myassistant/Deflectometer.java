package kuce15.myassistant;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class Deflectometer extends AppCompatActivity {

    EditText inputmarks;
    TextView updateresult, updatemaxmarks,minimummarkstv,Amarkstv,Aminusmarkstv,Bplusmarkstv,Bmarkstv,Bminusmarkstv;
    TextView chooseinternalmarksTextview;
    Button calculatedeflection;
    Button capturescreenshot;
    Bitmap mbitmap;
    String fintmarks;

    static int finternalmarks;
    double internalmarks = 0;
    boolean twentyfive;
    boolean fifty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deflectometer);

        inputmarks = (EditText) findViewById(R.id.marksinput);
        calculatedeflection = (Button) findViewById(R.id.button);
        updateresult = (TextView) findViewById(R.id.UpdateResult);
        updatemaxmarks = (TextView) findViewById(R.id.updatemaxgrade);
        chooseinternalmarksTextview=(TextView) findViewById(R.id.textView5) ;
        capturescreenshot = (Button) findViewById(R.id.button2);
        minimummarkstv = (TextView) findViewById(R.id.textView8);

        Amarkstv = (TextView) findViewById(R.id.textView9);
        Aminusmarkstv = (TextView) findViewById(R.id.textView12);

        Bplusmarkstv = (TextView) findViewById(R.id.textView13);


        Bmarkstv = (TextView) findViewById(R.id.textView15);


        Bminusmarkstv = (TextView) findViewById(R.id.textView16);
        if (shouldAskPermissions()) {
            askPermissions();
        }

    }


    public void calculatedeflectionthreshold(View view) {
        int marks = Integer.parseInt(inputmarks.getText().toString());
        internalmarks = convertinternalmarks(marks);

        maxgrade();
        minimummarks();
        Amarks();
        Aminusmarks();
        Bplusmarks();
        Bmarks();
        Bminusmarks();
        updateresult.setText("Required marks to avoid deflection is:" + internalmarks);


        ///if (twentyfive ==false && fifty==false)
        /// Toast.makeText(this,"PLEASE CHOOSE THE INTERNAL MARKS",Toast.LENGTH_SHORT).show();
        //else if ()
    }

    //calculate max grade when deflection occurs
    private void maxgrade() {
        double maxmarksafterdeflection = internalmarks - 1;
        int fullmarks = 100 - finternalmarks;
        double highestmarks = (maxmarksafterdeflection / fullmarks) * 100;
        if (highestmarks >= 80 && highestmarks <= 100) {
            updatemaxmarks.setText("Highest Grade after deflection is A");
        }
        if (highestmarks >= 75 && highestmarks < 80) {
            updatemaxmarks.setText("Highest Grade after deflection is A-");
        }
        if (highestmarks >= 70 && highestmarks < 75) {
            updatemaxmarks.setText("Highest Grade after deflection is B+");

        }
        if (highestmarks >= 65 && highestmarks < 70) {
            updatemaxmarks.setText("Highest Grade after deflection is B");

        }
        if (highestmarks >= 60 && highestmarks < 65) {
            updatemaxmarks.setText("Highest Grade after deflection is B-");

        }
        if (highestmarks >= 55 && highestmarks < 60) {
            updatemaxmarks.setText("Highest Grade after deflection is C+");

        }
        if (highestmarks >= 50 && highestmarks < 55) {
            updatemaxmarks.setText("Highest Grade after deflection is C");

        }
        if (highestmarks >= 45 && highestmarks < 50) {
            updatemaxmarks.setText("Highest Grade after deflection is C-");

        }
        if (highestmarks >= 40 && highestmarks < 45) {
            updatemaxmarks.setText("Highest Grade after deflection is D");
        }
        if (highestmarks >= 40 && highestmarks < 45) {
            updatemaxmarks.setText("Highest Grade after deflection is D");
        }


    }

    private void shareImage(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    //calculate deflection threshold
    private double convertinternalmarks(int intmarks) {
        double finalmarks = 0;
        setfullmarksofinternala();
        setfullmarksofinternalb();
        finalmarks = (0.75 * (100 - finternalmarks) * intmarks) / finternalmarks;

        return finalmarks;


    }

    public void setfullmarksofinternala() {
        if (fintmarks == "25") {
            finternalmarks = 25;
        }
        else
            twentyfive=false;
    }

    public void setfullmarksofinternalb() {
        if(fintmarks=="50") {
            finternalmarks = 50;

        }
        else   fifty=false;
    }

    public void createpdffile(View view) {
        mbitmap = getBitmapOFRootView(capturescreenshot);
        createImagebisection(mbitmap);

    }
    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
       requestPermissions(permissions,requestCode);
    }




    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }
    public void createImagebisection(Bitmap bmp) {
        int min = 0;
        int max = 1000000;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;

        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Pictures" + File.separator +"MyAssistant"+ File.separator+ "GPA" + i1 + ".png");

        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        shareImage(file);
    }


    public void chooseinternalfullmarks(View view) {
        final String[] items = new String[2];

        items[0] = "25";
        items[1] = "50";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                fintmarks= items[item];
                chooseinternalmarksTextview.setText(fintmarks);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void minimummarks() {
        minimummarkstv.setText("Minimum marks required to pass is 30");

    }

    public void Amarks() {

        int input = Integer.parseInt(inputmarks.getText().toString());
        int Amark = 80-input;
        Amarkstv.setText("Marks required to get an A:"+Amark);

    }

    public void Aminusmarks() {
        int input = Integer.parseInt(inputmarks.getText().toString());
        int Aminusmark = 75-input;
        Aminusmarkstv.setText("Marks required to get an A-:"+Aminusmark);

    }

    public void Bplusmarks() {
        int input = Integer.parseInt(inputmarks.getText().toString());
        int Bplusmark = 70-input;
        Bplusmarkstv.setText("Marks required to get  B+:"+Bplusmark);

    }

    public void Bmarks() {
        int input = Integer.parseInt(inputmarks.getText().toString());
        int Bmark = 65-input;
        Bmarkstv.setText("Marks required to get  B:"+Bmark);
    }

    public void Bminusmarks() {
        int input = Integer.parseInt(inputmarks.getText().toString());
        int Bminusmark = 60-input;
        Bminusmarkstv.setText("Marks required to get  B-:"+Bminusmark);

    }
}

