package com.example.mockito;


import com.example.mockito.dao.PhoneBookRepository;
import com.example.mockito.service.PhoneBookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;

import java.util.List;

/**
 * <p>在开始处详细描述该类的作用</p>
 * <p>Running Into NPE While Using Annotation </p>
 *BDD encourages writing tests in a natural, human-readable language that focuses on the behavior of the application.
 *
 * It defines a clearly structured way of writing tests following three sections (Arrange, Act, Assert):
 *
 * given some preconditions (Arrange)
 * when an action occurs (Act)
 * then verify the output (Assert)
 *
 * @author Jiahui.Huang
 * @date 2022/9/8 9:42
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationsUninitializedUnitTest {
    @Mock
    List<String> mockedList;
    PhoneBookService phoneBookService;
    PhoneBookRepository phoneBookRepository;

    String momContactName = "Mom";
    String momPhoneNumber = "01234";
    String xContactName = "x";
    String tooLongPhoneNumber = "01111111111111";

    @Before
    public void init() {
        phoneBookRepository = Mockito.mock(PhoneBookRepository.class);
        phoneBookService = new PhoneBookService(phoneBookRepository);
    }
    /**
     * Most of the time, this happens simply because we forget to properly enable Mockito annotations.
     *
     * So we have to keep in mind that each time we want to use Mockito annotations, we must take the extra step and initialize them as we already explained earlier.
     */
    @Test(expected = NullPointerException.class)
    public void whenMockitoAnnotationsUninitialized_thenNPEThrown(){
        Mockito.when(mockedList.size()).thenReturn(1);
    }

    /**
     * 3. Mockito vs. BDDMockito
     * The traditional mocking in Mockito is performed using when(obj).then*() in the Arrange step.
     *
     * Later, interaction with our mock can be validated using verify() in the Assert step.
     *
     * BDDMockito provides BDD aliases for various Mockito methods, so we can write our Arrange step using given (instead of when), likewise, we could write our Assert step using then (instead of verify).
     */
    @Test
    public void testMockito() {
        when(phoneBookRepository.contains(momContactName))
                .thenReturn(false);

        phoneBookService.register(momContactName, momPhoneNumber);

        verify(phoneBookRepository)
                .insert(momContactName, momPhoneNumber);


    }
//    Let's see how that compares to BDDMockito:
    @Test
    public void testBDDMockito() {
        given(phoneBookRepository.contains(momContactName))
                .willReturn(false);

        phoneBookService.register(momContactName, momPhoneNumber);

        then(phoneBookRepository)
                .should()
                .insert(momContactName, momPhoneNumber);
    }

}
