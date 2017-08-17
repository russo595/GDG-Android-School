package com.example.test.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    /**
     * Вызывается, когда активность создается впервые.
     */
    boolean clear_screen = true;
    boolean operator_state = false;
    boolean insert_state = false;
    boolean last_click = false;
    float Num1;
    float Num2;
    float Answer;
    String Operator = "";
    protected EditText tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (EditText) findViewById(R.id.tv);
        if (savedInstanceState != null) {
            tv.setText(savedInstanceState.getString(KEY_INDEX, ""));
            Num1 = savedInstanceState.getFloat("num1");
            Num2 = savedInstanceState.getFloat("num2");
            Operator = savedInstanceState.getString("operator");
        }
    }

    private static final String KEY_INDEX = "index";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_INDEX, tv.getText().toString());
        savedInstanceState.putFloat("num1", Num1);
        savedInstanceState.putFloat("num2", Num2);
        savedInstanceState.putString("operator", Operator);
    }

    public void insert_text(String text) {
        if (this.clear_screen) {
            tv.setText("");
            if (text != "0") {
                this.clear_screen = false;
            }
        }

        this.insert_state = true;
        this.last_click = true;
        tv.append(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.about_title) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void set_operator(String operator) {
        EditText tv = (EditText) findViewById(R.id.tv);
        if (tv.getText().toString().equals("."))
            tv.setText("0");
        if (this.insert_state && this.operator_state && this.last_click) {
            calculator();
        }

        if (tv.getText().toString().length() > 0) {
            this.Num1 = Float.parseFloat(tv.getText().toString());
        }
        this.operator_state = true;
        this.clear_screen = true;
        this.last_click = false;

        if (operator.equals("+"))
            this.Operator = "+";

        else if (operator.equals("-"))
            this.Operator = "-";

        else if (operator.equals("*"))
            this.Operator = "*";

        else if (operator.equals("/"))
            this.Operator = "/";

        else if (operator.equals("±")) {
            this.Answer = (-1) * this.Num1;

            if (this.Answer % 1 == 0) {
                tv.setText(String.valueOf((int) this.Answer + ""));
            } else tv.setText(this.Answer + "");
            this.clear_screen = true;

            this.Operator = "";
            this.last_click = true;
            this.operator_state = false;
        } else if (operator.equals("!")) {
            this.Answer = factorial(this.Num1);
            if (this.Answer % 1 == 0) {
                tv.setText(String.valueOf((int) this.Answer + ""));
            } else tv.setText(this.Answer + "");
            this.clear_screen = true;

            this.Operator = "";
            this.last_click = true;
            this.operator_state = false;
        } else if (operator.equals("√")) {
            this.Answer = (float) Math.sqrt(Float.parseFloat(tv.getText().toString()));
            if (this.Answer % 1 == 0) {
                tv.setText(String.valueOf((int) this.Answer + ""));
            } else tv.setText(this.Answer + "");
            this.clear_screen = true;

            this.Operator = "";
            this.last_click = true;
            this.operator_state = false;
        } else if (operator.equals("d")) {
            this.Answer = 1 / Float.parseFloat(tv.getText().toString());
            if (this.Num1 == 0) {
                Toast t = Toast.makeText(this, R.string.error, Toast.LENGTH_LONG);
                t.show();
            } else if (this.Answer % 1 == 0) {
                tv.setText(String.valueOf((int) this.Answer + ""));
            } else {
                tv.setText(this.Answer + "");
                this.clear_screen = true;

                this.Operator = "";
                this.last_click = true;
                this.operator_state = false;
            }
        } else if (operator.equals("^"))
            this.Operator = "^";

        else if (operator.equals("%"))
            this.Operator = "%";
    }

    public float factorial(float n) {
        if (n == 0)
            return 1;
        else if (n % 1 != 0 || n < 0) {
            Toast toast = Toast.makeText(this, R.string.error2, Toast.LENGTH_LONG);
            toast.show();
            return 0;
        } else return n * factorial(n - 1);
    }


    public void calculator() {
        EditText tv = (EditText) findViewById(R.id.tv);
        if (tv.getText().toString().equals(".")) {
            tv.setText("0");
        }
        if (tv.getText().toString().length() > 0) {
            this.Num2 = Float.parseFloat(tv.getText().toString());
        }
        if (this.Operator.equals("+")) {
            this.Answer = this.Num1 + this.Num2;
        } else if (this.Operator.equals("-")) {
            this.Answer = this.Num1 - this.Num2;
        } else if (this.Operator.equals("*")) {
            this.Answer = this.Num1 * this.Num2;
        } else if (this.Operator.equals("/")) {
            this.Answer = this.Num1 / this.Num2;
            if (this.Num2 == 0) {
                Toast t = Toast.makeText(this, R.string.error, Toast.LENGTH_LONG);
                t.show();
            }
        } else if (this.Operator.equals("^")) {
            this.Answer = (float) Math.pow(this.Num1, this.Num2);
        } else if (this.Operator.equals("%")) {
            this.Answer = (this.Num1 * this.Num2) / 100;
        } else {
            this.Answer = Float.parseFloat(tv.getText().toString());
        }

        if (this.Answer % 1 == 0) {
            tv.setText(String.valueOf((int) this.Answer + ""));
        } else tv.setText(this.Answer + "");
    }

    public void ButtonClickHandler(View v) {
        EditText tv = (EditText) findViewById(R.id.tv);
        Button buttonExe = (Button) findViewById(R.id.buttonExe);
        buttonExe.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        switch (v.getId()) {
            case R.id.button0:
                insert_text("0");
                break;
            case R.id.button1:
                insert_text("1");
                break;
            case R.id.button2:
                insert_text("2");
                break;
            case R.id.button3:
                insert_text("3");
                break;
            case R.id.button4:
                insert_text("4");
                break;
            case R.id.button5:
                insert_text("5");
                break;
            case R.id.button6:
                insert_text("6");
                break;
            case R.id.button7:
                insert_text("7");
                break;
            case R.id.button8:
                insert_text("8");
                break;
            case R.id.button9:
                insert_text("9");
                break;
            case R.id.buttonPoint:
                if (!tv.getText().toString().contains(".") || this.operator_state) {
                    if (tv.getText().toString().equals("0")) insert_text("0.");
                    else insert_text(".");
                }

                break;
            case R.id.buttonAdd:
                set_operator("+");
                break;
            case R.id.buttonSub:
                set_operator("-");
                break;
            case R.id.buttonMulti:
                set_operator("*");
                break;
            case R.id.buttonDiv:
                set_operator("/");
                break;
            case R.id.buttonSqr:
                set_operator("√");
                break;
            case R.id.buttonPM:
                set_operator("±");
                break;
            case R.id.buttonPow:
                set_operator("^");
                break;
            case R.id.buttonMod:
                set_operator("%");
                break;
            case R.id.buttonFac:
                set_operator("!");
                break;
            case R.id.buttonOnediv:
                set_operator("d");
                break;
            case R.id.buttonExe:
                if (tv.getText().toString().length() > 0 && this.Operator != "") {
                    calculator();
                    this.clear_screen = true;

                    this.Operator = "";
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

                this.Operator = "";
                this.operator_state = false;
                this.insert_state = false;
                this.last_click = false;
                this.clear_screen = true;
                tv.setText("0");
                break;
        }
    }


}