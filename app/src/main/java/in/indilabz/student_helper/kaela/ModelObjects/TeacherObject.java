package in.indilabz.student_helper.kaela.ModelObjects;

public class TeacherObject {
    private String imgUrl, name, designation, connections, id;
    private boolean selected;

    public TeacherObject() {
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public TeacherObject(String imgUrl, String name, String designation, String connections, String id) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.designation = designation;
        this.connections = connections;
        this.id = id;
        selected = false;
    }

    public String getId() {
        return id;
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

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }
}