package edu.aku.hassannaqvi.maamta_crf.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.maamta_crf.R;
import edu.aku.hassannaqvi.maamta_crf.core.AppMain;
import edu.aku.hassannaqvi.maamta_crf.core.DatabaseHelper;
import edu.aku.hassannaqvi.maamta_crf.validation.validatorClass;

public class EndingActivity extends Activity {

    @BindView(R.id.activity_ending)
    RelativeLayout activityEnding;
    @BindView(R.id.istatus)
    RadioGroup istatus;
    @BindView(R.id.istatusa)
    RadioButton istatusa;
    @BindView(R.id.istatusb)
    RadioButton istatusb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        ButterKnife.bind(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            istatusa.setEnabled(true);
            istatusb.setEnabled(false);
        } else {
            istatusa.setEnabled(false);
            istatusb.setEnabled(true);
        }
    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        if (ValidateForm()) {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                Intent endSec = new Intent(this, MainActivity.class);
                startActivity(endSec);
            }
        }
    }

    public boolean ValidateForm() {

        return validatorClass.EmptyRadioButton(this, istatus, istatusa, getString(R.string.istatus));
    }

    private void SaveDraft() {

        AppMain.fc.setIstatus(istatusa.isChecked() ? "1" : istatusb.isChecked() ? "2" : "0");
        AppMain.fc.setEndingDateTime((DateFormat.format("dd-MM-yyyy HH:mm", new Date())).toString());

    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnding();

        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

}
