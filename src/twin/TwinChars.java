package twin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TwinChars {
    private String text;
    private Map<Boolean, Set<Character>> map;

    public TwinChars(String text) {
        this.text = text;
        map = text.toUpperCase().chars()
                .mapToObj(ch -> (char) ch)
                .collect(
                        Collectors.partitioningBy(
                                c -> c % 2 == 0,
                                Collectors.toCollection(HashSet::new)
                        )
                );
    }

    public void printInfo() {
        System.out.format(
                "The difference between amounts is %d%n",
                calcDifference()
        );
        map.forEach((k, v) -> {
            String title = k ? "Even group" : "Odd group";
            System.out.println(title);
            v.forEach(ch -> System.out.format("'%c' %d%n", ch, (int) ch));
        });
    }

    public int calcDifference(){
        int evenSum = map.get(true).stream().mapToInt(ch -> ch).sum();
        int oddSum = map.get(false).stream().mapToInt(ch -> ch).sum();
        return Math.abs(evenSum - oddSum);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            new TwinChars(args[0]).printInfo();
        } else {
            System.out.println("No argument! Use following syntax:\n" +
                    "java TwinChars \"any string\"");
        }
    }
}
