package uluoglu.ibrahim.halil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateQuestionActivity extends AppCompatActivity {
    TextView file_text_view;
    EditText question_sentence_edit_text, option_a_et, option_b_et, option_c_et, option_d_et, option_e_et;
    CheckBox option_a_cb, option_b_cb, option_c_cb, option_d_cb, option_e_cb;
    Button save_button, image_attachment_button;

    QuestionDatabaseHelper questionDatabaseHelper = ProfileActivity.getQuestionDatabaseHelper();;
    static final int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        findIdOfElements();

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trueAnswer = null;
                if (!option_a_cb.isChecked() && !option_b_cb.isChecked() && !option_c_cb.isChecked()
                        && !option_d_cb.isChecked() && !option_e_cb.isChecked()) {
                    String errorMessage = "You have to check at least one true answer.";
                    Toast.makeText(CreateQuestionActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    String questionSentence = question_sentence_edit_text.getText().toString();
                    String optionA = option_a_et.getText().toString();
                    String optionB = option_b_et.getText().toString();
                    String optionC = option_c_et.getText().toString();
                    String optionD = option_d_et.getText().toString();
                    String optionE = option_e_et.getText().toString();

                    String filePath = file_text_view.getText().toString();
                    if (option_a_cb.isChecked()) {
                        trueAnswer = "A";
                    } else if (option_b_cb.isChecked()) {
                        trueAnswer = "B";
                    } else if (option_c_cb.isChecked()) {
                        trueAnswer = "C";
                    } else if (option_d_cb.isChecked()) {
                        trueAnswer = "D";
                    } else if (option_e_cb.isChecked()) {
                        trueAnswer = "E";
                    }
                    questionDatabaseHelper.insertQuestionData(questionSentence, optionA, optionB, optionC, optionD, optionE, trueAnswer, filePath);
                    StringBuffer buffer = new StringBuffer();
                    Cursor cursor = questionDatabaseHelper.getData();
                    while (cursor.moveToNext()) {
                        buffer.append("\n" + "Sentence" + cursor.getString(0) + "\n");
                        buffer.append("Option A " + cursor.getString(1) + "\n");
                        buffer.append("Option B " + cursor.getString(2) + "\n");
                        buffer.append("Option C " + cursor.getString(3) + "\n");
                        buffer.append("Option D " + cursor.getString(4) + "\n");
                        buffer.append("Option E " + cursor.getString(5) + "\n");
                        buffer.append("True Answer " + cursor.getString(6) + "\n");
                        buffer.append("Filepath " + cursor.getString(7) + "\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateQuestionActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Users");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }

            }
        });

        image_attachment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            file_text_view.setText(picturePath);
            cursor.close();
//            file_image_view.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            file_text_view.setVisibility(View.VISIBLE);
        }
    }


    public void findIdOfElements() {
        file_text_view = findViewById(R.id.file_text_view);
        question_sentence_edit_text = findViewById(R.id.question_sentence_edit_text);
        option_a_et = findViewById(R.id.option_a_et);
        option_b_et = findViewById(R.id.option_b_et);
        option_c_et = findViewById(R.id.option_c_et);
        option_d_et = findViewById(R.id.option_d_et);
        option_e_et = findViewById(R.id.opiton_e_et);
        option_a_cb = findViewById(R.id.option_a_cb);
        option_b_cb = findViewById(R.id.option_b_cb);
        option_c_cb = findViewById(R.id.option_c_cb);
        option_d_cb = findViewById(R.id.option_d_cb);
        option_e_cb = findViewById(R.id.option_e_cb);
        save_button = findViewById(R.id.save_button);
        image_attachment_button = findViewById(R.id.image_attachment_button);
    }

}