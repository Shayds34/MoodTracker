package projet3.sebastien.chavagnas.com.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DatabaseHelper myDB = new DatabaseHelper(this);
        Cursor mCursor = myDB.getAllData();
        ArrayList<Integer> mCount = new ArrayList<>();

        while (mCursor.moveToNext()){
            int mMood = mCursor.getInt(0);
            mCount.add(mMood);
        }

        // Could it be simplified ?
        switch (mCount.size()) {
            case 0:
                Toast.makeText(this, "The history is empty.", Toast.LENGTH_LONG).show();
                this.finish();
                myDB.close();
                break;
            case 1:
                String[] mDates = {getString(R.string.yesterday)};
                Adapter(mDates);
                break;
            case 2:
                mDates = new String[]{getString(R.string.two_days_ago),
                                    getString(R.string.yesterday)};
                Adapter(mDates);
                break;
            case 3:
                mDates = new String[]{getString(R.string.three_days_ago),
                        getString(R.string.two_days_ago),
                        getString(R.string.yesterday)};
                Adapter(mDates);
                break;
            case 4:
                mDates = new String[]{getString(R.string.four_days_ago),
                        getString(R.string.three_days_ago),
                        getString(R.string.two_days_ago),
                        getString(R.string.yesterday)};
                Adapter(mDates);
                break;
            case 5:
                mDates = new String[]{getString(R.string.five_days_ago),
                        getString(R.string.four_days_ago),
                        getString(R.string.three_days_ago),
                        getString(R.string.two_days_ago),
                        getString(R.string.yesterday)};
                Adapter(mDates);
                break;
            case 6:
                mDates = new String[]{getString(R.string.six_days_ago),
                        getString(R.string.five_days_ago),
                        getString(R.string.four_days_ago),
                        getString(R.string.three_days_ago),
                        getString(R.string.two_days_ago),
                        getString(R.string.yesterday)};
                Adapter(mDates);
                break;
            case 7:
                mDates = new String[]{getString(R.string.seven_days_ago),
                        getString(R.string.six_days_ago),
                        getString(R.string.five_days_ago),
                        getString(R.string.four_days_ago),
                        getString(R.string.three_days_ago),
                        getString(R.string.two_days_ago),
                        getString(R.string.yesterday)};
                Adapter(mDates);
                break;
        }
    }

        public void Adapter(String[] mDates) {
            ListAdapter datesAdapter = new CustomAdapter(this, mDates);
            ListView listView = findViewById(R.id.list_item);
            listView.setAdapter(datesAdapter);
        }
    }
