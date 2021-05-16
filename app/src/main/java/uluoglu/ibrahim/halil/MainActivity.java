package uluoglu.ibrahim.halil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static UserDatabaseHelper userDatabaseHelper;
    TabLayout mainTablayout;
    ViewPager mainViewPager;
    FloatingActionButton mainInfoIcon;
    EditText emailTextArea, passwordTextArea;
    TextView emailTextView;
    final String PASSWORD="12";
    final String SALT="halil";
    float k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDatabaseHelper = new UserDatabaseHelper(this);

        mainTablayout = findViewById(R.id.main_tablayout);
        mainViewPager = findViewById(R.id.main_view_pager);
        mainInfoIcon = findViewById(R.id.main_info_icon);
        emailTextArea = findViewById(R.id.emailTextArea);
        passwordTextArea = findViewById(R.id.passwordTextArea);
        emailTextView = findViewById(R.id.emailTexView);

        mainTablayout.addTab(mainTablayout.newTab().setText("Sign In"));
        mainTablayout.addTab(mainTablayout.newTab().setText("Sign Up"));
        final MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), this, mainTablayout.getTabCount());
        mainViewPager.setAdapter(mainAdapter);
        mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTablayout));
        mainInfoIcon.setTranslationY(300);
        mainTablayout.setTranslationY(300);
        mainInfoIcon.setAlpha(k);
        mainTablayout.setAlpha(k);
        mainInfoIcon.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        mainTablayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

        try {
            createUsers();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void createUsers() throws NoSuchAlgorithmException {
        Security security = new Security();
        String hashedPassword = security.get_SHA_512_SecurePassword(PASSWORD, SALT);
        userDatabaseHelper.insertUserData("Albert", "Einstein", "albert_einstein@gmail.com", hashedPassword, 11);
        userDatabaseHelper.insertUserData("Alan", "Turing", "alan_turing@gmail.com", hashedPassword, 12);
        userDatabaseHelper.insertUserData("Wilhelm", "Wilhelm", "wilhelm_leibniz@gmail.com", hashedPassword, 13);
        userDatabaseHelper.insertUserData("Joseph", "Fourier", "joseph_fourier@gmail.com", hashedPassword, 14);
        userDatabaseHelper.insertUserData("Marie", "Curie", "marie_curie@gmail.com", hashedPassword, 15);
        userDatabaseHelper.insertUserData("Mileva", "Maric", "mileva_maric@gmail.com", hashedPassword, 16);
        userDatabaseHelper.insertUserData("Ada", "Lovelace", "ada_lovelace@gmail.com", hashedPassword, 17);
        userDatabaseHelper.insertUserData("Canan", "Dağdeviren", "canan_dagdeviren@gmail.com", hashedPassword, 18);
        userDatabaseHelper.insertUserData("Oktay", "Sinanoğlu", "oktay_sinanoglu@gmail.com", hashedPassword, 19);
        userDatabaseHelper.insertUserData("Cahit", "Arf", "cahit_arf@gmail.com", hashedPassword, 20);
//        Cursor cursor = userDatabaseHelper.getData();
//        StringBuffer buffer = new StringBuffer();
//        while(cursor.moveToNext())
//        {
//            buffer.append("\n"+"First Name: " + cursor.getString(0)+"\n");
//            buffer.append("Last Name: " + cursor.getString(1)+"\n");
//            buffer.append("Email: " + cursor.getString(2)+"\n");
//            buffer.append("Password: " + cursor.getString(3)+"\n");
//            buffer.append("Photo ID: " + cursor.getString(4)+"\n");
//        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setCancelable(true);
//        builder.setTitle("Users");
//        builder.setMessage(buffer.toString());
//        builder.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userDatabaseHelper.deleteDatabase();
    }

    public static UserDatabaseHelper getUserDatabaseHelper() {
        return userDatabaseHelper;
    }


}