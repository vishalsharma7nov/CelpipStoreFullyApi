package com.celpipstore.JsonData;

import com.celpipstore.GetterAndSetterClasses.TotalTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandler {
    public List<TotalTest> totalTests = new ArrayList<>();
    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_PTESUBTYPE      = "PTEsubtype";
    public static final String KEY_COIN_COST       = "coin_cost";
    public static final String KEY_TOTAL_TEST      = "total_test";
    private JSONArray users = null;
    private String json;
    public JsonDataHandler(String json) {
        this.json = json;
    }

    public List<TotalTest> parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
           String[] id          = new String[users.length()];
           String[] PTEsubtype  = new String[users.length()];
           String[] coin_cost   = new String[users.length()];
           String[] total_test  = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                id[i]         = jo.getString(KEY_ID);
                PTEsubtype[i] = jo.getString(KEY_PTESUBTYPE);
                coin_cost[i]  = jo.getString(KEY_COIN_COST);
                total_test[i] = jo.getString(KEY_TOTAL_TEST);
                totalTests.add(new TotalTest(jo.getString(KEY_ID),jo.getString(KEY_PTESUBTYPE),jo.getString(KEY_COIN_COST),jo.getString(KEY_TOTAL_TEST)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  totalTests;
    }
}