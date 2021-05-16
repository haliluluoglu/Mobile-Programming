package uluoglu.ibrahim.halil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public String firstName, lastName, email, password;
    private int photoID;


    public UserDatabaseHelper(Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE UserDetails(firstName TEXT, lastName TEXT, email TEXT, password TEXT, photoID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserDetails");
    }

    public Boolean insertUserData(String firstName, String lastName, String email, String password, int photoID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("photoID", photoID);
        long result = DB.insert("UserDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateUserData(String firstName, String lastName, String email, String password, int photoID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails WHERE firstName= ?", new String[]{firstName});
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("photoID", photoID);
        if (cursor.getCount() > 0) {
            long result = DB.update("UserDetails", contentValues, "firstName=?", new String[]{firstName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Boolean deleteUserData(String firstName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails WHERE firstName= ?", new String[]{firstName});
        if (cursor.getCount() > 0) {
            long result = DB.delete("UserDetails", "firstName=?", new String[]{firstName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails", null);
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
