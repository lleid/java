package com.leo.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;

/**
 * 表格
 * 根据key去查询map
 */
public class TableTest {
    public static void main(String[] args) {
        Table<String, String, String> employeeTable = HashBasedTable.create();

        employeeTable.put("IBM", "101", "Mahesh");
        employeeTable.put("IBM", "102", "Ramesh");
        employeeTable.put("IBM", "103", "Suresh");

        employeeTable.put("Microsoft", "111", "Sohan");
        employeeTable.put("Microsoft", "112", "Mohan");
        employeeTable.put("Microsoft", "113", "Rohan");

        employeeTable.put("TCS", "121", "Ram");
        employeeTable.put("TCS", "122", "Shyam");
        employeeTable.put("TCS", "123", "Sunil");

        Map<String, String> ibmEmployees = employeeTable.row("IBM");
        ibmEmployees.forEach((k, v) -> System.out.println(k + "," + v));


        Set<String> employers = employeeTable.rowKeySet();
        employers.forEach(s -> System.out.println(s));

        employeeTable.column("102").forEach((k, v) -> {
            System.out.println(k + "," + v);
        });

    }
}
