import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        int counterOne = 0;
        int counterTwo = 0;

        for (Integer integer : list1) {
            if (integer == elem) {
                counterOne++;
            }
        }
        for (Integer integer : list2) {
            if (integer == elem) {
                counterTwo++;
            }
        }
        return counterOne == counterTwo;
    }
}