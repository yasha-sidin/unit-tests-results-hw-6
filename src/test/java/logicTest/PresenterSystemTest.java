package logicTest;

import logic.PresenterSystem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PresenterSystemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private PresenterSystem presenterSystem;

    @BeforeEach
    public void setUpStreamAndParams() {
        System.setOut(new PrintStream(outContent));
        presenterSystem = new PresenterSystem();
    }

    /**
     * Test output with decorating
     * @param value string data
     */
    @ParameterizedTest
    @DisplayName("Test presenter's method")
    @ValueSource(strings = {"Hello, world!", "132224", "Hello!"})
    public void testPresentResult(String value) {
        presenterSystem.presentResult(value);
        assertThat("Result: " + value + "\n").isEqualTo(outContent.toString());
    }

    @AfterEach
    public void clear() {
        System.setOut(originalOut);
        presenterSystem = null;
    }

}
