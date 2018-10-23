package edu.aku.hassannaqvi.maamta_crf.validation;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ali.azaz on 12/04/17.
 */

public abstract class validatorClass {

    public static boolean EmptyTextBox(Context context, EditText txt, String msg) {
        if (TextUtils.isEmpty(txt.getText().toString())) {
            Toast.makeText(context, "ERROR(empty): " + msg, Toast.LENGTH_SHORT).show();
            txt.setError("This data is Required! ");    // Set Error on last radio button
            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": This data is Required!");
            return false;
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean EmptyTextBox(Context context, EditText txt, String msg, int minLimit, int maxLimit) {
        if (EmptyTextBox(context, txt, msg)) {

            if (minLimit <= maxLimit) {
                if (txt.getText().toString().length() < minLimit || txt.getText().toString().length() > maxLimit) {
                    Toast.makeText(context, "ERROR(Invalid): " + msg, Toast.LENGTH_SHORT).show();
                    txt.setError("Length " + (minLimit == maxLimit ? "must be " + maxLimit : ("in between " + minLimit + "," + maxLimit)));    // Set Error on last radio button
                    txt.requestFocus();
                    Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Invalid Length!");
                    return false;
                } else {
                    txt.setError(null);
                    return true;
                }
            } else {
                txt.setError("Min Limit greater then or equal to max Limit");
                return false;
            }

        } else {
            return false;
        }
    }

    public static void setScrollViewFocus(ScrollView scrollView) {
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

    }

    public static boolean PatternTextBox(Context context, EditText txt, String msg, String pattern, String pat_type) {
        if (!txt.getText().toString().matches(pattern)) {
            Toast.makeText(context, "ERROR(Invalid): " + msg, Toast.LENGTH_SHORT).show();
            txt.setError("Required: " + pat_type);    // Set Error on last radio button
            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Required: " + pat_type);
            return false;
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean PatternTextBox(Context context, EditText txt, String msg, String pattern, int minSub, int maxSub, String pat_type) {
        if (minSub <= maxSub) {
            if (!txt.getText().toString().substring(minSub, maxSub).matches(pattern)) {
                Toast.makeText(context, "ERROR(Invalid): " + msg, Toast.LENGTH_SHORT).show();
                txt.setError("Required: " + pat_type);    // Set Error on last radio button
                txt.requestFocus();
                Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Required: " + pat_type);
                return false;
            } else {
                txt.setError(null);
                return true;
            }
        } else {
            txt.setError("Min Limit greater then or equal to max Limit");
            return false;
        }
    }

    public static boolean RangeTextBox(Context context, EditText txt, int min, int max, String msg, String type) {

        if (Integer.valueOf(txt.getText().toString()) < min || Integer.valueOf(txt.getText().toString()) > max) {
            Toast.makeText(context, "ERROR(invalid): " + msg, Toast.LENGTH_SHORT).show();
            txt.setError("Range is " + min + " to " + max + type + " ... ");    // Set Error on last radio button
            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Range is " + min + " to " + max + " times...  ");
            return false;
        } else {
            txt.setError(null);
            return true;
        }
    }


    public static boolean RangeTextBox(Context context, EditText txt, double min, double max, String msg, String type) {

        if (Double.valueOf(txt.getText().toString()) < min || Double.valueOf(txt.getText().toString()) > max) {
            Toast.makeText(context, "ERROR(invalid): " + msg, Toast.LENGTH_SHORT).show();
            txt.setError("Range is " + min + " to " + max + type + " ... ");    // Set Error on last radio button
            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Range is " + min + " to " + max + " times...  ");
            return false;
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean EmptySpinner(Context context, Spinner spin, String msg) {
        if (spin.getSelectedItem() == "....") {
            Toast.makeText(context, "ERROR(Empty)" + msg, Toast.LENGTH_SHORT).show();
            ((TextView) spin.getSelectedView()).setText("This Data is Required");
            ((TextView) spin.getSelectedView()).setTextColor(Color.RED);
            spin.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(spin.getId()) + ": This data is Required!");
            return false;
        } else {
            ((TextView) spin.getSelectedView()).setError(null);
            return true;
        }
    }


    public static boolean DuplicateItemSelected(Context context, Spinner spin, Spinner spin2, String msg) {
        if (spin.getSelectedItem().toString().contains(spin2.getSelectedItem().toString())) {
            Toast.makeText(context, "ERROR(invalid) Users same" + msg, Toast.LENGTH_SHORT).show();
            ((TextView) spin.getSelectedView()).setText("Users Same");
            ((TextView) spin2.getSelectedView()).setText("Users Same");
            ((TextView) spin.getSelectedView()).setTextColor(Color.RED);
            ((TextView) spin2.getSelectedView()).setTextColor(Color.RED);
            spin.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(spin.getId()) + ": This data is Required!");
            return false;
        } else {
            ((TextView) spin.getSelectedView()).setError(null);
            return true;
        }
    }

    public static boolean EmptyRadioButton(Context context, RadioGroup rdGrp, RadioButton rdBtn, String msg) {
        if (rdGrp.getCheckedRadioButtonId() == -1) {
            Toast.makeText(context, "ERROR(empty): " + msg, Toast.LENGTH_SHORT).show();
            rdBtn.setError("This data is Required!");    // Set Error on last radio button

            rdBtn.setFocusable(true);
            rdBtn.setFocusableInTouchMode(true);
            rdBtn.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(rdGrp.getId()) + ": This data is Required!");
            return false;
        } else {
            rdBtn.setError(null);
            return true;
        }
    }

    public static boolean EmptyRadioButton(Context context, RadioGroup rdGrp, RadioButton rdBtn, EditText txt, String msg) {
        if (rdGrp.getCheckedRadioButtonId() == -1) {
            Toast.makeText(context, "ERROR(empty): " + msg, Toast.LENGTH_SHORT).show();
            rdBtn.setError("This data is Required!");    // Set Error on last radio button

            rdBtn.setFocusable(true);
            rdBtn.setFocusableInTouchMode(true);
            rdBtn.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(rdGrp.getId()) + ": This data is Required!");
            return false;
        } else {
            rdBtn.setError(null);
            if (rdBtn.isChecked()) {
                return EmptyTextBox(context, txt, msg);
            } else {
                return true;
            }
        }
    }

    public static boolean EmptyCheckBox(Context context, LinearLayout container, CheckBox cbx, String msg) {

        Boolean flag = false;
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                if (cb.isChecked()) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            return true;
        } else {
            Toast.makeText(context, "ERROR(empty): " + msg, Toast.LENGTH_LONG).show();
            cbx.setError("This data is Required!");    // Set Error on last radio button

            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(cbx.getId()) + ": This data is Required!");
            return false;
        }
    }

    public static boolean EmptyCardCheckBox(Context context, CardView container, CheckBox cbx, String msg) {

        Boolean flag = false;
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof LinearLayout) {
                for (int j = 0; j < ((LinearLayout) v).getChildCount(); j++) {
                    View view = ((LinearLayout) v).getChildAt(j);
                    if (view instanceof CheckBox) {
                        CheckBox cb = (CheckBox) view;
                        if (cb.isChecked()) {
                            flag = true;
                            break;
                        }
                    }

                }
            }
        }
        if (flag) {
            return true;
        } else {
            Toast.makeText(context, "ERROR(empty): " + msg, Toast.LENGTH_LONG).show();
            cbx.setError("This data is Required!");    // Set Error on last radio button

            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(cbx.getId()) + ": This data is Required!");
            return false;
        }
    }

    public static boolean EmptyCheckBox(Context context, LinearLayout container, CheckBox cbx, EditText txt, String msg) {

        Boolean flag = false;
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                if (cb.isChecked()) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            cbx.setError(null);
            if (cbx.isChecked()) {
                return EmptyTextBox(context, txt, msg);
            }
            return true;
        } else {
            Toast.makeText(context, "ERROR(empty): " + msg, Toast.LENGTH_LONG).show();
            cbx.setError("This data is Required!");    // Set Error on last radio button

            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(cbx.getId()) + ": This data is Required!");
            return false;
        }
    }

}
