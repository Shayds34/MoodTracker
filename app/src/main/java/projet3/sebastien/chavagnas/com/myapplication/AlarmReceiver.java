package projet3.sebastien.chavagnas.com.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AlarmReceiver extends BroadcastReceiver {
    DatabaseHelper myDB;

    @Override
     public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("ALARM", "Action intent is : " + action);

        // Open database
        myDB = new DatabaseHelper(context);

        // Set date format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String mDate = simpleDateFormat.format(new Date());

        // Data from SharedPref into SQLite Database.
        final SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String mComment = (mSharedPreferences.getString("Comment", ""));
        int mMood = (mSharedPreferences.getInt("Mood", 0));
        myDB.addMood(new MoodItem(mDate, mComment, mMood));

        // Log test
        Log.d("ALARM", "Alarm !!! " + mDate + " " + mComment + " " + mMood);

        // Reset mood for the next day
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("Mood", 3);
        editor.apply();
    }

    public void setAlarmRepeat(Context context){
        // alarmUp is true if the Alarm already exists
        boolean alarmUp = (PendingIntent.getBroadcast(context, 0, new Intent("com.projet3.sebastien.chavagnas.com.myapplication.MY_UNIQUE_ACTION"), PendingIntent.FLAG_NO_CREATE) != null);

        // Check if the Alarm already exists
        if (alarmUp){
            Log.d("ALARM", "Alarm is already active");
        } else {
            // Simple Log test
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String mDate = simpleDateFormat.format(new Date());
            Log.d("ALARM", "Alarm is created ! " + mDate);

            // Setup the Alarm
            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.setAction("com.projet3.sebastien.chavagnas.com.myapplication.MY_UNIQUE_ACTION");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // The last time of day the mood will be saved
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 30);

            // Set AlarmManager with interval_day = 24. Every 24h
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }

        }
    }
}
