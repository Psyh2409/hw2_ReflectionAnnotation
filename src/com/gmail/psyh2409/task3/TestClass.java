package com.gmail.psyh2409.task3;

import com.gmail.psyh2409.task1.Test;

public class TestClass {
    public String p = "Test string";
    @Save
    private int y = 7;
    @Save
    protected int z = 8;

    public TestClass() {
    }

    public TestClass(int a) {
        this.y = a;
    }

    public TestClass(int a, int b) {
        this.y = a;
        this.z = b;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Test(a = 2, b = 42)
    private void sum(int a, int b){
        System.out.println(a+b);;
    }

    private void printObject(Object o) {
        System.out.println(y / z + " " + p + " ".concat(String.valueOf(o)));
    }

    @Override
    public String toString() {
        return "Test_Class{" +
                "p='" + p + '\'' +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
