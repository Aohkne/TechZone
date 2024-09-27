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