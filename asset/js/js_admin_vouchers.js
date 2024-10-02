// OBJECTS
const vouchers = [
  {
    id: 1,
    date: "25/9/2024",
    expireDate: "1/1/2025",
    salesPercentage: "10%",
    stock: 5,
  },
  {
    id: 2,
    date: "25/9/2024",
    expireDate: "1/1/2025",
    salesPercentage: "10%",
    stock: 5,
  },
  {
    id: 3,
    date: "25/9/2024",
    expireDate: "1/1/2025",
    salesPercentage: "10%",
    stock: 5,
  },
];
// DISPLAYING VOUCHERS
const voucherRows = vouchers
  .map((voucher) => {
    return `
    <tr>
        <td>${voucher.id}</td>
        <td>${voucher.date}</td>
        <td>${voucher.expireDate}</td>
        <td>${voucher.salesPercentage}</td>
        <td>${voucher.stock}</td>
        <td>
              <button
                style="background: linear-gradient(60deg, #ef5350, #e53935)"
              >
                Delete
              </button>
            </td>
    </tr>
    `;
  })
  .join("");
// Update list
document.getElementById("table-body").innerHTML = voucherRows;
// Update Card
document.getElementsByClassName("card-value")[0].innerHTML = vouchers.length;

// Select all checkbox function
function selectAll(source) {
  checkboxes = document.querySelectorAll("tbody .checkbox");
  checkboxes.forEach((checkbox) => {
    checkbox.checked = source.checked;
  });
}

// Handle Add Modal
const addButton = document.querySelector(".add-btn");
const addModal = document.getElementById("addModal");
const closeAddBtn = document.querySelector("#addModal .close-btn");
const cancelAddBtn = document.querySelector("#addModal .cancel-btn");

addButton.onclick = function () {
  addModal.style.display = "block";
};

closeAddBtn.onclick = function () {
  addModal.style.display = "none";
};

cancelAddBtn.onclick = function (event) {
  event.preventDefault();
  addModal.style.display = "none";
};

// Handle Edit Modal
const editModal = document.getElementById("editModal");
const closeEditBtn = document.querySelector("#editModal .close-btn");
const cancelEditBtn = document.querySelector("#editModal .cancel-btn");
const editButtons = document.querySelectorAll(".edit-btn"); // Adjust for dynamic creation

editButtons.forEach((button) => {
  button.addEventListener("click", () => {
    editModal.style.display = "block";
  });
});

closeEditBtn.onclick = function () {
  editModal.style.display = "none";
};

cancelEditBtn.onclick = function (event) {
  event.preventDefault();
  editModal.style.display = "none";
};

// Close modal when clicking outside
window.onclick = function (event) {
  if (event.target == addModal || event.target == editModal) {
    addModal.style.display = "none";
    editModal.style.display = "none";
  }
};

// Handle form submissions for adding vouchers
document
  .querySelector(".voucher-form")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    // Handle form submission logic here
    addModal.style.display = "none"; // Close modal after submission
  });
