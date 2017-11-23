package projet3.sebastien.chavagnas.com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MoodTrackerDataB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String MOOD_KEY = "_id";
    private static final String MOOD_DATE = "date";
    private static final String MOOD_COMMENT = "comment";
    private static final String MOOD_MOOD = "mood";

    private static final String MOOD_TABLE_NAME = "my_moods";

    private static final String MOOD_TABLE_CREATE = "CREATE TABLE " +
            MOOD_TABLE_NAME + " (" +
            MOOD_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MOOD_DATE + " DATETIME, " +
            MOOD_COMMENT + " TEXT, " +
            MOOD_MOOD + " INTEGER);";

    // Constructor
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Table creation
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MOOD_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOOD_TABLE_NAME);
    }

    // Add data to the database via Content Values
     void addMood(MoodItem moodItem){
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(MOOD_DATE, moodItem.getDate());
        mContentValues.put(MOOD_COMMENT, moodItem.getComment());
        mContentValues.put(MOOD_MOOD, moodItem.getMood());

        myDB.insert(MOOD_TABLE_NAME, null, mContentValues);
        myDB.close();
    }

    // Select the 7 last data from the Database starting from yesterday
    Cursor getAllData(){
        SQLiteDatabase mSQLiteDatabase = this.getWritableDatabase();
        Cursor cursor;
        cursor = mSQLiteDatabase.rawQuery("SELECT _id, strftime('%d-%m-%Y', date) AS one_day, comment, mood  FROM " + MOOD_TABLE_NAME + " WHERE one_day IS NOT NULL AND one_day < date('now', '-1 day') GROUP BY one_day ORDER BY " + MOOD_KEY + " DESC LIMIT 7", null);
        return cursor;
    }

    Cursor getDates(){
        SQLiteDatabase mSQLiteDatabase = this.getWritableDatabase();
        Cursor cursor;
        cursor = mSQLiteDatabase.rawQuery("SELECT strftime('%d-%m-%Y', date) AS one_day FROM " + MOOD_TABLE_NAME + " WHERE one_day IS NOT NULL AND one_day < date('now', '-1 day') GROUP BY one_day ORDER BY " + MOOD_KEY + " DESC LIMIT 7", null);
        return cursor;
    }
}