package pl.coderslab.users;

import pl.coderslab.warsztat2.User;
import pl.coderslab.warsztat2.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isBlank()) {
            getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
        } else {
            try {
                int userId = Integer.parseInt(id);
                UserDAO userDAO = new UserDAO();
                User editUser = userDAO.read(userId);
                request.setAttribute("user", editUser);
                getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);

            } catch (NumberFormatException e){
                getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setId(Integer.parseInt(request.getParameter("id")));

        UserDAO userDAO = new UserDAO();
        userDAO.update(user);
        response.sendRedirect(request.getContextPath() + "/user/list");

    }
}
