<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.PostEntity" %>
<%@ page import="java.util.List" %>

<div class="container" align="center" valign="middle">
    <div class="posts">
        <div class="container-input">
            <div class="posts-input">
                Fill the table Post
                <form action="./post" method="post">
                        <input type="text" name="id", placeholder="Id">
                        <input type="text" name="title", placeholder="Title">
                        <input type="text" name="content", placeholder="Content">
                        <input type="text" name="user_id", placeholder="User_Id">
                        <button type="submit">Save</button>
                </form>
            </div>
    </div>

        <% List<PostEntity> posts = (List<PostEntity>) request.getAttribute("table_post");%>
        <div class="table-post">
            <table border="1" cellspacing="0" cellpadding="1">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>title</th>
                        <th>content</th>
                        <th>user_id</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (PostEntity post : posts) { %>
                    <tr class="tableBody">
                        <td><%= post.getId() %>
                        <td><%= post.getTitle() %>
                        <td><%= post.getContent() %>
                        <td><%= post.getUser_id() %>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <div class="container-delete">
        <div class="user-input">
            select post id to delete
            <form action="./post" method="post">
                    <input type="text" name="id", placeholder="Id">
                    <button type="submit">Delete</button>
            </form>
        </div>
    </div>
</div>
