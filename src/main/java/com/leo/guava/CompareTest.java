package com.leo.guava;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * compare chain 式
 */
public class CompareTest {
    public static void main(String[] args) {

    }

    public class Person implements Comparable<Person> {
        public String name;
        public String age;


        @Override
        public int compareTo(Person o) {
            return ComparisonChain.start()
                    .compare(this.name, o.name)
                    .compare(this.age, o.age, Ordering.natural().nullsLast())
                    .result();
        }
    }
}
