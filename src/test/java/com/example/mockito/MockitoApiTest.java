package com.example.mockito;

import com.example.mockito.entity.MyList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.*;

/**
 * <p>在开始处详细描述该类的作用</p>
 * The simplest overloaded variant of the mock method is the one with a single parameter for the class to be mocked:
 *
 * @author Jiahui.Huang
 * @date 2022/9/8 11:01
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoApiTest {
    MyList listMock = mock(MyList.class);


}
