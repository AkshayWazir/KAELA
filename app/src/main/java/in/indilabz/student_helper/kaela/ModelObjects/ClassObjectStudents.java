package in.indilabz.student_helper.kaela.ModelObjects;

public class ClassObjectStudents {
    private String title;
    private String[] subs;
    private String extraSub;

    public ClassObjectStudents() {
    }

    public String getExtraSub() {
        return extraSub;
    }

    public void setExtraSub(String extraSub) {
        this.extraSub = extraSub;
    }

    public ClassObjectStudents(String title, String[] subs) {
        this.title = title;
        this.subs = subs;
    }

    public ClassObjectStudents(String title, String[] subs, String extraSub) {
        this.title = title;
        this.subs = subs;
        this.extraSub = extraSub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getSubs() {
        return subs;
    }

    public void setSubs(String[] subs) {
        this.subs = subs;
    }

}
