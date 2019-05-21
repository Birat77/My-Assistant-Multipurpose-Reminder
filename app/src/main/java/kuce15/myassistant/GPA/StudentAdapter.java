package kuce15.myassistant.GPA;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kuce15.myassistant.R;

//import static kuce15.myassistant.R.id.recycle_time;

/**
 * Created by Shoaib on 2/27/2017.
 */

public class StudentAdapter extends ArrayAdapter {
    List list= new ArrayList();

    // private Context scontext;
    //private List<SundayReminder> slist;

    public StudentAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Student object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        Holder holder;
        if (row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.routine,parent,false);
            holder=new Holder();
            holder.sNameText= (TextView) row.findViewById(R.id.recycle_name);
            holder.sRollNoText= (TextView) row.findViewById(R.id.recycle_roll_no);
            holder.sDepartment= (TextView) row.findViewById(R.id.recycle_department);
            holder.sGPA= (TextView) row.findViewById(R.id.recycle_gpa);
            row.setTag(holder);

        }
        {
            holder=(Holder)row.getTag();
        }
        Student student=(Student) getItem(position);
        holder.sNameText.setText(student.getsName());
        holder.sRollNoText.setText(student.getsRollNo());
        holder.sDepartment.setText(student.getsDepartment());
        holder.sGPA.setText(student.getsGPA());

        return row;
    }
    private static class  Holder
    {
        TextView sNameText,sRollNoText,sDepartment,sGPA;
    }
    //constructor
/*
    public SundayAdapter(List<SundayReminder> slist, Context scontext) {
        this.slist = slist;
        this.scontext = scontext;
    }

    @Override
    public int getCount() {
        return slist.size();
    }

    @Override
    public Object getItem(int position) {
        return slist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(scontext, R.layout.routine, null);
        TextView sTime=(TextView)v.findViewById(R.id.time_text);
        TextView sCourse=(TextView)v.findViewById(R.id.reminder_course);
        TextView sCourseInstructor=(TextView)v.findViewById(R.id.reminder_course_instructor);
        recycle_time.setText(slist.get(position).getTime());

        return null;
    }
    */
}
