package com.celpipstore.GetterAndSetterClasses.ListeningTest.Answer;

public class ListeningPart1Answer {
    private String q_number;
    private String q_response_type;
    private String q_response;
    private String q_response_val;
    private String q_right_option;
    private String q_right_option_val;

    public ListeningPart1Answer(String q_number, String q_response_type, String q_response, String q_response_val, String q_right_option, String q_right_option_val) {
        this.q_number = q_number;
        this.q_response_type = q_response_type;
        this.q_response = q_response;
        this.q_response_val = q_response_val;
        this.q_right_option = q_right_option;
        this.q_right_option_val = q_right_option_val;
    }

    public String getQ_number() {
        return q_number;
    }

    public void setQ_number(String q_number) {
        this.q_number = q_number;
    }

    public String getQ_response_type() {
        return q_response_type;
    }

    public void setQ_response_type(String q_response_type) {
        this.q_response_type = q_response_type;
    }

    public String getQ_response() {
        return q_response;
    }

    public void setQ_response(String q_response) {
        this.q_response = q_response;
    }

    public String getQ_response_val() {
        return q_response_val;
    }

    public void setQ_response_val(String q_response_val) {
        this.q_response_val = q_response_val;
    }

    public String getQ_right_option() {
        return q_right_option;
    }

    public void setQ_right_option(String q_right_option) {
        this.q_right_option = q_right_option;
    }

    public String getQ_right_option_val() {
        return q_right_option_val;
    }

    public void setQ_right_option_val(String q_right_option_val) {
        this.q_right_option_val = q_right_option_val;
    }
}
