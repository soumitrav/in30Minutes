package com.baeldung;

import com.baeldung.service.MockDAO;

public class MockServiceExample {
    MockDAO dao;
    public String getName(String yourName){
        return UtilTest.getInstance("").getNameFromDB(yourName);
    }
}
