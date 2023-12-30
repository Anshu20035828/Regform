package digi.coders.regform;
//
//import android.widget.CheckBox;
//import android.widget.RadioGroup;
//
//import com.google.android.material.textfield.TextInputEditText;

public class DataModel {
    String fname,sname,eemail,ppassword,ddob,f_check,s_check,t_check,r_radio,mobileNo;

    public DataModel() {
    }

    public DataModel(String fname, String sname, String eemail, String ppassword, String ddob, String f_check, String s_check, String t_check, String r_radio, String mobileNo) {
        this.fname = fname;
        this.sname = sname;
        this.eemail = eemail;
        this.ppassword = ppassword;
        this.ddob = ddob;
        this.f_check = f_check;
        this.s_check = s_check;
        this.t_check = t_check;
        this.r_radio = r_radio;
        this.mobileNo = mobileNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEemail() {
        return eemail;
    }

    public void setEemail(String eemail) {
        this.eemail = eemail;
    }

    public String getPpassword() {
        return ppassword;
    }

    public void setPpassword(String ppassword) {
        this.ppassword = ppassword;
    }

    public String getDdob() {
        return ddob;
    }

    public void setDdob(String ddob) {
        this.ddob = ddob;
    }

    public String getF_check() {
        return f_check;
    }

    public void setF_check(String f_check) {
        this.f_check = f_check;
    }

    public String getS_check() {
        return s_check;
    }

    public void setS_check(String s_check) {
        this.s_check = s_check;
    }

    public String getT_check() {
        return t_check;
    }

    public void setT_check(String t_check) {
        this.t_check = t_check;
    }

    public String getR_radio() {
        return r_radio;
    }

    public void setR_radio(String r_radio) {
        this.r_radio = r_radio;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
