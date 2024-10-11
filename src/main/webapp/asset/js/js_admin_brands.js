document.addEventListener("DOMContentLoaded", () => {
  // MODAL
  const addButton = document.querySelector(".add-btn");
  const closeButtons = document.querySelectorAll(".close-btn, .cancel-btn");
  let modal = document.querySelector(".modal");


  addButton.onclick = function () {
    modal.style.display = "block";
  };


  closeButtons.forEach(function (button) {
    button.onclick = function () {
      modal.style.display = "none";
    };
  });


  // ACCEPT BUTTON
  const brandForm = document.querySelector(".brand-form");
  const brandsTable = document.querySelector(".brands-table table");


  brandForm.addEventListener("submit", function (event) {
    event.preventDefault();


    const brandID = document.getElementById("brand-id").value;
    const brandName = document.getElementById("brand-name").value;
    const brandDescription = document.getElementById("brand-description").value;


    const newRow = document.createElement("tr");
    newRow.innerHTML = `
      <td><input type="checkbox" /></td>
      <td>${brandID}</td>
      <td>${brandName}</td>
      <td>${brandDescription}</td>
      <td>
        <button class="edit-btn" style="background: linear-gradient(60deg, #26c6da, #00acc1)">Edit</button>
        <button style="background: linear-gradient(60deg, #ef5350, #e53935)">Delete</button>
      </td>
    `;


    brandsTable.appendChild(newRow);
    brandForm.reset();
    modal.style.display = "none"; // Close modal after adding the new row


    attachEditButtonListeners(); // Re-attach event listeners to new edit buttons
  });


  // EDIT MODAL FUNCTIONALITY
  const editModal = document.getElementById("editModal");
  const closeEditBtn = document.querySelector("#editModal .close-btn");
  const cancelEditBtn = document.querySelector("#editModal .cancel-btn");


  closeEditBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });


  cancelEditBtn.addEventListener("click", () => {
    editModal.style.display = "none";
  });


  window.addEventListener("click", (event) => {
    if (event.target == editModal) {
      editModal.style.display = "none";
    }
  });


  function attachEditButtonListeners() {
    const editButtons = document.querySelectorAll(".edit-btn");
    editButtons.forEach((button) => {
      button.addEventListener("click", () => {
        editModal.style.display = "block";
      });
    });
  }


  attachEditButtonListeners(); // Initial call to attach listeners
});





