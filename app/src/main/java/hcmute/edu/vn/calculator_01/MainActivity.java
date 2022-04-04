package hcmute.edu.vn.calculator_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
        btnDivide, btnMultiply, btnSubtract, btnPlus, btnClear, btnDot, btnResult;
    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDot = findViewById(R.id.btnDot);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnPlus = findViewById(R.id.btnPlus);
        btnClear = findViewById(R.id.btnClear);
        btnResult = findViewById(R.id.btnResult);

        // get numbers event
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        // get operators event
        btnMultiply.setOnClickListener(this::getOperator);
        btnPlus.setOnClickListener(this::getOperator);
        btnSubtract.setOnClickListener(this::getOperator);
        btnDivide.setOnClickListener(this::getOperator);
        // get result event
        btnResult.setOnClickListener(this::getResult);
        // clear event
        btnClear.setOnClickListener(this::clear);
    }

    private void getResult(View view) {
        screen = findViewById(R.id.screen);
        String result = Calculator.getResult(view, screen);
        if (result == null) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập phép tính đúng!", Toast.LENGTH_SHORT)
                    .show();
            clear(view);
        }
        else
            screen.setText(result);

    }

    private void getOperator(View view) {
        screen = findViewById(R.id.screen);
        Calculator.getOperator((Button) view, screen);
    }

    // Bấm C
    public void clear(View view){
        screen = findViewById(R.id.screen);
        screen.setText("");
        Calculator.num1 = Calculator.num2 = null;
        Calculator.result = 0d;
        Calculator.mul = Calculator.divide = Calculator.sub = Calculator.add = null;
    }
    @Override
    public void onClick(View view) {
        screen = findViewById(R.id.screen);
        Calculator.getInput((Button) view, screen);
    }
}