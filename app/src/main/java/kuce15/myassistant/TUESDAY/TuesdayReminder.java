package kuce15.myassistant.TUESDAY;

/**
 * Created by asish on 3/3/17.
 */

public class TuesdayReminder {

    private int sID;
    private String sCourse;
    private String sCourseInstructor;
    private String sTime;
    private String sNotification;



    public TuesdayReminder(int sID, String sCourse, String sCourseinstructor, String sTime, String sNotification){
       /* sID = ID;
        sCourse = course;
        sCourseInstructor = courseinstructor;
        sTime = time;
        sNotification = notification; */
        this.setID(sID);
        this.setsCourse(sCourse);
        this.setsCourseInstructor(sCourseinstructor);
        this.setTime(sTime);
        this.setsNotification(sNotification);

    }

    public TuesdayReminder(String course, String courseinstructor, String time, String notification){
        sCourse = course;
        sCourseInstructor = courseinstructor;
        sTime = time;
        sNotification = notification;
    }


    // public SundayReminder(){}

    public int getsID() {
        return sID;
    }

    public void setID(int sID) {
        this.sID = sID;
    }

    public String getsCourse() {
        return sCourse;
    }

    public void setsCourse(String sCourse) {
        this.sCourse = sCourse;
    }

    public String getsCourseInstructor() {
        return sCourseInstructor;
    }

    public void setsCourseInstructor(String sCourseInstructor) {
        this.sCourseInstructor = sCourseInstructor;
    }

    public String getTime() {
        return sTime;
    }

    public void setTime(String sTime) {
        this.sTime = sTime;
    }

    public String getsNotification() {
        return sNotification;
    }

    public void setsNotification(String sNotification) {
        this.sNotification = sNotification;
    }
}


