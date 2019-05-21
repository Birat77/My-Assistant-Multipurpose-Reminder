package kuce15.myassistant.GPA;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

import kuce15.myassistant.R;

import static kuce15.myassistant.R.id.GradeText1;
import static kuce15.myassistant.R.id.GradeText2;
import static kuce15.myassistant.R.id.GradeText3;

public class SevenRows extends AppCompatActivity {
    TextView GradeTextView1;
    TextView GradeTextView2;
    TextView GradeTextView3;
    TextView GradeTextView4;
    TextView GradeTextView5;
    TextView GradeTextView6;
    TextView GradeTextView7;

    EditText NameText;
    EditText RollNoText;
    EditText DepartmentText;


    String sName;
    String sRollNo;
    String sDepartment;


    TextView SHOWGPA;

    TextView CreditTextView1;
    TextView CreditTextView2;
    TextView CreditTextView3;
    TextView CreditTextView4;
    TextView CreditTextView5;
    TextView CreditTextView6;
    TextView CreditTextView7;

    String grade1;
    String grade2;
    String grade3;
    String grade4;
    String grade5;
    String grade6;
    String grade7;

    String credit1;
    String credit2;
    String credit3;
    String credit4;
    String credit5;
    String credit6;
    String credit7;

    double subject_credit1;
    double subject_credit2;
    double subject_credit3;
    double subject_credit4;
    double subject_credit5;
    double subject_credit6;
    double subject_credit7;

    double subject_grade1;
    double subject_grade2;
    double subject_grade3;
    double subject_grade4;
    double subject_grade5;
    double subject_grade6;
    double subject_grade7;

    EditText SubjectText1;
    EditText SubjectText2;
    EditText SubjectText3;
    EditText SubjectText4;
    EditText SubjectText5;
    EditText SubjectText6;
    EditText SubjectText7;

    Button capturescreenshot;
    Bitmap mbitmap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_rows);
        SubjectText1=(EditText)findViewById(R.id.editText1);
        SubjectText2=(EditText)findViewById(R.id.editText2);
        SubjectText3=(EditText)findViewById(R.id.editText3);
        SubjectText4=(EditText)findViewById(R.id.editText4);
        SubjectText5=(EditText)findViewById(R.id.editText5);
        SubjectText6=(EditText)findViewById(R.id.editText6);
        SubjectText7=(EditText)findViewById(R.id.editText7);

        GradeTextView1=(TextView)findViewById(R.id.GradeText1);
        GradeTextView2=(TextView)findViewById(R.id.GradeText2);
        GradeTextView3=(TextView)findViewById(R.id.GradeText3);
        GradeTextView4=(TextView)findViewById(R.id.GradeText4);
        GradeTextView5=(TextView)findViewById(R.id.GradeText5);
        GradeTextView6=(TextView)findViewById(R.id.GradeText6);
        GradeTextView7=(TextView)findViewById(R.id.GradeText7);

        CreditTextView1=(TextView) findViewById(R.id.CreditText1);
        CreditTextView2=(TextView) findViewById(R.id.CreditText2);
        CreditTextView3=(TextView) findViewById(R.id.CreditText3);
        CreditTextView4=(TextView) findViewById(R.id.CreditText4);
        CreditTextView5=(TextView) findViewById(R.id.CreditText5);
        CreditTextView6=(TextView) findViewById(R.id.CreditText6);
        CreditTextView7=(TextView) findViewById(R.id.CreditText7);

        NameText=(EditText)findViewById(R.id.nametxt);
        RollNoText=(EditText)findViewById(R.id.rollnotxt);
        DepartmentText=(EditText)findViewById(R.id.departmenttxt);

        SHOWGPA=(TextView) findViewById(R.id.showGpa);
        capturescreenshot =(Button)findViewById(R.id.button5);

        if (shouldAskPermissions()) {
            askPermissions();
        }



    }

    public void Grade1(View view) {
        final String[] items = new String[10];

        items[0] = "A";
        items[1] = "A-";
        items[2] = "B+";
        items[3] = "B";
        items[4] = "B-";
        items[5] = "C+";
        items[6] = "C";
        items[7] = "C-";
        items[8] = "D";
        items[9] = "F";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                grade1= items[item];
                GradeTextView1.setText(grade1);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Grade2(View view) {
        final String[] items = new String[10];

        items[0] = "A";
        items[1] = "A-";
        items[2] = "B+";
        items[3] = "B";
        items[4] = "B-";
        items[5] = "C+";
        items[6] = "C";
        items[7] = "C-";
        items[8] = "D";
        items[9] = "F";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                grade2= items[item];
                GradeTextView2.setText(grade2);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Grade3(View view) {
        final String[] items = new String[10];

        items[0] = "A";
        items[1] = "A-";
        items[2] = "B+";
        items[3] = "B";
        items[4] = "B-";
        items[5] = "C+";
        items[6] = "C";
        items[7] = "C-";
        items[8] = "D";
        items[9] = "F";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                grade3= items[item];
                GradeTextView3.setText(grade3);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Grade4(View view) {
        final String[] items = new String[10];

        items[0] = "A";
        items[1] = "A-";
        items[2] = "B+";
        items[3] = "B";
        items[4] = "B-";
        items[5] = "C+";
        items[6] = "C";
        items[7] = "C-";
        items[8] = "D";
        items[9] = "F";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                grade4= items[item];
                GradeTextView4.setText(grade4);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void Grade5(View view) {
        final String[] items = new String[10];

        items[0] = "A";
        items[1] = "A-";
        items[2] = "B+";
        items[3] = "B";
        items[4] = "B-";
        items[5] = "C+";
        items[6] = "C";
        items[7] = "C-";
        items[8] = "D";
        items[9] = "F";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                grade5= items[item];
                GradeTextView5.setText(grade5);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void Grade6(View view) {
        final String[] items = new String[10];

        items[0] = "A";
        items[1] = "A-";
        items[2] = "B+";
        items[3] = "B";
        items[4] = "B-";
        items[5] = "C+";
        items[6] = "C";
        items[7] = "C-";
        items[8] = "D";
        items[9] = "F";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                grade6= items[item];
                GradeTextView6.setText(grade6);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void Credit7(View view) {
        final String[] items = new String[4];

        items[0] = "1";
        items[1] = "2";
        items[2] = "3";
        items[3] = "4";



        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Credit");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                credit7= items[item];
                CreditTextView7.setText(credit7);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Grade7(View view) {
        final String[] items = new String[10];

        items[0] = "A";
        items[1] = "A-";
        items[2] = "B+";
        items[3] = "B";
        items[4] = "B-";
        items[5] = "C+";
        items[6] = "C";
        items[7] = "C-";
        items[8] = "D";
        items[9] = "F";


        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Grade");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                grade7= items[item];
                GradeTextView7.setText(grade7);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void Credit1(View view) {
        final String[] items = new String[4];

        items[0] = "1";
        items[1] = "2";
        items[2] = "3";
        items[3] = "4";



        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Credit");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                credit1= items[item];
                CreditTextView1.setText(credit1);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Credit2(View view) {
        final String[] items = new String[4];

        items[0] = "1";
        items[1] = "2";
        items[2] = "3";
        items[3] = "4";



        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Credit");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                credit2= items[item];
                CreditTextView2.setText(credit2);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Credit3(View view) {
        final String[] items = new String[4];

        items[0] = "1";
        items[1] = "2";
        items[2] = "3";
        items[3] = "4";



        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Credit");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                credit3= items[item];
                CreditTextView3.setText(credit3);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Credit4(View view) {
        final String[] items = new String[4];

        items[0] = "1";
        items[1] = "2";
        items[2] = "3";
        items[3] = "4";



        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Credit");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                credit4= items[item];
                CreditTextView4.setText(credit4);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void Credit5(View view) {
        final String[] items = new String[4];

        items[0] = "1";
        items[1] = "2";
        items[2] = "3";
        items[3] = "4";



        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Credit");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                credit5= items[item];
                CreditTextView5.setText(credit5);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void Credit6(View view) {
        final String[] items = new String[4];

        items[0] = "1";
        items[1] = "2";
        items[2] = "3";
        items[3] = "4";



        // Create List Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Credit");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                credit6= items[item];
                CreditTextView6.setText(credit6);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    double gradeconversion(String grade)
    {
        double subject_grade=0;
        if(grade=="A")
        {
            subject_grade=4.00;
        }
        else if (grade=="A-")
        {
            subject_grade=3.70;
        }
        else if (grade=="B+")
        {
            subject_grade=3.30;
        }
        else if (grade=="B")
        {
            subject_grade=3.00;
        }
        else if (grade=="B-")
        {
            subject_grade=2.70;
        }
        else if (grade=="C+")
        {
            subject_grade=2.30;
        }
        else if (grade=="C")
        {
            subject_grade=2.00;
        }
        else if (grade=="C-")
        {
            subject_grade=1.70;
        }
        else if (grade=="D")
        {
            subject_grade=1;
        }
        else if (grade=="F") {


            subject_grade = 0;
        }
        return subject_grade;
    }
    double creditconversion(String credit)
    {
        double subject_credit=1;
        if(credit=="1")
        {
            subject_credit=1;
        }
        else if (credit=="2")
        {
            subject_credit=2;
        }
        else if (credit=="3")
        {
            subject_credit=3;
        }
        else if (credit=="4"){
            subject_credit=4;
        }
        return subject_credit;

    }

    public void CALCULATE(View view) {
        double totalcredits=0.00, totalscore=0.00,GPA=0.00;
        subject_credit1=creditconversion(credit1);
        subject_credit2=creditconversion(credit2);
        subject_credit3=creditconversion(credit3);
        subject_credit4=creditconversion(credit4);
        subject_credit5=creditconversion(credit5);
        subject_credit6=creditconversion(credit6);
        subject_credit7=creditconversion(credit7);

        subject_grade1=gradeconversion(grade1);
        subject_grade2=gradeconversion(grade2);
        subject_grade3=gradeconversion(grade3);
        subject_grade4=gradeconversion(grade4);
        subject_grade5=gradeconversion(grade5);
        subject_grade6=gradeconversion(grade6);
        subject_grade7=gradeconversion(grade7);

        totalcredits=(subject_credit1+subject_credit2+subject_credit3+subject_credit4+subject_credit5+subject_credit6+subject_credit7);
        totalscore=((subject_grade1*subject_credit1)+(subject_grade2*subject_credit2)+(subject_grade3*subject_credit3)+(subject_grade4*subject_credit4)+(subject_grade5*subject_credit5)+(subject_grade6*subject_credit6)+(subject_credit7*subject_grade7));
        GPA=(totalscore/totalcredits);
        sName= NameText.getText().toString();
        sRollNo= RollNoText.getText().toString();
        sDepartment= DepartmentText.getText().toString();

        StudentDatabase rb = new StudentDatabase(this);
        //BackGroundTask backGroundTask=new BackGroundTask(this);
        int ID = rb.addReminder(new Student(sName, sRollNo, sDepartment, String.valueOf(GPA) ));

        SHOWGPA.setText(String.valueOf(GPA));




    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        //  requestPermissions(permissions, requestCode);
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
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Pictures"+ File.separator +"MyAssistant"+ File.separator+ "GPA" + i1 + ".png");

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

    private void shareImage(File file){
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

    public void takescreenshot(View view) {
        mbitmap= getBitmapOFRootView(capturescreenshot);
        createImagebisection(mbitmap);
    }





}

