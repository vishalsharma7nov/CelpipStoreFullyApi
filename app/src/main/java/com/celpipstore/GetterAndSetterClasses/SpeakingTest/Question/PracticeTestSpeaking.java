package com.celpipstore.GetterAndSetterClasses.SpeakingTest.Question;

public class PracticeTestSpeaking {
   private String id;
   private String test_code;
   private String q1_question;
   private String created_date;
   private String updated_date;
   private String added_by;
   private String role;

    public PracticeTestSpeaking(String id, String test_code, String q1_question, String created_date, String updated_date, String added_by, String role) {
        this.id = id;
        this.test_code = test_code;
        this.q1_question = q1_question;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.added_by = added_by;
        this.role = role;
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

    public String getQ1_question() {
        return q1_question;
    }

    public void setQ1_question(String q1_question) {
        this.q1_question = q1_question;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
