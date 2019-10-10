package com.celpipstore.GetterAndSetterClasses;

public class VocabularyTestQuestionList {
    protected String id;
    protected String level_id;
    protected String title;
    protected String question_image;
    protected String options1;
    protected String options2;
    protected String options3;

    public VocabularyTestQuestionList(String id, String level_id, String title, String question_image, String options1, String options2, String options3, String options4) {
        this.id = id;
        this.level_id = level_id;
        this.title = title;
        this.question_image = question_image;
        this.options1 = options1;
        this.options2 = options2;
        this.options3 = options3;
        this.options4 = options4;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion_image() {
        return question_image;
    }

    public void setQuestion_image(String question_image) {
        this.question_image = question_image;
    }

    public String getOptions1() {
        return options1;
    }

    public void setOptions1(String options1) {
        this.options1 = options1;
    }

    public String getOptions2() {
        return options2;
    }

    public void setOptions2(String options2) {
        this.options2 = options2;
    }

    public String getOptions3() {
        return options3;
    }

    public void setOptions3(String options3) {
        this.options3 = options3;
    }

    public String getOptions4() {
        return options4;
    }

    public void setOptions4(String options4) {
        this.options4 = options4;
    }

    protected String options4;
}
