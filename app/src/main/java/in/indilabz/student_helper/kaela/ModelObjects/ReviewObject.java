package in.indilabz.student_helper.kaela.ModelObjects;

public class ReviewObject {
    private String StudentName, question, subject;
    private int rating;

    public ReviewObject(String studentName, String question, String subject, int rating) {
        StudentName = studentName;
        this.question = question;
        this.subject = subject;
        this.rating = rating;
    }

    public String getStudentName() {
        return StudentName;
    }

    public String getQuestion() {
        return question;
    }

    public String getSubject() {
        return subject;
    }

    public int getRating() {
        return rating;
    }
}
