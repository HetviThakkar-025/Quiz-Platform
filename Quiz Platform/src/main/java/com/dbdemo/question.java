package com.dbdemo;

public class question {
    private int id;
    private String ques;
    private String o1;
    private String o2;
    private String o3;
    private String o4;
    private String ans;

    public question(int id, String ques, String o1, String o2, String o3, String o4, String ans) {
        this.id = id;
        this.ques = ques;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.ans = ans;
    }

    public int getId() {
        return id;
    }

    public String getQues() {
        return ques;
    }

    public String getO1() {
        return o1;
    }

    public String getO2() {
        return o2;
    }

    public String getO3() {
        return o3;
    }

    public String getO4() {
        return o4;
    }

    public String getAns() {
        return ans;
    }
}
