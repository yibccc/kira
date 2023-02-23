package com.zpq.mybatis.test;

import com.zpq.scheduling.Scheduling;
import org.junit.jupiter.api.Test;

public class SchedulingTest {
    @Test
    public void testSailingMap() {
        Scheduling scheduling = new Scheduling();
        scheduling.getDayScheduling(7, 1);
    }

    @Test
    public void testStartTime() {
        Scheduling scheduling = new Scheduling();

    }

    @Test
    public void test() {
        Integer a = null;
        int b = a;
        System.out.println(b);  //空指针
    }

}
