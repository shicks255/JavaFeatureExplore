package ModernJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Closure
{

    public static void main(String[] args)
    {
//        simpleClosure();
        useFunctions();
    }

    public static void simpleClosure()
    {
        List<String> titles = new ArrayList<>(Arrays.asList("king robert", "queen mary", "prince william", "prince henry"));

        Predicate<String> isKing = startsWith("king");
        Predicate<String> isQueen = startsWith("queen");

//        titles.removeIf(isKing);
//        titles.removeIf(isQueen);
        //or
        System.out.println(titles.stream().filter(startsWith("king")).collect(Collectors.joining()));
        System.out.println(titles.stream().filter(startsWith("queen")).collect(Collectors.joining()));
        System.out.println(titles);
    }

    public static void useFunctions()
    {
        Function<String, Predicate<String>> makeNameChecker =
                (String nameToCheck) ->
                        (String check) -> check.equalsIgnoreCase(nameToCheck);

        List<String> names = new ArrayList<>(Arrays.asList("Joey", "Steve", "steven", "eric", "chris", "ed", "sean", "david", "alex"));

        List<String> onlyErics = names.stream()
                .filter(makeNameChecker.apply("eric"))
                .collect(Collectors.toList());
        System.out.println(onlyErics);

        List<String> onlySteve = names.stream()
                .filter(makeNameChecker.apply("steve"))
                .collect(Collectors.toList());
        System.out.println(onlySteve);

        //or to be even more concise...
        Function<String, Predicate<String>> makeNameChecker2 =
                nameToCheck -> checkThisName -> checkThisName.equalsIgnoreCase(nameToCheck);

        List<String> onlyDave = names.stream()
                .filter(makeNameChecker2.apply("david"))
                .collect(Collectors.toList());
        System.out.println(onlyDave);
    }

    public static Predicate<String> startsWith(String letter)
    {
        return x -> x.startsWith(letter);
    }

    //old way, none lambda
    public static Predicate<String> startsWithOld(String letter)
    {
        return new Predicate<String>()
        {
            @Override
            public boolean test(String s)
            {
                return s.startsWith(letter);
            }
        };
    }

}
