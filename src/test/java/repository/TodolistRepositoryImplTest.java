package repository;

import entity.Todolist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseUtil;

import javax.sql.DataSource;

public class TodolistRepositoryImplTest {

    private DataSource dataSource;

    private TodoListRepository todoListRepository;

    @BeforeEach
    void setUP() {
        dataSource = DatabaseUtil.getDataSource();
        todoListRepository = new TodoListRepositoryImpl(dataSource);

    }

    @Test
    void testAdd() {
        Todolist todolist = new Todolist();
        todolist.setToodo("Hanif");
        todoListRepository.add(todolist);

    }
}
