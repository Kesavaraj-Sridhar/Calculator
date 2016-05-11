package in.codehex.calculator;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.codetail.animation.SupportAnimator;

public class MainActivity extends AppCompatActivity {
    private final int MENUITEM_CLOSE = 300;

    ImageView bgImageView;
    LinearLayout calculatorLayout;
    Boolean flag = true;
    float pixelDensity;
    Animation alphaAnimation;
    boolean hidden = true;

    private TextView txtCalc = null;
    private Button btnZero = null;
    private Button btnOne = null;
    private Button btnTwo = null;
    private Button btnThree = null;
    private Button btnFour = null;
    private Button btnFive = null;
    private Button btnSix = null;
    private Button btnSeven = null;
    private Button btnEight = null;
    private Button btnNine = null;
    private Button btnPlus = null;
    private Button btnMinus = null;
    private Button btnMultiply = null;
    private Button btnDivide = null;
    private Button btnEquals = null;
    private Button btnC = null;
    private Button btnDecimal = null;
    private Button btnMC = null;
    private Button btnMR = null;
    private Button btnMM = null;
    private Button btnMP = null;
    private Button btnBS = null;
    private Button btnPerc = null;
    private Button btnSqrRoot = null;
    private Button btnPM = null;

    private double num = 0;
    private double memNum = 0;
    private int operator = 1;
    // 0 = nothing, 1 = plus, 2 = minus, 3 =
    // multiply, 4 = divide
    private boolean readyToClear = false;
    private boolean hasChanged = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pixelDensity = getResources().getDisplayMetrics().density;
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initControls();
        initScreenLayout();
        reset();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triggerReveal();
            }
        });
    }

    private void initScreenLayout() {

		/*
         * The following three command lines you can use depending upon the
		 * emulator device you are using.
		 */

        // 320 x 480 (Tall Display - HVGA-P) [default]
        // 320 x 240 (Short Display - QVGA-L)
        // 240 x 320 (Short Display - QVGA-P)

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // this.showAlert(dm.widthPixels +" "+ dm.heightPixels, dm.widthPixels
        // +" "+ dm.heightPixels, dm.widthPixels +" "+ dm.heightPixels, false);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        if (height < 400 || width < 300) {
            txtCalc.setTextSize(20);
        }

        if (width < 300) {
            btnMC.setTextSize(18);
            btnMR.setTextSize(18);
            btnMP.setTextSize(18);
            btnMM.setTextSize(18);
            btnBS.setTextSize(18);
            btnDivide.setTextSize(18);
            btnPlus.setTextSize(18);
            btnMinus.setTextSize(18);
            btnMultiply.setTextSize(18);
            btnEquals.setTextSize(18);
            btnPM.setTextSize(18);
            btnPerc.setTextSize(18);
            btnC.setTextSize(18);
            btnSqrRoot.setTextSize(18);
            btnNine.setTextSize(18);
            btnEight.setTextSize(18);
            btnSeven.setTextSize(18);
            btnSix.setTextSize(18);
            btnFive.setTextSize(18);
            btnFour.setTextSize(18);
            btnThree.setTextSize(18);
            btnTwo.setTextSize(18);
            btnOne.setTextSize(18);
            btnZero.setTextSize(18);
            btnDecimal.setTextSize(18);
        }

        btnZero.setTextColor(Color.MAGENTA);
        btnOne.setTextColor(Color.MAGENTA);
        btnTwo.setTextColor(Color.MAGENTA);
        btnThree.setTextColor(Color.MAGENTA);
        btnFour.setTextColor(Color.MAGENTA);
        btnFive.setTextColor(Color.MAGENTA);
        btnSix.setTextColor(Color.MAGENTA);
        btnSeven.setTextColor(Color.MAGENTA);
        btnEight.setTextColor(Color.MAGENTA);
        btnNine.setTextColor(Color.MAGENTA);
        btnPM.setTextColor(Color.MAGENTA);
        btnDecimal.setTextColor(Color.MAGENTA);

        btnMP.setTextColor(Color.BLUE);
        btnMM.setTextColor(Color.BLUE);
        btnMR.setTextColor(Color.BLUE);
        btnMC.setTextColor(Color.BLUE);
        btnBS.setTextColor(Color.BLUE);
        btnC.setTextColor(Color.RED);
        btnPerc.setTextColor(Color.BLACK);
        btnSqrRoot.setTextColor(Color.BLACK);
    }

    private void initControls() {
        bgImageView = (ImageView) findViewById(R.id.bgImageView);
        calculatorLayout = (LinearLayout) findViewById(R.id.calculator_layout);
        calculatorLayout.setVisibility(View.INVISIBLE);
        txtCalc = (TextView) findViewById(R.id.maintxtCalc);
        btnZero = (Button) findViewById(R.id.mainbtnZero);
        btnOne = (Button) findViewById(R.id.mainbtnOne);
        btnTwo = (Button) findViewById(R.id.mainbtnTwo);
        btnThree = (Button) findViewById(R.id.mainbtnThree);
        btnFour = (Button) findViewById(R.id.mainbtnFour);
        btnFive = (Button) findViewById(R.id.mainbtnFive);
        btnSix = (Button) findViewById(R.id.mainbtnSix);
        btnSeven = (Button) findViewById(R.id.mainbtnSeven);
        btnEight = (Button) findViewById(R.id.mainbtnEight);
        btnNine = (Button) findViewById(R.id.mainbtnNine);
        btnPlus = (Button) findViewById(R.id.mainbtnPlus);
        btnMinus = (Button) findViewById(R.id.mainbtnMinus);
        btnMultiply = (Button) findViewById(R.id.mainbtnMultiply);
        btnDivide = (Button) findViewById(R.id.mainbtnDivide);
        btnEquals = (Button) findViewById(R.id.mainbtnEquals);
        btnC = (Button) findViewById(R.id.mainbtnC);
        btnDecimal = (Button) findViewById(R.id.mainbtnDecimal);
        btnMC = (Button) findViewById(R.id.mainbtnMC);
        btnMR = (Button) findViewById(R.id.mainbtnMR);
        btnMM = (Button) findViewById(R.id.mainbtnMM);
        btnMP = (Button) findViewById(R.id.mainbtnMP);
        btnBS = (Button) findViewById(R.id.mainbtnBS);
        btnPerc = (Button) findViewById(R.id.mainbtnPerc);
        btnSqrRoot = (Button) findViewById(R.id.mainbtnSqrRoot);
        btnPM = (Button) findViewById(R.id.mainbtnPM);

        btnZero.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(0);
            }
        });
        btnOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(1);
            }
        });
        btnTwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(2);
            }
        });
        btnThree.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(3);
            }
        });
        btnFour.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(4);
            }
        });
        btnFive.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(5);
            }
        });
        btnSix.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(6);
            }
        });
        btnSeven.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(7);
            }
        });
        btnEight.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(8);
            }
        });
        btnNine.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleNumber(9);
            }
        });
        btnPlus.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(1);
            }
        });
        btnMinus.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(2);
            }
        });
        btnMultiply.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(3);
            }
        });
        btnDivide.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(4);
            }
        });
        btnEquals.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleEquals(0);
            }
        });
        btnC.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                reset();
            }
        });
        btnDecimal.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleDecimal();
            }
        });
        btnPM.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handlePlusMinus();
            }
        });
        btnMC.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                memNum = 0;
            }
        });
        btnMR.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                setValue(Double.toString(memNum));
            }
        });
        btnMM.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                memNum = memNum
                        - Double.parseDouble(txtCalc.getText().toString());
                operator = 0;
            }
        });
        btnMP.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                memNum = memNum
                        + Double.parseDouble(txtCalc.getText().toString());
                operator = 0;
            }
        });
        btnBS.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                handleBackspace();
            }
        });
        btnSqrRoot.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                setValue(Double.toString(Math.sqrt(Double.parseDouble(txtCalc
                        .getText().toString()))));
            }
        });
        btnPerc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                setValue(Double.toString(num
                        * (0.01 * Double.parseDouble(txtCalc.getText()
                        .toString()))));
            }
        });

        txtCalc.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int i, android.view.KeyEvent e) {
                if (e.getAction() == KeyEvent.ACTION_DOWN) {
                    int keyCode = e.getKeyCode();

                    // txtCalc.append("["+Integer.toString(keyCode)+"]");

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_0:
                            handleNumber(0);
                            break;

                        case KeyEvent.KEYCODE_1:
                            handleNumber(1);
                            break;

                        case KeyEvent.KEYCODE_2:
                            handleNumber(2);
                            break;

                        case KeyEvent.KEYCODE_3:
                            handleNumber(3);
                            break;

                        case KeyEvent.KEYCODE_4:
                            handleNumber(4);
                            break;

                        case KeyEvent.KEYCODE_5:
                            handleNumber(5);
                            break;

                        case KeyEvent.KEYCODE_6:
                            handleNumber(6);
                            break;

                        case KeyEvent.KEYCODE_7:
                            handleNumber(7);
                            break;

                        case KeyEvent.KEYCODE_8:
                            handleNumber(8);
                            break;

                        case KeyEvent.KEYCODE_9:
                            handleNumber(9);
                            break;

                        case 43:
                            handleEquals(1);
                            break;

                        case KeyEvent.KEYCODE_EQUALS:
                            handleEquals(0);
                            break;

                        case KeyEvent.KEYCODE_MINUS:
                            handleEquals(2);
                            break;

                        case KeyEvent.KEYCODE_PERIOD:
                            handleDecimal();
                            break;

                        case KeyEvent.KEYCODE_C:
                            reset();
                            break;

                        case KeyEvent.KEYCODE_SLASH:
                            handleEquals(4);
                            break;

                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            return false;
                    }
                }

                return true;
            }
        });
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
        if (id == R.id.action_animate) {

            triggerReveal();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private void handleEquals(int newOperator) {
        if (hasChanged) {
            switch (operator) {
                case 1:
                    num = num + Double.parseDouble(txtCalc.getText().toString());
                    break;
                case 2:
                    num = num - Double.parseDouble(txtCalc.getText().toString());
                    break;
                case 3:
                    num = num * Double.parseDouble(txtCalc.getText().toString());
                    break;
                case 4:
                    num = num / Double.parseDouble(txtCalc.getText().toString());
                    break;
            }

            String txt = Double.toString(num);
            txtCalc.setText(txt);
            //txtCalc.setSelection(txt.length());

            readyToClear = true;
            hasChanged = false;
        }

        operator = newOperator;
    }

    private void handleNumber(int num) {
        if (operator == 0)
            reset();

        String txt = txtCalc.getText().toString();
        if (readyToClear) {
            txt = "";
            readyToClear = false;
        } else if (txt.equals("0"))
            txt = "";

        txt = txt + Integer.toString(num);

        txtCalc.setText(txt);
        //txtCalc.setSelection(txt.length());

        hasChanged = true;
    }

    private void setValue(String value) {
        if (operator == 0)
            reset();

        if (readyToClear) {
            readyToClear = false;
        }

        txtCalc.setText(value);
        //txtCalc.setSelection(value.length());

        hasChanged = true;
    }

    private void handleDecimal() {
        if (operator == 0)
            reset();

        if (readyToClear) {
            txtCalc.setText("0.");
            //txtCalc.setSelection(2);
            readyToClear = false;
            hasChanged = true;
        } else {
            String txt = txtCalc.getText().toString();

            if (!txt.contains(".")) {
                txtCalc.append(".");
                hasChanged = true;
            }
        }
    }

    private void handleBackspace() {
        if (!readyToClear) {
            String txt = txtCalc.getText().toString();
            if (txt.length() > 0) {
                txt = txt.substring(0, txt.length() - 1);
                if (txt.equals(""))
                    txt = "0";

                txtCalc.setText(txt);
                //txtCalc.setSelection(txt.length());
            }
        }
    }

    private void handlePlusMinus() {
        if (!readyToClear) {
            String txt = txtCalc.getText().toString();
            if (!txt.equals("0")) {
                if (txt.charAt(0) == '-')
                    txt = txt.substring(1, txt.length());
                else
                    txt = "-" + txt;

                txtCalc.setText(txt);
                //txtCalc.setSelection(txt.length());
            }
        }
    }

    private void reset() {
        num = 0;
        txtCalc.setText("0");
        //txtCalc.setSelection(1);
        operator = 1;
    }
    private void triggerReveal() {
        int cx = calculatorLayout.getMeasuredWidth();
        int cy = calculatorLayout.getMeasuredHeight();
        cx -= ((28 * pixelDensity) + (16 * pixelDensity));
        int radius = Math.max(calculatorLayout.getWidth(),calculatorLayout.getHeight());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {


            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(calculatorLayout,cx, cy, 0 ,radius);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(800);

            SupportAnimator animator_reverse = animator.reverse();

            if (hidden) {
                calculatorLayout.setVisibility(View.VISIBLE);
                animator.start();
                hidden = false;
            } else {
                animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        calculatorLayout.setVisibility(View.INVISIBLE);
                        hidden = true;

                    }

                    @Override
                    public void onAnimationCancel() {

                    }

                    @Override
                    public void onAnimationRepeat() {

                    }
                });
                animator_reverse.start();

            }
        } else {
            if (hidden) {
                Animator anim = android.view.ViewAnimationUtils.createCircularReveal(calculatorLayout, cx, cy, 0, radius);
                calculatorLayout.setVisibility(View.VISIBLE);
                anim.start();
                hidden = false;

            } else {
                Animator anim = android.view.ViewAnimationUtils.createCircularReveal(calculatorLayout, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        calculatorLayout.setVisibility(View.INVISIBLE);
                        hidden = true;
                    }
                });
                anim.start();

            }
        }
    }
}
