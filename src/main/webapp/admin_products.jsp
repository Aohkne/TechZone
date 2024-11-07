<%-- 
    Document   : admin_products
    Created on : Sep 17, 2024, 10:17:49 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script src="/asset/js/js_all/js_modal.js" defer></script>
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
                    <li>
                        <a href="/Admin/Vouchers"
                           ><i class="fa-solid fa-map"></i>Vouchers</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Orders"
                           ><i class="fa-solid fa-table-list"></i>Orders</a
                        >
                    </li>
                    <li>
                        <a href="/Admin/Chat"
                           ><i class="fa-solid fa-table-list"></i>Chat</a
                        >
                    </li>
                </ul>
            </div>
            <div class="account dropdown-button">
                <div class="account-icon-name">
                    <i class="fa-solid fa-user"></i>
                    <p class="account-name">${name}</p>
                    <div class="dropdown-content">
                        <ul>
                            <li><a href="/Admin/Profile">Profile</a></li>
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
                    <form method="POST" action="/Admin/Product"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearchProduct" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Products</p>
                    <p class="card-value">${countProduct}</p>
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
                        class="color-btn"
                        style="
                        background: linear-gradient(60deg, #26c6da, #00acc1);
                        color: white;
                        "
                        onclick="showModal('color-modal')"
                        >
                        Color
                    </button>
                    <button
                        class="add-btn"
                        style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                        >
                        Add
                    </button>
                    <form action="/Admin/Product" method="POST">                        
                        <button
                            class="sort-btn"
                            style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
                            name="btnSortProduct"
                            type="submit">
                            Sort
                        </button>
                    </form>
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
                        <th>Color</th>
                        <th>Quantity</th>
                        <th class="operations">Operations</th>
                    </tr>
                    <c:choose>
                        <c:when test="${not empty allProduct}">
                            <c:forEach var="product" items="${allProduct}">
                                <tr>
                                    <td>${product.proDetail_id}</td>
                                    <td>
                                        <img
                                            src=".${product.pro_image}"
                                            alt="${product.pro_name}"
                                            class="product-img"
                                            />
                                    </td>
                                    <td>${product.pro_name}</td>
                                    <td>${product.pro_price}</td>
                                    <td>${product.pro_sale}</td>
                                    <td>${product.brand_id}</td>
                                    <td>${product.cat_id}</td>
                                    <td>${product.madein}</td>
                                    <td>${product.color_name}</td>
                                    <td>${product.pro_quantity}</td>
                                    <td>
                                        <button style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                                onclick="showModal('edit-modal'); editProduct(${product.proDetail_id}, '${product.pro_name}', '${product.pro_price}', '${product.pro_sale}', '${product.madein}', '${product.pro_quantity}');">
                                            Edit
                                        </button>
                                        <button style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                                onclick="showModal('delete-modal'); deleteProduct(${product.proDetail_id})">
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="9">No users found</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>

            <!-- MODAL -->
            <div id="myModal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Add Product</h1>
                    <span class="close-btn" onclick="closeModal('myModal')">&times;</span>
                    <form action="/Admin/Product" method="POST" class="product-form" enctype="multipart/form-data">
                        <label>
                            <input
                                type="hidden"
                                id="product-id"
                                placeholder="Product ID"
                                name="pro_id"
                                required=""
                                />
                        </label>
                        <label>
                            Enter product name
                            <input
                                type="text"
                                id="product-name"
                                placeholder="Product name"
                                name="pro_name"
                                required=""
                                />
                        </label>
                        <label>
                            Enter description
                            <input
                                type="text"
                                id="description"
                                placeholder="Description"
                                name="description"
                                required=""
                                />
                        </label>
                        <label>
                            Enter product price
                            <input
                                type="number"
                                id="product-price"
                                placeholder="Product price"
                                name="pro_price"
                                required=""
                                />
                        </label>
                        <label>
                            Enter product sale price (optional)
                            <input
                                type="number"
                                id="product-sale-price"
                                placeholder="Product sale price"
                                name="pro_sale"
                                required=""
                                />
                        </label>
                        <label>
                            Enter product country
                            <input
                                type="text"
                                id="product-country"
                                placeholder="Product country"
                                name="madein"
                                required=""
                                />
                        </label>
                        <label>
                            Select Product Category
                            <select name="cat_id" id="product-category" required>
                                <c:forEach var="category" items="${nameCat}">
                                    <option value="${category.cat_id}">${category.cat_name}</option>
                                </c:forEach>
                            </select>
                        </label>

                        <label>
                            Select Product brand
                            <select name="brand_id" id="product-brand" required>
                                <c:forEach var="category" items="${nameBrand}">
                                    <option value="${category.brand_id}">${category.brand_name}</option>
                                </c:forEach>
                            </select>
                        </label>

                        <label>
                            Enter product quantity
                            <input
                                type="number"
                                id="product-stock"
                                placeholder="Product quantity"
                                step="1"
                                name="quantity"
                                required=""
                                />
                        </label>
                        <label>
                            Enter product color
                            <input
                                type="text"
                                id="product-color"
                                placeholder="Product color"
                                step="1"
                                name="color_name"
                                value="default"
                                readonly=""
                                />
                        </label>
                        <label
                            >Choose the product image<input
                                type="file"
                                id="product-image"
                                accept="image/png, image/jpeg"
                                style="border: none"
                                name="image"
                                required=""
                                /></label>
                        <div class="add-cancel-btn">
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                onclick="closeModal('myModal')"
                                type="button"
                                >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                                class="accept-btn"
                                name="btnAddProduct"
                                >
                                Add
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- EDIT PRODUCT MODAL -->
            <div id="edit-modal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Edit Product</h1>
                    <span class="close-btn" onclick="closeModal('edit-modal')">&times;</span>
                    <form action="/Admin/Product" method="post" class="edit-product-form">
                        <label>
                            <input
                                type="hidden"
                                id="edit-product-id"
                                placeholder="Product ID"
                                name="proDetail_id"
                                />
                        </label>
                        <label>
                            Enter product name
                            <input
                                type="text"
                                id="edit-product-name"
                                placeholder="Product name"
                                name="pro_name"
                                />
                        </label>
                        <label>
                            Enter product price
                            <input
                                type="text"
                                id="edit-product-price"
                                placeholder="Product price"
                                name="pro_price"
                                />
                        </label>
                        <label>
                            Enter product sale price (optional)
                            <input
                                type="text"
                                id="edit-product-sale"
                                placeholder="Product sale price"
                                name="pro_sale"
                                />
                        </label>
                        <label>
                            Enter product country
                            <input
                                type="text"
                                id="edit-product-madein"
                                placeholder="Product country"
                                name="madein"
                                />
                        </label>
                        <label>
                            Enter product quantity
                            <input
                                type="number"
                                id="edit-product-quantity"
                                placeholder="Product quantity"
                                step="1"
                                name="quantity"
                                />
                        </label>
                        <div class="add-cancel-btn">
                            <button
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                onclick="closeModal('edit-modal')"
                                type="button"
                                >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                class="accept-btn"
                                name="btnEditProduct"
                                >
                                Save
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- DELETE MODAL -->
            <div id="delete-modal" class="modal">
                <div class="modal-content">
                    <h1>Confirm delete?</h1>
                    <form action="/Admin/Product" method="post" class="delete-form">
                        <input type="hidden" id="delete-product-id" name="proDetail_id"/>
                        <button
                            style="background:#ffffff; color:#000000"
                            class="cancel-btn"
                            type="button"
                            onclick="closeModal('delete-modal')"
                            >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            style="background: linear-gradient(60deg, #ef5350, #e53935); color:#ffffff"
                            class="confirm-delete-btn"
                            name="btnDeleteProduct"
                            >
                            Delete
                        </button>
                    </form>
                </div>
            </div>

            <!-- COLOR PRODUCT MODAL -->
            <div id="color-modal" class="modal">
                <!-- Modal content -->
                <div class="modal-content">
                    <h1>Color Product</h1>
                    <span class="close-btn" onclick="closeModal('color-modal')"
                          >&times;</span
                    >
                    <form action="/Admin/Product" method="POST" class="color-product-form" enctype="multipart/form-data">
                        <label for="">
                            Select Product Name
                            <select name="pro_id" id="product" required>
                                <c:forEach var="product" items="${productDaoName}">
                                    <option value="${product.pro_id}">${product.pro_name}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <label for=""
                               >Input Product Color
                            
                            <input type="text" placeholder="Product color..." name="color_name" required=""/>
                        </label>
                        <label for=""
                               >Quantity
                            <input
                                type="number"
                                placeholder="Product quantity..."
                                step="1"
                                min="1"
                                name="quantity"
                                required=""
                                />
                        </label>
                        <label id="product-image-label"
                               >Choose the product image<input
                                type="file"
                                id="product-image"
                                accept="image/png, image/jpeg"
                                style="border: none"
                                name="image"
                                required
                                /></label>
                        <div class="add-cancel-btn">
                            <button
                                type="button"
                                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                class="cancel-btn"
                                onclick="closeModal('color-modal')"
                                >
                                Cancel
                            </button>
                            <button
                                type="submit"
                                style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                                class="accept-btn"
                                name="btnAddColor"
                                >
                                Save
                            </button>
                        </div>
                    </form>
                </div>
            </div>


        </main>
        <script>
            function editProduct(proDetailId, proName, proPrice, proSale, madeIn, proQuantity) {
                // Set values in the edit modal fields
                document.getElementById('edit-product-id').value = proDetailId;
                document.getElementById('edit-product-name').value = proName;
                document.getElementById('edit-product-price').value = proPrice;
                document.getElementById('edit-product-sale').value = proSale;
                document.getElementById('edit-product-madein').value = madeIn;
                document.getElementById('edit-product-quantity').value = proQuantity;
            }

            function deleteProduct(proDetailId) {
                // Set the value in the delete modal
                document.getElementById('delete-product-id').value = proDetailId;
            }
        </script>  
    </body>
</html>



