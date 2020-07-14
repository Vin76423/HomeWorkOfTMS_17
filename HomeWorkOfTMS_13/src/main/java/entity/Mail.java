package entity;

import entity.templates.Template;

public class Mail {
    private String email;
    private Template massage;

    public Mail(String email, Template massage) {
        this.email = email;
        this.massage = massage;
    }

    public Mail() { }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Template getMassage() {
        return massage;
    }

    public void setMassage(Template massage) {
        this.massage = massage;
    }


    @Override
    public String toString() {
        return "Получатель : " + email + "\n\n" + massage;
    }
}
