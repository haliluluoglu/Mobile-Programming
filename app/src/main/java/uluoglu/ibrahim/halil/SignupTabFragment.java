package uluoglu.ibrahim.halil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {
    TextView firstNameTextArea, lastNameTextArea, emailTextArea, passwordTextArea, reEnterPasswordTextArea, phoneNumberTextArea, birthDayPicker;
    Button signupButton;
    ImageView signupImageButton;
    float k = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);


        firstNameTextArea = root.findViewById(R.id.firstNameTextArea);
        lastNameTextArea = root.findViewById(R.id.lastNameTextArea);
        emailTextArea = root.findViewById(R.id.emailTextArea);
        passwordTextArea = root.findViewById(R.id.passwordTextArea);
        reEnterPasswordTextArea = root.findViewById(R.id.reEnterPasswordTextArea);
        phoneNumberTextArea = root.findViewById(R.id.phoneNumberTextArea);
        birthDayPicker = root.findViewById(R.id.birthDayPicker);

//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String emailControl = emailTextArea.getText().toString();
//                UserDatabaseHelper userDatabaseHelper = MainActivity.getUserDatabaseHelper();
//                SQLiteDatabase DB = userDatabaseHelper.getWritableDatabase();
//                Cursor cursor = DB.rawQuery("SELECT * FROM UserDetails WHERE email= ?", new String[]{emailControl});
//                if (cursor.getCount() > 0) {
//                    String errorMessage = "User already exist. Try again.";
//                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
//                    firstNameTextArea.setText("");
//                    lastNameTextArea.setText("");
//                    emailTextArea.setText("");
//                    passwordTextArea.setText("");
//                    reEnterPasswordTextArea.setText("");
//                    phoneNumberTextArea.setText("");
//                }
//                else
//                {
//                    if(passwordTextArea.toString().compareTo(reEnterPasswordTextArea.getText().toString())==0)
//                    {
//                        String errorMessage = "Succesfull! User registered.";
//                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
//                        Security security = new Security();
//                        String hashedPassword = security.get_SHA_512_SecurePassword(passwordTextArea.toString(), "halil");
//                        userDatabaseHelper.insertUserData("Albert", "Einstein", "albert_einstein@gmail.com", hashedPassword, 40);
//                    }
//                    else
//                    {
//                        String errorMessage = "Password and re-entered password dont match each other.";
//                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        });
        return root;

    }
}
