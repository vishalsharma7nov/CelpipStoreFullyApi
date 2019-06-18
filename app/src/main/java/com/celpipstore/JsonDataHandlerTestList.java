package com.celpipstore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.celpipstore.JsonDataHandler.KEY_PTESUBTYPE;

class JsonDataHandlerTestList {
    public static String[] id;
    public static String[] test_code;

    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";

    private JSONArray users = null;

    private String json;

    public JsonDataHandlerTestList(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id          = new String[users.length()];
            test_code  = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                id[i]         = jo.getString(KEY_ID);
                test_code[i] = jo.getString(KEY_TEST_CODE);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}