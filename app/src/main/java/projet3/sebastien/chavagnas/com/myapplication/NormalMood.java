package projet3.sebastien.chavagnas.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

/**
 * Created by Sébastien CHAVAGNAS - OpenClassrooms on 16/11/2017.
 */

public class NormalMood extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.normal_mood, container, false);
    }
}