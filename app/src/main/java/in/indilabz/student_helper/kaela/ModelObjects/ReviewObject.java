package in.indilabz.student_helper.kaela.ModelObjects;

public class ReviewObject {
    private String StudentName, review, School;

    public ReviewObject() {
    }

    public ReviewObject(String studentName, String review,String school) {
        StudentName = studentName;
        this.review = review;
        this.School = school;
    }

    public String getSchool() {
        return School;
    }

    public String getStudentName() {
        return StudentName;
    }

    public String getReview() {
        return review;
    }
}
