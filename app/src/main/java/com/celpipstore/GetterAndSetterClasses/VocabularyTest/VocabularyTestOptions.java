package com.celpipstore.GetterAndSetterClasses.VocabularyTest;

public class VocabularyTestOptions {
    private String question_id;
    private String option_id;
    private String title;
    private String is_correct;

    public VocabularyTestOptions(String question_id, String option_id, String title, String is_correct) {
        this.question_id = question_id;
        this.option_id = option_id;
        this.title = title;
        this.is_correct = is_correct;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(String is_correct) {
        this.is_correct = is_correct;
    }
}
