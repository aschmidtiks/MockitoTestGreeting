import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestGreeting {

    Main main;
    String input = "Kevin";

    @BeforeEach
    public void initTests() {
        main = new Main();
    }

    @Mock
    Main mockedMain;

    @Test
    public void testMockingIsMethodInvocated() {
        mockedMain.greet(input);
        mockedMain.print(input);
        //when(mockedMain.print("Kevin")).thenReturn("Hello, " + "Kevin");  // -> geht nicht da void
        verify(mockedMain).greet(input);
        verify(mockedMain).print(input);
    }

    @Test
    public void testStubbingVoidMethod() {
        doNothing().when(mockedMain).greet(input);
        mockedMain.greet(input);
        verify(mockedMain).greet(input);
    }

    @Test
    public void testMockingGreeting() {
        when(mockedMain.generatePrintable(input)).thenReturn("Hello, " + input);
        mockedMain.generatePrintable(input);
        verify(mockedMain).generatePrintable(input);

        //doThrow(new NullPointerException()).when(mockedMain).greet("");
        //Oder:
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                System.out.print("Input gets processed but should not be null");
                return null;
            }
        }).when(mockedMain).greet(null);
        mockedMain.greet(null);
        verify(mockedMain).greet(null);
    }

    @Test
    public void testAssertions() {
        assertEquals("Hello, " + input, main.generatePrintable(input));
        assertEquals("Hello, my friend", main.generatePrintable(null));
    }
}
