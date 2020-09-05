package com.baeldung;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockito.Mock;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class MyClass{

    public Logger logger = LoggerFactory.getLogger(MyClass.class);
    //other your fields and methods

    public int add(int i){
        logger.info("this is "+1);
        return 1+2;
    }
}


