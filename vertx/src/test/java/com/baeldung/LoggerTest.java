package com.baeldung;

import ch.qos.logback.core.Appender;
import com.baeldung.service.MathApplication;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.junit.Before;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.logging.Level;

@PrepareForTest(Logger.class)
public class LoggerTest {

    @Mock
    private Appender mockedAppender;


    @Before
    public void init(){
        Logger root = (Logger) LoggerFactory.getLogger(MathApplication.class);

    }

}
