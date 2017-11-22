package projet3.sebastien.chavagnas.com.myapplication;


class MoodItem {
    private String mDate;
    private String mComment;
    private int mMood;

    MoodItem(String date, String comment, int mood) {
        this.mDate = date;
        this.mComment = comment;
        this.mMood = mood;
    }

    String getDate() {
        return this.mDate;
    }

    // public void setDate(String date) {
    //     mDate = date;
    // }

    String getComment() {
        return this.mComment;
    }

    // public void setComment(String comment) {
    //     mComment = comment;
    // }

    int getMood() {
        return this.mMood;
    }

    // public void setMood(int mood) {
    //     mMood = mood;
    // }
}


