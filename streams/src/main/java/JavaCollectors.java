import java.util.*;
import java.util.stream.Collectors;

public class JavaCollectors {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3,4,5,6,6,7);

        //toCollections (supplier) : is used to create a collection using collector
        System.out.println("******ToCollection");
        List<Integer> numberList = numbers.stream()
                .filter(x -> x%2==0)
                .collect(Collectors.toCollection(ArrayList::new));    //new arraysList

        System.out.println(numberList);

        System.out.println("*********Set*************");
        Set<Integer> numberSet = numbers.stream()
                .filter(x -> x%2==0)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(numberSet);

//toList(): returns a collector interface that gathers the input data into a new list
        System.out.println("*********ToList*************");
        List<Integer> numberListTo = numbers.stream()
                .filter(x -> x%2==0)
                .collect(Collectors.toList());
        System.out.println(numberListTo);


        //toSet() : returns a Collector interface that gathers the init data into a new set

        System.out.println("*********ToList*************");
        Set<Integer> numberSet2 = numbers.stream()
                .filter(x -> x%2==0)
                .collect(Collectors.toSet());
        System.out.println(numberSet2);

        System.out.println("*********Map*************");
        //toMap(Function,Function) : returns a collector interface that gathers the input data into a new map
       Map<String,Integer> dishMap = DishData.getAll().stream()
               .collect(Collectors.toMap(Dish::getName,Dish::getCalories));

        System.out.println(dishMap);

        System.out.println("*********counting*************");
        //counting() : returns a collector that counts the number of the elements
        Long evenCount = numbers.stream()
                .filter(x -> x%2==0)
                .collect(Collectors.counting());
        System.out.println(evenCount);


        //summingInt(ToIntFunction) : returns a collector that produces the sum of a integer-value func
         Integer sum = DishData.getAll().stream()
                .collect(Collectors.summingInt(Dish::getCalories));


        System.out.println(sum);

        System.out.println("*********averagingInt*************");
        //averagingInt(ToIntFunction) : returns the average of the integers
        Double avgCalories = DishData.getAll().stream()
                .collect(Collectors.averagingInt(Dish::getCalories));

        System.out.println(avgCalories);

        //joining() : is used to join various elements of a character or string array inta a single string object

        List<String> courses = Arrays.asList("Java","JS","TS");
        String str = courses.stream()
                .collect(Collectors.joining(","));
        System.out.println(str);

        System.out.println("*********partitioning*************");
        //partitioning(): is used to partition a stream of objects (or set of elements)based on given predicate

        Map<Boolean,List<Dish>> veggieDish = DishData.getAll().stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));

        System.out.println(veggieDish);

        System.out.println("*********groupingBy*************");
        //groupingBY(): is used for groping objects by some property and storing rresults in a map instance

        Map<Type,List<Dish>> dishType = DishData.getAll().stream()
                .collect(Collectors.groupingBy(Dish::getType));

        System.out.println(dishType);




    }
}
