<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.UserEntity" %>
<%@ page import="models.PostEntity" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <body>
        <p><center>Welcome on my lab 1</center></p>
     </body>
</head>

<div class="container" align="center" valign="middle">
    <div class="user-input">
        Fill the table user
        <form action="./user" method="post">
                <input type="text" name="id", placeholder="Id">
                <input type="text" name="username", placeholder="Username">
                <button type="submit">Save</button>
        </form>
    </div>
    <div class="users">
        <% List<UserEntity> users = (List<UserEntity>) request.getAttribute("table_user");%>
        <div class="table-user" >
            <table border="1" cellspacing="0" cellpadding="1">
                <thead>
                    <tr>
                        <th>id_user</th>
                        <th>username</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (UserEntity user : users) { %>
                    <tr>
                        <td><%= user.getId() %>
                        <td><%= user.getUsername() %>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <div class="container-delete">
        <div class="user-input">
            select user id to delete 
            <form action="./user" method="post">
                    <input type="text" name="id", placeholder="Id">
                    <button type="submit">Delete</button>
            </form>
        </div>
    </div>


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

    <div class="posts">
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