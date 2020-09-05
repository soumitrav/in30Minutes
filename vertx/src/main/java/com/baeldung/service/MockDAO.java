package com.baeldung.service;

public class MockDAO {
    public String getNameFromDB(String name){
        return "Hello "+name;
    }
}
