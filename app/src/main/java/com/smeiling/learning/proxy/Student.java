package com.smeiling.learning.proxy;

import com.smeiling.learning.Logg;

/**
 * @Author: Smeiling
 * @Date: 2019-02-28 16-35
 * @Description:
 */
public class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logg.debug(name + "上交班费50元");
    }
}
