import java.util.Arrays;
import java.util.List;

public class CharacterTask {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("JAVA","APPLE","HONDA","DEVELOPMENT");

        words.stream()
                .map(String::length)
                .forEach(System.out::println);

    }
}
