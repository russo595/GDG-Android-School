package com.example.test.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import static com.example.test.calculator.Fact.factorial;


public class MainActivity extends AppCompatActivity {

    /**
     * Вызывается, когда активность создается впервые.
     */
    private boolean clear_screen = true;
    private boolean operator_state = false;
    private boolean insert_state = false;
    private boolean last_click = false;
    private float num1;
    private float num2;
    private float answer;
    private String operator = "";
    private EditText tv;

    private static final String KEY_INDEX = "index";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        if (savedInstanceState != null) {
            tv.setText(savedInstanceState.getString(KEY_INDEX, ""));
            num1 = savedInstanceState.getFloat("num1");
            num2 = savedInstanceState.getFloat("num2");
            operator = savedInstanceState.getString("operator");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_INDEX, tv.getText().toString());
        savedInstanceState.putFloat("num1", num1);
        savedInstanceState.putFloat("num2", num2);
        savedInstanceState.putString("operator", operator);
    }

    public void insertText(String text) {
        if (this.clear_screen) {
            tv.setText("");
            if (!Objects.equals(text, "0")) {
                this.clear_screen = false;
            }
        }

        this.insert_state = true;
        this.last_click = true;
        tv.append(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.about_title) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setOperator(String operator) {
        EditText tv = findViewById(R.id.tv);
        if (tv.getText().toString().equals("."))
            tv.setText("0");
        if (this.insert_state && this.operator_state && this.last_click) {
            calculator();
        }

        if (tv.getText().toString().length() > 0) {
            this.num1 = Float.parseFloat(tv.getText().toString());
        }
        this.operator_state = true;
        this.clear_screen = true;
        this.last_click = false;

        switch (operator) {
            case "+":
                this.operator = "+";

                break;
            case "-":
                this.operator = "-";

                break;
            case "*":
                this.operator = "*";

                break;
            case "/":
                this.operator = "/";

                break;
            case "±":
                this.answer = (-1) * this.num1;

                if (this.answer % 1 == 0) {
                    tv.setText(String.valueOf((int) this.answer + ""));
                } else tv.setText(String.format("%s", this.answer));
                this.clear_screen = true;

                this.operator = "";
                this.last_click = true;
                this.operator_state = false;

                break;
            case "!":
                this.answer = factorial(this.num1, getApplicationContext());
                if (this.answer % 1 == 0) {
                    tv.setText(String.valueOf((int) this.answer + ""));
                } else tv.setText(String.format("%s", this.answer));
                this.clear_screen = true;

                this.operator = "";
                this.last_click = true;
                this.operator_state = false;

                break;
            case "√":
                this.answer = (float) Math.sqrt(Float.parseFloat(tv.getText().toString()));
                if (this.answer % 1 == 0) {
                    tv.setText(String.valueOf((int) this.answer + ""));
                } else tv.setText(String.format("%s", this.answer));
                this.clear_screen = true;

                this.operator = "";
                this.last_click = true;
                this.operator_state = false;

                break;
            case "d":
                this.answer = 1 / Float.parseFloat(tv.getText().toString());
                if (this.num1 == 0) {
                    Toast t = Toast.makeText(this, R.string.error, Toast.LENGTH_LONG);
                    t.show();
                } else if (this.answer % 1 == 0) {
                    tv.setText(String.valueOf((int) this.answer + ""));
                } else {
                    tv.setText(String.format("%s", this.answer));
                    this.clear_screen = true;

                    this.operator = "";
                    this.last_click = true;
                    this.operator_state = false;
                }

                break;
            case "^":
                this.operator = "^";

                break;
            case "%":
                this.operator = "%";

                break;
        }
    }

    public void calculator() {
        EditText tv = findViewById(R.id.tv);
        if (tv.getText().toString().equals(".")) {
            tv.setText("0");
        }
        if (tv.getText().toString().length() > 0) {
            this.num2 = Float.parseFloat(tv.getText().toString());
        }
        switch (this.operator) {
            case "+":
                this.answer = this.num1 + this.num2;
                break;
            case "-":
                this.answer = this.num1 - this.num2;
                break;
            case "*":
                this.answer = this.num1 * this.num2;
                break;
            case "/":
                this.answer = this.num1 / this.num2;
                if (this.num2 == 0) {
                    Toast t = Toast.makeText(this, R.string.error, Toast.LENGTH_LONG);
                    t.show();
                }
                break;
            case "^":
                this.answer = (float) Math.pow(this.num1, this.num2);
                break;
            case "%":
                this.answer = (this.num1 * this.num2) / 100;
                break;
            default:
                this.answer = Float.parseFloat(tv.getText().toString());
                break;
        }

        if (this.answer % 1 == 0) {
            tv.setText(String.valueOf((int) this.answer + ""));
        } else tv.setText(String.format("%s", this.answer));
    }

    public void ButtonClickHandler(View v) {
        EditText tv = findViewById(R.id.tv);
        Button buttonExe = findViewById(R.id.buttonExe);
        buttonExe.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        switch (v.getId()) {
            case R.id.button0:
                insertText("0");
                break;
            case R.id.button1:
                insertText("1");
                break;
            case R.id.button2:
                insertText("2");
                break;
            case R.id.button3:
                insertText("3");
                break;
            case R.id.button4:
                insertText("4");
                break;
            case R.id.button5:
                insertText("5");
                break;
            case R.id.button6:
                insertText("6");
                break;
            case R.id.button7:
                insertText("7");
                break;
            case R.id.button8:
                insertText("8");
                break;
            case R.id.button9:
                insertText("9");
                break;
            case R.id.buttonPoint:
                if (!tv.getText().toString().contains(".") || this.operator_state) {
                    if (tv.getText().toString().equals("0")) insertText("0.");
                    else insertText(".");
                }

                break;
            case R.id.buttonAdd:
                setOperator("+");
                break;
            case R.id.buttonSub:
                setOperator("-");
                break;
            case R.id.buttonMulti:
                setOperator("*");
                break;
            case R.id.buttonDiv:
                setOperator("/");
                break;
            case R.id.buttonSqr:
                setOperator("√");
                break;
            case R.id.buttonPM:
                setOperator("±");
                break;
            case R.id.buttonPow:
                setOperator("^");
                break;
            case R.id.buttonMod:
                setOperator("%");
                break;
            case R.id.buttonFac:
                setOperator("!");
                break;
            case R.id.buttonOnediv:
                setOperator("d");
                break;
            case R.id.buttonExe:
                if (tv.getText().toString().length() > 0 && this.operator != "") {
                    calculator();
                    this.clear_screen = true;

                    this.operator = "";
                    this.operator_state = false;
                }
                break;
            case R.id.buttonDel:
                if (tv.getText().toString().length() > 1) {
                    String tv_new = tv.getText().toString().substring(0, tv.getText().toString().length() - 1);
                    tv.setText(tv_new);
                    this.clear_screen = false;
                } else {
                    tv.setText("0");
                    this.clear_screen = true;
                }
                break;
            case R.id.buttonClear:

                this.operator = "";
                this.operator_state = false;
                this.insert_state = false;
                this.last_click = false;
                this.clear_screen = true;
                tv.setText("0");
                break;
        }
    }
}