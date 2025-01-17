package servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dto.Post;
import services.PostService;

import java.io.IOException;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    @Inject
    private PostService postService;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("title") == null) {
            try {
                postService.delete(Integer.parseInt(req.getParameter("id")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            resp.sendRedirect("./main");
            return;
        }

        Post post = new Post();
        post.setId(Integer.parseInt(req.getParameter("id")));
        post.setContent(req.getParameter("content"));
        post.setTitle(req.getParameter("title"));
        post.setUser_id(Integer.parseInt(req.getParameter("user_id")));

        postService.create(post);
        resp.sendRedirect("./main");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("table_post", postService.getAll());
        request.getRequestDispatcher("post.jsp").forward(request, response);
    }
}