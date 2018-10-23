package edu.aku.hassannaqvi.maamta_crf.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.maamta_crf.R;
import edu.aku.hassannaqvi.maamta_crf.contracts.FormsContract;
import edu.aku.hassannaqvi.maamta_crf.core.AppMain;
import edu.aku.hassannaqvi.maamta_crf.core.DatabaseHelper;
import edu.aku.hassannaqvi.maamta_crf.databinding.ActivityInfoBinding;
import edu.aku.hassannaqvi.maamta_crf.validation.validatorClass;

public class InfoActivity extends AppCompatActivity {

    ActivityInfoBinding bi;

    private static final String TAG = InfoActivity.class.getSimpleName();
    DatabaseHelper db;
    String fTYPE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_info);
        bi.setCallback(this);

        InitializingItems();
        SetContent();

    }

    public void InitializingItems() {

        db = new DatabaseHelper(this);

//        fTYPE = getIntent().getStringExtra("fType");
        fTYPE = "crf1";
        this.setTitle(LabelsAssign(fTYPE));

        bi.mlw09a.setManager(getSupportFragmentManager());
        bi.mlw09a.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
        bi.mlw09b.setManager(getSupportFragmentManager());
        bi.mlw13.setManager(getSupportFragmentManager());
        bi.mlw13.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));

    }

    public void SetContent() {
        bi.mlwCrf3A.setVisibility(fTYPE.equals("crf3") ? View.VISIBLE : View.GONE);
        bi.mlwCrf3B.setVisibility(fTYPE.equals("crf3") ? View.VISIBLE : View.GONE);
    }

    private String LabelsAssign(String fTYPE) {
        switch (fTYPE) {
            case "crf1":
                return getString(R.string.crf1);
            case "crf2":
                return getString(R.string.crf2);
            case "crf3":
                return getString(R.string.crf3);
            case "crf4":
                return getString(R.string.crf4);
            case "crf5":
                return getString(R.string.crf5);
            case "crf6":
                return getString(R.string.crf6);
        }
        return "";
    }

    public void BtnEnd() {
        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

                AppMain.endActivity(this, this);

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnScanSpec() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan the QR code of Machine");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(false);

        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                bi.mlwScan.setEnabled(true);
                bi.mlwScan.setError(null);
            } else {
//                if (result.getContents().contains("WB")) {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                bi.mlwScan.setText("§" + result.getContents().trim());
                bi.mlwScan.setEnabled(false);
                bi.mlwScan.setError(null);
                /*} else {
                    bi.mlwScan.setError("Please Scan correct QR code");
                }*/
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void BtnContinue() {
        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {

                finish();
                startActivity(new Intent(this, MainActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean UpdateDB() {
        Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);

        rowId = db.addForm(AppMain.fc);

        AppMain.fc.setID(rowId);

        if (rowId != null) {
            AppMain.fc.setUID(
                    (AppMain.fc.getDeviceID() + AppMain.fc.getID()));
            Toast.makeText(this, "Current Form No: " + AppMain.fc.getUID(), Toast.LENGTH_SHORT).show();

            // Update UID of Last Inserted Form
            db.updateFormsUID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        AppMain.fc = new FormsContract();

        AppMain.fc.setTagID(sharedPref.getString("tagName", null));
        AppMain.fc.setFormDate((DateFormat.format("dd-MM-yyyy HH:mm", new Date())).toString());
        AppMain.fc.setInterviewer01(AppMain.userName);
        AppMain.fc.setDeviceID(AppMain.deviceId);
        AppMain.fc.setStudyID(bi.mlw01.getText().toString());
        AppMain.fc.setSpecimenID(bi.mlwScan.getText().toString());
        AppMain.fc.setFormType(fTYPE);
        AppMain.fc.setApp_version(AppMain.versionName + "." + AppMain.versionCode);

        JSONObject sInfo = new JSONObject();
        sInfo.put(fTYPE + "a02", bi.mlw02.getText().toString());
        sInfo.put(fTYPE + "a03", bi.mlw03.getText().toString());
        sInfo.put(fTYPE + "a04", bi.mlw04.getText().toString());
        sInfo.put(fTYPE + "a05", bi.mlw05a.isChecked() ? "1" : bi.mlw05b.isChecked() ? "2"
                : bi.mlw05c.isChecked() ? "3" : bi.mlw05d.isChecked() ? "4" : "0");
        sInfo.put(fTYPE + "a06", bi.mlw06a.isChecked() ? "1" : bi.mlw06b.isChecked() ? "2"
                : bi.mlw06c.isChecked() ? "3" : "0");
        sInfo.put(fTYPE + "a07", bi.mlw07a.isChecked() ? "1" : bi.mlw07b.isChecked() ? "2" : "0");
        sInfo.put(fTYPE + "a08", bi.mlw08.getText().toString());
        sInfo.put(fTYPE + "a09a", bi.mlw09a.getText().toString());
        sInfo.put(fTYPE + "a09b", bi.mlw09b.getText().toString());

        sInfo.put(fTYPE + "a10", bi.mlw10.getText().toString());

        if (fTYPE.equals("crf3")) {
            sInfo.put(fTYPE + "a11a", bi.mlw11a.getText().toString());
            sInfo.put(fTYPE + "a11b", bi.mlw11b.getText().toString());
            sInfo.put(fTYPE + "a12", bi.mlw12a.isChecked() ? "1" : bi.mlw12b.isChecked() ? "2" : "0");
            sInfo.put(fTYPE + "a13", bi.mlw13.getText().toString());
        }

        AppMain.fc.setsInfo(String.valueOf(sInfo));

        setGPS();

    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat.equals("0") && lang.equals("0")) {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(dt)).toString();

            AppMain.fc.setGpsLat(lat);
            AppMain.fc.setGpsLng(lang);
            AppMain.fc.setGpsAcc(acc);
            AppMain.fc.setGpsTime(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

    public boolean ValidateForm() {

        if (!validatorClass.EmptyTextBox(this, bi.mlw01, getString(R.string.mlw01), 10, 10)) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.mlw02, getString(R.string.mlw02))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.mlw03, getString(R.string.mlw03))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.mlw04, getString(R.string.mlw04))) {
            return false;
        }
        if (!validatorClass.PatternTextBox(this, bi.mlw04, getString(R.string.mlw04), "[^0-9]{4,4}[0-9]{5,5}-[^0-9]{1,1}[0-9]{1,2}", 0, 12, "XXXXXXXXX-XX")) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bi.mlw05, bi.mlw05a, getString(R.string.mlw05))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.mlwScan, "Specimen ID")) {
            Toast.makeText(this, "Please scan specimen ID!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (bi.mlwScan.isEnabled()) {

            if (bi.mlwScan.getText().toString().length() > 6) {

                if (!validatorClass.PatternTextBox(this, bi.mlwScan, "Specimen ID", "[^0-9]{1,1}[0-9]{5,5}-", 0, 7, "XXXXX-XX+")) {
                    return false;
                }

            } else {
                bi.mlwScan.setError("Incompatible length Specimen ID");
                Toast.makeText(this, "Incompatible length Specimen ID", Toast.LENGTH_SHORT).show();
                return false;
            }

        }

        if (!validatorClass.EmptyRadioButton(this, bi.mlw06, bi.mlw06a, getString(R.string.mlw06))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.mlw07, bi.mlw07a, getString(R.string.mlw07))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.mlw08, getString(R.string.mlw08))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.mlw09a, getString(R.string.mlw09a))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.mlw09b, getString(R.string.mlw09b))) {
            return false;
        }

        if (fTYPE.equals("crf3")) {
            if (!validatorClass.EmptyTextBox(this, bi.mlw11a, getString(R.string.mlw11a))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.mlw11b, getString(R.string.mlw11b))) {
                return false;
            }
        }

        if (!validatorClass.EmptyTextBox(this, bi.mlw10, getString(R.string.mlw10))) {
            return false;
        }

        if (fTYPE.equals("crf3")) {
            if (!validatorClass.EmptyRadioButton(this, bi.mlw12, bi.mlw12a, getString(R.string.mlw12))) {
                return false;
            }
            return validatorClass.EmptyTextBox(this, bi.mlw13, getString(R.string.mlw13));
        }

        return true;

    }

}
