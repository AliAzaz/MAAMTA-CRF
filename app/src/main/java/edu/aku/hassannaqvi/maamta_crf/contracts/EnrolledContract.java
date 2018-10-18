package edu.aku.hassannaqvi.maamta_crf.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 12/2/2016.
 */
public class EnrolledContract {
    private static final String TAG = "ELEGIBLES_CONTRACT";

    private Long _ID;

    // for child level Randomisation

    private String ID;
    private String puid;
    private String uc_code;
    private String tehsil_code;
    private String village_code;
    private String lhw_code;
    private String studyid;
    private String pw_name;
    private String h_name;
    private String lmp;
    private String edd;
    private String fupdt;
    private String fupround;
    private String resp_type;


    public EnrolledContract() {
    }

    public EnrolledContract(EnrolledContract ec) {
        this.ID = ec.ID;
        this.puid = ec.puid;
        this.uc_code = ec.uc_code;
        this.tehsil_code = ec.tehsil_code;
        this.village_code = ec.village_code;
        this.lhw_code = ec.lhw_code;
        this.studyid = ec.studyid;
        this.pw_name = ec.pw_name;
        this.h_name = ec.h_name;
        this.lmp = ec.lmp;
        this.edd = ec.edd;
        this.fupdt = ec.fupdt;
        this.fupround = ec.fupround;
        this.resp_type = ec.resp_type;
    }

    public EnrolledContract Sync(JSONObject jsonObject) throws JSONException {
        this.puid = jsonObject.getString(EnrolledTable.COLUMN_PUID);
        this.uc_code = jsonObject.getString(EnrolledTable.COLUMN_UC_CODE);
        this.tehsil_code = jsonObject.getString(EnrolledTable.COLUMN_TEHSIL_CODE);
        this.village_code = jsonObject.getString(EnrolledTable.COLUMN_VILLAGE_CODE);
        this.lhw_code = jsonObject.getString(EnrolledTable.COLUMN_LHW_CODE);
        this.studyid = jsonObject.getString(EnrolledTable.COLUMN_STUDYID);
        this.pw_name = jsonObject.getString(EnrolledTable.COLUMN_PW_NAME);
        this.h_name = jsonObject.getString(EnrolledTable.COLUMN_H_NAME);
        this.lmp = jsonObject.getString(EnrolledTable.COLUMN_LMP);
        this.edd = jsonObject.getString(EnrolledTable.COLUMN_EDD);
        this.fupdt = jsonObject.getString(EnrolledTable.COLUMN_FUPDT);
        this.fupround = jsonObject.getString(EnrolledTable.COLUMN_FUPROUND);
        this.resp_type = jsonObject.getString(EnrolledTable.COLUMN_RESP_TYPE);

        return this;

    }

    public EnrolledContract Hydrate(Cursor cursor) {
        this.ID = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_ID));
        this.puid = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_PUID));
        this.uc_code = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_UC_CODE));
        this.tehsil_code = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_TEHSIL_CODE));
        this.village_code = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_VILLAGE_CODE));
        this.lhw_code = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_LHW_CODE));
        this.studyid = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_STUDYID));
        this.pw_name = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_PW_NAME));
        this.h_name = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_H_NAME));
        this.lmp = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_LMP));
        this.edd = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_EDD));
        this.fupdt = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_FUPDT));
        this.fupround = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_FUPROUND));
        this.resp_type = cursor.getString(cursor.getColumnIndex(EnrolledTable.COLUMN_RESP_TYPE));

        return this;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public String getUc_code() {
        return uc_code;
    }

    public void setUc_code(String uc_code) {
        this.uc_code = uc_code;
    }

    public String getTehsil_code() {
        return tehsil_code;
    }

    public void setTehsil_code(String tehsil_code) {
        this.tehsil_code = tehsil_code;
    }

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    public String getLhw_code() {
        return lhw_code;
    }

    public void setLhw_code(String lhw_code) {
        this.lhw_code = lhw_code;
    }

    public String getStudyid() {
        return studyid;
    }

    public void setStudyid(String studyid) {
        this.studyid = studyid;
    }

    public String getPw_name() {
        return pw_name;
    }

    public void setPw_name(String pw_name) {
        this.pw_name = pw_name;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getLmp() {
        return lmp;
    }

    public void setLmp(String lmp) {
        this.lmp = lmp;
    }

    public String getEdd() {
        return edd;
    }

    public void setEdd(String edd) {
        this.edd = edd;
    }

    public String getFupdt() {
        return fupdt;
    }

    public void setFupdt(String fupdt) {
        this.fupdt = fupdt;
    }

    public String getFupround() {
        return fupround;
    }

    public void setFupround(String fupround) {
        this.fupround = fupround;
    }

    public String getResp_type() {
        return resp_type;
    }

    public void setResp_type(String resp_type) {
        this.resp_type = resp_type;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(EnrolledTable.COLUMN_ID, this.ID == null ? JSONObject.NULL : this.ID);
        json.put(EnrolledTable.COLUMN_PUID, this.puid == null ? JSONObject.NULL : this.puid);
        json.put(EnrolledTable.COLUMN_UC_CODE, this.uc_code == null ? JSONObject.NULL : this.uc_code);
        json.put(EnrolledTable.COLUMN_TEHSIL_CODE, this.tehsil_code == null ? JSONObject.NULL : this.tehsil_code);
        json.put(EnrolledTable.COLUMN_VILLAGE_CODE, this.village_code == null ? JSONObject.NULL : this.village_code);
        json.put(EnrolledTable.COLUMN_LHW_CODE, this.lhw_code == null ? JSONObject.NULL : this.lhw_code);
        json.put(EnrolledTable.COLUMN_STUDYID, this.studyid == null ? JSONObject.NULL : this.studyid);
        json.put(EnrolledTable.COLUMN_PW_NAME, this.pw_name == null ? JSONObject.NULL : this.pw_name);
        json.put(EnrolledTable.COLUMN_H_NAME, this.h_name == null ? JSONObject.NULL : this.h_name);
        json.put(EnrolledTable.COLUMN_LMP, this.lmp == null ? JSONObject.NULL : this.lmp);
        json.put(EnrolledTable.COLUMN_EDD, this.edd == null ? JSONObject.NULL : this.edd);
        json.put(EnrolledTable.COLUMN_FUPDT, this.fupdt == null ? JSONObject.NULL : this.fupdt);
        json.put(EnrolledTable.COLUMN_FUPROUND, this.fupround == null ? JSONObject.NULL : this.fupround);
        json.put(EnrolledTable.COLUMN_RESP_TYPE, this.resp_type == null ? JSONObject.NULL : this.resp_type);

        return json;
    }

    public static abstract class EnrolledTable implements BaseColumns {

        public static final String TABLE_NAME = "followups_pw";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PUID = "puid";
        public static final String COLUMN_UC_CODE = "uc_code";
        public static final String COLUMN_TEHSIL_CODE = "tehsil_code";
        public static final String COLUMN_VILLAGE_CODE = "village_code";
        public static final String COLUMN_LHW_CODE = "lhw_code";
        public static final String COLUMN_STUDYID = "studyid";
        public static final String COLUMN_PW_NAME = "pw_name";
        public static final String COLUMN_H_NAME = "h_name";
        public static final String COLUMN_LMP = "lmp";
        public static final String COLUMN_EDD = "edd";
        public static final String COLUMN_FUPDT = "fupdt";
        public static final String COLUMN_FUPROUND = "fupround";
        public static final String COLUMN_RESP_TYPE = "resp_type";

        public static final String _URIGET = "followups_pw.php";
    }

}
