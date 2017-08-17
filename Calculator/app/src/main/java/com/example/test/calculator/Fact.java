package com.example.test.calculator;

public class Fact {
    public void factorial(float n) {
        float ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * i;
        }
    }
}
