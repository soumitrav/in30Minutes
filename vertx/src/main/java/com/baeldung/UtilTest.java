package com.baeldung;

import com.baeldung.service.MockDAO;

public class UtilTest {
    public static MockDAO dao = new MockDAO();

    public static MockDAO getInstance(String name){
        return dao;
    }
}
