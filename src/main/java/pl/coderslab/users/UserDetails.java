package pl.coderslab.users;

import pl.coderslab.warsztat2.User;
import pl.coderslab.warsztat2.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDetails", value = "/user/show")
public class UserDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isBlank()) {
            getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
        } else {
            try {
                int userId = Integer.parseInt(id);
                UserDAO userDAO = new UserDAO();
                User displayUser = userDAO.read(userId);
                request.setAttribute("user", displayUser);
                getServletContext().getRequestDispatcher("/users/show.jsp").forward(request, response);


            } catch (NumberFormatException e){
                getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
