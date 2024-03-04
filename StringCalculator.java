import java.util.Arrays;

public class StringCalculator {
    public static int add(String input) {
        final String[] numbers;
        
        if (input.isEmpty()) {
            return 0;
        } else if (input.startsWith("//")) {
            final int lineEndIndex = input.indexOf("\n");
            String delimiter = input.substring(2, lineEndIndex);

            if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
                delimiter = delimiter.substring(1, delimiter.length() - 1);
                final String[] delimiters = delimiter.split("]\\[");
                delimiter = String.join("|", Arrays.stream(delimiters)
                                                             .map(d -> d.replace("|", "\\|"))
                                                             .toArray(String[]::new));
            }
            numbers = input.substring(lineEndIndex + 1).split(delimiter);
        } else {
            numbers = input.split(",|\n");
        }

        final String[] negatives = Arrays.stream(numbers)
                                         .filter(n -> n.startsWith("-"))
                                         .toArray(String[]::new);

        if (negatives.length > 0) {
            throw new IllegalArgumentException("Negatives not allowed: " + String.join(",", negatives));
        }

        return Arrays.stream(numbers)
                     .filter(n -> !n.isBlank())
                     .filter(n -> n.length() < 4)
                     .mapToInt(Integer::parseInt)
                     .sum();
    }


    public static void test(final String input) {
        System.out.println("input: " + input.replaceAll("\n", "\\n") + " output: " + StringCalculator.add(input));
    }

    public static void testThrows(final String input) {
        try {
            StringCalculator.add(input);
            System.out.println("input: " + input + " output: " + "no exception thrown");
        } catch (final IllegalArgumentException e) {
            System.out.println("input: " + input + " output: " + e.getMessage());
        }
    }
    

    public static void main(String[] args) {
        test("");
        test("1");
        test("1,2");
        test("1\n2,3");
        test("//;\n1;2");
        testThrows("-1,2");
        testThrows("2,-4,3,-5");
        test("1000,2");
        test("//[|||]\n1|||2|||3"); // delimiter not valid regex, greedy matching makes this match each individual pipe symbol
        test("//[|][%]\n1|2%3"); // delimiter not valid regex, comes out in regex as '|%' which is not found in the input
    }
}

