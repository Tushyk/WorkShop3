package pl.coderslab.warsztat2;

public class MainDAO {
    public static void main(String[] args) {
        User user = new User();
        UserDAO userDAO = new UserDAO();
        //User read = userDAO.read(51);

        //System.out.println(read.getName());
        user.setName("tomek");
        user.setPassword("blabla");
        user.setEmail("mario@.wp");

        System.out.println("testowanie");



    }
}
