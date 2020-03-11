package ModernJava;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalPractice
{
    //database call -> iterator process ->
    public static void main(String[] args) {
        PersonDatabase database = PersonDatabase.getInstance();

        doDatabaseCall(database)
                .getData(p -> p.age >= 30) // abstraction for making query
                .process(person -> person.forEach(p -> System.out.println(p))); //abstraction for doing something with results
    }

    public static DatabaseProcessor<Person> doDatabaseCall(PersonDatabase database) {
        return filter -> {
            return consumer -> {
                List<Person> results = database.getItems().stream()
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
