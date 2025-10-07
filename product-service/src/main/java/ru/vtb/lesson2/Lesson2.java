package ru.vtb.lesson2;

import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class Lesson2 {
    /**
     * Дан список строк. Сформируйте такую версию списка, где не будет null, пустых строк и дубликатов.
     * <p>
     * [null, “”, “ABC”, “ABC”,”QQ”]  => [“ABC”,”QQ”]
     *
     * @param list
     * @return
     */
    public List<String> one(List<String> list) {
        return list.stream()
                .filter(r -> r!= null && !r.equals(""))
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Дан список строк. Посчитайте количество разных букв во всех строках.
     * <p>
     * [“ABC”,”CDE”, “EE”] => 5 (так как уникальные буквы это ABCDE)
     *
     * @param list
     * @return
     */
    public long two(List<String> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .reduce((item, item2) -> item + item2)
                .orElse("")
                .chars()
                .distinct().count();
    }

    /**
     * Дан список строк. Верните самое длинное слово из списка.
     * <p>
     * [“ABC”,”CDEF”, “EE”] => “CDEF”
     *
     * @param list
     * @return
     */
    public String three(List<String> list) {
        return list.stream().max((o1, o2) -> o1.length() > o2.length() ? 0 : -1)
                .orElse(null);
    }

    /**
     * Дан список чисел. Найдите в списке 3-е наибольшее число
     * <p>
     * [5, 2, 10, 9, 4, 3, 10, 1, 13] => 10
     *
     * @param list
     * @return
     */
    public Integer four(List<Integer> list, Integer pos) {
        if (list.size() < pos-1) {
            log.info("размер массива меньше позиции");
            return 0;
        }
        return list.stream()
                .sorted(Comparator.reverseOrder())
                .toList()
                .get(pos-1);
    }

    /**
     * Дан список объектов типа Сотрудник (имя, возраст, должность),
     * необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
     *
     * @return
     */
    public List<Employee> five(List<Employee> employees) {
        return employees.stream()
                .filter(emp -> emp.getPosition().equals("Инженер"))
                .sorted((o1, o2) -> o1.getAge() > o2.getAge() ? -1 : 0)
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Дан список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
     *
     * @param employees
     * @return
     */
    public Double six(List<Employee> employees) {
        return employees.stream()
                .filter(emp -> emp.getPosition().equals("Инженер"))
                .mapToDouble(Employee::getAge)
                .average()
                .orElse(0D);
    }

    /**
     * Дана строка, которая представляет собой предложение из нескольких слов. Необходимо получить Map,
     * в которой ключом будет число, означающее длину слова, а значение это список слов указанной длины.
     * Пробелы и другие разделители необходимо убрать. Слова в разном регистре считаются одним и тем же словом.
     * <p>
     * “Мама мыла Окно, окно было довольно” => [4: мама, мыла, окно, было; 8: довольно]
     *
     * @return
     */
    public Map<Integer, List<String>> seven(String str) {
        return Arrays.stream(str.toLowerCase()
                        .replaceAll("[^а-яА-Я|\\s]", "")
                        .split(" "))
                .toList()
                .stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toList()));
    }

    /**
     * Дан список строк. Каждая строка содержит предложение. Верните список самых длинных слов.
     * <p>
     * [“Мама мыла Окно, окно было довольно”, “кровать”] => [довольно]
     * <p>
     * (довольно – самое длинное слово и оно одно)
     *
     * @return
     */
    public List<String> nine(List<String> list) {
        var maxSent = new TreeMap<Integer, List<String>>();
        Arrays.stream(list.stream()
                        .reduce((sent1, sent2) -> sent1 + " " + sent2)// Собираем все предложения в едино
                        .map(l -> l.replaceAll("[^а-яА-Я|\\s]", "")) // убираем не буквы и не пробелы
                        .map(l -> l.split(" ")) // делим на слова
                        .get())
                .forEach( word -> { //  ищем макс слова закидываем в дерево по весу
                    if(maxSent.containsKey(word.length())){
                        ArrayList<String> listNew = new ArrayList<>(List.copyOf(maxSent.get(word.length())));
                        listNew.add(word);
                        maxSent.put(word.length(), listNew);
                    } else {
                        maxSent.put(word.length(), List.of(word));
                    }
                });
        return maxSent.lastEntry().getValue(); // Последний лист самый тяжелый
    }
}
