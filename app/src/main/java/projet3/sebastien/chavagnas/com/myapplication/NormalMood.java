package projet3.sebastien.chavagnas.com.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;


public class NormalMood extends Fragment {
    SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("Comment", "");
        editor.putInt("Mood", 2);
        editor.apply();
        return inflater.inflate(R.layout.normal_mood, container, false);
    }
}