package uluoglu.ibrahim.halil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    ImageView profilePicture;
    TextView welcomeText,profileNameText;
    String photoID,firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        photoID = intent.getStringExtra("photo_id_of_user");
        firstName = intent.getStringExtra("user_first_name");
        lastName = intent.getStringExtra("user_last_name");
        System.out.println("lastname:"+lastName);
        profilePicture = findViewById(R.id.profileLogoImageView);
        welcomeText = findViewById(R.id.welcomeText);
        profileNameText = findViewById(R.id.profileNameText);
        String finalText = firstName+" "+lastName;

        switch (photoID)
        {
            case "11":
                profilePicture.setImageResource(R.drawable.albert_einstein);
                profileNameText.setText(finalText);
                break;
            case "12":
                profilePicture.setImageResource(R.drawable.alan_turing);
                profileNameText.setText(finalText);
                break;
            case "13":
                profilePicture.setImageResource(R.drawable.wilhelm_leibniz);
                profileNameText.setText(finalText);
                break;
            case "14":
                profilePicture.setImageResource(R.drawable.joseph_fourier);
                profileNameText.setText(finalText);
                break;
            case "15":
                profilePicture.setImageResource(R.drawable.marie_curie);
                profileNameText.setText(finalText);
                break;
            case "16":
                profilePicture.setImageResource(R.drawable.mileva_maric);
                profileNameText.setText(finalText);
                break;
            case "17":
                profilePicture.setImageResource(R.drawable.ada_lovelace);
                profileNameText.setText(finalText);
                break;
            case "18":
                profilePicture.setImageResource(R.drawable.canan_dagdeviren);
                profileNameText.setText(finalText);
                break;
            case "19":
                profilePicture.setImageResource(R.drawable.oktay_sinanoglu);
                profileNameText.setText(finalText);
                break;
            case "20":
                profilePicture.setImageResource(R.drawable.cahit_arf);
                profileNameText.setText(finalText);
                break;
            default:
                profilePicture.setVisibility(View.VISIBLE);
                profileNameText.setVisibility(View.VISIBLE);
        }
    }
}