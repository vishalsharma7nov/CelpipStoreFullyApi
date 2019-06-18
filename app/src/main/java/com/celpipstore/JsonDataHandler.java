package com.celpipstore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonDataHandler {
    public static String[] id;
    public static String[] PTEsubtype;
    public static String[] coin_cost;
    public static String[] total_test;

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

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id          = new String[users.length()];
            PTEsubtype  = new String[users.length()];
            coin_cost   = new String[users.length()];
            total_test  = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                id[i]         = jo.getString(KEY_ID);
                PTEsubtype[i] = jo.getString(KEY_PTESUBTYPE);
                coin_cost[i]  = jo.getString(KEY_COIN_COST);
                total_test[i] = jo.getString(KEY_TOTAL_TEST);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}