package com.celpipstore.JsonData.ListeningTest.Answers;

import com.celpipstore.GetterAndSetterClasses.ListeningTest.Answer.ListeningPart1Answer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerListeningPart1Answer {
    private List<ListeningPart1Answer> listeningPart1Answer = new ArrayList<>();
    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";
    private String json;
    private JSONArray users = null;
    private static final String JSON_ARRAY          = "response_data";
    private static final String JSON_q_number            = "q_number";
    private static final String JSON_q_response_type     = "q_response_type";
    private static final String JSON_q_response          = "q_response";
    private static final String JSON_q_response_val      = "q_response_val";
    private static final String JSON_q_right_option      = "q_right_option";
    private static final String JSON_q_right_option_val  = "q_right_option_val";

    public JsonDataHandlerListeningPart1Answer(String json) {
        this.json = json;
    }

    public List<ListeningPart1Answer> parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            String[] q_number            = new String[users.length()];
            String[] q_response_type     = new String[users.length()];
            String[] q_response          = new String[users.length()];
            String[] q_response_val      = new String[users.length()];
            String[] q_right_option      = new String[users.length()];
            String[] q_right_option_val  = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                q_number[i]             = jo.getString(JSON_q_number);
                q_response_type[i]      = jo.getString(JSON_q_response_type);
                q_response[i]           = jo.getString(JSON_q_response);
                q_response_val[i]       = jo.getString(JSON_q_response_val);
                q_right_option[i]       = jo.getString(JSON_q_right_option);
                q_right_option_val[i]   = jo.getString(JSON_q_right_option_val);
                listeningPart1Answer.add(new ListeningPart1Answer(jo.getString(JSON_q_number),jo.getString(JSON_q_response_type),jo.getString(JSON_q_response),jo.getString(JSON_q_response_val),jo.getString(JSON_q_right_option),jo.getString(JSON_q_right_option_val)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  listeningPart1Answer;
    }
}
