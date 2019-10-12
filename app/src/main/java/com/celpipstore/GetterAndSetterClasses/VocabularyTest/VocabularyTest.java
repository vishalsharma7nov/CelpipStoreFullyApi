package com.celpipstore.GetterAndSetterClasses.VocabularyTest;

public class VocabularyTest {
    protected String id;
    protected String name;
    protected String total_questions;
    protected String active;
    protected String coin_cost;

    public VocabularyTest(String id, String name, String total_questions, String active, String coin_cost) {
        this.id = id;
        this.name = name;
        this.total_questions = total_questions;
        this.active = active;
        this.coin_cost = coin_cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal_questions() {
        return total_questions;
    }

    public void setTotal_questions(String total_questions) {
        this.total_questions = total_questions;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCoin_cost() {
        return coin_cost;
    }

    public void setCoin_cost(String coin_cost) {
        this.coin_cost = coin_cost;
    }
}
