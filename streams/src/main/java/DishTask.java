import static java.util.Comparator.comparing;

public class DishTask {
    public static void main(String[] args) {
        //print all dish name that has less than 4000 calories

        DishData.getAll().stream()
                .filter(dish -> dish.getCalories()<400)
                .map(Dish::getName)
                .forEach(System.out::println);


        //print the length of the name of each dish
        System.out.println("**************************");
        DishData.getAll().stream()
                .map(Dish::getName)
                .map(String::length)
                .forEach(System.out::println);

        //print Three high caloric Dish name (>300)
        System.out.println("**************************");
        DishData.getAll().stream()
                .filter(dish -> dish.getCalories()>300)
                .map(Dish::getName)
                .limit(3)
                .forEach(System.out::println);

        //print all dish name that are velow 400 calories in sorted
        System.out.println("**************************");
        DishData.getAll().stream()
                .filter(dish -> dish.getCalories()<400)
                .sorted(comparing(Dish::getCalories).reversed())
                .map(Dish::getName)
                .forEach(System.out::println);


    }
}
