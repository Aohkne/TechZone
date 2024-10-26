let cashBtn = document.querySelectorAll(".rightContainer__feature .btn")[0];
let creditBtn = document.querySelectorAll(".rightContainer__feature .btn")[1];
let img = document.querySelector(".rightContainer__img img");

cashBtn.onclick = (e) => {
  cashBtn.classList.toggle("active");
  creditBtn.classList.toggle("active");
  img.src = "./asset/img/img__payment/cash.png";
};

creditBtn.onclick = () => {
  cashBtn.classList.toggle("active");
  creditBtn.classList.toggle("active");
  img.src = "./asset/img/img__payment/qr.png";
};

// Update Order
function updateOrder() {
  let out = "";
  let orderList = document.querySelector(".leftContainer__list");

  //Get local Storage
  list = JSON.parse(localStorage.getItem("productList"));

  if (list) {
    list.forEach((e) => {
      if (e.check) {
        var total = 0;
        price = e.price.split(" ");

        total += +price[0].replace(".", "") * e.quantity;
        total += "VND";
        total = total.replace(/\B(?=(\d{3})+(?!\d))/g, ".");
        out += `
        <div class="leftContainer__item">
        <input type="hidden" value="${e.id}">
          <div class="leftContainer__img">
              <img src= ${e.img} />
          </div>
          <div class="leftContainer__content">
              <div class="leftContainer__name">${e.name}</div>
              <div class="leftContainer__description">description</div>
          </div>
          <div class="leftContainer__price"><span>${e.quantity} x </span>${total}</div>
      </div>
        `;
      }
    });

    orderList.innerHTML = out;
  }

  //Update total
  let totalPrice = document.querySelector(".totalPrice__content");

  var total = 0;
  var price;
  list.forEach((e) => {
    if (e.check) {
      price = e.price.split(" ");
      total += +price[0] * e.quantity;
    }
  });

  price = total + " VND";
  price = price.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

  totalPrice.textContent = price;
}

updateOrder();
