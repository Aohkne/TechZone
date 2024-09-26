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
  const brandForm = document.querySelector(".brand-form");
  const brandsTable = document.querySelector(".brands-table table");

  // Add event listener to the form's submit event
  brandForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent form submission

    // Get form input values
    const brandID = document.getElementById("brand-id").value;
    const brandName = document.getElementById("brand-name").value;
    const brandDescription = document.getElementById("brand-description").value;

    // Create a new row element
    const newRow = document.createElement("tr");

    // Set the inner HTML of the new row
    newRow.innerHTML = `
      <td><input type="checkbox" /></td>
      <td>${brandID}</td>
      <td>${brandName}</td>
      <td>${brandDescription}</td>
      <td>
        <button style="background: linear-gradient(60deg, #26c6da, #00acc1)">Edit</button>
        <button style="background: linear-gradient(60deg, #ef5350, #e53935)">Delete</button>
      </td>
    `;

    // Append the new row to the table
    brandsTable.appendChild(newRow);

    // Reset the form after submission
    brandForm.reset();

    // Close the modal (you can implement a function to handle this)
    document.getElementById("myModal").style.display = "none";
  });
});
