<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.UserEntity" %>
<%@ page import="java.util.List" %>

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
</div>

