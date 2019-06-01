package com.celpipstore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonDataHandlerVocabularyTestQuestionList {
    public static String[] id;
    public static String[] level_id;
    public static String[] title;
    public static String[] question_image;
    public static String[] options1;
    public static String[] options2;
    public static String[] options3;
    public static String[] options4;

    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_LEVEL_ID        = "level_id";
    public static final String KEY_TITLE           = "title";
    public static final String KEY_QUESTION_IMAGE  = "question_image";
    public static final String KEY_OPTIONS         = "options";

    private JSONArray users = null;

    private String json;

    public JsonDataHandlerVocabularyTestQuestionList(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id                  = new String[users.length()];
            level_id            = new String[users.length()];
            title               = new String[users.length()];
            question_image      = new String[users.length()];
            options1             = new String[users.length()];
            options2             = new String[users.length()];
            options3             = new String[users.length()];
            options4            = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                JSONArray jArray = jo.getJSONArray("options");

                id[i]                  = jo.getString(KEY_ID);
                level_id[i]            = jo.getString(KEY_LEVEL_ID);
                title[i]               = jo.getString(KEY_TITLE);
                question_image[i]      = jo.getString(KEY_QUESTION_IMAGE);

                for (int j = 0 ; j < jArray.length();j++)
                {
                    JSONObject j1 = jArray.getJSONObject(j);
                    options1[i]             = j1.getString("title");
                    options2[i]             = j1.getString("title");
                    options3[i]             = j1.getString("title");
                    options4[i]             = j1.getString("title");
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}