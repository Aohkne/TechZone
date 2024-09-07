
<%@page import="DAOs.AccountDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Product</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">        
        <style>
            .active{
                background-color: black;
            }
            .navbar-brand {
                margin-left: 10px; /* Thay đổi giá trị này để điều chỉnh độ thụt đầu dòng */
            }

        </style>
    </head>
    <body>
        <div class="container-fluid p-0">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0">
                <a class="navbar-brand" href="/ProductController">PRJ301</a>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="text-white nav-link active p-3 m-0" href="/ProductController">Products</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="container">
                <h1 class="my-4">List of Products</h1>
                <a href="/ProductController/Create"><button class="btn btn-primary mb-3" type="submit">Add New</button></a>
                <div class="d-flex justify-content-center">
                    <div class="table-responsive container-fluid p-0">
                        <table class="table table-bordered">
                            <tr>
                                <th>ID</th>
                                <th>Product Name</th>
                                <th>Product Price</th>
                                <th></th>
                            </tr>
                            <%
                                AccountDAO dao = new AccountDAO();
                                ResultSet rs = dao.getAll();
                                while (rs.next()) {
                            %>
                            <tr>
                                <td><%= rs.getString("email")%></td>
                                <td><%= rs.getString("password")%></td>

                     
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    </body>
</html>
