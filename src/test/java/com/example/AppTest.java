package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AppTest {

    @Test
    public void testAdd() {
        App obj = new App();
        assertEquals(8, obj.add(5, 3));
    }
}