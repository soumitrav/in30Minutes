package com.baeldung;

import com.baeldung.service.MockDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest({MockExample.class,MockServiceExample.class,UtilTest.class, MockDAO.class})
public class MockExampleTest {

    /*@InjectMocks
    MockExample mockExample;

    @Mock
    MockServiceExample mockServiceExample;*/

    MockExample mockExample = new MockExample();

    MockServiceExample exa = new MockServiceExample();
    //MockServiceExample mockServiceExample = mock(MockServiceExample.class);
    @Before
    public void init(){

        //when(mockServiceExample.getName(any())).thenReturn("Hello Soumitra");
        mockExample.setService(exa);
        MockDAO dao = mock(MockDAO.class);
        when(dao.getNameFromDB(anyString())).thenReturn("Hello");
        PowerMockito.mockStatic(UtilTest.class);
        //when(UtilTest.getInstance(anyString())).thenReturn(dao);
    }
    @Test
    public void testExample() throws IllegalAccessException, NoSuchFieldException {
        MockDAO dao = mock(MockDAO.class);
        when(dao.getNameFromDB(anyString())).thenReturn("Hello");
        //PowerMockito.mockStatic(UtilTest.class);
        //when(UtilTest.getInsta nce(anyString())).thenReturn(new MockDAO());
        UtilTest test = mock(UtilTest.class);
        Field daoField = UtilTest.class.getDeclaredField("dao");
        daoField.setAccessible(true);
        daoField.set(daoField,dao);

        assertEquals("Hello", mockExample.callName("callName"));
    }

    @Test
    public void testExampleService(){
        assertEquals("Hello",exa.getName("Soumitra"));

    }

}
