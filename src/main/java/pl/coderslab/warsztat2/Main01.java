package pl.coderslab.warsztat2;

public class Main01 {
    public static void main(String[] args) {
        User user = new User();
        UserDAO userDAO = new UserDAO();
        user.setEmail("adam@wp.pl");
        user.setName("Adam");
        user.setPassword("Adam");

        userDAO.create(user);

    }
}
