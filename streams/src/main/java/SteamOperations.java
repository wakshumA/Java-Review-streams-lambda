import java.util.Arrays;
import java.util.List;

public class SteamOperations {

    public static void main(String[] args) {
        List<Integer> myList = Arrays.asList(1,34,4,6,9,5,2,2,2);
        myList.forEach(System.out::println);

        System.out.println("filter");
        myList.stream()
                .filter(i -> i%2 ==0)
                .distinct()
                .forEach(System.out::println);


        //Limit
        System.out.println("Limit");
        myList.stream()
                .filter(i -> i%2==0)
                .limit(1)
                .forEach(System.out::println);


        //SKIP
        System.out.println("SKIP");
        myList.stream()
                .filter(i -> i%2==0)
                .skip(2)
                .forEach(System.out::println);

        //Map
        System.out.println("MAP");
        myList.stream()
                .map(number -> number*2)
                .filter(i -> i%2==0)
                .forEach(System.out::println);





    }
}
