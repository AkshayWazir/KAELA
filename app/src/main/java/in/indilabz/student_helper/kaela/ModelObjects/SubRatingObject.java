package in.indilabz.student_helper.kaela.ModelObjects;

public class SubRatingObject {
    private String title;
    private float progress;

    public SubRatingObject() {
    }

    public SubRatingObject(String title, float progress) {
        this.title = title;
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
