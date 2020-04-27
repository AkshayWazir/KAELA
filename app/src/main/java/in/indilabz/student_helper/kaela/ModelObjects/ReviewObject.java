package in.indilabz.student_helper.kaela.ModelObjects;

public class ReviewObject {
    private String StudentName, review, subject;
    private int rating;

    public ReviewObject() {
    }

    public ReviewObject(String studentName, String review, String subject, int rating) {
        StudentName = studentName;
        this.review = review;
        this.subject = subject;
        this.rating = rating;
    }

    public String getStudentName() {
        return StudentName;
    }

    public String getReview() {
        return review;
    }

    public String getSubject() {
        return subject;
    }

    public int getRating() {
        return rating;
    }
}
