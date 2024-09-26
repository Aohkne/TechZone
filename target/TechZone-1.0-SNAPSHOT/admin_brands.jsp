<%-- 
    Document   : admin_brands
    Created on : Sep 17, 2024, 10:04:37 PM
    Author     : Le Huu Khoa - CE181099
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.BrandDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Categories</title>
        <!-- Style Admin Brands -->
        <link rel="stylesheet" href="/asset/css/style_admin_brands.css" />
        <!-- Style Sidebar -->
        <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
        <!-- Scripts Admin Brands -->
        <script src="/asset/js/js_admin_brands.js" defer></script>
        <!-- Scripts Delete button -->
        <script src="/asset/js/js_all/js_delete-button.js"></script>
        <!-- Fontawesome icons -->
        <script
            src="https://kit.fontawesome.com/d40f80c35f.js"
            crossorigin="anonymous"
            defer
        ></script>
        <!-- Script Account -->
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
                        <a href="admin_dashboard.jsp"
                           ><i class="fa-solid fa-list"></i>Dashboard</a
                        >
                    </li>
                    <li>
                        <a href="admin_products.jsp"
                           ><i class="fa-solid fa-box"></i>Products</a
                        >
                    </li>
                    <li>
                        <a href="admin_categories.jsp"
                           ><i class="fa-solid fa-layer-group"></i>Categories</a
                        >
                    </li>
                    <li>
                        <a href="admin_users.jsp"
                           ><i class="fa-solid fa-users"></i>Users</a
                        >
                    </li>
                    <li>
                        <a href="admin_reviews.jsp"
                           ><i class="fa-solid fa-comment"></i>Reviews</a
                        >
                    </li>
                    <li>
                        <a href="admin_brands.jsp"
                           ><i class="fa-solid fa-map"></i>Brands</a
                        >
                    </li>
                </ul>
            </div>
            <div class="account dropdown-button">
                <div class="account-icon-name">
                    <i class="fa-solid fa-user"></i>
                    <p class="account-name">Nguyen Trong Quy</p>
                    <div class="dropdown-content">
                        <ul>
                            <li><a href="#">Profile</a></li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- MAIN CONTENT -->
        <main>
            <nav>
                <p class="title">Brands</p>
                <div class="search-bar">
                    <input type="text" placeholder="Search" /><i
                        class="fa-solid fa-magnifying-glass"
                        ></i>
                </div>
            </nav>
            <div class="card-container">
                <div class="card">
                    <p class="card-name">Brands</p>
                    <p class="card-value">4</p>
                    <div
                        class="card-icon"
                        style="background: linear-gradient(60deg, #26c6da, #00acc1)"
                        >
                        <i class="fa-solid fa-map"></i>
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
                        class="delete-btn"
                        style="background: linear-gradient(60deg, #ef5350, #e53935)"
                        >
                        Delete
                    </button>
                    <button
                        class="sort-btn"
                        style="background: linear-gradient(60deg, #ffa726, #fb8c00)"
                        >
                        Sort
                    </button>
                </div>
            </div>
            <!-- BRANDS TABLE -->
            <div class="brands-table">
                <h1 class="table-name">BRANDS LIST</h1>
                <table>
                    <tr>
                        <th><input type="checkbox" /></th>
                        <th class="id">ID</th>
                        <th class="name">Name</th>
                        <th class="description">Description</th>
                        <th class="operations">Operations</th>
                    </tr>
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td>1</td>
                        <td>Apple</td>
                        <td>
                            Apple Inc. designs, manufactures and markets smartphones, personal
                            computers, tablets, wearables and accessories, and sells a variety
                            of related services. Its product categories include iPhone, Mac,
                            iPad, and Wearables, Home and Accessories. Its software platforms
                            include iOS, iPadOS, macOS, watchOS, and tvOS.
                        </td>
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
                        <td><input type="checkbox" /></td>
                        <td>2</td>
                        <td>Samsung</td>
                        <td>
                            Samsung is a global leader in technology, opening new
                            possibilities for people everywhere. Through relentless innovation
                            and discovery, they are transforming the worlds of TVs,
                            smartphones, wearable devices, tablets, digital appliances,
                            network systems, medical devices, semiconductors, and LED
                            solutions.
                        </td>
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
                        <td><input type="checkbox" /></td>
                        <td>3</td>
                        <td>Xiaomi</td>
                        <td>
                            Xiaomi Corporation (/??a?mi/; Chinese: ????), commonly known
                            as Xiaomi (registered as Xiaomi Inc.), is a Chinese designer and
                            manufacturer of consumer electronics and related software, home
                            appliances, automobiles and household hardware, with headquarters
                            in Beijing, China.
                        </td>
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
                        <td><input type="checkbox" /></td>
                        <td>4</td>
                        <td>Oppo</td>
                        <td>
                            Oppo (sometimes stylized as OPPO) is a Chinese consumer
                            electronics manufacturer headquartered in Dongguan, Guangdong. Its
                            major product lines include smartphones, smart devices, audio
                            devices, power banks, and other electronic products.
                        </td>
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
                    <h1>Add Brand</h1>
                    <span class="close-btn">&times;</span>
                    <form action="" class="brand-form">
                        <label>
                            Enter brand ID
                            <input
                                type="text"
                                id="brand-id"
                                placeholder="Brand ID"
                                required
                                />
                        </label>
                        <label>
                            Enter brand name
                            <input
                                type="text"
                                id="brand-name"
                                placeholder="Brand name"
                                required
                                />
                        </label>
                        <label>
                            Enter brand description
                            <textarea
                                placeholder="Brand description"
                                id="brand-description"
                                rows="15"
                                style="padding: 10px"
                                ></textarea>
                        </label>

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
        </main>
    </body>
</html>

