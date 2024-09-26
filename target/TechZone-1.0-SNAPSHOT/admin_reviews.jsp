<%-- 
    Document   : admin_reviews
    Created on : Sep 17, 2024, 10:18:42 PM
    Author     : Le Huu Khoa - CE181099
--%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Comments</title>
    <link rel="stylesheet" href="/asset/css/style_admin_reviews.css" />
    <link rel="stylesheet" href="/asset/css/css_all/style_sidebar.css" />
    <script src="/asset/js/js_admin_reviews.js" defer></script>
    <script src="/asset/js/js_all/js_delete-button.js"></script>
    <script
      src="https://kit.fontawesome.com/d40f80c35f.js"
      crossorigin="anonymous"
      defer
    ></script>
    <script src="/JS/admin_reviews.js"></script>
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
        <p class="title">Reviews</p>
        <div class="search-bar">
          <input type="text" placeholder="Search" /><i
            class="fa-solid fa-magnifying-glass"
          ></i>
        </div>
      </nav>
      <div class="card-container">
        <div class="card">
          <p class="card-name">Reviews</p>
          <p class="card-value">5</p>
          <div
            class="card-icon"
            style="background: linear-gradient(60deg, #ef5350, #e53935)"
          >
            <i class="fa-solid fa-comment"></i>
          </div>
          <hr />
          <p class="card-graph">Graph Details</p>
        </div>
        <div class="buttons-container">
          <button style="background: linear-gradient(60deg, #ef5350, #e53935)">
            Delete
          </button>
          <button style="background: linear-gradient(60deg, #ffa726, #fb8c00)">
            Sort
          </button>
        </div>
      </div>
      <!-- REVIEWS TABLE -->
      <div class="reviews-table">
        <h1 class="table-name">REVIEWS LIST</h1>
        <table>
          <tr>
            <th><input type="checkbox" /></th>
            <th class="review">Reviews</th>
            <th class="author">Author</th>
            <th class="product-name">Product Name</th>
            <th class="posted-date">Posted Date</th>
            <th class="operations">Operations</th>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td>S?n ph?m này r?t là t?, pin y?u, r?t xu?ng m?t cái là h?...</td>
            <td>Bao Bao</td>
            <td>IPhone 13</td>
            <td>15/9/2024 | 7:18 PM</td>
            <td>
              <button
                style="background: linear-gradient(60deg, #ef5350, #e53935)"
              >
                Delete
              </button>
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td>
              S?n ph?m r?t tuy?t v?i! Camera siêu nét, ch?i game r?t m??t và r?t
              r?!
            </td>
            <td>Khoa Le</td>
            <td>Oppo Reno 12</td>
            <td>15/9/2024 | 7:18 PM</td>
            <td>
              <button
                style="background: linear-gradient(60deg, #ef5350, #e53935)"
              >
                Delete
              </button>
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td>C?ng ???c</td>
            <td>Quy Nguyen</td>
            <td>Xiaomi 13C</td>
            <td>15/9/2024 | 7:18 PM</td>
            <td>
              <button
                style="background: linear-gradient(60deg, #ef5350, #e53935)"
              >
                Delete
              </button>
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td>Tôi không thích s?n ph?m này!</td>
            <td>Gia Chan</td>
            <td>Samsung A05</td>
            <td>15/9/2024 | 7:18 PM</td>
            <td>
              <button
                style="background: linear-gradient(60deg, #ef5350, #e53935)"
              >
                Delete
              </button>
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td>ádsadjsadsadjlkasdlksalkdalsk</td>
            <td>Tre trau 123</td>
            <td>IPhone 13</td>
            <td>15/9/2024 | 7:18 PM</td>
            <td>
              <button
                style="background: linear-gradient(60deg, #ef5350, #e53935)"
              >
                Delete
              </button>
            </td>
          </tr>
        </table>
      </div>
    </main>
  </body>
</html>

