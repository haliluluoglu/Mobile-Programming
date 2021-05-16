package uluoglu.ibrahim.halil;

import androidx.cardview.widget.CardView;

public class QuestionItem {
    private int imageResource;
    private String sentenceM, optionA, optionB, optionC, optionD, optionE, trueAnswer, filePath;

    public QuestionItem(int imageResource, String sentence, String optionA,
                        String optionB, String optionC, String optionD, String optionE, String trueAnswer, String filePath) {
        this.imageResource = imageResource;
        sentenceM = sentence;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.optionE = optionE;
        this.trueAnswer = trueAnswer;
        this.filePath = filePath;

    }

    public int getImageResource() {
        return imageResource;
    }

    public String getSentenceM() {
        return sentenceM;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getOptionE() {
        return optionE;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public String getFilePath() {
        return filePath;
    }
}