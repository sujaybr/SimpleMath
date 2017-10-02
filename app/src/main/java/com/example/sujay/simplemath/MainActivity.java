package com.example.sujay.simplemath;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();
    int operatorone, operatortwo, score, totscore, opernflag, lvlflag;
    long opanswer;
    Button operation, level, check, next;
    EditText et;
    LinearLayout space;
    TextView op1, op2, op, scores,checktext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operation = (Button) findViewById(R.id.boperation);
        level = (Button) findViewById(R.id.blevel);
        check = (Button) findViewById(R.id.bcheck);
        next = (Button) findViewById(R.id.bnext);

        et = (EditText) findViewById(R.id.eanswer);

        op1 = (TextView) findViewById(R.id.tvop1);
        op2 = (TextView) findViewById(R.id.tvop2);
        op = (TextView) findViewById(R.id.tvop);
        scores = (TextView) findViewById(R.id.tvscores);
        checktext = (TextView) findViewById(R.id.tvcheckresult);

        space = (LinearLayout) findViewById(R.id.space);

        //Initially
        score = 0;
        totscore = 0;
        lvlflag = 10;
        opernflag = 1;
        refresh();

        //Done Initializing

        operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opernflag == 3)
                    opernflag = 1;
                else
                    opernflag ++;

                switch (opernflag){
                    case 1:
                        operation.setText("ADD");
                        op.setText("+");
                        break;
                    case 2:
                        operation.setText("SUBTRACT");
                        op.setText("-");
                        break;
                    case 3:
                        operation.setText("MULTIPLY");
                        op.setText("x");
                        break;
                }
                refresh();
            }
        });

        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvlflag == 100000)
                    lvlflag = 10;
                else
                    lvlflag *= 10;
                switch (lvlflag){
                    case 10:
                        level.setText("LEVEL 1");
                        break;
                    case 100:
                        level.setText("LEVEL 2");
                        break;
                    case 1000:
                        level.setText("LEVEL 3");
                        break;
                    case 10000:
                        level.setText("LEVEL 4");
                        break;
                    case 100000:
                        level.setText("LEVEL 5");
                        break;
                }
                refresh();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            int flag = 0;
            @Override
            public void onClick(View v) {
                if (et.getText().toString().equals(Long.toString(opanswer)) || flag == 1){
                    if(flag == 1){
                        flag = 0;
                    }
                    else {
                        space.setBackgroundColor(Color.GREEN);
                        score ++;
                    }
                    refresh();
                }
                else{
                    space.setBackgroundColor(Color.RED);
                    if(flag == 0)
                        flag = 1;
                    else
                        flag = 0;
                    Toast.makeText(getApplicationContext(),"Click CHECK to see the Answer and \nNEXT to Skip the question",Toast.LENGTH_SHORT).show();
                }
                et.setText("");
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checktext.setText(opanswer + "");
                totscore ++;
            }
        });

    }

    private void refresh() {
        operatorone = rand.nextInt(500000) % lvlflag;
        operatortwo = rand.nextInt(500000) % lvlflag;
        opanswer = computeanswer(operatorone,operatortwo,opernflag);

        scores.setText(score + " / " + totscore);

        totscore ++;
        op1.setText(operatorone + "");
        op2.setText(operatortwo + "");
        checktext.setText("");
    }

    private int computeanswer(int operatorone, int operatortwo, int opernflag) {
        switch (opernflag){
            case 1:
                return operatorone + operatortwo;
            case 2:
                return operatorone - operatortwo;
            case 3:
                return operatorone * operatortwo;
        }
        return 0;
    }
}

