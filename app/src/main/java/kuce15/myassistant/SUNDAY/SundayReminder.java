package kuce15.myassistant.SUNDAY;

/**
 * Created by Shoaib on 2/26/2017.
 */

public class SundayReminder {

    private int sID;
    private String sCourse;
    private String sCourseInstructor;
    private String sTime;
    private String sNotification;

    public SundayReminder(int sID, String sCourse, String sCourseinstructor, String sTime, String sNotification){
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

    public SundayReminder(String course, String courseinstructor, String time, String notification){
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
