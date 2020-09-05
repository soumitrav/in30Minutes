package com.baeldung;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(Logger.class)
public class MyTestClass{
    @InjectMocks
    private MyClass myClass = new MyClass();



    @Before
    public void init() {
        myClass.logger = LoggerFactory.getLogger(MyClass.class);
        //when(myClass.add(any())).thenReturn(3);
        //doNothing().when(log).info(anyString());


    }


    @Test
    public void firstTest() {
        //doNothing().when(myClass.logger).info(any());
        when(myClass.add(10)).thenReturn(20);
        //verify( myClass,times(1)).add(10);
    }
}
