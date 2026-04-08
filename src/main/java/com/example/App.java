package com.example;

public class App {

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        App obj = new App();
        System.out.println("Result = " + obj.add(5, 3));
    }
}