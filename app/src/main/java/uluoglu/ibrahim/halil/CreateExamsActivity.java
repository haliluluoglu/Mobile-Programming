package uluoglu.ibrahim.halil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateExamsActivity extends AppCompatActivity {
    private ArrayList<QuestionItem> exampleList;
    private RecyclerView recyclerView;
    private CreateExamsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button buttonInsert;
    private Button buttonRemove;
    private TextView seekBarTextView,examNameTextView,examTimeTextView;
    CreateQuestionActivity createQuestionActivity;
    CardView itemCardView;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exams);
        seekBarTextView = findViewById(R.id.seekbar_text_view);
        itemCardView = findViewById(R.id.item_card_view);
        examNameTextView =findViewById(R.id.test_name_et);
        examTimeTextView =findViewById(R.id.test_time);
        seekBar = findViewById(R.id.seek_bar);
        createList();
        buildRecyclerView();
        setButtons();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int number = seekBar.getProgress();
                switch (number) {
                    case 2:
                        seekBarTextView.setText("2");
                        break;
                    case 3:
                        seekBarTextView.setText("3");
                        break;
                    case 4:
                        seekBarTextView.setText("4");
                        break;
                    case 5:
                        seekBarTextView.setText("5");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void insertItem(int position) {
//        exampleList.add(position, new QuestionItem(R.drawable.home_icon, "New Item At Position" + position, "This is Line 2"));
        adapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        exampleList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void createList() {
        exampleList = new ArrayList<>();
        String questionSentence = null, optionA = null, optionB = null, optionC = null, optionD = null, optionE = null, trueAnswer = null, filePath = null;
        QuestionDatabaseHelper questionDatabaseHelper = ProfileActivity.getQuestionDatabaseHelper();
        SQLiteDatabase DB = questionDatabaseHelper.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM QuestionDetails", null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            questionSentence = cursor.getString(0);
            optionA = cursor.getString(1);
            optionB = cursor.getString(2);
            optionC = cursor.getString(3);
            optionD = cursor.getString(4);
            optionE = cursor.getString(5);
            trueAnswer = cursor.getString(6);
            filePath = cursor.getString(7);
            exampleList.add(new QuestionItem(R.drawable.home_icon, questionSentence, optionA, optionB, optionC, optionD, optionE, trueAnswer, filePath));
        }


    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CreateExamsAdapter(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CreateExamsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void setButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExamsDatabaseHelper examsDatabaseHelper=null;
                examsDatabaseHelper.insertExamData(examNameTextView.toString(),examTimeTextView.toString(),seekBarTextView.toString());
                String errorMessage = "Successful the exams has been created.";
                Toast.makeText(CreateExamsActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}