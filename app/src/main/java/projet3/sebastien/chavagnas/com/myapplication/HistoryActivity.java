package projet3.sebastien.chavagnas.com.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class HistoryActivity extends AppCompatActivity {

    // My Database
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Open Database
        myDB = new DatabaseHelper(this);

        // Create a new cursor showing the content of getListContents
        Cursor cursor = myDB.getListContents();

        // Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[] {DatabaseHelper.MOOD_DATE, DatabaseHelper.MOOD_COMMENT};
        int[] toViewIDs = new int[] {R.id.display_date, R.id.display_comment};


        //Create the adapter to map columns of the DB onto elements of the UI
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,                   // Context
                        R.layout.list_item_comments,    // Our row template
                        cursor,                         // Our cursor
                        fromFieldNames,                 // Our columns in DataBase
                        toViewIDs,                      // Our views in custom layout
                        0
                );

        // Set the adapter for the list view
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(myCursorAdapter);


    }
}
