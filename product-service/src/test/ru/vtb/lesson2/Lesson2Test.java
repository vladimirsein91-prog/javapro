package ru.vtb.lesson2;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Log4j2
public class Lesson2Test {

    private Lesson2 service;

    @Before
    public void before() {
        service = new Lesson2();
    }

    @Test
    public void one() {
        var list = new ArrayList<String>();
        list.add(null);
        list.add("");
        list.add("");
        list.add("ABC");
        list.add("ABC");
        list.add("QQ");
        var res = service.one(list);
        log.info(res);
        assertEquals(res.size(), 2);
        assertEquals(res.get(0), "ABC");
    }


    @Test
    public void two() {
        var list = new ArrayList<String>();
        list.add(null);
        list.add("ABC");
        list.add("CDE");
        list.add("EE");
        var res = service.two(list);
        assertEquals(res, 5L);
    }

    @Test
    public void three() {
        var list = new ArrayList<String>();
        list.add("ABC");
        list.add("CDEF");
        list.add("EE");
        var res = service.three(list);
        assertEquals(res, "CDEF");
    }

    @Test
    public void four() {
        var list = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        var res = service.four(list, 3);
        assertEquals(res.longValue(), 10);
    }

    @Test
    public void five() {
        var res = service.five(getEmp());
        log.info(res);
        assertEquals(res.size(), 3);
    }

    @Test
    public void six() {
        var res = service.six(getEmp());
        log.info(res);
        assertEquals(res.longValue(), 38);
    }

    @Test
    public void seven() {
        var res = service.seven("Мама мыла Окно, окно былобы довольно");
        log.info(res);
        //[4: мама, мыла, окно, было; 8: довольно]
        assertEquals(res.size(), 3);
    }

    @Test
    public void nine() {
        var res = service.nine(List.of("Мама мыла Окно, окно было довольно", "кровать"));
        log.info(res);
        assertEquals(res.size(), 1);
    }

    private List<Employee> getEmp() {
        return List.of(new Employee("Саня", 30, "Инженер"),
                new Employee("Петя", 25, "Инженер"),
                new Employee("Клава", 18, "Менеджер"),
                new Employee("Вова", 40, "Инженер"),
                new Employee("Андрей", 50, "Инженер"),
                new Employee("Саша", 45, "Инженер"));
    }
}