package logicTest;

import logic.ListMiddleChecker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListMiddleCheckerTest {
    List<Integer> list1;
    List<Integer> list2;

    @BeforeEach
    public void setup() {
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
    }

    /**
     * Test comparing two empty lists.
     */
    @Test
    @DisplayName("Test comparing two empty lists")
    public void testCompareListsMiddlesWithEmptyLists() {
        assertThat(ListMiddleChecker.compareListsMiddles(list1, list2)).isNull();
    }

    /**
     * Test comparing two lists with numbers(middle on first list is more than middle on second list)
     */
    @Test
    @DisplayName("Test comparing two lists with numbers(middle on first list is more than middle on second list)")
    public void testCompareListsMiddlesWithNumbersFirstMustBeMoreThanSecond() {
        list1.add(1);
        list1.add(2);
        list1.add(15);
        list2.add(6);
        list2.add(2);
        assertThat(ListMiddleChecker.compareListsMiddles(list1, list2)).isEqualTo("Первый список имеет большее среднее значение.");
    }

    /**
     * Test comparing two lists with numbers(middle on second list is more than middle on first list)
     */
    @Test
    @DisplayName("Test comparing two lists with numbers(middle on second list is more than middle on first list)")
    public void testCompareListsMiddlesWithNumbersSecondMustBeMoreThanFirst() {
        list1.add(1);
        list1.add(2);
        list1.add(0);
        list2.add(6);
        list2.add(2);
        assertThat(ListMiddleChecker.compareListsMiddles(list1, list2)).isEqualTo("Второй список имеет большее среднее значение.");
    }

    /**
     * Test comparing two lists with numbers(middles are equals)
     */
    @Test
    @DisplayName("Test comparing two lists with numbers(middles are equals)")
    public void testCompareListsMiddlesWithNumbersMiddlesAreEquals() {
        list1.add(1);
        list1.add(2);
        list1.add(6);
        list2.add(6);
        list2.add(0);
        assertThat(ListMiddleChecker.compareListsMiddles(list1, list2)).isEqualTo("Средние значения равны.");
    }

    @AfterEach
    public void clear() {
        list1 = null;
        list2 = null;
    }
}
