package edu.aku.hassannaqvi.maamta_crf.otherclasses;

/**
 * Created by Ali on 01-Mar-17.
 */

public class EligibleParticipants {

    String l_uid, wname, dob;

    public EligibleParticipants(String l_uid, String wname, String dob) {
        this.l_uid = l_uid;
        this.wname = wname;
        this.dob = dob;
    }

    public String getL_uid() {
        return l_uid;
    }

    public void setL_uid(String l_uid) {
        this.l_uid = l_uid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getDob() {
        return dob;
    }
}
