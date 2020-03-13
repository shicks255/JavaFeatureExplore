package ModernJava.Streams;

import ModernJava.Person;
import ModernJava.PersonDatabase;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collectors.*;

public class StreamCollecting {

    public static void main(String[] args) {
        PersonDatabase database = PersonDatabase.getInstance();
        List<Person> people = database.getItems();

        Set<Person> personSet = collectToset(people);
        Map<Integer, List<Person>> personByAge = groupByAge(people);
        Map<Integer, List<Person>> personByAgeAndFiltering = groupByAgeAndFiltering(people);
        Map<Integer, List<String>> personByAgeAndMapping = groupByAgeAndMapping(people);
        Map<Integer, List<String>> personByAgeWithFlatMap = groupByAgeAndFlatMapLetters(people);
        Map<Integer, Map<Integer, List<Person>>> personByDoubleGrouping = groupByAgeThenLetterCount(people);
        Map<Integer, IntSummaryStatistics> nameCountbyAgeStats = summarizing(people);
        Map<String, List<String>> skillsByPerson = skillsByPersonList(people);
    }

    public static Set<Person> collectToset(List<Person> people) {
        Set<Person> personSet = people.stream()
                .collect(toSet());
        System.out.println(personSet);
        return personSet;
    }

    public static Map<Integer, List<Person>> groupByAge(List<Person> people) {
        Map<Integer, List<Person>> byage = people.stream()
                .filter(x -> x.getAge() > 30)
                .collect(groupingBy(Person::getAge));

        System.out.println(byage);
        return byage;
    }

    //groupingBy and filtering()
    public static Map<Integer, List<Person>> groupByAgeAndFiltering(List<Person> people) {
        Map<Integer, List<Person>> byage = people.stream()
                .collect(groupingBy(Person::getAge, filtering(x -> x.getAge() > 30, toList())));

        System.out.println(byage);
        return byage;
    }

    //groupingBy and mapping()
    public static Map<Integer, List<String>> groupByAgeAndMapping(List<Person> people) {
        Map<Integer, List<String>> byage = people.stream()
                .collect(groupingBy(Person::getAge, mapping(x -> x.getName(), toList())));

        System.out.println(byage);
        return byage;
    }

    //groupingBy and flatMapping()
    public static Map<Integer, List<String>> groupByAgeAndFlatMapLetters(List<Person> people) {
        Map<Integer, List<String>> byage = people.stream()
                .collect(groupingBy(Person::getAge,
                        flatMapping(x -> Arrays.stream(x.getName().split("")), toList())));

        System.out.println(byage);
        return byage;
    }

    //groupingBy and groupingBy()
    public static Map<Integer, Map<Integer, List<Person>>> groupByAgeThenLetterCount(List<Person> people) {
        Map<Integer, Map<Integer, List<Person>>> byage = people.stream()
                .collect(groupingBy(Person::getAge,
                        groupingBy(x -> x.getName().length())));

        System.out.println(byage);
        return byage;
    }

    //summarizing
    public static Map<Integer, IntSummaryStatistics> summarizing(List<Person> people) {
        Map<Integer, IntSummaryStatistics> stats = people.stream()
                .collect(groupingBy(Person::getAge,
                        summarizingInt(x -> x.getName().length())));

        System.out.println(stats);
        return stats;
    }

    //trying a custom Collector
    public static Map<String, List<String>> skillsByPersonList(List<Person> people) {
        long currentTime = System.currentTimeMillis();
        Map<String, List<String>> skillsByPeople = people.stream()
                .collect(myCustomCollector());
        System.out.println(skillsByPeople);
        System.out.println("Took " + (System.currentTimeMillis() - currentTime));

        currentTime = System.currentTimeMillis();
        Map<String, List<String>> skillsByPeopleParallel = people.parallelStream()
                .collect(myCustomCollector());
        System.out.println(skillsByPeopleParallel);
        System.out.println("Took parallel " + (System.currentTimeMillis() - currentTime));

        return skillsByPeople;
    }

    private static Collector<Person, Map<String, List<String>>, Map<String, List<String>>> myCustomCollector() {
        return new Collector<>() {
            private List<String> combineAndReturn(List<String> one, List<String> two) {
                one.addAll(two);
                return one;
            }

            private List<String> initialize(Person person) {
                return new ArrayList<>(Arrays.asList(person.getName()));
            }

            @Override
            public Supplier<Map<String, List<String>>> supplier() {
                return () -> new ConcurrentHashMap<>();
            }

            @Override
            public BiConsumer<Map<String, List<String>>, Person> accumulator() {
                return (accumulatingMap, person) -> person.getSkills()
                        .forEach(skill -> accumulatingMap.merge(skill, initialize(person), this::combineAndReturn));
            }

            @Override
            public BinaryOperator<Map<String, List<String>>> combiner() {
                return (m1,m2) -> {
                    m2.entrySet().forEach(es -> {
                        m2.getOrDefault(es.getKey(), new ArrayList<>()).addAll(es.getValue());
                    });

                    return m1;
                };
            }

            @Override
            public Function<Map<String, List<String>>, Map<String, List<String>>> finisher() {
                return Function.identity();
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Set.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT);
            }
        };
    }
}
