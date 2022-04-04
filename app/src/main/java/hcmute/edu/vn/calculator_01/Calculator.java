package hcmute.edu.vn.calculator_01;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Calculator {
    static Double num1 = null, num2 = null, result = 0d;
    static Boolean mul, divide, add, sub, newScreen = true;

    public static void getInput(Button button, TextView screen){
        if (newScreen) // Màn hình cần được làm trống
            screen.setText("");
        String newScreenText;// Số tiếp theo
        if (screen.getText() == null) // Nếu màn hình trống
            newScreenText = "";
        else{ // Màn hình đang có số
            // Không cho phép một màn hình có 2 dấu .
            if (screen.getText().toString().contains(".") && button.getText().toString().equals("."))
                return;
            // Nối số vừa nhập vào số trên màn hình
            newScreenText = screen.getText().toString() + button.getText().toString();
        }
        // Tới đây thì màn hình không cần được làm trống nữa
        newScreen = false;
        screen.setText(newScreenText);
    }

    // Bấm dấu =
    public static String getResult(View view, TextView screen) {
        if (screen.getText() != null)
            if (num1 == null && num2 == null) {
                newScreen = true;
                result = Double.parseDouble(screen.getText().toString());
                if (result - result.intValue() != 0)
                    return cleanDecimalPointNumber(String.format("%.6f", result));
                else
                    return result.intValue() + "";
            }


        try {
            // Khi bấm dấu = thì có 2 trường hợp nên xuất kết quả:
            //    1. vừa nhập 1 số chưa thực hiện phép tính -> xuất số đó
            //    2. đã thực hiện phép tính -> xuất kết quả
            if (num1 != null) {
                if (screen.getText() != null) // Kiểm tra screen có số chưa
                    num2 = Double.parseDouble(screen.getText().toString()); // Lấy số thứ 2
                if (add){
                    result = num1 + num2;
                }
                else if (sub) {
                    result = num1 - num2;
                }
                else if (mul) {
                    result = num1 * num2;
                }
                else { // Phép chia
                    // Không thể chia 0
                    if (num2 == 0f) {
                        return null;
                    } else {
                        result = num1 / num2;
                    }
                }
                // gán kết quả trên screen vào num1 và reset
                num1 = result;
                newScreen = true;
                // return kết quả
                if (result - result.intValue() != 0)
                    return cleanDecimalPointNumber(String.format("%.6f", result));
                else
                    return result.intValue() + "";

            }
        }catch (Exception ex){
            return null;
        }
        return null;
    }

    // Bấm phép tính
    public static void getOperator(Button button, TextView screen){
        if (screen.getText() != null)
            num1 = Double.parseDouble(screen.getText().toString());
        if (button.getText().toString().equals("×")){
            mul = true;
            sub = add = divide = false;
        }
        else if (button.getText().toString().equals("÷")){
            divide = true;
            sub = add = mul = false;
        }
        else if (button.getText().toString().equals("+")){
            add = true;
            sub = divide = mul = false;
        }
        else {
            sub = true;
            divide = add = mul = false;
        }
        newScreen = true;
    }
    private static String cleanDecimalPointNumber(String number) {
        while (number.lastIndexOf("0") == number.length() - 1){
            number = number.substring(0, number.length()-1);
        }
        if (number.charAt(number.length()-1) == '.')
            number = number.substring(0, number.length()-1);
        return number;
    }

}
