package uluoglu.ibrahim.halil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.TimeUnit;

public class SigninTabFragment extends Fragment {
    TextView emailTextArea, passwordTextArea;
    Button signinButton;
    String emailControl, passwordControl, errorMessage;
    int loginErrorCounter = 3;
    float k = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signin_tab_fragment, container, false);
        emailTextArea = root.findViewById(R.id.emailTextArea);
        passwordTextArea = root.findViewById(R.id.passwordTextArea);
        signinButton = root.findViewById(R.id.signinButton);

        emailTextArea.setTranslationX(800);
        passwordTextArea.setTranslationX(800);
        signinButton.setTranslationX(800);

        emailTextArea.setAlpha(k);
        passwordTextArea.setAlpha(k);
        signinButton.setAlpha(k);

        emailTextArea.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordTextArea.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signinButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailControl = emailTextArea.getText().toString();
                passwordControl = String.valueOf(passwordTextArea.getText());

                if (emailControl.isEmpty() || passwordControl.isEmpty()) {
                    loginErrorCounter--;
                    errorMessage = "Email or Password can not be empty! Last " + loginErrorCounter + " attempt!";
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    if (loginErrorCounter == 0) {
                        makeButtonsInvisible();
                    }
                } else {
                    String dbFirstName = null, dbLastName = null, dbEmail = null, dbPhotoID = null, dbPassword = null;
                    Security security = new Security();
                    String hashedPassword = security.get_SHA_512_SecurePassword(passwordControl, "halil");
                    UserDatabaseHelper userDatabaseHelper = MainActivity.getUserDatabaseHelper();
                    SQLiteDatabase DB = userDatabaseHelper.getWritableDatabase();
                    Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails WHERE email= ? AND password= ?", new String[]{emailControl, hashedPassword});
                    while (cursor.moveToNext()) {
                        dbFirstName = cursor.getString(0);
                        dbLastName = cursor.getString(1);
                        dbEmail = cursor.getString(2);
                        dbPassword = cursor.getString(3);
                        dbPhotoID = cursor.getString(4);
                    }
                    System.out.println("[SHOW][HASHED]-->" + dbFirstName + "  " + dbLastName + " " + dbEmail + " " + dbPassword + " " + hashedPassword);
                    if (cursor.getCount() > 0) {
                        loginErrorCounter = 3;
                        errorMessage = "Successful login, loading...";
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), ProfileActivity.class);
                        System.out.println("0 dan buyuk");
                        intent.putExtra("user_first_name", dbFirstName);
                        intent.putExtra("user_last_name", dbLastName);
                        intent.putExtra("photo_id_of_user", dbEmail);
                        intent.putExtra("photo_id_of_user", dbPhotoID);
                        startActivity(intent);
                    } else {
                        errorMessage = "Your Email or Password is wrong! Try again! Last " + loginErrorCounter + " attempt!";
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        loginErrorCounter--;
                        if (loginErrorCounter == 0) {
                            makeButtonsInvisible();
                        }
                    }
                }
            }
        });

        return root;
    }

    public void makeButtonsInvisible() {
        errorMessage = "It was your last attempt.Directing to sign up screen...";
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
        signinButton.setVisibility(View.INVISIBLE);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(getContext(), SignupTabFragment.class);
        startActivity(intent2);
    }
}
