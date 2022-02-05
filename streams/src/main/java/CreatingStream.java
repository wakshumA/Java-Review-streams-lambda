import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreatingStream {
    public static void main(String[] args) {

        //How to create streams, creating streams from Array

        String[] courses = {"Java","JS","TS"};
        Stream<String> courseStream = Arrays.stream(courses);

        //Creating stream from Collection

        List<String> courseList = Arrays.asList("Java","DS","Spring");
        Stream<String> listStream = courseList.stream();

        List<Course> myCourses = Arrays.asList(
                new Course("JAva",100),
                new Course("DS",101),
                new Course("Spring",102),
                new Course("Microservices",101)

        );

        Stream<Course> myCourseStream = myCourses.stream();


        //Creating Stream from values
        Stream<Integer> stream = Stream.of(1,2,3,4);

    }
}
