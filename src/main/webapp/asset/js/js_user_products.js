//Quantity Product
let productQuantity = document.querySelector(".product__quantity");
let increase = productQuantity.querySelector(".fa-plus");
let decrease = productQuantity.querySelector(".fa-minus");
let numOrder = productQuantity.querySelector(".product__num");

increase.onclick = () => {
  numOrder.innerHTML = +numOrder.textContent + 1;
};

decrease.onclick = () => {
  if (+numOrder.textContent <= 1) {
    return;
  }
  numOrder.innerHTML = +numOrder.textContent - 1;
};
