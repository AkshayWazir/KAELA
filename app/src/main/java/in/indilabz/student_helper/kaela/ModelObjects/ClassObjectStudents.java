package in.indilabz.student_helper.kaela.ModelObjects;

public class ClassObjectStudents {
    private String title;
    private String[] subs;
    private TeacherObject[] teachers;

    public ClassObjectStudents() {
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

    public TeacherObject[] getTeachers() {
        return teachers;
    }

    public void setTeachers(TeacherObject[] teachers) {
        this.teachers = teachers;
    }
}
