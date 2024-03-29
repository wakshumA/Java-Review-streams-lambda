package functionalinterfaces;

import java.util.function.*;

public class Examples {

    public static void main(String[] args) {

        //************PREDICATE******************//
        //Anonymous class
//
//        Predicate<Integer> lesserThan = new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//                return integer<18;
//            }
//        };
//
//        lesserThan.test(50);

        Predicate<Integer> lesserThan = b -> b<18; //Implementation of test method that belongs to Predicate interface
        Boolean result = lesserThan.test(50);
        System.out.println(result);


        //************CONSUMER******************//
        Consumer<Integer> display = i -> System.out.println(i);
        display.accept(50);

        //************BICONSUMER******************//
        BiConsumer<Integer,Integer> addTwo = (x,y) -> System.out.println(x+y);
        addTwo.accept(100,200);

        //************FUNCTION******************//
        Function<String,String> fun = s -> "Hello " + s;
        String  str = fun.apply("Ozzy");
        System.out.println(str);

        System.out.println(fun.apply("Apple"));

        //************SUPPLIER******************//
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println(randomValue.get());


        Predicate<Integer> pred = (p) -> p%2 == 0? true : false;
        System.out.println(pred.test(10));


        Function<String,Integer> fun2 = s ->  s.length();
        Integer result3 = fun2.apply("wakshum");
        System.out.println(result3);


        BiFunction<Integer,Integer,Integer> biFunction = (a,b) -> a*b;

        System.out.println(biFunction.apply(20,40));


        








    }
}
