//Open close account

let accountImg = document.querySelector(".account__img");
let accountContainer = document.querySelector(".account__container");

accountImg.onclick = () => {
  accountContainer.style.display = "block";
};

document.addEventListener("click", (e) => {
  let accountContent = document.querySelector(".account__content");

  if (
    e.target != document.querySelector("#button_next") &&
    e.target != accountContent &&
    !accountContent.contains(e.target) &&
    e.target != accountImg &&
    !accountImg.contains(e.target)
  ) {
    accountContainer.style.display = "none";
  }
});
