package uluoglu.ibrahim.halil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionDatabaseHelper extends SQLiteOpenHelper {

    public String questionSentence, optionA, optionB, optionC, optionD, optionE, trueAnswer, filePath;

    public QuestionDatabaseHelper(Context context) {
        super(context, "Questions.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QuestionDetails(questionSentence TEXT, optionA TEXT, optionB TEXT, optionC TEXT, optionD TEXT, opitonE TEXT, trueAnswer TEXT, filePath TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS QuestionDetails");
    }


    public Boolean insertQuestionData(String questionSentence, String optionA, String optionB, String optionC, String optionD, String optionE, String trueAnswer, String filePath) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("questionSentence", questionSentence);
        contentValues.put("optionA", optionA);
        contentValues.put("optionB", optionB);
        contentValues.put("optionC", optionC);
        contentValues.put("optionD", optionD);
        contentValues.put("opitonE", optionE);
        contentValues.put("trueAnswer", trueAnswer);
        contentValues.put("filePath", filePath);
        long result = DB.insert("QuestionDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM QuestionDetails", null);
        return cursor;
    }

    public void deleteDatabase() {
        SQLiteDatabase DB = this.getReadableDatabase();
        DB.delete("QuestionDetails", null, null);
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
