package com.celpipstore.GetterAndSetterClasses.WritingTest;

public class WritingTask1 {
    private String id;
    private String test_code;
    private String question_title;
    private String email_title;
    private String created_date;
    private String updated_date;
    private String added_by;
    private String role;
    private String status;

    public WritingTask1(String id, String test_code, String question_title, String email_title, String created_date, String updated_date, String added_by, String role, String status) {
        this.id = id;
        this.test_code = test_code;
        this.question_title = question_title;
        this.email_title = email_title;
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

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getEmail_title() {
        return email_title;
    }

    public void setEmail_title(String email_title) {
        this.email_title = email_title;
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
