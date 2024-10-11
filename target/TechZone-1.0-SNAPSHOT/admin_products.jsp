<%-- 
    Document   : admin_products
    Created on : Sep 17, 2024, 10:17:49 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="DAOs.AccountDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Products</title>
        <link rel="stylesheet" href="/asset/css/style_admin_products.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <script src="/asset/js/js_admin_products.js" defer></script>
        <script src="/asset/js/js_all/js_delete-button.js" defer></script>
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <script src="/asset/js/js_all/js_account.js" defer></script>
    </head>
    <body>
        <!-- SIDE BAR -->
        <div class="sidebar">
            <div class="logo">
                <h1>TechZone</h1>
            </div>
            <div class="list-container">
                <ul class="list">
                    <li>
                        <a href="/Admin"
                           ><i class="fa-solid fa-list"></i>Dashboard</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Product"
                           ><i class="fa-solid fa-box"></i>Products</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Category"
                           ><i class="fa-solid fa-layer-group"></i>Categories</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Users"
                           ><i class="fa-solid fa-users"></i>Users</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Review"
                           ><i class="fa-solid fa-comment"></i>Reviews</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Brand"
                           ><i class="fa-solid fa-map"></i>Brands</a
                        >
                    </li>
                </ul>
            </div>
            <%
                AccountDAO dao = new AccountDAO();
                int userId = -1;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("id")) {
                            userId = Integer.parseInt(cookie.getValue());
                            break;
                        }
                    }
                }
                String name = dao.GetNameAdmin(userId);
                System.out.println(name);
                System.out.println(userId);
            %>
            <div class="account dropdown-button">
                <div class="account-icon-name">
                    <i class="fa-solid fa-user"></i>
                    <p class="account-name"><%=name%></p>
                    <div class="dropdown-content">
                        <ul>
                            <li><a href="#">Profile</a></li>
                            <li><a href="/Logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- MAIN CONTENT -->
        <main>
            <nav>
                <p class="title">Products</p>
                <div class="search-bar">
                    <input type="text" placeholder="Search" /><i
                        class="fa-solid fa-magnifying-glass"
                        ></i>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Products</p>
                    <p class="card-value">4</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-box"></i>
                    </div>
                    <hr />
                    <p class="card-graph">Graph Details</p>
                </div>
                <div class="buttons-container">
                    <button
                        class="add-btn"
                        style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                        >
                        Add
                    </button>
                    <button
                        class="sort-btn"
                        style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
                        >
                        Sort
                    </button>
                </div>
            </div>
            <!-- PRODUCTS TABLE -->
            <div class="products-table">
                <h1 class="table-name">PRODUCTS LIST</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Sale Price</th>
                        <th>Brand</th>
                        <th>Category</th>
                        <th>Made In</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Stock</th>
                        <th class="operations">Operations</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>
                            <img
                                src="/asset/img/img_all/img_product/img_phone/iphone13.jpg"
                                alt="IPhone 13"
                                class="product-img"
                                />
                        </td>
                        <td>IPhone 13</td>
                        <td>$199</td>
                        <td>$100</td>
                        <td>Apple</td>
                        <td>Phone</td>
                        <td>United States</td>
                        <td>15/9/2024</td>
                        <td>15/9/2024</td>
                        <td>11</td>
                        <td>
                            <button
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                >
                                Edit</button
                            ><button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                >
                                Delete
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>
                            <img
                                src="/asset/img/img_all/img_product/img_phone/iphone13.jpg"
                                alt="Samsung A05"
                                class="product-img"
                                />
                        </td>
                        <td>Samsung A05</td>
                        <td>$140</td>
                        <td>$95</td>
                        <td>Samsung</td>
                        <td>Phone</td>
                        <td>Korea</td>
                        <td>15/9/2024</td>
                        <td>15/9/2024</td>
                        <td>24</td>
                        <td>
                            <button
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                >
                                Edit</button
                            ><button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                >
                                Delete
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>
                            <img
                                src="/asset/img/img_all/img_product/img_phone/iphone13.jpg"
                                alt="Xiaomi 13C"
                                class="product-img"
                                />
                        </td>
                        <td>Xiaomi 13C</td>
                        <td>$95</td>
                        <td>$60</td>
                        <td>Xiaomi</td>
                        <td>Phone</td>
                        <td>China</td>
                        <td>15/9/2024</td>
                        <td>15/9/2024</td>
                        <td>4</td>
                        <td>
                            <button
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                >
                                Edit</button
                            ><button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                >
                                Delete
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>
                            <img
                                src="/asset/img/img_all/img_product/img_phone/iphone13.jpg"
                                alt="Oppo Reno 12"
                                class="product-img"
                                />
                        </td>
                        <td>Oppo Reno 12</td>
                        <td>$50</td>
                        <td>$30</td>
                        <td>Oppo</td>
                        <td>Phone</td>
                        <td>China</td>
                        <td>15/9/2024</td>
                        <td>15/9/2024</td>
                        <td>11</td>
                        <td>
                            <button
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                >
                                Edit</button
                            ><button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                >
                                Delete
                            </button>
                        </td>
                    </tr>
                </table>
            </div>
            <!-- MODAL -->
            <div id="myModal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Add Product</h1>
                    <span class="close-btn">&times;</span>
                    <form action="" class="product-form">
                        <label>
                            Enter product ID
                            <input
                                type="text"
                                id="product-id"
                                placeholder="Product ID"
                                required
                                />
                        </label>
                        <label>
                            Enter product name
                            <input
                                type="text"
                                id="product-name"
                                placeholder="Product name"
                                required
                                />
                        </label>
                        <label>
                            Enter product price
                            <input
                                type="number"
                                id="product-price"
                                placeholder="Product price"
                                required
                                />
                        </label>
                        <label>
                            Enter product sale price (optional)
                            <input
                                type="number"
                                id="product-sale-price"
                                placeholder="Product sale price"
                                />
                        </label>
                        <label>
                            Enter product brand
                            <select name="product-brand" id="product-brand">
                                <option value="Apple">Apple</option>
                                <option value="Samsung">Samsung</option>
                                <option value="Oppo">Oppo</option>
                                <option value="Xiaomi">Xiaomi</option>
                            </select>
                        </label>
                        <label>
                            Enter product category
                            <select name="product-category" id="product-category">
                                <option value="Phone">Phone</option>
                                <option value="Laptop">Laptop</option>
                                <option value="Watch">Watch</option>
                            </select>
                        </label>
                        <label>
                            Enter product country
                            <input
                                type="text"
                                id="product-country"
                                placeholder="Product country"
                                required
                                />
                        </label>
                        <label>
                            Enter product stock
                            <input
                                type="number"
                                id="product-stock"
                                placeholder="Product stock"
                                step="1"
                                required
                                />
                        </label>
                        <label
                            >Choose the product image<input
                                type="file"
                                id="product-image"
                                accept="image/png, image/jpeg"
                                style="border: none"
                                required
                                /></label>
                        <div class="add-cancel-btn">
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                                class="accept-btn"
                                >
                                Add
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- EDIT PRODUCT MODAL -->
            <div id="editModal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Edit Product</h1>
                    <span class="close-btn">&times;</span>
                    <form action="" class="edit-product-form">
                        <label>
                            Enter product ID
                            <input
                                type="text"
                                id="edit-product-id"
                                placeholder="Product ID"
                                required
                                />
                        </label>
                        <label>
                            Enter product name
                            <input
                                type="text"
                                id="edit-product-name"
                                placeholder="Product name"
                                required
                                />
                        </label>
                        <label>
                            Enter product price
                            <input
                                type="number"
                                id="edit-product-price"
                                placeholder="Product price"
                                required
                                />
                        </label>
                        <label>
                            Enter product sale price (optional)
                            <input
                                type="number"
                                id="edit-product-sale-price"
                                placeholder="Product sale price"
                                />
                        </label>
                        <label>
                            Enter product brand
                            <select name="edit-product-brand" id="edit-product-brand">
                                <option value="Apple">Apple</option>
                                <option value="Samsung">Samsung</option>
                                <option value="Oppo">Oppo</option>
                                <option value="Xiaomi">Xiaomi</option>
                            </select>
                        </label>
                        <label>
                            Enter product category
                            <select name="edit-product-category" id="edit-product-category">
                                <option value="Phone">Phone</option>
                                <option value="Laptop">Laptop</option>
                                <option value="Watch">Watch</option>
                            </select>
                        </label>
                        <label>
                            Enter product country
                            <input
                                type="text"
                                id="edit-product-country"
                                placeholder="Product country"
                                required
                                />
                        </label>
                        <label>
                            Enter product stock
                            <input
                                type="number"
                                id="edit-product-stock"
                                placeholder="Product stock"
                                step="1"
                                required
                                />
                        </label>
                        <label id="edit-product-image-label"
                               >Choose the product image<input
                                type="file"
                                id="edit-product-image"
                                accept="image/png, image/jpeg"
                                style="border: none"
                                /></label>
                        <div class="add-cancel-btn">
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                class="accept-btn"
                                >
                                Save
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>



