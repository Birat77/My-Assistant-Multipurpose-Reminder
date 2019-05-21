package kuce15.myassistant.MONDAY;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import kuce15.myassistant.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class monday extends Fragment {
    private FloatingActionButton mAddRoutine;
    Button btnMonday;
    MondayDatabase myDB;
    private int sReceivedID;
    // Constant Intent String


    public monday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.monday, container, false);
        ;
        mAddRoutine = (FloatingActionButton) view.findViewById(R.id.add_routine);
        mAddRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MondayAdd.class);
                startActivity(intent);
            }
        });

        btnMonday=(Button)view.findViewById(R.id.buttonMonday);
        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteMonday();
            }
        });
        // Get reminder id from intent

        ListView listView = (ListView) view.findViewById(R.id.listView);
        ListView listView1 = (ListView) view.findViewById(R.id.listView1);
        ListView listView2 = (ListView) view.findViewById(R.id.listView2);
        myDB = new MondayDatabase(getActivity());

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        ArrayList<String> thelist1 = new ArrayList<>();
        ArrayList<String> thelist2 = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if (data.getCount() == 0) {
            Toast.makeText(getActivity(), "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                {
                    theList.add(data.getString(1));
                    ListAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, theList);
                    listView.setAdapter(listAdapter);
                }
                {
                    thelist1.add(data.getString(2));
                    ListAdapter listAdapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, thelist1);
                    listView1.setAdapter(listAdapter1);
                }
                {
                    thelist2.add(data.getString(3));
                    ListAdapter listAdapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, thelist2);
                    listView2.setAdapter(listAdapter2);
                }
            }
        }
        //{
        //  thelist2.add(data.getString(3));
        //  ListAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, thelist2);
        //  listView2.setAdapter(listAdapter);
        // }


        return view;


    }
}
  /*  public void AddData(String newEntry) {

        boolean insertData = myDB.addData(newEntry);


    }
*/

