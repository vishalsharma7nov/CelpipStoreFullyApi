package com.celpipstore.GetterAndSetterClasses.WritingTest;

public class WritingTask2 {
    private String id;
    private String test_code;
    private String question_title;
    private String option_1;
    private String option_2;
    private String created_date;
    private String updated_date;
    private String added_by;
    private String role;
    private String status;

    public WritingTask2(String id, String test_code, String question_title, String option_1, String option_2, String created_date, String updated_date, String added_by, String role, String status) {
        this.id = id;
        this.test_code = test_code;
        this.question_title = question_title;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.added_by = added_by;
        this.role = role;
        this.status = status;
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

    public String getOption_1() {
        return option_1;
    }

    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }

    public String getOption_2() {
        return option_2;
    }

    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
