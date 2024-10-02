const products = [
  {
    id: 1,
    image: "asset/img/img_all/img_product/img_phone/iphone13.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
  {
    id: 2,
    image: "asset/img/img_all/img_product/img_phone/oppoReno12.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
  {
    id: 3,
    image: "asset/img/img_all/img_product/img_phone/samsungA05.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
  {
    id: 4,
    image: "asset/img/img_all/img_product/img_phone/xiaomi13C.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
];
// RENDERING PRODUCTS
function renderTable() {
  // Create a document fragment to hold the table rows
  const fragment = document.createDocumentFragment();
  // Go through product list and append to fragment
  products.forEach((product) => {
    const tr = document.createElement("tr");

    tr.innerHTML = `
      <td>${product.id}</td>
      <td><img src="${product.image}" alt="${
      product.name
    }" class="product-img" /></td>
      <td>${product.name}</td>
      <td>$${product.price}</td>
      <td>${product.salePrice || "-"}</td>
      <td>${product.brand}</td>
      <td>${product.category}</td>
      <td>${product.madeIn}</td>
      <td>${product.createDate}</td>
      <td>${product.updateDate}</td>
      <td>${product.stock}</td>
      <td>
        <button class="edit-btn" onclick="editData(this)" style="background: linear-gradient(60deg, #26c6da, #00acc1);">Edit</button>
        <button class="delete-btn" style="background: linear-gradient(60deg, #ef5350, #e53935);">Delete</button>
      </td>
    `;

    fragment.appendChild(tr);
  });

  // Update the table body with fragment
  const tableBody = document.getElementById("table-body");
  tableBody.appendChild(fragment);
  // Update the card value
  document.querySelector(".card-value").innerHTML = products.length;
}
// Render table
renderTable();

// MODAL
// Add Product
const addButton = document.querySelector(".add-btn");
const closeButton = document.querySelectorAll(".close-btn, .cancel-btn");
let modal = document.querySelector(".modal");

addButton.onclick = function () {
  modal.style.display = "block";
};

closeButton.forEach(function (button) {
  button.onclick = function () {
    modal.style.display = "none";
  };
});

// Edit Product
document.addEventListener("DOMContentLoaded", () => {
  // Get the modal
  const editModal = document.getElementById("editModal");

  // Get the close button
  const closeBtn = document.querySelector("#editModal .close-btn");
  const cancelBtn = document.querySelector("#editModal .cancel-btn");
  // Get all the edit buttons
  const editButtons = document.querySelectorAll(".edit-btn");

  // Loop through each edit button
  editButtons.forEach((button) => {
    button.addEventListener("click", () => {
      // Open the edit modal when the edit button is clicked
      editModal.style.display = "block";
    });
  });

  // When the close button is clicked, close the modal
  closeBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });
  cancelBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });
  // Optional: close the modal if the user clicks outside the modal content
  window.addEventListener("click", (event) => {
    if (event.target == editModal) {
      editModal.style.display = "none";
    }
  });
});
