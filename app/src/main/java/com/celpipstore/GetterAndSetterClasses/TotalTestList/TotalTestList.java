package com.celpipstore.GetterAndSetterClasses.TotalTestList;

public class TotalTestList {
    String id;
    String test_code;

    public TotalTestList(String id, String test_code) {
        this.id = id;
        this.test_code = test_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest_code() {
        return test_code;
    }

    public void setTest_code(String test_code) {
        this.test_code = test_code;
    }
}

