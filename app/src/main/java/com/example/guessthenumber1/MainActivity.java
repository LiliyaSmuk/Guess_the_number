package com.example.guessthenumber1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int guess = -1;
    boolean status = false;
    int min_num = 1;
    int max_num;

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    Button lvl_button1;
    Button lvl_button2;
    Button exit_button;

    protected void startNewGame(int max) {
        status = true;
        min_num = 1;
        max_num = max;
        guess = (int) ((Math.random() * (max - 1)) + 1);
        tvInfo.setText(getResources().getString(R.string.try_to_guess));
        bControl.setText(getResources().getString(R.string.input_value));
        String txt = "Попробуй угадать число (" + 1 + "; " + max + ")";
        tvInfo.setText(txt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.textView1);
        etInput = (EditText) findViewById(R.id.editText1);
        bControl = (Button) findViewById(R.id.button1);
        lvl_button1 = (Button) findViewById(R.id.lvl_button1);
        lvl_button2 = (Button) findViewById(R.id.lvl_button2);
        exit_button = (Button) findViewById(R.id.exit_button);


        bControl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!status) {
                    if (guess == -1) {
                        tvInfo.setText(getResources().getString(R.string.lvl_choose));
                    } else {
                        tvInfo.setText(getResources().getString(R.string.win_txt));
                    }
                } else {
                    String current_etInput = etInput.getText().toString();
                    if (current_etInput.equals("")){
                        tvInfo.setText(getResources().getString(R.string.empty_lineInGame));
                    } else {
                        int inp = Integer.parseInt(current_etInput);
                        if (inp > max_num) {
                            tvInfo.setText(getResources().getString(R.string.max_error));
                        } else if (inp < min_num) {
                            tvInfo.setText(getResources().getString(R.string.min_error));
                        } else if (inp > guess) {
                            tvInfo.setText(getResources().getString(R.string.ahead));
                        } else if (inp < guess) {
                            tvInfo.setText(getResources().getString(R.string.behind));
                        } else if (inp == guess) {
                            tvInfo.setText(getResources().getString(R.string.hit));
                            status = false;
                        }
                    }
                }
                etInput.setText("");
            }
        });

        lvl_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(100);
            }
        });

        lvl_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(1000);
            }
        });

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}