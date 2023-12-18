package logicTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static logic.StringParser.parseStringToList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringParserTest {
    /**
     * Test parsing from string to list with string which consists from 6 numbers with space between them
     */
    @Test
    @DisplayName("Test valid returning with 6 numbers")
    public void testParseStringToListWithValidValue() {
        String value = "12 32 34 55 222 4";
        assertThat(parseStringToList(value)).isEqualTo(List.of(12, 32, 34, 55, 222, 4));
    }

    /**
     * Test parsing from string to list with string which consists from 1 number
     */
    @Test
    @DisplayName("Test valid returning with one number")
    public void testParseStringToListWithOneValidValue() {
        String value = "12";
        assertThat(parseStringToList(value)).isEqualTo(List.of(12));
    }

    /**
     * Test parsing from string to list with string which consists from 1 too big number, must throw NumberFormatException
     */
    @Test
    @DisplayName("Test with one too big integer as a string, must throw NumberFormatException")
    public void testParseStringToListWithOneTooBigIntegerValue() {
        String value = "126765435678987654345678987654";
        assertThatThrownBy(() -> parseStringToList(value)).isInstanceOf(NumberFormatException.class);
    }

    /**
     * Test with not valid params as a string, must throw NumberFormatException
     */
    @Test
    @DisplayName("Test with not valid params as a string, must throw NumberFormatException")
    public void testParseStringToListWithNotValidValues() {
        String value = "21321 hello WORLD 24 32rrc 32";
        assertThatThrownBy(() -> parseStringToList(value)).isInstanceOf(NumberFormatException.class);
    }
}
