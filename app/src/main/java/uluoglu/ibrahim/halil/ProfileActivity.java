package uluoglu.ibrahim.halil;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ImageView navHeader;
    ImageButton navigationImageButton, makeQuestionImageButton, listQuestionsImageButton, createExamImageButton, editExamImageButton;
    TextView welcomeText, profileNameText;
    String photoID, firstName, lastName;
    private static QuestionDatabaseHelper questionDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        questionDatabaseHelper = new QuestionDatabaseHelper(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar_main);
        navigationImageButton = findViewById(R.id.navigation_image_button);
        makeQuestionImageButton = findViewById(R.id.make_question_image_button);
//        listQuestionsImageButton = findViewById(R.id.list_questions_image_button);
        createExamImageButton = findViewById(R.id.create_exam_image_button);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toogle.syncState();

        makeQuestionImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, CreateQuestionActivity.class);
                startActivity(intent);
            }
        });

        createExamImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, CreateExamsActivity.class);
                startActivity(intent);
            }
        });
//        editExamImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileActivity.this, EditExamsActivity.class);
//                startActivity(intent);
//            }
//        });

        Intent intent = getIntent();
        photoID = intent.getStringExtra("photo_id_of_user");
        firstName = intent.getStringExtra("user_first_name");
        lastName = intent.getStringExtra("user_last_name");
        navHeader = findViewById(R.id.nav_header_imageView);
        String finalText = firstName + " " + lastName;
//        switch (photoID) {
//            case "11":
//                navHeader.setImageResource(R.drawable.albert_einstein);
//                profileNameText.setText(finalText);
//                break;
//            case "12":
//                navHeader.setImageResource(R.drawable.alan_turing);
//                profileNameText.setText(finalText);
//                break;
//            case "13":
//                navHeader.setImageResource(R.drawable.wilhelm_leibniz);
//                profileNameText.setText(finalText);
//                break;
//            case "14":
//                navHeader.setImageResource(R.drawable.joseph_fourier);
//                profileNameText.setText(finalText);
//                break;
//            case "15":
//                navHeader.setImageResource(R.drawable.marie_curie);
//                profileNameText.setText(finalText);
//                break;
//            case "16":
//                navHeader.setImageResource(R.drawable.mileva_maric);
//                profileNameText.setText(finalText);
//                break;
//            case "17":
//                navHeader.setImageResource(R.drawable.ada_lovelace);
//                profileNameText.setText(finalText);
//                break;
//            case "18":
//                navHeader.setImageResource(R.drawable.canan_dagdeviren);
//                profileNameText.setText(finalText);
//                break;
//            case "19":
//                navHeader.setImageResource(R.drawable.oktay_sinanoglu);
//                profileNameText.setText(finalText);
//                break;
//            case "20":
//                navHeader.setImageResource(R.drawable.cahit_arf);
//                profileNameText.setText(finalText);
//                break;
//            default:
//                navHeader.setVisibility(View.VISIBLE);
//                profileNameText.setVisibility(View.VISIBLE);
//        }
    }


    public static QuestionDatabaseHelper getQuestionDatabaseHelper() {
        return questionDatabaseHelper;
    }


}