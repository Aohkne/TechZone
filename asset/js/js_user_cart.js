//Update cart
function updateCartList() {
  let cartList = document.querySelector(".cart__list");

  let out = "";
  var i = 1;

  list = JSON.parse(localStorage.getItem("productList"));
  if (list) {
    //Update Cart
    list.forEach((e) => {
      var total = 0;
      price = e.price.split(" ");
      total += +price[0] * e.quantity;
      total += "000 VND";
      total = total.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

      out += `
      <div class="cart__item col l-12">
          <div class="row">
              <input type="hidden" value="${e.id}">
              <div class="cart__icon col l-1">
                  <input type="checkbox" name="" id="">
              </div>

              <div class="cart__title col l-4">

                  <div class="title__content">
                      <img src="${e.img}" alt="">
                      <div class="cart__name">
                          ${e.name}
                          <!-- <div class="cart__description">
                              Color: White
                          </div> -->
                      </div>

                  </div>
              </div>

              <div class="cart__title col l-2">
                  ${e.price}
              </div>

              <div class="cart__title col l-2">
                  <div class="cart__quantity">
                      <i class="fa-solid fa-plus"></i>
                      <div class="cart__num">${e.quantity}</div>
                      <i class="fa-solid fa-minus"></i>
                  </div>
              </div>

              <div class="cart__title col l-2">
                  ${total}
              </div>

              <div class="cart__title col l-1">
                  <button>Delete</button>
              </div>

          </div>

      </div>
  `;
    });
    cartList.innerHTML = out;
    updateCartQuantity();
  }

  //Update total
  let cartFooter = document.querySelector(".cart__footer");

  if (list.length > 0) {
    var total = 0;
    var price;
    list.forEach((e) => {
      price = e.price.split(" ");
      total += +price[0] * e.quantity;
    });

    price = total + "000 VND";
    price = price.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

    cartFooter.innerHTML = `
    <div class="row">
          <div class="cart__voucher l-7">
              <img src="./asset/img/img_all/img_cart/voucher.png" alt="">
              <span>Voucher</span>
          </div>

          <div class="cart__total l-3">
              Total:
              <span>${price}</span>
          </div>

          <div class="cart__btn l-2">
              <button>Order Now</button>
          </div>
      </div>
      
  `;

    //check list
    let checkInput = document.querySelectorAll(
      '.cart__icon input[type ="checkbox"]'
    );
    checkInput[0].onclick = () => {
      if (checkInput[0].checked) {
        checkInput.forEach((e) => {
          e.checked = true;
        });
        checkInput.forEach((e, index) => {
          e.onclick = () => {
            if (!e.checked && index > 0) {
              checkInput[0].checked = false;
            }
          };
        });
      } else {
        checkInput.forEach((e) => {
          e.checked = false;
        });
      }
    };
  } else {
    cartFooter.innerHTML = `
      <div class="img__EmptyCart">
          <img src="./asset/img/img_all/img_cart/emptyCart.png" alt="">
      </div>
    `;
  }
}

updateCartList();

function updateCartQuantity() {
  let cartItem = document.querySelectorAll(".cart__item");
  list = JSON.parse(localStorage.getItem("productList"));

  cartItem.forEach((e) => {
    let increase = e.querySelector(".fa-plus");
    let decrease = e.querySelector(".fa-minus");
    let remove = e.querySelector(".cart__title button");
    let id = e.querySelector('input[type="hidden"]').value;

    increase.onclick = () => {
      let currItem = list.find((e) => e.id === id);
      currItem.quantity += 1;
      //update to local Storage
      localStorage.setItem("productList", JSON.stringify(list));
      updateCartList();
    };

    decrease.onclick = () => {
      let currItem = list.find((e) => e.id === id);
      if (currItem.quantity <= 1) {
        list = list.filter((e) => e.id !== id);
      } else {
        currItem.quantity -= 1;
      }
      //update to local Storage
      localStorage.setItem("productList", JSON.stringify(list));
      updateCartList();
    };

    remove.onclick = () => {
      list = list.filter((e) => e.id !== id);

      //update to local Storage
      localStorage.setItem("productList", JSON.stringify(list));
      updateCartList();
    };
  });
}
