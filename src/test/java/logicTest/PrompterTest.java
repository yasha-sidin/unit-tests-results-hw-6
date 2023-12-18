package logicTest;

import logic.Commands;
import logic.PresenterSystem;
import logic.Prompter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PrompterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private Prompter prompter;

    @BeforeEach
    public void setUpStreamAndParams() {
        System.setOut(new PrintStream(outContent));
        prompter = new Prompter();
    }

    /**
     * Test positive event in promptData method.
     */
    @Test
    @DisplayName("Test positive event in promptData method")
    public void testPromptDataOnPositiveEvent() {
        String input = "Some input data";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat(prompter.promptData("Data: ")).isEqualTo("Some input data");
    }

    /**
     * Test inform data output in promptCommand method
     */
    @Test
    @DisplayName("Test inform data output in promptCommand method")
    public void testPromptCommandInformDataOutput()  {
        StringBuilder sb = new StringBuilder();
        sb.append("Commands: \n");
        sb.append("(");
        for (Commands command : Commands.values()) {
            sb.append(command).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ")");
        sb.append("\n");
        sb.append("Input command: ");
        String input = "EXIT";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        prompter.promptCommand();
        assertThat(sb.toString()).isEqualTo(outContent.toString());
    }

    /**
     * Test valid results of promptCommand method
     * @param input data which will be input
     */
    @ParameterizedTest
    @DisplayName("Test result of prompt if we input EXIT or RUN commands")
    @ValueSource(strings = { "EXIT", "exit", "RUN", "run", "rUn", "ExiT" })
    public void testPromptCommandValidResults(String input)  {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Commands value = prompter.promptCommand();
        assertThat(value).isEqualTo(Commands.valueOf(input.toUpperCase()));
    }

    /**
     * Program must output "Wrong command!" if user input not valid command and method must return null.
     */
    @Test
    @DisplayName("Test bad command input")
    public void testPromptCommandBadChoice() {
        String input1 = "Not valid command";
        ByteArrayInputStream in = new ByteArrayInputStream(input1.getBytes());
        System.setIn(in);
        assertThat(prompter.promptCommand()).isNull();
        assertThat(outContent.toString().split(": ")[outContent.toString().split(": ").length - 1]).isEqualTo("Wrong command!\n");
    }

    @AfterEach
    public void clear() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        prompter = null;
    }
}
