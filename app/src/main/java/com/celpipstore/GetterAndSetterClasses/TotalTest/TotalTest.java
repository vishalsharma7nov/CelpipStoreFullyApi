package com.celpipstore.GetterAndSetterClasses.TotalTest;

public class TotalTest {
    String id;
    String PTEsubtype;
    String coin_cost;
    String total_test;

    public TotalTest(String id, String PTEsubtype, String coin_cost, String total_test) {
        this.id = id;
        this.PTEsubtype = PTEsubtype;
        this.coin_cost = coin_cost;
        this.total_test = total_test;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPTEsubtype() {
        return PTEsubtype;
    }

    public void setPTEsubtype(String PTEsubtype) {
        this.PTEsubtype = PTEsubtype;
    }

    public String getCoin_cost() {
        return coin_cost;
    }

    public void setCoin_cost(String coin_cost) {
        this.coin_cost = coin_cost;
    }

    public String getTotal_test() {
        return total_test;
    }

    public void setTotal_test(String total_test) {
        this.total_test = total_test;
    }
}
