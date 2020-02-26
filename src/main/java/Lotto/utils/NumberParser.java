package Lotto.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberParser {
    private final static String DELIMITER = ",";
    private final static String MESSAGE_ONLY_NUMBER = "숫자만 입력하세요.";

    private NumberParser() {
    };

    public static List<Integer> parse(String input) {
        return Stream.of(input.split(DELIMITER))
                .map(String::trim)
                .filter(t -> !t.isEmpty())
                .map(NumberParser::validate)
                .collect(Collectors.toList());
    }

    private static int validate(String parsed) {
        try {
            return Integer.parseInt(parsed);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(MESSAGE_ONLY_NUMBER);
        }
    }
}
