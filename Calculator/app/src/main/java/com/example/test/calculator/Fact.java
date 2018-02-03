package com.example.test.calculator;

import android.content.Context;
import android.widget.Toast;

class Fact {
    static float factorial(float n, Context context) {
        if (n == 0)
            return 1;
        else if (n % 1 != 0 || n < 0) {
            Toast.makeText(context, R.string.error2, Toast.LENGTH_LONG).show();
            return 0;
        } else return n * factorial(n - 1, context);
    }
}
