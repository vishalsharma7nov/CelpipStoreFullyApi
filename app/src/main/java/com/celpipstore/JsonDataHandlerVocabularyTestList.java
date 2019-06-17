package com.celpipstore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonDataHandlerVocabularyTestList {
    public static String[] id;
    public static String[] name;
    public static String[] total_questions;
    public static String[] active;
    public static String[] coin_cost;

    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_NAME            = "name";
    public static final String KEY_TOTAL_QUESTIONS = "total_questions";
    public static final String KEY_ACTIVE          = "active";
    public static final String KEY_COIN_COST       = "coin_cost";

    private JSONArray users = null;

    private String json;

    public JsonDataHandlerVocabularyTestList(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id              = new String[users.length()];
            name            = new String[users.length()];
            total_questions = new String[users.length()];
            active          = new String[users.length()];
            coin_cost       = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                id[i]              = jo.getString(KEY_ID);
                name[i]            = jo.getString(KEY_NAME);
                total_questions[i] = jo.getString(KEY_TOTAL_QUESTIONS);
                active[i]          = jo.getString(KEY_ACTIVE);
                coin_cost[i]       = jo.getString(KEY_COIN_COST);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}