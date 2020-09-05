package com.baeldung;

public class MockExample {

    MockServiceExample service = new MockServiceExample();

    public String callName(String name){
        return getService().getName(name);
    }

    public MockServiceExample getService(){
        return service;
    }

    public void setService(MockServiceExample service){
        this.service = service;
    }
}
