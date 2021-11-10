package com.cp;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static final String PATH = "./resources/formulas.txt";

    public static void main(String[] args) throws IOException {
        var text = parseFileToList(PATH);

        for (List<String> wordList : text) {
            var iter = wordList.listIterator();
            while (iter.hasNext()) {
                var tmp = iter.next().toCharArray();
                if (!(isNumber(tmp) || isIdentifier(tmp))) {
                    if (!(tmp.length == 1 && isOperator(tmp[0]))) {
                        iter.set("");
                    }
                }
            }
        }

        //Task 2
        List<String> excluded = new ArrayList<>();
        text.stream().filter(wordList -> wordList.contains("")).flatMap(Collection::stream).filter(word -> isIdentifier(word.toCharArray())).forEach(excluded::add);
        text.stream().filter(wordList ->
                wordList.stream()
                        .filter(word -> word.equals("("))
                        .count() != wordList.stream()
                        .filter(word -> word.equals(")"))
                        .count()).flatMap(Collection::stream).filter(word -> isIdentifier(word.toCharArray())).forEach(excluded::add);

        //Task 1
        text.removeIf(wordList -> wordList.contains(""));
        text.removeIf(wordList ->
                wordList.stream()
                        .filter(word -> word.equals("("))
                        .count() != wordList.stream()
                        .filter(word -> word.equals(")"))
                        .count());

        var it = text.iterator();

        while (it.hasNext()) {
            var wordList = it.next();

            var bracketList = wordList.stream()
                    .filter(word -> word.length() == 1 && isBracket(word.charAt(0)))
                    .collect(Collectors.toList());
            var stack = new ArrayDeque<String>();

            for (String bracket : bracketList) {
                if (bracket.equals("(")) {
                    stack.push(bracket);
                    continue;
                }
                if (stack.isEmpty()) {
                    it.remove();
                    break;
                }
                if (bracket.equals(")"))
                    stack.pop();
            }
            if (!stack.isEmpty()) {
                it.remove();
            }
        }

        //Task 2
        text.stream().filter(wordList ->
                wordList.stream()
                        .filter(word -> word.length() == 1 && isSign(word.charAt(0)))
                        .count() + 1 != wordList.stream()
                        .filter(word -> isIdentifier(word.toCharArray()) || isNumber(word.toCharArray()))
                        .count()).flatMap(Collection::stream).filter(word -> isIdentifier(word.toCharArray())).forEach(excluded::add);
        //Task 1
        text.removeIf(wordList ->
                wordList.stream()
                        .filter(word -> word.length() == 1 && isSign(word.charAt(0)))
                        .count() + 1 != wordList.stream()
                        .filter(word -> isIdentifier(word.toCharArray()) || isNumber(word.toCharArray()))
                        .count());

        System.out.println("Before validation");
        var formulas = text.stream()
                .map(wordList -> String.join("", wordList))
                .collect(Collectors.toList());

        for (String formula : formulas) {
            System.out.println(formula);
        }
        System.out.println();

        //Task2
        text.stream().filter(wordList -> (long) wordList.size() <= 2)
                .flatMap(Collection::stream)
                .filter(word -> isIdentifier(word.toCharArray()))
                .forEach(excluded::add);

        text.stream()
                .filter(wordList -> wordList.stream().noneMatch(word -> isSign(word.charAt(0))))
                .flatMap(Collection::stream)
                .filter(word -> isIdentifier(word.toCharArray()))
                .forEach(excluded::add);

        //Task 1
        text.removeIf(wordList ->
                (long) wordList.size() <= 2);
        text.removeIf(wordList ->
                wordList.stream().noneMatch(word -> isSign(word.charAt(0))));
        it = text.iterator();
        a:
        while (it.hasNext()) {
            var wordList = it.next();
            for (int i = 1; i < wordList.size() - 1; i++) {
                if (isSign(wordList.get(i).charAt(0))) {
                    if (!((isIdOrNumber(wordList.get(i - 1)) || wordList.get(i - 1).equals(")")) &&
                            (isIdOrNumber(wordList.get(i + 1)) || wordList.get(i + 1).equals("(")))) {
                        excluded.addAll(wordList.stream().filter(word -> isIdentifier(word.toCharArray())).collect(Collectors.toList()));
                        it.remove();
                        continue a;
                    }
                }
                if ((wordList.get(i)).equals("(")) {
                    if (!((isOperator(wordList.get(i - 1).charAt(0)) || wordList.get(i - 1).equals("(")) &&
                            (isIdOrNumber(wordList.get(i + 1)) || wordList.get(i + 1).equals("(")))) {
                        excluded.addAll(wordList.stream().filter(word -> isIdentifier(word.toCharArray())).collect(Collectors.toList()));
                        it.remove();
                        continue a;
                    }
                }
                if ((wordList.get(i)).equals(")")) {
                    if (!((isIdOrNumber(wordList.get(i - 1)) || wordList.get(i - 1).equals(")")) &&
                            (isOperator(wordList.get(i + 1).charAt(0)) || wordList.get(i + 1).equals(")")))) {
                        excluded.addAll(wordList.stream().filter(word -> isIdentifier(word.toCharArray())).collect(Collectors.toList()));
                        it.remove();
                        continue a;
                    }
                }
                if (isIdOrNumber(wordList.get(i))) {
                    if (!((isOperator(wordList.get(i - 1).charAt(0)) || wordList.get(i - 1).equals("(")) &&
                            (isOperator(wordList.get(i + 1).charAt(0)) || wordList.get(i + 1).equals(")")))) {
                        excluded.addAll(wordList.stream().filter(word -> isIdentifier(word.toCharArray())).collect(Collectors.toList()));
                        it.remove();
                        continue a;
                    }
                }
            }
        }

        var identifiers  = text.stream()
                .map(list -> list.stream()
                        .filter(word -> isIdentifier(word.toCharArray()))
                        .sorted()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (int i = 0; i < text.size(); i++) {
            var iter = identifiers.get(i).iterator();
            for (int j = 0; j < text.get(i).size(); j++) {
                if (isIdentifier(text.get(i).get(j).toCharArray()) && iter.hasNext()) {
                    text.get(i).set(j, iter.next());
                }
            }
        }

        formulas = text.stream()
                .map(wordList -> String.join("", wordList))
                .collect(Collectors.toList());

        System.out.println("After validation");
        for (String formula : formulas) {
            System.out.println(formula);
        }
        System.out.println();

        System.out.println("Excluded: ");
        excluded.forEach(word -> System.out.print(word + " "));
        System.out.println();
    }

    public static List<List<String>> parseFileToList(String path) throws IOException {
        Scanner sc = new Scanner(Paths.get(path));
        List<List<String>> text = new ArrayList<>();
        while (sc.hasNextLine()) {
            text.add(new ArrayList<>());
            String line = sc.nextLine().trim();
            Scanner scanner = new Scanner(line);
            while (scanner.hasNext()) {
                String word = scanner.next();
                char[] arr = word.toCharArray();

                StringBuilder tmp = new StringBuilder();
                for (char c : arr) {
                    if (isOperator(c)) {
                        if (tmp.length() == 0) {
                            text.get(text.size() - 1).add(String.valueOf(c));
                            continue;
                        } else {
                            text.get(text.size() - 1).add(tmp.toString());
                            tmp = new StringBuilder();
                            text.get(text.size() - 1).add(String.valueOf(c));
                            continue;
                        }
                    }
                    tmp.append(c);
                }
                if (tmp.length() != 0) {
                    text.get(text.size() - 1).add(tmp.toString());
                }
            }
        }
        return text;
    }

    public static boolean isIdentifier(char[] arr) {
        for (char let : arr) {
            if (!Character.isLetter(let))
                return false;
        }
        return arr.length != 0;
    }

    public static boolean isSign(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static boolean isBracket(char ch) {
        return ch == '(' || ch == ')';
    }

    public static boolean isOperator(char ch) {
        return isSign(ch) || isBracket(ch);
    }

    public static boolean isNumber(char[] arr) {
        for (char let : arr) {
            if (!Character.isDigit(let))
                return false;
        }
        return true;
    }

    public static boolean isIdOrNumber(String word) {
        return isIdentifier(word.toCharArray()) || isNumber(word.toCharArray());
    }

}
