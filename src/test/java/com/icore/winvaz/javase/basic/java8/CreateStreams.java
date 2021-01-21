package com.icore.winvaz.javase.basic.java8;

import com.icore.winvaz.javase.Person;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/12/4 15:37
 * @Version 1.0.0
 */
public class CreateStreams {

    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.println("...");
        }
        System.out.println();
    }

    public static <T> void show(String title, IntStream stream) {
        final int SIZE = 10;
        int[] firstElements = stream.limit(SIZE + 1).toArray();
        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.length; i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements[i]);
            else System.out.println("...");
        }
        System.out.println();
    }

    @Test
    public void test1() {
        // filter()方法，会产生一个流，它的元素与某种条件相匹配
        List<String> strings = Arrays.asList("aa", "b", "cc", "d");
        // strings.forEach(System.out::println);
        // strings.stream().filter(x -> x.length() == 2).forEach(System.out::println);
        // 转换流中的值。map方法并传递执行该转换的函数，方法引用
        // strings.stream().map(String::toUpperCase).forEach(System.out::println);
        // lambda表达式
        // strings.stream().map(s -> s.substring(0, 1)).forEach(System.out::println);

        // 编辑
        // letters("boat").stream().forEach(s -> System.out.println(s));
        // IntStream.range()
    }

    @Test
    public void test2() {
        List<String> strings = Arrays.asList("your", "boat");
        // Stream<Stream<String>> streamStream = strings.stream().map(s -> letters(s)); // [...["y", "o", "u", "r"],["b"
        // ...]]
        // 将其摊平为字母流[..."y"..."b"...]
        Stream<String> stringStream = strings.stream().flatMap(s -> letters(s));
        stringStream.forEach(System.out::println);
    }

    @Test
    public void optionalTest() {
        /**
         *  Java核心技术 卷II 11页
         *  Optional<T>对象是一种包装器对象。
         *  有效使用Optional的关键是要使用这样的方法：它在值不存在的情况下会产生一个可替代物，
         *  而只有在值存在的情况下才会使用这个值。
         */
        Stream<String> stringStream = Stream.of("GaosLin", "Java", "Hello", "World", "");
        Optional<String> first = stringStream.filter(s -> s.startsWith("H")).findFirst();
        // Optional<String> first = stringStream.parallel().filter(s -> s.startsWith("H")).findAny();
        // System.out.println(first.orElse(""));
        // System.out.println(first.orElseGet(() -> Locale.getDefault().getDisplayName()));
        // System.out.println(first.orElseThrow(RuntimeException::new));

        // ifPresetn()方法
        // List<String> results = new ArrayList<>();
        // ifPresent()从该函数不会反悔任何值，如果想要处理函数结果，应该使用map
        // first.ifPresent(results::add);
        // Optional<Boolean> added = first.map(results::add);

        List<String> wordList = Arrays.asList("GaosLin", "Java", "Hello", "World", "");
        Optional<String> optionalValue = wordList.stream().filter(s -> s.contains("a")).findFirst();
        System.out.println(optionalValue.orElse("No Word") + " contains char a");

        Optional<Object> optionalString = Optional.empty();
        Object result = optionalString.orElse("N/A");
        System.out.println("result: " + result);
        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);
        try {
            result = optionalString.orElseThrow(IllegalStateException::new);
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        optionalValue = wordList.stream().filter(s -> s.contains("Hell")).findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " contains Hello"));

        Set<String> results = new HashSet<>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added);

        System.out.println(inverse(4.0).flatMap(CreateStreams::squareRoot));
        System.out.println(inverse(-1.0).flatMap(CreateStreams::squareRoot));
        System.out.println(inverse(0.0).flatMap(CreateStreams::squareRoot));
        Optional<Double> result2 = Optional.of(-4.0).flatMap(CreateStreams::inverse).flatMap(CreateStreams::squareRoot);
        System.out.println(result2);

    }

    @Test
    public void collectTest() {
        // 收集结果
        Iterator<Integer> iterator = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array:" + numbers); // Note it's an Object[] array

        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number: " + number);
            System.out.println("The following statement throws an exception:");
            Integer[] numbers2 = (Integer[]) numbers; // Throws exception
        } catch (Exception e) {
            System.out.println(e);
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array: " + numbers3); // Note it's an Integer[] array

        Set<String> set = noVowels().collect(Collectors.toSet());
        show("set", set);

        TreeSet<String> treeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("treeSet", treeSet);

        String result = noVowels().limit(3).collect(Collectors.joining());
        System.out.println("Joining: " + result);
        result = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("Joining with commas: " + result);

        IntSummaryStatistics intSummaryStatistics = noVowels().collect(Collectors.summarizingInt(String::length));
        double average = intSummaryStatistics.getAverage();
        int max = intSummaryStatistics.getMax();
        System.out.println("Average word length: " + average);
        System.out.println("Max word length: " + max);
        System.out.println("forEach:");
        noVowels().limit(4).forEach(System.out::println);
    }

    public static Stream<String> noVowels() {
        List<String> wordList = Arrays.asList("pulic", "static", "void", "class");
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.print(label + ": " + set.getClass().getName());
        System.out.println("[" + set.stream().limit(10).map(Objects::toString).collect(Collectors.joining(", ")) + "]");
    }

    @Test
    public void collectIntoMapTest() {
        /**
         * Java核心技术 卷II 21页 toMap()
         */
        Map<Integer, String> ageToName = person().collect(Collectors.toMap(Person::getAge, Person::getName));
        System.out.println("ageToName: " + ageToName);

        Map<Integer, Person> ageToPerson = person().collect(Collectors.toMap(Person::getAge, Function.identity()));
        System.out.println("ageToPerson: " + ageToPerson.getClass().getName() + ageToPerson);

        ageToPerson = person().collect(Collectors.toMap(Person::getAge, Function.identity(),
                (existingValue, newValue) -> {
                    throw new IllegalStateException();
                }, TreeMap::new));
        System.out.println("ageToPerson: " + ageToPerson.getClass().getName() + ageToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(Collectors.toMap(Locale::getDisplayName,
                locale -> locale.getDisplayLanguage(locale),
                (existingValue, newValue) -> existingValue));
        System.out.println("languageNames: " + languageNames);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguageSets = locales.collect(Collectors.toMap(Locale::getDisplayCountry,
                locale -> Collections.singleton(locale.getDisplayLanguage()),
                (a, b) -> { // Union of a and b
                    Set<String> union = new HashSet<>(a);
                    union.addAll(b);
                    return union;
                }));
        System.out.println("countryLanguageSets: " + countryLanguageSets);
    }

    /**
     * Java核心技术 卷II 25页
     * @author wdq
     * @create 2021/1/4 15:10
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void downStreamCollectorsTest() throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(Collectors.groupingBy(Locale::getCountry,
                Collectors.toSet()));
        System.out.println("countryToLocaleSet: " + countryToLocaleSet);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
        System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);

        Stream<City> cities = readCities("cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(Collectors.groupingBy(City::getState,
                Collectors.summingInt(City::getPopulation)));
        System.out.println("stateToCtiyPopulation: " + stateToCityPopulation);

        cities = readCities("cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(Collectors.groupingBy(City::getState,
                Collectors.mapping(City::getName,
                Collectors.maxBy(Comparator.comparing(String::length)))));
        System.out.println("stateToLongestCityName: " + stateToLongestCityName);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry,
                Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
        System.out.println("countryToLanguages: " + countryToLanguages);

        cities = readCities("cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopluationSummary = cities.collect(Collectors.groupingBy(City::getState,
                Collectors.summarizingInt(City::getPopulation)));
        System.out.println(stateToCityPopluationSummary.get("NY"));

        cities = readCities("cities.txt");
        cities.collect(Collectors.groupingBy(City::getState, Collectors.reducing("", City::getName,
                (s, t) -> s.length() == 0 ? t : s + ", " + t)));
        cities = readCities("cities.txt");
        Map<String, String> stateToCityNames = cities.collect(Collectors.groupingBy(City::getState,
                Collectors.mapping(City::getName,
                Collectors.joining(", "))));
        System.out.println("stateToCityNames: " + stateToCityNames);
    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).map(l -> l.split(", ")).map(a -> new City(a[0], a[1],
                Integer.parseInt(a[2])));
        // return null;
    }

    /**
     * Java核心技术 卷II 31页
     * @author wdq
     * @create 2021/1/4 15:20
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void primitiveTypeStreamsTest() throws IOException {
        IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
        show("is1", is1);

        IntStream is2 = IntStream.range(5, 10);
        show("is2", is2);

        IntStream is3 = IntStream.rangeClosed(5, 10);
        show("is3", is3);
        /*
        Path path = Paths.get("../gutenberg/alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream is4 = words.mapToInt(String::length);
        show("is4", is4);
        */

        String sentence = "\uD835\uDD46 is the set of octonions";
        System.out.println(sentence);

        IntStream codes = sentence.codePoints();
        System.out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining()));

        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        IntStream is5 = integers.mapToInt(Integer::intValue);
        show("is5", is5);

    }

    /**
     * Java核心技术 卷II 37页
     *  并行流
     * @author wdq
     * @create 2021/1/4 16:34
     * @param
     * @Return void
     * @exception
     */
    @Test
    public void parallelStreamsTest() {
        // String contents = new String(Files.readAllBytes(Paths.get("../guteberg/alice30.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList("What", "You", "Name", "My", "Name", "Is", "Tom", "And", "Jerry");

        // 非常危险的代码
        int[] shortWords = new int[10];
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));

        // 多次尝试结果可能不同或总是错误数据
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));

        // 补充：分组汇总
        Map<Integer, Long> shortWordCounts =
                wordList.parallelStream().filter(s -> s.length() < 10).collect(Collectors.groupingBy(String::length,
                Collectors.counting()));
        System.out.println(shortWordCounts);

        // 不确定性的下游收集器
        Map<Integer, List<String>> result =
                wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(2));

        ConcurrentMap<Integer, Long> wordCounts =
                wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length,
                        Collectors.counting()));
        System.out.println(wordCounts);


    }

    @Data
    static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }
    }

    public static Stream<Person> person() {
        return Stream.of(new Person("zhangsan", 11), new Person("lisi", 12), new Person("wangwu", 18));
    }

    /**
     * 创建Optional值
     *
     * @param x
     * @throws
     * @author wdq
     * @create 2020/12/30 14:43
     * @Return java.util.Optional<java.lang.Double>
     */
    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
            // 使用IntSream.range()方法，实现起来优雅得多
            // IntStream range = IntStream.range(i, i + 1);
            // result.add(range);
        }
        return result.stream();
    }

    public static void main(String[] args) throws IOException {
        // Path path = Paths.get("./");
        // String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        // 静态方法创建流
        // Stream<String> words = Stream.of(contents.split("\\PL+"));
        // show("word", words);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        // 创建一个空流，不包含任何元素
        Stream<Object> silence = Stream.empty();
        show("silence", silence);
        // 创建无限流
        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echo", echos);
        // stream.limit(n)会返回一个新流，它在n个元素之后结束
        Stream<Double> randoms = Stream.generate(Math::random).limit(5);
        show("randoms", randoms);
        // 调用stream.skip(n)正好相反，它会丢弃前n个元素
        randoms.skip(2).forEach(System.out::println);
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);
        // 正则
        // Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        // show("wordsAnotherWay", wordsAnotherWay);
        // 文件静态方法
        // try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
        //     show("lines", lines);
        // }
        Object[] powers =
                Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println("Fetching " + e)).limit(20).toArray();
    }
}
