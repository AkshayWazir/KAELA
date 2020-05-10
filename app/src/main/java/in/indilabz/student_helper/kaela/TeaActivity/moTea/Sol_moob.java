package in.indilabz.student_helper.kaela.TeaActivity.moTea;

import android.graphics.Bitmap;

public class Sol_moob {
    private String name, desig, desc;
    private Bitmap proPic, solPic;

    public Sol_moob() {
    }

    public Sol_moob(String name, String desig, String desc, Bitmap proPic, Bitmap solPic) {
        this.name = name;
        this.desig = desig;
        this.desc = desc;
        this.proPic = proPic;
        this.solPic = solPic;
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
