package servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PostService;
import services.UserService;

import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Inject
    private UserService userService;

    @Inject
    private PostService postService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("table_user", userService.getAll());
        request.setAttribute("table_post", postService.getAll());
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
