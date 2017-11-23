package projet3.sebastien.chavagnas.com.myapplication;

import android.database.Cursor;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

        // My Database
        DatabaseHelper myDB = new DatabaseHelper(this);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            // Create the adapter that will return a fragment for each of the five moods
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the VerticalViewPager with the sections adapter
            final VerticalViewPager verticalViewPager = findViewById(R.id.container2);
            verticalViewPager.setAdapter(sectionsPagerAdapter);
            // Starting the app from the Happy Mood fragment
            verticalViewPager.setCurrentItem(3);

            // Set up buttons
            ImageView comment_btn = findViewById(R.id.comment_fab);
            ImageView share_btn = findViewById(R.id.share_fab);
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
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.getDefault());
                                    String mDate = simpleDateFormat.format(new Date());

                                    // The current position of our VerticalViewPager
                                    // From where the user want to comment the mood of the day
                                    int mood = verticalViewPager.getCurrentItem();

                                    // Concatenation of Date / Comment / Mood to show in our Toast
                                    String mText = mDate + " " + etComment.getText().toString() + " " + mood;

                                    if (etComment.length() != 0){
                                        myDB.addMood(new MoodItem(mDate, etComment.getText().toString(), mood));
                                        Toast.makeText(MainActivity.this, mText, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "You must put something into the text field.", Toast.LENGTH_LONG).show();
                                    }


                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // TODO

                                }
                            });
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                }
            });

            // Share - Button
            // So far, it shares the very last comment only
            // TODO
            // Improvements
            share_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHelper myDB = new DatabaseHelper(getApplicationContext());
                    Cursor mCursor = myDB.getLastComment();
                    mCursor.moveToNext();
                    String mLastComment = mCursor.getString(0);


                    Intent mIntent = new Intent(Intent.ACTION_SEND);
                    mIntent.setType("text/plain");

                    mIntent.putExtra(Intent.EXTRA_TEXT, "Share a comment from my application: \"" + mLastComment + "\"");
                    startActivity(Intent.createChooser(mIntent, getString(R.string.share_using)));
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
                return 5;
            }
        }

        @Override
        protected void onDestroy(){
            VerticalViewPager verticalViewPager = findViewById(R.id.container2);

            String mComment = "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", Locale.getDefault());
            String mDate = simpleDateFormat.format(new Date());
            int mood = verticalViewPager.getCurrentItem();

            myDB.addMood(new MoodItem(mDate, mComment, mood));
            super.onDestroy();
        }
    }

