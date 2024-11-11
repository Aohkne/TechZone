
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Comments</title>
        <link rel="stylesheet" href="/asset/css/style_admin_vouchers.css" />
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <script src="/asset/js/js_admin_vouchers.js" defer></script>
        <script src="/asset/js/js_all/js_delete-button.js" defer></script>
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <script src="/asset/js/js_all/js_account.js" defer></script>
        <script src="/asset/js/js_all/js_modal.js"></script>
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
                <p class="title">Vouchers</p>

                <div class="search-bar">
                    <form method="POST" action="/Admin/Vouchers"> 
                        <input type="text" name="query" placeholder="Search" required />
                        <button type="submit" name="btnsearchVoucher" style="border: none">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>

            </nav>
            <c:if test="${not empty sessionScope.errorMessage}">
                <div style="padding: 15px; margin-bottom: 20px; color: #721c24; background-color: #f8d7da; border: 1px solid #f5c6cb; border-radius: 4px;" role="alert">
                    ${sessionScope.errorMessage}
                </div>
            </c:if>

            <c:if test="${not empty sessionScope.success}">
                <div style="padding: 15px; margin-bottom: 20px; color: #155724; background-color: #d4edda; border: 1px solid #c3e6cb; border-radius: 4px;" role="alert">
                    ${sessionScope.success}
                </div>
            </c:if>


            <!-- Remove session attributes after displaying the messages -->
            <c:remove var="errorMessage" scope="session" />
            <c:remove var="success" scope="session" />
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Vouchers</p>
                    <p class="card-value">${countVoucher}</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                        >
                        <i class="fa-solid fa-ticket"></i>
                    </div>
                    <hr />
                </div>


                <!-- Buttons: Sort and Add -->
                <div class="buttons-container">
                    <button
                        class="add-btn"
                        style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                        onclick="showModal('addModal')"
                        >
                        Add
                    </button>
                    <form action="/Admin/Vouchers" method="POST"> 
                        <button
                            class="sort-btn"
                            style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
                            name="btnSort"
                            type="submit">
                            Sort
                        </button>
                    </form>
                </div>
            </div>


            <!-- VOUCHERS TABLE -->
            <div class="table-container">
                <h1 class="table-name">VOUCHERS LIST</h1>
                <table>
                    <tr>
                        <th class="voucher-id-th">ID</th>
                        <th class="voucher-name-th">Name</th>
                        <th class="voucher-quantity-th">Quantity</th>
                        <th class="voucher-expire-date-th">Expire Date</th>
                        <th class="voucher-discount-th">Type</th>
                        <th class="voucher-discount-th1">User Name</th>
                        <th></th>
                    </tr>                
                    <c:if test="${not empty allBrand}">
                        <c:forEach var="user" items="${allBrand}">
                            <tr>
                                <td>${user.voucherDetail_id}</td>
                                <td>${user.voucher_name}</td>
                                <td>${user.voucher_quantity}</td>
                                <td>${user.voucher_expire_date}</td>
                                <td>${user.voucher_type}</td>
                                <td>${user.username}</td>                              
                                <td>
                                    <button
                                        name="btnDeleteVoucher"
                                        type="button"
                                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                                        onclick="showModal('delete-modal'); deleteVoucher(${user.voucherDetail_id});"
                                        >
                                        Delete
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty allBrand}">
                        <tr>
                            <td colspan="4">No users found</td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </main>
        <!-- ADD MODAL -->
        <div id="addModal" class="modal">
            <div class="modal-content">
                <h1>Add Voucher</h1>
                <span class="close-btn" onclick="closeModal('addModal')">&times;</span>
                <form class="voucher-form" action="/Admin/Vouchers" method="POST">
                    <label>
                        Choose Users:
                        <select name="user_id" id="voucher-id" required="">
                            <c:forEach var="category" items="${username}">
                                <option value="${category.user_id}">${category.username}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <label>
                        Choose Voucher Type:
                        <select id="voucher-id" name="voucher_id" required="">
                            <c:forEach var="category" items="${voucherType}">
                                <option value="${category.voucher_id}">${category.voucher_type}</option>
                            </c:forEach>
                        </select>
                    </label>

                    <label>
                        Enter Voucher Discount(%)<br>
                        (normal: 5-10;  medium: 15-25;  rare: 30-50)
                        <input
                            type="number"
                            id="voucher-discount"
                            placeholder="Voucher Discount"
                            name="voucher_discount"
                            oninput="if (parseInt(this.value) < 5) this.value = 5"
                            required=""
                            />
                    </label>
                    <label>
                        Choose Voucher Expire Date
                        <input type="date" id="voucher-expire-date" name="voucher_expire_date" required />
                    </label>
                    <label>
                        Enter Voucher Quantity
                        <input
                            type="number"
                            id="quantity"
                            name="voucher_quantity"
                            placeholder="Voucher Quantity"                      
                            oninput="if (parseInt(this.value) < 1) this.value = 1"
                            required=""
                            />
                    </label>
                    <div class="add-cancel-btn">
                        <button
                            type="button"
                            class="cancel-btn"
                            style="background: linear-gradient(60deg, #ef5350, #e53935)"
                            onclick="closeModal('addModal')"
                            >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            name="btnAddVoucher"
                            class="accept-btn"
                            style="background: linear-gradient(60deg, #66bb6a, #43a047)"
                            >
                            Add
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- DELETE MODAL -->
        <div id="delete-modal" class="modal">
            <div class="modal-content">
                <h1>Confirm delete?</h1>
                <form action="/Admin/Vouchers" method="POST" class="delete-form">
                    <input type="hidden" id="delete-brand-id" name="voucherDetail_id"/>
                    <button
                        type="button"
                        style="color: #000000"
                        class="cancel-delete-btn"
                        onclick="closeModal('delete-modal')"
                        >
                        Cancel
                    </button>
                    <button
                        type="submit"
                        style="
                        background: linear-gradient(60deg, #ef5350, #e53935);
                        color: #ffffff;
                        "
                        class="confirm-delete-btn"
                        name="btnDeleteVoucher"
                        >
                        Delete
                    </button>
                </form>
            </div>
        </div>
        <script>
            const today = new Date().toISOString().split('T')[0];
            document.getElementById("voucher-expire-date").setAttribute("min", today);
            function deleteVoucher(brandId) {

                // Set values in modal fields
                document.getElementById('delete-brand-id').value = brandId;

                const editModal = document.getElementById("deleteModal");
                editModal.style.display = "block";
            }
        </script>
    </body>
</html>





