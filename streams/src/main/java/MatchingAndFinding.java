import java.util.Optional;

public class MatchingAndFinding {


    public static void main(String[] args) {

        //ALL MATCH

        boolean isHealthy = DishData.getAll().stream().allMatch(dish -> dish.getCalories() < 1000);
        System.out.println(isHealthy);

        //AnyMATCH
        System.out.println("********************************");
        if (DishData.getAll().stream().anyMatch(Dish::isVegetarian))
            System.out.println("The menu is vegetarian friendly");


        //Non MATCH
        System.out.println("**********************");
        boolean health2 = DishData.getAll().stream().noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println(isHealthy);

        //FIND ANY
        System.out.println("**********************");

        Optional<Dish> dish = DishData.getAll().stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dish.get());


       // FIND FIRST
        System.out.println("**********************");
        Optional<Dish> dish2 = DishData.getAll().stream()
                .filter(Dish::isVegetarian)
                .findFirst();
        System.out.println(dish.get());


    }
}
