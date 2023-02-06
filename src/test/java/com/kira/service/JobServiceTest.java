package com.kira.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author shkstart
 * @create 2023-01-2714:55
 */
@SpringBootTest
public class JobServiceTest {
    @Autowired
    private IJobService service;

    @Test
    void testById(){

        System.out.println( service.getById(1));
    }
}
