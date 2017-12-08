package projet3.sebastien.chavagnas.com.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SuperHappyMood extends Fragment {
    SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("Comment", "");
        editor.putInt("Mood", 4);
        editor.apply();
        return inflater.inflate(R.layout.super_happy_mood, container, false);

    }
}