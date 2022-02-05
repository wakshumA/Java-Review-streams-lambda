import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class StateTest {


    public static void main(String[] args) {

        State va = new State();
        va.addCity("Chicago");
        va.addCity("Arington");
        va.addCity("Fairfax");
        va.addCity("Falls");

        State tx = new State();
        tx.addCity("Dallas");
        tx.addCity("Plano");
        tx.addCity("Austin");
        tx.addCity("Houston");


        List<State> list = Arrays.asList(va,tx);

    //    list.stream().map(state -> state.getCity()).forEach(System.out::println);

        list.stream()
                .map(state -> state.getCity())
                .flatMap(List::stream)
                .forEach(System.out::println);

    }
}
