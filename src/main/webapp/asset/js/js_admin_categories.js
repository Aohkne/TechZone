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
  const categoryForm = document.querySelector(".category-form");
  const categoriesTable = document.querySelector(".categories-table table");

  // Add event listener to the form's submit event
  categoryForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent form submission

    // Get form input values
    const categoryID = document.getElementById("category-id").value;
    const categoryName = document.getElementById("category-name").value;
    const categoryDescription = document.getElementById(
      "category-description"
    ).value;

    // Create a new row element
    const newRow = document.createElement("tr");

    // Set the inner HTML of the new row
    newRow.innerHTML = `
      <td><input type="checkbox" /></td>
      <td>${categoryID}</td>
      <td>${categoryName}</td>
      <td>${categoryDescription}</td>
      <td>
        <button style="background: linear-gradient(60deg, #26c6da, #00acc1)">Edit</button>
        <button style="background: linear-gradient(60deg, #ef5350, #e53935)">Delete</button>
      </td>
    `;

    // Append the new row to the table
    categoriesTable.appendChild(newRow);

    // Reset the form after submission
    categoryForm.reset();

    // Close the modal (you can implement a function to handle this)
    document.getElementById("myModal").style.display = "none";
  });
});