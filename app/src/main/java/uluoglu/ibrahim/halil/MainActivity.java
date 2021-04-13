package uluoglu.ibrahim.halil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText emailTextArea,passwordTextArea;
    TextView emailTextView,passwordTextView;
    Button loginButton,signupButton;
    String emailControl,passwordControl,errorMessage;
    ArrayList<User> usersList = new ArrayList<User>();
    int loginErrorCounter=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailTextArea = findViewById(R.id.emailTextArea);
        passwordTextArea = findViewById(R.id.passwordTextArea);
        emailTextView = findViewById(R.id.emailTexView);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        createUsers();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailControl=emailTextArea.getText().toString();
                passwordControl= String.valueOf(passwordTextArea.getText());

                if(emailControl.isEmpty() || passwordControl.isEmpty())
                {
                    loginErrorCounter--;
                    errorMessage = "Email or Password can not be empty! Last "+loginErrorCounter+" attempt!";
                    Toast.makeText(MainActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
                    if(loginErrorCounter==0)
                    {
                        makeButtonsInvisible();
                    }
                }

                //atemmpt 0 ise yapma ekle
                User findedUser=null;
                for(User aUser : usersList)
                {
                    if((aUser.getEmail().equals(emailControl)) && (aUser.getPassword().equals(passwordControl)))
                    {
                        findedUser=aUser;
                    }
                }
                if(findedUser!=null)
                {
                    loginErrorCounter=3;
                    errorMessage = "Successful login, loading...";
                    Toast.makeText(MainActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    System.out.println("id gönderdim: "+findedUser.getPhotoID());
                    intent.putExtra("photo_id_of_user",String.valueOf(findedUser.getPhotoID()));
                    intent.putExtra("user_first_name",findedUser.getFirstName());
                    intent.putExtra("user_last_name",findedUser.getLastName());
                    startActivity(intent);
                }
                else if(findedUser==null)
                {
                    errorMessage = "Your Email or Password is wrong! Try again! Last " + loginErrorCounter +" attempt!";
                    Toast.makeText(MainActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
                    loginErrorCounter--;
                    if(loginErrorCounter==0)
                    {
                        makeButtonsInvisible();
                    }
                }



            }
        });
    }

    public void makeButtonsInvisible()
    {
        errorMessage = "It was your last attempt. Please contact us!";
        Toast.makeText(MainActivity.this,errorMessage,Toast.LENGTH_LONG).show();
        loginButton.setVisibility(View.INVISIBLE);
        signupButton.setVisibility(View.INVISIBLE);
    }

    public void createUsers()
    {

        User albertEinstein = new User("Albert","Einstein","albert_einstein@gmail.com","123456",11);
        User alanTuring = new User("Alan","Turing","alan_turing@gmail.com","123456",12);
        User wilhelmLeibniz = new User("Wilhelm","Leibniz","wilhelm_leibniz@gmail.com","123456",13);
        User josephFourier = new User("Joseph","Fourier","joseph_fourier@gmail.com","123456",14);
        User marieCurie = new User("Marie","Curie","marie_curie@gmail.com","123456",15);
        User milevMaric = new User("Mileva","Maric","mileva_maric@gmail.com","123456",16);
        User adaLovelace = new User("Ada","Lovelace","ada_lovelace@gmail.com","123456",17);
        User cananDagdeviren = new User("Canan","Dağdeviren","canan_dagdeviren@gmail.com","123456",18);
        User oktaySinanoglu = new User("Oktay","Sinanoğlu","oktay_sinanoglu@gmail.com","123456",19);
        User cahitArf = new User("Cahit","Arf","cahit_arf@gmail.com","123456",20);
        usersList.add(albertEinstein);
        usersList.add(alanTuring);
        usersList.add(wilhelmLeibniz);
        usersList.add(josephFourier);
        usersList.add(marieCurie);
        usersList.add(milevMaric);
        usersList.add(adaLovelace);
        usersList.add(cananDagdeviren);
        usersList.add(oktaySinanoglu);
        usersList.add(cahitArf);
    }
}