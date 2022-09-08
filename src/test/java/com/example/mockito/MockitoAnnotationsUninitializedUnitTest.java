package com.example.mockito;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

/**
 * <p>在开始处详细描述该类的作用</p>
 * <p>Running Into NPE While Using Annotation </p>
 *
 * @author Jiahui.Huang
 * @date 2022/9/8 9:42
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationsUninitializedUnitTest {
    @Mock
    List<String> mockedList;

    /**
     * Most of the time, this happens simply because we forget to properly enable Mockito annotations.
     *
     * So we have to keep in mind that each time we want to use Mockito annotations, we must take the extra step and initialize them as we already explained earlier.
     */
    @Test(expected = NullPointerException.class)
    public void whenMockitoAnnotationsUninitialized_thenNPEThrown(){
        Mockito.when(mockedList.size()).thenReturn(1);
    }
}
