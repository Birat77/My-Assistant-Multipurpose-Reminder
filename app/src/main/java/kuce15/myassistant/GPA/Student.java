package kuce15.myassistant.GPA;

/**
 * Created by Shoaib on 2/26/2017.
 */

public class Student {

    private int sID;
    private String sName;
    private String sRollNo;
    private String sDepartment;
    private String sGPA;



    public Student(int sID, String sName, String sRollNo , String sDepartment, String sGPA){
       /* sID = ID;
        sCourse = course;
        sCourseInstructor = courseinstructor;
        sTime = time;
        sNotification = notification; */
        this.setID(sID);
        this.setsName(sName);
        this.setsRollNo(sRollNo);
        this.setsDepartment(sDepartment);
        this.setsGPA(sGPA);

    }

    public Student(String name, String rollno, String department, String gpa){
        sName = name;
        sRollNo = rollno;
        sDepartment = department;
        sGPA = gpa;
    }


    // public SundayReminder(){}

    public int getsID() {
        return sID;
    }

    public void setID(int sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsRollNo() {
        return sRollNo;
    }

    public void setsRollNo(String sRollNo) {
        this.sRollNo = sRollNo;
    }

    public String getsDepartment() {
        return sDepartment;
    }

    public void setsDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public String getsGPA() {
        return sGPA;
    }

    public void setsGPA(String sGPA) {
        this.sGPA = sGPA;
    }
}
