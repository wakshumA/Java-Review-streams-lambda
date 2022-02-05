import java.util.Optional;

import static java.util.Comparator.comparing;

public class TransactionTest {

    public static void main(String[] args) {

        //1. Find all transaction in 2011 and sort by value
        System.out.println("********************************");
        TrasactionData.getAll().stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(System.out::println);


        //2- What are all the unique cities where the readers work?
        TrasactionData.getAll().stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);


        //3- Find all traders from Cambridge and sort them by name
        TrasactionData.getAll().stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("*********************");
        //4- Return a string of all trader's names sorted alphabetically
        String result = TrasactionData.getAll().stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce(" ",(name1,name2) -> name1+name2 + " ");

        System.out.println(result);

        //- Are any traders based in Milan

        boolean milanBased = TrasactionData.getAll().stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        //6- print the values of all transactions from the traders living in Cambridge
        System.out.println("**********************************");
        TrasactionData.getAll().stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);


        //7 - what the highest value of all the transactions
        System.out.println("**************************Task-7");
        Optional<Integer> high = TrasactionData.getAll().stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(high.get());


        //8-Find the transactions with the smallest value
        System.out.println("******************Task-8 **************");

        Optional<Transaction> smValue = TrasactionData.getAll().stream()
                .reduce((t1,t2) -> t1.getValue()<t2.getValue() ? t1 : t2);

        System.out.println(smValue.get());

        Optional<Transaction> smallestValue = TrasactionData.getAll().stream()
                .min(comparing(Transaction::getValue));
        System.out.println(smallestValue.get());


    }
}
