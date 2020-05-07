package in.indilabz.student_helper.kaela.ModelObjects;

public class AdaUnsQueObj {
    private String ques, desc, name, id, quesId;

    public AdaUnsQueObj() {
    }

    public AdaUnsQueObj(String ques, String desc, String name, String id, String quesId) {
        this.ques = ques;
        this.desc = desc;
        this.name = name;
        this.id = id;
        this.quesId = quesId;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuesId() {
        return quesId;
    }

    public void setQuesId(String quesId) {
        this.quesId = quesId;
    }
}
