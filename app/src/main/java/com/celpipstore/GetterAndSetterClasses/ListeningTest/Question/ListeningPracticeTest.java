package com.celpipstore.GetterAndSetterClasses.ListeningTest.Question;

public class ListeningPracticeTest {
    private String id;
    private String test_code;
    private String PTEtypeid;
    private String PTEsubtype_id;
    private String questionid;
    private String question;
    private String mp3URL;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    public ListeningPracticeTest(String id, String test_code, String PTEtypeid, String PTEsubtype_id, String questionid, String question, String mp3URL, String option1, String option2, String option3, String option4, String answer) {
        this.id = id;
        this.test_code = test_code;
        this.PTEtypeid = PTEtypeid;
        this.PTEsubtype_id = PTEsubtype_id;
        this.questionid = questionid;
        this.question = question;
        this.mp3URL = mp3URL;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest_code() {
        return test_code;
    }

    public void setTest_code(String test_code) {
        this.test_code = test_code;
    }

    public String getPTEtypeid() {
        return PTEtypeid;
    }

    public void setPTEtypeid(String PTEtypeid) {
        this.PTEtypeid = PTEtypeid;
    }

    public String getPTEsubtype_id() {
        return PTEsubtype_id;
    }

    public void setPTEsubtype_id(String PTEsubtype_id) {
        this.PTEsubtype_id = PTEsubtype_id;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMp3URL() {
        return mp3URL;
    }

    public void setMp3URL(String mp3URL) {
        this.mp3URL = mp3URL;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
