package projet3.sebastien.chavagnas.com.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class HistoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DatabaseHelper myDB = new DatabaseHelper(this);
        Cursor mCursor = myDB.getAllData();

        ArrayList<String> mAllDates = new ArrayList<>();

        while (mCursor.moveToNext()){
            String mDate = mCursor.getString(1);
            mAllDates.add(mDate);
        }

        Collections.reverse(mAllDates);
        String[] mDates = new String[mAllDates.size()];

        for (int i = 0; i < mDates.length; i++){
            mDates[i] = mAllDates.get(i);
        }

        if (mAllDates.size() == 0){
            Toast.makeText(this, "The history is empty.", Toast.LENGTH_LONG).show();
            this.finish();
            myDB.close();
        } else {
            Adapter(mDates);
        }
    }

        public void Adapter(String[] mDates) {
            ListAdapter datesAdapter = new CustomAdapter(this, mDates);
            ListView listView = findViewById(R.id.list_item);
            listView.setAdapter(datesAdapter);
        }
    }
