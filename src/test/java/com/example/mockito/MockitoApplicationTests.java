package com.example.mockito;

import com.example.mockito.entity.MyDictionary;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static org.junit.Assert.assertEquals;

/**
 * Finally, here are some notes about Mockito annotations:
 *
 * Mockito's annotations minimize repetitive mock creation code.
 * They make tests more readable.
 * @InjectMocks is necessary for injecting both @Spy and @Mock instances.
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MockitoApplicationTests {

    @Mock
    List mockedList;

    @Spy
    ArrayList<String> spiedList = new ArrayList<>();

    @Captor
    ArgumentCaptor argumentCaptor;

    @Mock
    Map<String, String> wordMap;

    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Spy
    MyDictionary spyDic = new MyDictionary();

    MyDictionary spyDic1;

    @Before
    public void init(){
        spyDic1 = new MyDictionary(wordMap);
    }


    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        assertEquals(0, mockedList.size());

        Mockito.when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }

    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        spiedList.add("one");
        spiedList.add("two");

        assertEquals(2, spiedList.size());
        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");


//        Mockito.doReturn(100).when(spiedList).size();
        Mockito.when(spiedList.size()).thenReturn(100);
        assertEquals(100, spiedList.size());


    }

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        mockedList.add("three");

        Mockito.verify(mockedList).add(argumentCaptor.capture());

        assertEquals("three", argumentCaptor.getValue());
    }

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        Assert.assertEquals("aMeaning", dic.getMeaning("aWord"));
    }

    //Mockito doesn't support injecting mocks into spies
    @Test
    public void whenUseInjectMocksAnnotation_thenFalse() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", spyDic.getMeaning("aWord"));
    }

    //If we want to use a mock with a spy, we can manually inject the mock through a constructor:
    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect1() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", spyDic1.getMeaning("aWord"));
    }

    //Often we may run into NullPointerException when we try to actually use the instance annotated with @Mock or @Spy:



}
