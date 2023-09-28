package com.skarvo.todosService.models;

public class CompletionStatus {

    private Integer id;

    private boolean completed;

    public CompletionStatus(int id, boolean completed) {
        this.id = id;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "id=" + id +
                "&completed=" + completed;
    }
}
