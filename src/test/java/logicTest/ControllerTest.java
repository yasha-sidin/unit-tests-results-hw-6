package logicTest;

import com.sun.tools.javac.Main;
import logic.Commands;
import logic.Controller;
import logic.PresenterSystem;
import logic.Prompter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ControllerTest {
    private Prompter prompterMock;
    private PresenterSystem presenterSystem;
    private Controller controller;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        prompterMock = mock(Prompter.class);
        presenterSystem = new PresenterSystem();
        controller = new Controller(presenterSystem, prompterMock);
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test good scenario of RUN command executing
     */
    @DisplayName("Test good scenario of RUN command executing")
    @Test
    public void testRUNCommandGoodScenario() {
        when(prompterMock.promptCommand()).thenAnswer(new ReturnsElementsOf(Arrays.asList(Commands.RUN, Commands.EXIT)));
        when(prompterMock.promptData(anyString())).thenReturn("11 22 55");
        controller.run();
        assertTrue(true);
    }

    /**
     * Test bad scenario of RUN command executing
     */
    @DisplayName("Test bad scenario of RUN command executing")
    @Test
    public void testRUNCommandBadScenario() {
        when(prompterMock.promptCommand()).thenAnswer(new ReturnsElementsOf(Arrays.asList(Commands.RUN, Commands.EXIT)));
        when(prompterMock.promptData(anyString())).thenReturn("11 hhh 55");
        controller.run();
        assertEquals("Error: For input string: \"hhh\"\n" +
                "Program switched off. Goodbye!\n", outContent.toString());
    }

    @DisplayName("Test EXIT command")
    @Test
    public void testEXITCommand() {
        when(prompterMock.promptCommand()).thenReturn(Commands.EXIT);
        controller.run();
        assertEquals("Program switched off. Goodbye!\n", outContent.toString());
    }

    @AfterEach
    public void clean() {
        prompterMock = null;
        presenterSystem = null;
        controller = null;
        System.setOut(originalOut);
    }
}
