package entity;

public class Todolist {

    private Integer id;

    private String todo;

    public Todolist() {
    }

    public Todolist(String toodo) {
        this.todo = toodo;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String toodo) {
        this.todo = toodo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
