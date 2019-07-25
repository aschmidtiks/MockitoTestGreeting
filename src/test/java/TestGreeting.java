import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TestGreeting {

    Main main;
    String[] input;
    String printable;

    @BeforeEach
    public void initTests() {
        main = new Main();
        printable = "";
    }

    @Mock
    Main mockedMain;

    @Test
    public void testMockIsMethodInvoked() {
        mockedMain.greet(input);
        mockedMain.print(printable);
        //when(mockedMain.print("Kevin")).thenReturn("Hello, " + "Kevin");  // -> geht nicht da void
        verify(mockedMain).greet(input);
        verify(mockedMain).print(printable);
    }

    @Test
    public void testStubbingVoidMethod() {
        doNothing().when(mockedMain).greet(input);
        mockedMain.greet(input);
        verify(mockedMain).greet(input);
    }

    @Test
    public void testMockGreeting() {
        when(mockedMain.generatePrintable(input)).thenReturn("Hello, " + input);
        mockedMain.generatePrintable(input);
        verify(mockedMain).generatePrintable(input);

        //doThrow(new NullPointerException()).when(mockedMain).greet(""); //Test gibt Exeption aus und aber ist nicht bestanden
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
    public void testAssertionsNormalInput() {
        input = new String[1];
        input[0] = "Kevin";
        assertEquals("Hello, " + input[0] + ".", main.generatePrintable(input));
    }
    @Test
    public void testAssertionsNullInput() {
        input = new String[1];
        input[0] = null;
        assertEquals("Hello, my friend" + ".", main.generatePrintable(input));
    }
    @Test
    public void testAssertionsUpperCaseInput() {
        input = new String[1];
        input[0] = "JERRY";
        assertEquals("HELLO, " + input[0] + "!", main.generatePrintable(input));
    }
    @Test
    public void testAssertionsTwoInputs() {
        input = new String[2];
        input[0] = "Max";
        input[1] = "Moritz";
        assertEquals("Hello, " + input[0] + " and " + input[1] + ".", main.generatePrintable(input));
    }
    @Test
    public void testAssertionsThreeInputs() {
        input = new String[3];
        input[0] = "Max";
        input[1] = "Moritz";
        input[2] = "Helga";
        assertEquals("Hello, " + input[0] + ", " + input[1] + " and " + input[2] + ".", main.generatePrintable(input));
    }
    @Test
    public void testAssertionsFourInputs() {
        input = new String[4];
        input[0] = "Max";
        input[1] = "Moritz";
        input[2] = "Helga";
        input[3] = "Otto";
        assertEquals("Hello, " + input[0] + ", " + input[1] + ", " + input[2] + " and " + input[3] + ".", main.generatePrintable(input));
    }
    @Test
    public void testAssertionsNormalAndUpperInputs() {
        input = new String[3];
        input[0] = "Amy";
        input[1] = "BRIAN";
        input[2] = "Charlotte";
        assertEquals("Hello, " + input[0] + " and " + input[2] + ". AND HELLO " + input[1] + "!", main.generatePrintable(input));
    }
}
