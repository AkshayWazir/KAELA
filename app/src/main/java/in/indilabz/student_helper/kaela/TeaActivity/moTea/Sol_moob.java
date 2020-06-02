package in.indilabz.student_helper.kaela.TeaActivity.moTea;

import android.graphics.Bitmap;

public class Sol_moob {
    private String name, desig, desc, sol_id;
    private Bitmap proPic, solPic;
    boolean accepted;

    public Sol_moob() {
    }

    public Sol_moob(String name, String desig, String desc, Bitmap proPic, Bitmap solPic) {
        this.name = name;
        this.desig = desig;
        this.desc = desc;
        this.proPic = proPic;
        this.solPic = solPic;
        this.accepted = false;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getSol_id() {
        return sol_id;
    }

    public void setSol_id(String sol_id) {
        this.sol_id = sol_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Bitmap getProPic() {
        return proPic;
    }

    public void setProPic(Bitmap proPic) {
        this.proPic = proPic;
    }

    public Bitmap getSolPic() {
        return solPic;
    }

    public void setSolPic(Bitmap solPic) {
        this.solPic = solPic;
    }
}
