// MODAL
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
// ACCEPT BUTTON
document.addEventListener("DOMContentLoaded", () => {
  // Select the form and table elements
  const productForm = document.querySelector(".product-form");
  const productsTable = document.querySelector(".products-table table");

  // Add event listener to the form's submit event
  productForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent form submission

    // Get form input values
    const productId = document.getElementById("product-id").value;
    const productName = document.getElementById("product-name").value;
    const productPrice = document.getElementById("product-price").value;
    const productSalePrice =
      document.getElementById("product-sale-price").value;
    const productBrand = document.getElementById("product-brand").value;
    const productCategory = document.getElementById("product-category").value;
    const productCountry = document.getElementById("product-country").value;
    const productStock = document.getElementById("product-stock").value;
    const productImage = document.getElementById("product-image").files[0];
    const productImageSrc = productImage
      ? URL.createObjectURL(productImage)
      : "";

    // Create a new row element
    const newRow = document.createElement("tr");

    // Set the inner HTML of the new row
    newRow.innerHTML = `
      <td><input type="checkbox" /></td>
      <td>${productId}</td>
      <td><img src="${productImageSrc}" alt="${productName}" class="product-img" /></td>
      <td>${productName}</td>
      <td>$${productPrice}</td>
      <td>$${productSalePrice}</td>
      <td>${productBrand}</td>
      <td>${productCategory}</td>
      <td>${productCountry}</td>
      <td>${new Date().toLocaleDateString()}</td>
      <td>${new Date().toLocaleDateString()}</td>
      <td>${productStock}</td>
      <td>
        <button style="background: linear-gradient(60deg, #26c6da, #00acc1)">Edit</button>
        <button style="background: linear-gradient(60deg, #ef5350, #e53935)">Delete</button>
      </td>
    `;

    // Append the new row to the table
    productsTable.appendChild(newRow);

    // Reset the form after submission
    productForm.reset();

    // Close the modal (you can implement a function to handle this)
    document.getElementById("myModal").style.display = "none";
  });
});
