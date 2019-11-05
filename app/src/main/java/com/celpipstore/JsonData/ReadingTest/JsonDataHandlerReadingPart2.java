package com.celpipstore.JsonData.ReadingTest;

import com.celpipstore.GetterAndSetterClasses.ReadingTest.Question.ReadingTestPart2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDataHandlerReadingPart2 {
    private List<ReadingTestPart2> readingTestPart2 = new ArrayList<>();
    private static String id;
    private static String test_code;
    private static String image_1;
    private static String image_2;
    private static String image_3;
    private static String image_4;
    private static String q1_question;
    private static String q1_option1;
    private static String q1_option2;
    private static String q1_option3;
    private static String q1_option4;
    private static String q1_answerer;
    private static String q2_question;
    private static String q2_option1;
    private static String q2_option2;
    private static String q2_option3;
    private static String q2_option4;
    private static String q2_answerer;
    private static String q3_question;
    private static String q3_option1;
    private static String q3_option2;
    private static String q3_option3;
    private static String q3_option4;
    private static String q3_answerer;
    private static String q4_question;
    private static String q4_option1;
    private static String q4_option2;
    private static String q4_option3;
    private static String q4_option4;
    private static String q4_answerer;
    private static String q5_question;
    private static String q5_option1;
    private static String q5_option2;
    private static String q5_option3;
    private static String q5_option4;
    private static String q5_answerer;
    private static String q6_question;
    private static String q6_option1;
    private static String q6_option2;
    private static String q6_option3;
    private static String q6_option4;
    private static String q6_answerer;
    private static String q7_question;
    private static String q7_option1;
    private static String q7_option2;
    private static String q7_option3;
    private static String q7_option4;
    private static String q7_answerer;
    private static String q8_question;
    private static String q8_option1;
    private static String q8_option2;
    private static String q8_option3;
    private static String q8_option4;
    private static String q8_answerer;
    private static String created_date;
    private static String updated_date;
    public static final String KEY_ID              = "id";
    public static final String KEY_TEST_CODE       = "test_code";
    private String json;

    public JsonDataHandlerReadingPart2(String json) {
        this.json = json;
    }

    public List<ReadingTestPart2> parseJSON() {
        try {
                JSONObject jsonObject = new JSONObject(json);
                id              = jsonObject.getJSONObject("data").getString(KEY_ID);
                test_code       = jsonObject.getJSONObject("data").getString(KEY_TEST_CODE);
                image_1         = jsonObject.getJSONObject("data").getString("image_1");
                image_2         = jsonObject.getJSONObject("data").getString("image_2");
                image_3         = jsonObject.getJSONObject("data").getString("image_3");
                image_4         = jsonObject.getJSONObject("data").getString("image_4");
                q1_question     = jsonObject.getJSONObject("data").getString("q1_question");
                q1_option1      = jsonObject.getJSONObject("data").getString("q1_option1");
                q1_option2      = jsonObject.getJSONObject("data").getString("q1_option2");
                q1_option3      = jsonObject.getJSONObject("data").getString("q1_option3");
                q1_option4      = jsonObject.getJSONObject("data").getString("q1_option4");
                q1_answerer     = jsonObject.getJSONObject("data").getString("q1_answere");
                q2_question     = jsonObject.getJSONObject("data").getString("q2_question");
                q2_option1      = jsonObject.getJSONObject("data").getString("q2_option1");
                q2_option2      = jsonObject.getJSONObject("data").getString("q2_option2");
                q2_option3      = jsonObject.getJSONObject("data").getString("q2_option3");
                q2_option4      = jsonObject.getJSONObject("data").getString("q2_option4");
                q2_answerer     = jsonObject.getJSONObject("data").getString("q2_answere");
                q3_question     = jsonObject.getJSONObject("data").getString("q3_question");
                q3_option1      = jsonObject.getJSONObject("data").getString("q3_option1");
                q3_option2      = jsonObject.getJSONObject("data").getString("q3_option2");
                q3_option3      = jsonObject.getJSONObject("data").getString("q3_option3");
                q3_option4      = jsonObject.getJSONObject("data").getString("q3_option4");
                q3_answerer     = jsonObject.getJSONObject("data").getString("q3_answere");
                q4_question     = jsonObject.getJSONObject("data").getString("q4_question");
                q4_option1      = jsonObject.getJSONObject("data").getString("q4_option1");
                q4_option2      = jsonObject.getJSONObject("data").getString("q4_option2");
                q4_option3      = jsonObject.getJSONObject("data").getString("q4_option3");
                q4_option4      = jsonObject.getJSONObject("data").getString("q4_option4");
                q4_answerer     = jsonObject.getJSONObject("data").getString("q4_answere");
                q5_question     = jsonObject.getJSONObject("data").getString("q5_question");
                q5_option1      = jsonObject.getJSONObject("data").getString("q5_option1");
                q5_option2      = jsonObject.getJSONObject("data").getString("q5_option2");
                q5_option3      = jsonObject.getJSONObject("data").getString("q5_option3");
                q5_option4      = jsonObject.getJSONObject("data").getString("q5_option4");
                q5_answerer     = jsonObject.getJSONObject("data").getString("q5_answere");
                q6_question     = jsonObject.getJSONObject("data").getString("q6_question");
                q6_option1      = jsonObject.getJSONObject("data").getString("q6_option1");
                q6_option2      = jsonObject.getJSONObject("data").getString("q6_option2");
                q6_option3      = jsonObject.getJSONObject("data").getString("q6_option3");
                q6_option4      = jsonObject.getJSONObject("data").getString("q6_option4");
                q6_answerer     = jsonObject.getJSONObject("data").getString("q6_answere");
                q7_question     = jsonObject.getJSONObject("data").getString("q7_question");
                q7_option1      = jsonObject.getJSONObject("data").getString("q7_option1");
                q7_option2      = jsonObject.getJSONObject("data").getString("q7_option2");
                q7_option3      = jsonObject.getJSONObject("data").getString("q7_option3");
                q7_option4      = jsonObject.getJSONObject("data").getString("q7_option4");
                q7_answerer     = jsonObject.getJSONObject("data").getString("q7_answere");
                q8_question     = jsonObject.getJSONObject("data").getString("q8_question");
                q8_option1      = jsonObject.getJSONObject("data").getString("q8_option1");
                q8_option2      = jsonObject.getJSONObject("data").getString("q8_option2");
                q8_option3      = jsonObject.getJSONObject("data").getString("q8_option3");
                q8_option4      = jsonObject.getJSONObject("data").getString("q8_option4");
                q8_answerer     = jsonObject.getJSONObject("data").getString("q8_answere");
                created_date    = jsonObject.getJSONObject("data").getString("created_date");
                updated_date    = jsonObject.getJSONObject("data").getString("updated_date");
                readingTestPart2.add(new ReadingTestPart2(jsonObject.getJSONObject("data").getString(KEY_ID),jsonObject.getJSONObject("data").getString(KEY_TEST_CODE),jsonObject.getJSONObject("data").getString("image_1"),jsonObject.getJSONObject("data").getString("image_2"),jsonObject.getJSONObject("data").getString("image_3"),jsonObject.getJSONObject("data").getString("image_4"),jsonObject.getJSONObject("data").getString("q1_question"),jsonObject.getJSONObject("data").getString("q1_option1"),jsonObject.getJSONObject("data").getString("q1_option2"),jsonObject.getJSONObject("data").getString("q1_option3"),jsonObject.getJSONObject("data").getString("q1_option4"),jsonObject.getJSONObject("data").getString("q1_answere"),jsonObject.getJSONObject("data").getString("q2_question"),jsonObject.getJSONObject("data").getString("q2_option1"),jsonObject.getJSONObject("data").getString("q2_option2"),jsonObject.getJSONObject("data").getString("q2_option3"),jsonObject.getJSONObject("data").getString("q2_option4"),jsonObject.getJSONObject("data").getString("q2_answere"),jsonObject.getJSONObject("data").getString("q3_question"),jsonObject.getJSONObject("data").getString("q3_option1"),jsonObject.getJSONObject("data").getString("q3_option2"),jsonObject.getJSONObject("data").getString("q3_option3"),jsonObject.getJSONObject("data").getString("q3_option4"),jsonObject.getJSONObject("data").getString("q3_answere"),jsonObject.getJSONObject("data").getString("q4_question"),jsonObject.getJSONObject("data").getString("q4_option1"),jsonObject.getJSONObject("data").getString("q4_option2"),jsonObject.getJSONObject("data").getString("q4_option3"),jsonObject.getJSONObject("data").getString("q4_option4"),jsonObject.getJSONObject("data").getString("q4_answere"),jsonObject.getJSONObject("data").getString("q5_question"),jsonObject.getJSONObject("data").getString("q5_option1"),jsonObject.getJSONObject("data").getString("q5_option2"),jsonObject.getJSONObject("data").getString("q5_option3"),jsonObject.getJSONObject("data").getString("q5_option4"),jsonObject.getJSONObject("data").getString("q5_answere"),jsonObject.getJSONObject("data").getString("q6_question"),jsonObject.getJSONObject("data").getString("q6_option1"),jsonObject.getJSONObject("data").getString("q6_option2"),jsonObject.getJSONObject("data").getString("q6_option3"),jsonObject.getJSONObject("data").getString("q6_option4"),jsonObject.getJSONObject("data").getString("q6_answere"),jsonObject.getJSONObject("data").getString("q7_question"),jsonObject.getJSONObject("data").getString("q7_option1"),jsonObject.getJSONObject("data").getString("q7_option2"),jsonObject.getJSONObject("data").getString("q7_option3"),jsonObject.getJSONObject("data").getString("q7_option4"),jsonObject.getJSONObject("data").getString("q7_answere"),jsonObject.getJSONObject("data").getString("q8_question"),jsonObject.getJSONObject("data").getString("q8_option1"),jsonObject.getJSONObject("data").getString("q8_option2"),jsonObject.getJSONObject("data").getString("q8_option3"),jsonObject.getJSONObject("data").getString("q8_option4"),jsonObject.getJSONObject("data").getString("q8_answere"),jsonObject.getJSONObject("data").getString("created_date"),jsonObject.getJSONObject("data").getString("updated_date")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return readingTestPart2;
    }
}
