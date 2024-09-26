<%-- 
    Document   : admin_categories
    Created on : Sep 17, 2024, 10:13:48 PM
    Author     : Le Huu Khoa - CE181099
--%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Categories</title>
    <link rel="stylesheet" href="/asset/css/style_admin_categories.css" />
    <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
    <script src="/asset/js/js_admin_categories.js" defer></script>
    <script src="/asset/js/js_all/js_delete-button.js"></script>
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
        <p class="title">Categories</p>
        <div class="search-bar">
          <input type="text" placeholder="Search" /><i
            class="fa-solid fa-magnifying-glass"
          ></i>
        </div>
      </nav>
      <div class="card-container">
        <div class="card">
          <p class="card-name">Categories</p>
          <p class="card-value">3</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #26c6da, #00acc1)"
          >
            <i class="fa-solid fa-layer-group"></i>
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
      <!-- CATEGORIES TABLE -->
      <div class="categories-table">
        <h1 class="table-name">CATEGORIES LIST</h1>
        <table>
          <tr>
            <th><input type="checkbox" /></th>
            <th>Category ID</th>
            <th>Category Name</th>
            <th>Description</th>
            <th class="operations">Operations</th>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td>1</td>
            <td>Phone</td>
            <td>
              A smartphone is a cellular telephone with an integrated computer
              and other features not originally associated with telephones, such
              as an operating system (OS), web browsing and the ability to run
              software applications.
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
            <td>Laptop</td>
            <td>
              A laptop is a portable computer that can be easily carried around.
              It's a device designed for personal use and can perform various
              functions such as browsing the internet, creating documents,
              playing games, etc. Laptops are generally smaller in size than
              desktop computers and are battery powered.
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
            <td>Watch</td>
            <td>
              A smartwatch is a watch that offers extra functionality and
              connectivity on top of the features offered by standard watches.
              They do this by including a computer system that carries out the
              normal functionality we expect, but can also handle some extra
              bells and whistles.
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
          <h1>Add Category</h1>
          <span class="close-btn">&times;</span>
          <form action="" class="category-form">
            <label>
              Enter category ID
              <input
                type="text"
                id="category-id"
                placeholder="Category ID"
                required
              />
            </label>
            <label>
              Enter category name
              <input
                type="text"
                id="category-name"
                placeholder="Category name"
                required
              />
            </label>
            <label>
              Enter category description
              <textarea
                id="category-description"
                placeholder="Category description"
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

