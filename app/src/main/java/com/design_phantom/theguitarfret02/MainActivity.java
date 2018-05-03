package com.design_phantom.theguitarfret02;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static int fretCount = 26;
    private final static int stringCount = 6;
    private final static int btWidthDp = 70;
    private final static int btHeightDp = 36;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //scaleを取得
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        //drawableにセットされた背景画像を取得する
        Drawable bgImage = getResources().getDrawable(R.drawable.bgimg_guitar_fret);
        Drawable bgImageZero = getResources().getDrawable(R.drawable.bgimg_guitar_zero_fret);

        //LayoutParam
        ViewGroup.LayoutParams paramsFret = new ViewGroup.LayoutParams(
                (int) (btWidthDp * metrics.density),
                (int) (btHeightDp * metrics.density)
        );
        ViewGroup.LayoutParams paramsZeroFret = new ViewGroup.LayoutParams(
                (int) (60 * metrics.density),
                (int) (btHeightDp * metrics.density)
        );
        ViewGroup.LayoutParams paramsText = new ViewGroup.LayoutParams(
                (int) (btWidthDp * metrics.density),
                (int) (18 * metrics.density)
        );

        GridLayout layout = findViewById(R.id.gridlayout);
        GridLayout layout_zero = findViewById(R.id.gridlayout_zero);

        for (int i = 0; i < stringCount + 1; i++) {

            //開放弦部分の表示
            if(i != stringCount){
                TextView tvZero = new TextView(this);
                tvZero.setLayoutParams(paramsZeroFret);
                tvZero.setText("〇");
                tvZero.setGravity(Gravity.CENTER);
                tvZero.setBackground(bgImageZero);
                layout_zero.addView(tvZero);
            }

            for (int j = 0; j < fretCount; j++) {

                //フレット数の表示箇所
                if (i == stringCount) {
                    TextView tv = new TextView(this);
                    tv.setText(String.valueOf(j + 1));
                    tv.setLayoutParams(paramsText);
                    tv.setGravity(Gravity.CENTER);
                    layout.addView(tv);
                } else { //フレット表示部分
                    final Button bt = new Button(this);
                    //set listner
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(MainActivity.this,String.valueOf(param.width),Toast.LENGTH_SHORT ).show();
                            if(bt.getText().equals("")){
                                bt.setText("●");
                            }else{
                                bt.setText("");
                            }

                        }
                    });
                    bt.setLayoutParams(paramsFret);
                    bt.setBackground(bgImage);
                    layout.addView(bt);
                }
            }
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }
}
