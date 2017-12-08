package projet3.sebastien.chavagnas.com.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // There is 5 different moods
    private final static int NUMBER_MOODS = 5;

    // My SharedPreferences
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmReceiver alarmReceiver = new AlarmReceiver();
        alarmReceiver.setAlarmRepeat(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Create the adapter that will return a fragment for each of the five moods
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the VerticalViewPager with the sections adapter
        final VerticalViewPager verticalViewPager = findViewById(R.id.container2);
        verticalViewPager.setAdapter(sectionsPagerAdapter);

        // Starting the app from the saved fragment, or from Happy Mood fragment if it's a new day
        verticalViewPager.setCurrentItem(3);

        // Set up buttons
        ImageView comment_btn = findViewById(R.id.comment_fab);
        ImageView history_btn = findViewById(R.id.history_fab);

        // Comment - Button
        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                final ViewGroup nullParent = null;
                final View mView = getLayoutInflater().inflate(R.layout.comment_dialog_box, nullParent);

                mBuilder.setView(mView)
                        .setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                EditText etComment = mView.findViewById(R.id.etComment);

                                // The current position of our VerticalViewPager
                                // From where the user want to comment the mood of the day
                                int mood = verticalViewPager.getCurrentItem();

                                // Saved comment
                                String mSavedComment = "" + getText(R.string.saved_comment);
                                // Empty comment
                                String mEmptyComment = "" + getText(R.string.empty_comment);

                                // Check if comment EditText isn't empty
                                if (etComment.length() != 0){
                                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                                    editor.putString("Comment", etComment.getText().toString());
                                    editor.putInt("Mood", mood);
                                    editor.apply();
                                    Toast.makeText(MainActivity.this, mSavedComment, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, mEmptyComment, Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        // History - Button
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    // Displays the Sad/Bad Mood
                    return new BadMood();
                case 1:
                    // Displays the Disappointed Mood
                    return new DisappointedMood();
                case 2:
                    // Displays the Normal Mood
                    return new NormalMood();
                case 3:
                    // Displays the Happy Mood
                    return new HappyMood();
                case 4:
                    // Displays the Super Happy Mood
                    return new SuperHappyMood();
            }
            return null;
        }

        @Override
        public int getCount() {
            // There is 5 different mood
            return NUMBER_MOODS;
        }
    }
}

