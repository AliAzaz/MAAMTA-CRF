package edu.aku.hassannaqvi.maamta_crf.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class LHWsContract {

    private static final String TAG = "Lhws_CONTRACT";
    Long _ID;
    String lhwId;
    String lhwName;
    String clusterName;
    String clusterCode;


    public LHWsContract() {
        // Default Constructor
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getLhwId() {
        return lhwId;
    }

    public void setLhwId(String lhwId) {
        this.lhwId = lhwId;
    }

    public String getLhwName() {
        return lhwName;
    }

    public void setLhwName(String lhwName) {
        this.lhwName = lhwName;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterCode() {
        return clusterCode;
    }

    public void setClusterCode(String clusterCode) {
        this.clusterCode = clusterCode;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(LHWsTable._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(LHWsTable.COLUMN_LHWID, this.lhwId == null ? JSONObject.NULL : this.lhwId);
        json.put(LHWsTable.COLUMN_LHWNAME, this.lhwName == null ? JSONObject.NULL : this.lhwName);
        json.put(LHWsTable.COLUMN_CLUSTERNAME, this.clusterName == null ? JSONObject.NULL : this.clusterName);
        json.put(LHWsTable.COLUMN_CLUSTERCODE, this.clusterCode == null ? JSONObject.NULL : this.clusterCode);
        return json;
    }

    public LHWsContract Sync(JSONObject jsonObject) throws JSONException {
        this.lhwId = jsonObject.getString(LHWsTable.COLUMN_LHWID);
        this.lhwName = jsonObject.getString(LHWsTable.COLUMN_LHWNAME);
        this.clusterName = jsonObject.getString(LHWsTable.COLUMN_CLUSTERNAME);
        this.clusterCode = jsonObject.getString(LHWsTable.COLUMN_CLUSTERCODE);

        return this;

    }

    public LHWsContract Hydrate(Cursor cursor) {
        this._ID = cursor.getLong(cursor.getColumnIndex(LHWsTable._ID));
        this.lhwId = cursor.getString(cursor.getColumnIndex(LHWsTable.COLUMN_LHWID));
        this.lhwName = cursor.getString(cursor.getColumnIndex(LHWsTable.COLUMN_LHWNAME));
        return this;
    }


    public static abstract class LHWsTable implements BaseColumns {

        public static final String TABLE_NAME = "LHWs";
        public static final String _ID = "id";
        public static final String COLUMN_LHWID = "lhwid";
        public static final String COLUMN_LHWNAME = "lhwname";
        public static final String COLUMN_CLUSTERNAME = "clustername";
        public static final String COLUMN_CLUSTERCODE = "clustercode";


        public static final String _URI = "getlhws.php";
    }
}
