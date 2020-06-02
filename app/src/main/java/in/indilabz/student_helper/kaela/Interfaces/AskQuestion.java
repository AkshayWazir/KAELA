package in.indilabz.student_helper.kaela.Interfaces;

public interface AskQuestion {
    void selectTeacher(String teachId, String teachMail);

    void removeTeacher(String teachId, String mail);

    void showProfile(String teachId);
}
