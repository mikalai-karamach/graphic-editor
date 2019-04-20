package by.bsuir.karamach.util;

import java.util.ArrayList;
import java.util.List;

public final class UICoordinatesParser {

    private static final String REGEX_SPACE = "\\s+";

    private UICoordinatesParser() {
    }

    public static List<Integer> parseToCoordinates(String line, int amountOfCoordinatesExpected) throws ParserException {
        String[] parsedLexemes = line.split(REGEX_SPACE);

        List<Integer> coordinates = new ArrayList<>();

        String errorMessage;

        try {

            for (String lexeme : parsedLexemes) {
                coordinates.add(Integer.valueOf(lexeme));
            }

            if (coordinates.size() != amountOfCoordinatesExpected) {
                throw new ParserException("Invalid amount of parameters");
            }

        } catch (NumberFormatException e) {

            errorMessage = e.getMessage();
            throw new ParserException("Number format exception: " + errorMessage);

        }

        return coordinates;

    }

    public static List<Integer> parseToCoordinates(String line) throws ParserException {
        String[] parsedLexemes = line.split(REGEX_SPACE);

        List<Integer> coordinates = new ArrayList<>();

        String errorMessage;

        try {

            for (String lexeme : parsedLexemes) {
                coordinates.add(Integer.valueOf(lexeme));
            }

            if (isEvenSize(coordinates)) {
                throw new ParserException("Invalid amount of parameters");
            }

        } catch (NumberFormatException e) {

            errorMessage = e.getMessage();
            throw new ParserException("Number format exception: " + errorMessage);
        }

        return coordinates;
    }

    private static boolean isEvenSize(List<Integer> coordinates) {
        return coordinates.size() % 2 == 1;
    }
}
