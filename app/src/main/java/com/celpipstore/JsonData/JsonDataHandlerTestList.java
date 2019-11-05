package com.celpipstore.JsonData;

import com.celpipstore.GetterAndSetterClasses.TotalTestList.TotalTestList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerTestList {
    public List<TotalTestList> totalTestLists = new ArrayList<>();
    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";
    private JSONArray users = null;
    private String json;
    public JsonDataHandlerTestList(String json) {
        this.json = json;
    }
    public List<TotalTestList> parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            String[] id          = new String[users.length()];
            String[] test_code  = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                id[i]         = jo.getString(KEY_ID);
                test_code[i] = jo.getString(KEY_TEST_CODE);
                totalTestLists.add(new TotalTestList( jo.getString(KEY_ID),jo.getString(KEY_TEST_CODE)));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return totalTestLists;
    }
}