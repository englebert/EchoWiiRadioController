package com.echowii.radiocontroller;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FullscreenActivity extends AppCompatActivity {
//    /**
//     * Whether or not the system UI should be auto-hidden after
//     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
//     */
    private static final boolean AUTO_HIDE = false;
//
//    /**
//     * If {@link #AUTO_HIDE} is set, the number of millisfullscreen_content_controlseconds to wait after
//     * user interaction before hiding the system UI.
//     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
/*

/**
* Some older devices needs a small delay between UI widget updates
* and a change of the status and navigation bar.
* /
*/
    private static final int UI_ANIMATION_DELAY = 300;

    /**
     * Vibration duration
     */
    private static final int VIBRATION_DELAY = 100;

    private View mContentView;
    private Button buttonChannelA;
    private Button buttonChannelB;
    private Button buttonChannelC;
    private Button buttonChannelD;
    private Button buttonChannelE;
    private Button buttonChannelF;
    private Vibrator mVibrator;

    RelativeLayout layout_joystick_left, layout_joystick_right;
    JoyStickClass js, js_right;
    TextView textview_x, textview_y, textview_angle, textview_distance, textview_console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        // mVisible = true;
        // mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.textview_brandname);


        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        // Initialize a new instance of system Vibrator
        mVibrator = (Vibrator)getSystemService(this.VIBRATOR_SERVICE);

        buttonChannelA = findViewById(R.id.button_channel_a);
        buttonChannelA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVibrator.vibrate(VIBRATION_DELAY);

//                ShapeDrawable sd = new ShapeDrawable(new OvalShape());
//                sd.setIntrinsicHeight(10);
//                sd.setIntrinsicWidth(10);
//                sd.setPadding(0,0,10, 10);
//                sd.getPaint().setColor(Color.parseColor("#FFFFFF"));
//                Paint paint = new Paint();
//                paint.setColor(Color.WHITE);
//
//                Bitmap.Config conf = Bitmap.Config.ARGB_4444;
//                Bitmap bitmap = Bitmap.createBitmap(600, 600, conf);
//                Canvas canvas = new Canvas(bitmap);
//
//                canvas.drawCircle(250, 250, 200, paint);
//
//                paint = new Paint();
//                paint.setColor(Color.RED);
//                canvas.drawCircle(250, 250, 180, paint);
//
//                ImageView iv = (ImageView) findViewById(R.id.joystick_left);
//                iv.setImageBitmap(bitmap);
            }
        });

        buttonChannelB = findViewById(R.id.button_channel_b);
        buttonChannelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVibrator.vibrate(VIBRATION_DELAY);
            }
        });

        buttonChannelC = findViewById(R.id.button_channel_c);
        buttonChannelC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVibrator.vibrate(VIBRATION_DELAY);
            }
        });

        buttonChannelD = findViewById(R.id.button_channel_d);
        buttonChannelD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVibrator.vibrate(VIBRATION_DELAY);
            }
        });

        buttonChannelE = findViewById(R.id.button_channel_e);
        buttonChannelE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVibrator.vibrate(VIBRATION_DELAY);
            }
        });

        buttonChannelF = findViewById(R.id.button_channel_f);
        buttonChannelF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVibrator.vibrate(VIBRATION_DELAY);
            }
        });

        textview_x = (TextView)findViewById(R.id.textview_x);
        textview_y = (TextView)findViewById(R.id.textview_y);
        textview_angle = (TextView)findViewById(R.id.textview_angle);
        textview_distance = (TextView)findViewById(R.id.textview_distance);
        textview_console = (TextView)findViewById(R.id.textview_console);

        layout_joystick_left = (RelativeLayout) findViewById(R.id.layout_joystick_left);
        layout_joystick_right = (RelativeLayout) findViewById(R.id.layout_joystick_right);

        js = new JoyStickClass(this.getApplicationContext(), layout_joystick_left, R.drawable.image_button);
        js.setStickSize(150, 150);
        js.setLayoutAlpha(150);
        js.setLayoutSize(500,500);
        js.setOffset(90);
        js.setMinimumDistance(50);

        js_right = new JoyStickClass(this.getApplicationContext(), layout_joystick_right, R.drawable.image_button);
        js_right.setStickSize(150, 150);
        js_right.setLayoutAlpha(150);
        js_right.setLayoutSize(500,500);
        js_right.setOffset(90);
        js_right.setMinimumDistance(50);

        layout_joystick_left.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    textview_x.setText("X : " + String.valueOf(js.getX()));
                    textview_y.setText("Y : " + String.valueOf(js.getY()));
                    textview_angle.setText("Angle : " + String.valueOf(js.getAngle()));
                    textview_distance.setText("Distance : " + String.valueOf(js.getDistance()));

                    int direction = js.get8Direction();
                    if(direction == JoyStickClass.STICK_UP) {
                        textview_console.setText("Direction : Up");
                    } else if(direction == JoyStickClass.STICK_UPRIGHT) {
                        textview_console.setText("Direction : Up Right");
                    } else if(direction == JoyStickClass.STICK_RIGHT) {
                        textview_console.setText("Direction : Right");
                    } else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
                        textview_console.setText("Direction : Down Right");
                    } else if(direction == JoyStickClass.STICK_DOWN) {
                        textview_console.setText("Direction : Down");
                    } else if(direction == JoyStickClass.STICK_DOWNLEFT) {
                        textview_console.setText("Direction : Down Left");
                    } else if(direction == JoyStickClass.STICK_LEFT) {
                        textview_console.setText("Direction : Left");
                    } else if(direction == JoyStickClass.STICK_UPLEFT) {
                        textview_console.setText("Direction : Up Left");
                    } else if(direction == JoyStickClass.STICK_NONE) {
                        textview_console.setText("Direction : Center");
                    }
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                    textview_x.setText("X :");
                    textview_y.setText("Y :");
                    textview_angle.setText("Angle :");
                    textview_distance.setText("Distance :");
                    textview_console.setText("Direction :");
                }
                return true;
            }
        });

        layout_joystick_right.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js_right.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
//                    textview_x.setText("X : " + String.valueOf(js.getX()));
//                    textview_y.setText("Y : " + String.valueOf(js.getY()));
//                    textview_angle.setText("Angle : " + String.valueOf(js.getAngle()));
//                    textview_distance.setText("Distance : " + String.valueOf(js.getDistance()));

                    int direction = js.get8Direction();
                    if(direction == JoyStickClass.STICK_UP) {
//                        textview_console.setText("Direction : Up");
                    } else if(direction == JoyStickClass.STICK_UPRIGHT) {
//                        textview_console.setText("Direction : Up Right");
                    } else if(direction == JoyStickClass.STICK_RIGHT) {
//                        textview_console.setText("Direction : Right");
                    } else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
//                        textview_console.setText("Direction : Down Right");
                    } else if(direction == JoyStickClass.STICK_DOWN) {
//                        textview_console.setText("Direction : Down");
                    } else if(direction == JoyStickClass.STICK_DOWNLEFT) {
//                        textview_console.setText("Direction : Down Left");
                    } else if(direction == JoyStickClass.STICK_LEFT) {
//                        textview_console.setText("Direction : Left");
                    } else if(direction == JoyStickClass.STICK_UPLEFT) {
//                        textview_console.setText("Direction : Up Left");
                    } else if(direction == JoyStickClass.STICK_NONE) {
//                        textview_console.setText("Direction : Center");
                    }
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
//                    textview_x.setText("X :");
//                    textview_y.setText("Y :");
//                    textview_angle.setText("Angle :");
//                    textview_distance.setText("Distance :");
//                    textview_console.setText("Direction :");
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (AUTO_HIDE) {
//                delayedHide(AUTO_HIDE_DELAY_MILLIS);
//            }
            return false;
        }
    };

//    private void toggle() {
//        if (mVisible) {
//            hide();
//        } else {
//            show();
//        }
//    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        // mControlsView.setVisibility(View.GONE);
        // mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        // mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            // mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
