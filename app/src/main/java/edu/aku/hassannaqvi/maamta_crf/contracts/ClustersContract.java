package edu.aku.hassannaqvi.maamta_crf.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class ClustersContract {

    private static final String TAG = "Clusters_CONTRACT";
    Long _ID;
    String clusterCode;
    String clusterName;


    public ClustersContract() {
        // Default Constructor
    }

    public ClustersContract Sync(JSONObject jsonObject) throws JSONException {
        this.clusterCode = jsonObject.getString(ClustersTable.COLUMN_CLUSTERCODE);
        this.clusterName = jsonObject.getString(ClustersTable.COLUMN_CLUSTERNAME);

        return this;

    }

    public ClustersContract Hydrate(Cursor cursor) {
        this._ID = cursor.getLong(cursor.getColumnIndex(ClustersTable._ID));
        this.clusterCode = cursor.getString(cursor.getColumnIndex(ClustersTable.COLUMN_CLUSTERCODE));
        this.clusterName = cursor.getString(cursor.getColumnIndex(ClustersTable.COLUMN_CLUSTERNAME));

        return this;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getClusterCode() {
        return clusterCode;
    }

    public void setClusterCode(String clusterCode) {
        this.clusterCode = clusterCode;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(ClustersTable._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(ClustersTable.COLUMN_CLUSTERCODE, this.clusterCode == null ? JSONObject.NULL : this.clusterCode);
        json.put(ClustersTable.COLUMN_CLUSTERNAME, this.clusterName == null ? JSONObject.NULL : this.clusterName);
        return json;
    }


    public static abstract class ClustersTable implements BaseColumns {

        public static final String TABLE_NAME = "clusters";
        public static final String _ID = "_id";
        public static final String COLUMN_CLUSTERCODE = "clustercode";
        public static final String COLUMN_CLUSTERNAME = "clustername";

        public static final String _URI = "getclusters.php";
    }
}
