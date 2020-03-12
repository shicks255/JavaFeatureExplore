package ModernJava;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class StreamCreating {

    public static void main(String[] args) {

        Stream<Double> intGeneratedStream = Stream.generate(() -> Math.random() * 100);
        List<Double> randomNumbers = intGeneratedStream.limit(10).collect(toList());
        System.out.println(randomNumbers);


        Stream<Integer> intIteratedStream = Stream.iterate(0, (i) -> ++i);
//        List<Integer> evenNumbers = intIteratedStream.limit(10).collect(toList());
//        System.out.println(evenNumbers);
        Map<Boolean, Map<Boolean, List<Integer>>> evenAndOddsPartition = intIteratedStream.limit(25).collect(partitioningBy(x -> x % 2 == 0, partitioningBy(x -> x > 10)));
        System.out.println(evenAndOddsPartition);

        Stream<Integer> intStream2 = Stream.iterate(0, (i) -> i < 10, (i) -> i+1);
        List<Integer> first10 = intStream2.collect(toList());
        System.out.println(first10);
    }

}
