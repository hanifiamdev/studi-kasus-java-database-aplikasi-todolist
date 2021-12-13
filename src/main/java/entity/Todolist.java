package entity;

public class Todolist {

    private Integer id;

    private String toodo;

    public Todolist() {
    }

    public Todolist(String toodo) {
        this.toodo = toodo;
    }

    public String getToodo() {
        return toodo;
    }

    public void setTodo(String toodo) {
        this.toodo = toodo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
