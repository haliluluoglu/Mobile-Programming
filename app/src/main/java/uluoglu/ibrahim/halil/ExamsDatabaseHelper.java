package uluoglu.ibrahim.halil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExamsDatabaseHelper extends SQLiteOpenHelper {
    public String firstName, lastName, email, password;
    private int photoID;


    public ExamsDatabaseHelper(Context context) {
        super(context, "ExamsData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ExamDetails(exam_name TEXT, exam_time TEXT, difficulty TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ExamDetails");
    }

    public Boolean insertExamData(String exam_name, String exam_time, String difficulty) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("exam_name", firstName);
        contentValues.put("exam_time", lastName);
        contentValues.put("difficulty", email);
        long result = DB.insert("ExamDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM ExamDetails", null);
        return cursor;
    }

    public void deleteDatabase() {
        SQLiteDatabase DB = this.getReadableDatabase();
        DB.delete("UserDetails",null,null);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
