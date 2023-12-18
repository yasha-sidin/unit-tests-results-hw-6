package logic;

import java.util.List;

public class ListMiddleChecker {
    public static String compareListsMiddles(List<Integer> list1, List<Integer> list2) {
        double middle1 = getMiddle(list1);
        double middle2 = getMiddle(list2);
        if (middle1 > middle2) return "Первый список имеет большее среднее значение.";
        if (middle2 > middle1) return "Второй список имеет большее среднее значение.";
        if (middle1 == middle2) return "Средние значения равны.";
        return null;
    }

    private static double getMiddle(List<Integer> list) {
        return list.stream().mapToDouble(Integer::doubleValue).sum() / list.size();
    }
}
