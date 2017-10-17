package com.unistudent.app.unistudentappv1.models;

/**
 * Created by user on 16/10/2017.
 */

public class Exercise {
    private String question ="";
    private String answer ="";
    private int number = 0;

    public Exercise(){
    }

    public Exercise(String question){
        this.question = question; this.number = 0;
        this.answer = "";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
