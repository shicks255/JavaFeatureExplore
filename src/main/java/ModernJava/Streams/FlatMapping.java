package ModernJava.Streams;

import ModernJava.Person;
import ModernJava.PersonDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapping {

    public static void main(String[] args) {
        PersonDatabase database = PersonDatabase.getInstance();

        List<Person> people = database.getItems();

        List<String> characters = people.stream()
                .map(x -> x.getName().toCharArray())
                .map(x -> Arrays.toString(x))
                .collect(Collectors.toList());
        System.out.println(characters);

        List<List<String>> unFlattened = people.stream()
                .map(x -> x.getSkills())
                .collect(Collectors.toList());
        System.out.println(unFlattened);

        List<String> allSkills = people.stream()
                .flatMap(x -> x.getSkills().stream())
                .collect(Collectors.toList());

        System.out.println(allSkills);

    }


}
