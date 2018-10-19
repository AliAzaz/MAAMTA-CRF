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
import edu.aku.hassannaqvi.maamta_crf.contracts.EnrolledContract;
import edu.aku.hassannaqvi.maamta_crf.contracts.FormsContract;
import edu.aku.hassannaqvi.maamta_crf.core.AppMain;
import edu.aku.hassannaqvi.maamta_crf.core.DatabaseHelper;
import edu.aku.hassannaqvi.maamta_crf.databinding.ActivityInfoBinding;

public class InfoActivity extends AppCompatActivity {

    ActivityInfoBinding bi;

    private static final String TAG = InfoActivity.class.getSimpleName();
    DatabaseHelper db;
    EnrolledContract enrolledParticipant;
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

        fTYPE = getIntent().getStringExtra("fType");
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
            } else {
                if (result.getContents().contains("WB")) {
                    Toast.makeText(this, "WB Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    bi.mlwScan.setText("§" + result.getContents().trim());
                    bi.mlwScan.setEnabled(false);
                    bi.mlwScan.setError(null);
                } else {
                    bi.mlwScan.setError("Please Scan correct QR code");
                }
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
        AppMain.fc.setInterviewer01(AppMain.loginMem[1]);
        AppMain.fc.setInterviewer02(AppMain.loginMem[2]);

        AppMain.fc.setUccode(enrolledParticipant.getUc_code());
        AppMain.fc.setTehsilcode(enrolledParticipant.getTehsil_code());
        AppMain.fc.setVillagecode(enrolledParticipant.getVillage_code());
        AppMain.fc.setLhwCode(enrolledParticipant.getLhw_code());

        AppMain.fc.setDeviceID(AppMain.deviceId);
//        AppMain.fc.setStudyID(bi.studyID.getText().toString());
        AppMain.fc.setFormType(AppMain.formType);

        AppMain.fc.setApp_version(AppMain.versionName + "." + AppMain.versionCode);

        JSONObject sInfo = new JSONObject();
        sInfo.put("puid", enrolledParticipant.getPuid());
        sInfo.put("pw_name", enrolledParticipant.getPw_name());
        sInfo.put("h_name", enrolledParticipant.getH_name());
        sInfo.put("lmp", enrolledParticipant.getLmp());
        sInfo.put("edd", enrolledParticipant.getEdd());
        sInfo.put("fupdt", enrolledParticipant.getFupdt());
        sInfo.put("fupround", enrolledParticipant.getFupround());
        sInfo.put("resp_type", enrolledParticipant.getResp_type());

        /*sInfo.put(AppMain.formType + "a04", bi.pfa04a.isChecked() ? "1" : bi.pfa04b.isChecked() ? "2" : "0");
        sInfo.put(AppMain.formType + "a06", bi.pfa06a.isChecked() ? "1" : bi.pfa06b.isChecked() ? "2" : "0");*/

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

        /*if (!validatorClass.EmptyRadioButton(this, bi.pfa04, bi.pfa04b, getString(R.string.pfa04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.pfa06, bi.pfa06b, getString(R.string.pfa03));*/

        return true;
    }

}
