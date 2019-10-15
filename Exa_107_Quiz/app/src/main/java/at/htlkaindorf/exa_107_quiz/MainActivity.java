package at.htlkaindorf.exa_107_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import at.htlkaindorf.exa_107_quiz.beans.QuizQuestion;
import at.htlkaindorf.exa_107_quiz.bl.Category;
import at.htlkaindorf.exa_107_quiz.bl.QuestionPool;

public class MainActivity extends AppCompatActivity {

    private TextView[] resultTvs = new TextView[5];
    private TextView tvQuestion;
    private TextView tvCategory;
    private Button[] answerBtns = new Button[4];
    private Button btContinue;

    private QuestionPool pool;
    private List<QuizQuestion> currentQuestions;
    private int currentIndex = 0;
    private Category currentCategory;
    private QuizQuestion currentQuestion;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pool = new QuestionPool(getResources());

        resultTvs[0] = findViewById(R.id.tvF1);
        resultTvs[1] = findViewById(R.id.tvF2);
        resultTvs[2] = findViewById(R.id.tvF3);
        resultTvs[3] = findViewById(R.id.tvF4);
        resultTvs[4] = findViewById(R.id.tvF5);

        answerBtns[0] = findViewById(R.id.bt0);
        answerBtns[1] = findViewById(R.id.bt1);
        answerBtns[2] = findViewById(R.id.bt2);
        answerBtns[3] = findViewById(R.id.bt3);

        for (Button btn : answerBtns) {
            btn.setEnabled(true);
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onAnswerClick(v);
                }
            });
        }

        tvQuestion = findViewById(R.id.tvQuestion);
        tvCategory = findViewById(R.id.tvCategory);

        btContinue = findViewById(R.id.btContinue);
        btContinue.setOnClickListener(new onContinueListener());

        Random rand = new Random();
        if(rand.nextInt(2)==1) {
            displayQuestion(Category.sport);
        } else {
            displayQuestion(Category.pc_components);
        }
//        displayQuestion(Category.sport);
    }

    public class onContinueListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onDisplayQuestion(v);
        }
    }

    public void onDisplayQuestion(View v) {
        if(currentIndex >= 4) {
            Toast.makeText(getApplicationContext(), "Already finished the questions", Toast.LENGTH_LONG).show();
            return;
        }
        currentIndex++;
        displayQuestion(currentCategory);
    }

    public void onAnswerClick(View v) {
        Button btn = (Button) v;
        int btnNumber = Integer.parseInt(btn.getHint().toString());

        if(btnNumber == currentQuestion.getCorrectAnswer()) {
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            resultTvs[currentIndex].setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        } else {
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            answerBtns[currentQuestion.getCorrectAnswer()-1].setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            resultTvs[currentIndex].setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }


        for (Button bt: answerBtns) {
            bt.setEnabled(false);
        }
    }

    public void displayQuestion(Category cat) {
        currentQuestions = pool.getQuestionByCategory(cat);
        currentQuestion = currentQuestions.get(currentIndex);
        currentCategory = cat;
        tvCategory.setText(getString(R.string.categoryFormat, cat.toString()));

        Log.e(TAG, currentIndex+"");

        tvQuestion.setText(currentQuestion.getQuestion());

        List<String> answers = currentQuestion.getAnswers();
        for (int i = 0; i<4;i++) {
            answerBtns[i].setText(answers.get(i));
            answerBtns[i].setEnabled(true);
            answerBtns[i].setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
        }
    }
}
