package in.indilabz.student_helper.kaela.ModelObjects;

public class TeacherObject {
    private String imgUrl, name, designation, rating;

    public TeacherObject() {
    }

    public TeacherObject(String imgUrl, String name, String designation, String rating) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.designation = designation;
        this.rating = rating;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}