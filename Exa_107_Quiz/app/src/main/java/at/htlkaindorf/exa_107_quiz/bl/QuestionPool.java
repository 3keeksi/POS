package at.htlkaindorf.exa_107_quiz.bl;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.htlkaindorf.exa_107_quiz.R;
import at.htlkaindorf.exa_107_quiz.beans.QuizQuestion;

public class QuestionPool {
    private static Map<Category, List<QuizQuestion>> pool = new HashMap<>();

    private String TAG = QuestionPool.class.getSimpleName();

    public QuestionPool(Resources resources) {
        parseCSV(resources);
    }

    private void parseCSV(Resources resources) {
        Map<Category, Integer> rawIDs = new HashMap<>();
        rawIDs.put(Category.sport, R.raw.sport);
        rawIDs.put(Category.pc_components, R.raw.pc_components);

        InputStream stream;
        InputStreamReader streamReader;
        BufferedReader br;
        for (Category cat : rawIDs.keySet()) {

            stream = resources.openRawResource(rawIDs.get(cat));
            streamReader = new InputStreamReader(stream);
            br = new BufferedReader(streamReader);

            try {
                String line = br.readLine();

                List<QuizQuestion> questions = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    String[] split = line.split(";");

                    String[] answers = split[1].split(",");
                    List<String> ansList = new ArrayList<>(Arrays.asList(answers));
                    int correctAns = Integer.parseInt(split[2]);

                    questions.add(new QuizQuestion(split[0], ansList, correctAns));
                }

                pool.put(cat, questions);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public List<QuizQuestion> getQuestionByCategory(Category category) {
        return pool.get(category);
    }
}
