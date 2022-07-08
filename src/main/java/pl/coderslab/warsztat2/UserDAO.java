package pl.coderslab.warsztat2;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DbUtil;
import pl.coderslab.utils.DbUtil;
import pl.coderslab.warsztat2.User;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {
    private static final String CREATE_QUERY =
            "INSERT INTO users(user_name, user_email, password) VALUES (?, ?, ?)";
    private static final String READ_QUERY =
            "select * from users where id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE users SET user_name = ?, user_email = ?, password = ? where id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users where id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    User[] users = new User[0];

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User read(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(READ_QUERY);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement update =
                    conn.prepareStatement(UPDATE_QUERY);
            update.setString(1, user.getName());
            update.setString(2, user.getEmail());
            update.setString(3, user.getPassword());
            update.setLong(4,user.getId());
            update.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement delete = conn.prepareStatement(DELETE_QUERY);
            delete.setLong(1, userId);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }
    public User[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement displayAll = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = displayAll.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user,users);
                System.out.printf("username: %s, mail: %s ID: %s\n", user.getName(), user.getEmail(), user.getId());
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}