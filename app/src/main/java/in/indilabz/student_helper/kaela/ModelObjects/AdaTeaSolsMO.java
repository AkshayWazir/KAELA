package in.indilabz.student_helper.kaela.ModelObjects;

public class AdaTeaSolsMO {
    private String proImage, solImage;
    private String teaNam, teaDesi, solDes;

    public AdaTeaSolsMO() {
    }

    public AdaTeaSolsMO(String proImage, String solImage, String teaNam, String teaDesi, String solDes) {
        this.proImage = proImage;
        this.solImage = solImage;
        this.teaNam = teaNam;
        this.teaDesi = teaDesi;
        this.solDes = solDes;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public String getSolImage() {
        return solImage;
    }

    public void setSolImage(String solImage) {
        this.solImage = solImage;
    }

    public String getTeaNam() {
        return teaNam;
    }

    public void setTeaNam(String teaNam) {
        this.teaNam = teaNam;
    }

    public String getTeaDesi() {
        return teaDesi;
    }

    public void setTeaDesi(String teaDesi) {
        this.teaDesi = teaDesi;
    }

    public String getSolDes() {
        return solDes;
    }

    public void setSolDes(String solDes) {
        this.solDes = solDes;
    }
}
