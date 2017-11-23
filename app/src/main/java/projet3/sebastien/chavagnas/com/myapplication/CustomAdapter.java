package projet3.sebastien.chavagnas.com.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, String[] history) {
        super(context, R.layout.list_item_comments, history);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mHistoryInflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View mCustomView = mHistoryInflater.inflate(R.layout.list_item_comments, parent, false);

        String mRow = getItem(position);
        TextView mDate = mCustomView.findViewById(R.id.display_date);
        mDate.setText(mRow);
        ImageView mShowComment = mCustomView.findViewById(R.id.comment_icon);
        RelativeLayout mRelativeLayout = mCustomView.findViewById(R.id.row_content);

        // Make our ListView only 7 rows (getCount())
        mCustomView.getLayoutParams().height = parent.getHeight() / getCount();
        mCustomView.requestLayout();

        // Opening DB and create Cursors
        DatabaseHelper myDB = new DatabaseHelper(getContext());
        Cursor mCursor = myDB.getAllData();
        Cursor mDateCursor = myDB.getDates();

        // Create ArrayList
        ArrayList<String> mDatesArrayList = new ArrayList<>();
        ArrayList<String> mCommentsArrayList = new ArrayList<>();
        ArrayList<Integer> mMoodsArrayList = new ArrayList<>();


        // Adding SQLite data to ArrayLists
        while (mDateCursor.moveToNext()){
            String mDates = mDateCursor.getString(0);
            mDatesArrayList.add(mDates);
        }

        // Adding SQLite data to ArrayLists
        while (mCursor.moveToNext()){
            String mCommentColumn = mCursor.getString(2);
            int mMoodColumn = mCursor.getInt(3);
            mCommentsArrayList.add(mCommentColumn);
            mMoodsArrayList.add(mMoodColumn);
        }

        // Reversing lists to match ListView order
        Collections.reverse(mDatesArrayList);
        Collections.reverse(mCommentsArrayList);
        Collections.reverse(mMoodsArrayList);

        // Print test
        System.out.println("mDatesArrayList : " + mDatesArrayList);

        int[] mMoods = new int[mMoodsArrayList.size()];
        final String[] mComments = new String[mCommentsArrayList.size()];

        for ( int i = 0; i < mMoodsArrayList.size(); i++){
            mComments[i] = mCommentsArrayList.get(i);
            mMoods[i] = mMoodsArrayList.get(i);
        }

        // Make the mShowComment button visible/invisible whenever there is a comment or not
        if (mComments[position].equals("")) {
            mShowComment.setVisibility(View.GONE);
        } else {
            mShowComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), mComments[position], Toast.LENGTH_LONG).show();
                }
            });
        }

        // Make the ListView item change background color and width depending on which mood is found in SQLite Database
        switch (mMoods[position]) {
            case 0:
                // Bad mood here, so red background and the smallest width
                mRelativeLayout.setBackgroundColor(0xffde3c50);                     // @color/faded_red
                mCustomView.getLayoutParams().width = parent.getWidth() / 5;        // 1/5 of screen's width
                return mCustomView;
            case 1:
                // Disappointed mood here, so grey background and 2/5 width
                mRelativeLayout.setBackgroundColor(0xff9b9b9b);                     // @color/warm_grey
                mCustomView.getLayoutParams().width = parent.getWidth() * 2 / 5;    // 2/5 of screen's width
                return mCustomView;
            case 2:
                // Normal mood here, so blue background and 3/5 width
                mRelativeLayout.setBackgroundColor(0xa5468ad9);                     // @color/cornflower_blue_65
                mCustomView.getLayoutParams().width = parent.getWidth() * 3 / 5;    // 3/5 of screen's width
                return mCustomView;
            case 3:
                // Happy mood here, so light sage background and 4/5 width
                mRelativeLayout.setBackgroundColor(0xffb8e986);                     // @color/light_sage
                mCustomView.getLayoutParams().width = parent.getWidth() * 4 / 5;    // 4/5 of screen's width
                return mCustomView;
            case 4:
                // Very happy mood here, so yellow background and all the width
                mRelativeLayout.setBackgroundColor(0xfff9ec4f);                     // @color/banana_yellow
                mCustomView.getLayoutParams().width = parent.getWidth();            // screen's width
                return mCustomView;
        }
        return mCustomView;
    }
}
