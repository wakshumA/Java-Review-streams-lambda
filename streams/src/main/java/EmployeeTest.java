import java.util.List;

public class EmployeeTest {

    //print all emails

    public static void main(String[] args) {

        //print all emails - One employee has one email - One to One
        EmployeeData.readAll()
                .map(Employee::getEmpEmail)
                .forEach(System.out::println);


        //print all phone numbers
        EmployeeData.readAll()
                .map(Employee::getEmployeePhoneNumbers)
                .forEach(System.out::println);

        System.out.println("****************************");
        EmployeeData.readAll()
                .flatMap(employee -> employee.getEmployeePhoneNumbers().stream())
                .forEach(System.out::println);

        System.out.println("****************************");
        EmployeeData.readAll()
                .map(Employee::getEmployeePhoneNumbers)
                .flatMap(List::stream)
                .forEach(System.out::println);
    }
}
