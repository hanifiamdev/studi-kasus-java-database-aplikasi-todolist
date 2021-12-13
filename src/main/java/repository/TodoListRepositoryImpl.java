package repository;


import entity.Todolist;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoListRepositoryImpl implements TodoListRepository {

    public Todolist[] data = new Todolist[10];

    private DataSource dataSource;

    public TodoListRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public Todolist[] getAll() {
        return data;
    }

    public boolean isFull() {
        var isFull = true;
        for(int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                // model masih ada yang kosong
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public void resizeIfFull() {
        // jika Full maka di resize
        if (isFull()) {
            var temp = data;
            data = new Todolist[data.length * 2];

            for (int i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
    }

    @Override
    public void add(Todolist todolist) {
        String sql = "INSERT INTO todolist(todo) VALUES (?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, todolist.getToodo());
            statement.executeUpdate();

        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }

    private boolean isExists(Integer number) {
        String sql = "SELECT * FROM todolist WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, number);

            try(ResultSet resultset = statement.executeQuery()) {
                if(resultset.next()) {
                    return true;
                }else {
                    return false;
                }
            }

        }catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean remove(Integer number) {
        if(isExists(number)) {
            String sql = "DELETE FROM todolist WHERE id = ?";
            try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, number);
                statement.executeUpdate();

                return true;
            }catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }else {
            return false;
        }
    }
}
