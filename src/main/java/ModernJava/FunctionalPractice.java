package ModernJava;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalPractice
{
    static List<Person> database = new ArrayList<>();
    //database call -> iterator process ->
    public static void main(String[] args) {
        database.add(new Person("Steve", 30));
        database.add(new Person("Eric", 32));
        database.add(new Person("Rini", 30));
        database.add(new Person("Alex", 30));

        doDatabaseCall()
                .getData(p -> p.age >= 30) // abstraction for making query
                .process(person -> person.forEach(p -> System.out.println(p))); //abstraction for doing something with results
    }

    public static DatabaseProcessor<Person> doDatabaseCall() {
        return filter -> {
            return consumer -> {
                List<Person> results = database.stream()
                        .filter(filter)
                        .collect(Collectors.toList());
                consumer.accept(results);
            };
        };
    }

    @FunctionalInterface
    public interface ListProcessor<T> {
        void process(Consumer<T> doSomething);
    }

    @FunctionalInterface
    public interface DatabaseProcessor<T> {
        ListProcessor<List<T>> getData(Predicate<T> filter);
    }
}

class Person {
    String name = "";
    int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person - " + name + " is " + age + " years old";
    }
}
