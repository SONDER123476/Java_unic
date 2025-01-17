package servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dto.User;
import services.UserService;

import java.io.IOException;

@WebServlet(name = "user", value = "/user")
public class UserServlet extends HttpServlet {
    @Inject
    private UserService userService;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("username") == null) {
            try {
                userService.delete(Integer.parseInt(req.getParameter("id")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            resp.sendRedirect("./main");
            return;
        }

        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setUsername(req.getParameter("username"));

        userService.create(user);
        resp.sendRedirect("./main");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("table_user", userService.getAll());
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }
}