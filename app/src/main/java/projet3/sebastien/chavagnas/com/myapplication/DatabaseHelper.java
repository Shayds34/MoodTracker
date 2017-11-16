package projet3.sebastien.chavagnas.com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDataBase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String MOOD_KEY = "_id";
    static final String MOOD_DATE = "date";
    static final String MOOD_COMMENT = "comment";
    private static final String MOOD_MOOD = "mood";

    private static final String MOOD_TABLE_NAME = "my_moods";

    private static final String MOOD_TABLE_CREATE = "CREATE TABLE " +
            MOOD_TABLE_NAME + " (" +
            MOOD_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MOOD_DATE + " TEXT, " +
            MOOD_COMMENT + " TEXT, " +
            MOOD_MOOD + " INTEGER);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MOOD_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOOD_TABLE_NAME);
    }

    // Add data to the database via Content Values
    boolean addData(String date, String comment, int mood) {
        SQLiteDatabase mSQLiteDatabase = this.getWritableDatabase();
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(MOOD_DATE, date);
        mContentValues.put(MOOD_COMMENT, comment);
        mContentValues.put(MOOD_MOOD, mood);

        long mResult = mSQLiteDatabase.insert(MOOD_TABLE_NAME, null, mContentValues);

        // if (mResult != -1) {
        //     return true;
        // } else {
        //     return false;
        // }
        // Simplify to :
        return mResult != -1;
    }


    Cursor getListContents(){
        SQLiteDatabase mSQLiteDatabase = this.getWritableDatabase();
        Cursor cursor;

        // Show all comments:
        // cursor = mSQLiteDatabase.rawQuery("SELECT _id, date, comment FROM " + MOOD_TABLE_NAME + " ORDER BY " + MOOD_KEY + " DESC", null);

        // Show only the last 7 comments:
        cursor = mSQLiteDatabase.rawQuery("SELECT _id, date, comment FROM " + MOOD_TABLE_NAME + " ORDER BY " + MOOD_KEY + " DESC LIMIT 7", null);

        return cursor;
    }
}