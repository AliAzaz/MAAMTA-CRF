package edu.aku.hassannaqvi.maamta_crf.validation;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by ali.azaz on 03/19/18.
 */

public class ClearClass {

    public static void ClearRadioButton(LinearLayout container, RadioGroup rdGrp) {
        if (rdGrp.getCheckedRadioButtonId() == -1) {

            rdGrp.clearCheck();

            for (int i = 0; i < container.getChildCount(); i++) {
                View v = container.getChildAt(i);
                if (v instanceof RadioButton) {
                    v.setEnabled(false);
                }
            }
        }
    }

    public static void ClearRadioButton(LinearLayout container, RadioGroup rdGrp, EditText othertxt) {
        if (rdGrp.getCheckedRadioButtonId() == -1) {

            rdGrp.clearCheck();
            othertxt.setText(null);

            for (int i = 0; i < container.getChildCount(); i++) {
                View v = container.getChildAt(i);
                if (v instanceof RadioButton) {
                    v.setEnabled(false);
                }
            }
        }
    }

    public static void ClearCheckBoxes(LinearLayout container) {
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
                v.setEnabled(false);
            }
        }
    }

    public static void ClearCheckBoxes(LinearLayout container, EditText othertxt) {

        othertxt.setText(null);

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
                v.setEnabled(false);
            }
        }
    }

    public static void ClearAllFields(LinearLayout container, Boolean flag) {
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
                ((CheckBox) v).setError(null);
                v.setEnabled(flag);
            } else if (v instanceof RadioGroup) {
                if (!flag) {
                    ((RadioGroup) v).clearCheck();
                }
                for (int j = 0; j < ((RadioGroup) v).getChildCount(); j++) {
                    ((RadioGroup) v).getChildAt(j).setEnabled(flag);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setText(null);
                ((EditText) v).setError(null);
                v.setEnabled(flag);
                v.clearFocus();

            } else if (v instanceof io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) {
                ((io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) v).setText(null);
                v.setEnabled(flag);
            } else if (v instanceof CardView) {
                ClearAllCardFields((CardView) v, true);
            } else if (v instanceof LinearLayout) {
                ClearAllFields((LinearLayout) v, flag);
            }
        }
    }

    public static void ClearAllCardFields(CardView container, Boolean flag) {
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
                ((CheckBox) v).setError(null);
                v.setEnabled(flag);
            } else if (v instanceof RadioGroup) {
                if (!flag) {
                    ((RadioGroup) v).clearCheck();
                }
                for (int j = 0; j < ((RadioGroup) v).getChildCount(); j++) {
                    ((RadioGroup) v).getChildAt(j).setEnabled(flag);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setText(null);
                ((EditText) v).setError(null);
                v.setEnabled(flag);
                v.clearFocus();

            } else if (v instanceof io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) {
                ((io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) v).setText(null);
                v.setEnabled(flag);
            } else if (v instanceof LinearLayout) {
                for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
                    View v1 = ((LinearLayout) v).getChildAt(k);
                    if (v1 instanceof CheckBox) {
                        ((CheckBox) v1).setChecked(false);
                        ((CheckBox) v1).setError(null);
                        v1.setEnabled(flag);
                    } else if (v1 instanceof RadioGroup) {
                        if (!flag) {
                            ((RadioGroup) v1).clearCheck();
                        }
                        for (int j = 0; j < ((RadioGroup) v1).getChildCount(); j++) {
                            ((RadioGroup) v1).getChildAt(j).setEnabled(flag);
                        }
                    } else if (v1 instanceof EditText) {
                        ((EditText) v1).setText(null);
                        ((EditText) v1).setError(null);
                        v1.clearFocus();
                        v1.setEnabled(flag);
                    } else if (v1 instanceof io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) {
                        ((io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) v1).setText(null);
                        ((io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText) v1).setError(null);
                        v1.setEnabled(flag);
                    }
                }
            }
        }
    }


}
