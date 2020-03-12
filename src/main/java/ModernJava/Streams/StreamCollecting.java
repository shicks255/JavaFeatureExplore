package ModernJava.Streams;

import ModernJava.Person;
import ModernJava.PersonDatabase;

import java.util.*;

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
}
