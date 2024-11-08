// Hover cart
let navSearchCartIcon = document.querySelector(".navSearch__cartIcon");
let cartContainer = document.querySelector(".cart__container");

navSearchCartIcon.addEventListener("click", () => {
    if (cartContainer.style.display == "block") {
        cartContainer.style.display = "none";
    } else {
        cartContainer.style.display = "block";
    }
});

//Order to cart
let productItem = document.querySelectorAll(".product__item");

let list = [];

//Add to local Storage (from Cart)
productItem.forEach((e) => {
    let orderBtn = e.querySelector(".product__btn i");

    orderBtn.onclick = () => {
        let id = e.querySelector("input").value;
        let img = e.querySelector("img").src;
        let name = e.querySelector(".product__name").textContent;
        let price = e.querySelector(".product__price").textContent;

        //Check local Storage exist
        if (JSON.parse(localStorage.getItem("productList"))) {
            list = JSON.parse(localStorage.getItem("productList"));
        }

        //add to list
        let currItem = list.find((p) => p.id === id);
        if (currItem) {
            currItem.quantity += 1;
        } else {
            addItem(id, img, name, price, 1);
        }
        //add to local Storage
        localStorage.setItem("productList", JSON.stringify(list));

        //Update cart
        updateCartList();
    };
});

//Add to Cart
// Add to local Storage (from user_products)

let addBtn = document.querySelector(".add__btn");

if (addBtn) {
    let productContainer = document.querySelector(".product__container");
    let id = productContainer.querySelector("input").value;
    let img = productContainer.querySelector(".product__img img").src;
    let name = productContainer.querySelector(".product__name").textContent;
    let price = productContainer.querySelector(".product__price").textContent;
    addBtn.onclick = () => {
        let quantity = productContainer.querySelector(".product__num");
        //Check local Storage exist
        if (JSON.parse(localStorage.getItem("productList"))) {
            list = JSON.parse(localStorage.getItem("productList"));
        }

        //add to list
        let currItem = list.find((p) => p.id === id);
        if (currItem) {
            currItem.quantity += +quantity.textContent;
        } else {
            addItem(id, img, name, price, +quantity.textContent);
        }

        //add to local Storage
        localStorage.setItem("productList", JSON.stringify(list));

        //reset quantity
        quantity.innerHTML = 1;

        //Update cart
        updateCartList();
    };
}

// Add to local Storage (from user_category)
let cateList = document.querySelectorAll(".cate__list");
if (cateList) {
    cateList.forEach((e) => {
        let id = e.querySelector("input").value;
        let img = e.querySelector(".cate__img img").src;
        let name = e.querySelector(".cate__name").textContent;
        let price = e.querySelector(".cate__price").textContent;
        let orderIcon = e.querySelector(".order__icon");
        orderIcon.onclick = () => {
            //Check local Storage exist
            if (JSON.parse(localStorage.getItem("productList"))) {
                list = JSON.parse(localStorage.getItem("productList"));
            }
            //add to list
            let currItem = list.find((p) => p.id === id);
            if (currItem) {
                currItem.quantity += 1;
            } else {
                addItem(id, img, name, price, 1);
            }
            //add to local Storage
            localStorage.setItem("productList", JSON.stringify(list));
            //Update cart
            updateCartList();
        };
    });
}

function addItem(
        id,
        img,
        name,
        price,
        quantity,
        check = false,
        voucher = {
        id: null,
        img: "./asset/img/img_all/img_cart/voucher_rare.png",
        voucher: "Voucher",
        }
) {
    const item = {id, img, name, price, quantity, check, voucher};
    list.push(item);
}

//Update cart
function updateCartList() {
    let cartList = document.querySelector(".cart__list");
    let out = "";
    var i = 1;

    if (JSON.parse(localStorage.getItem("productList"))) {
        list = JSON.parse(localStorage.getItem("productList"));
        //Num of Order
        document.querySelector(".list__quantity").innerHTML = list.length;

        //Update Cart
        list.forEach((e) => {
            out += `
      <div class="cart__item">
        <div class="row">
            <input type="hidden" value="${e.id}">
            <div class="cart__icon col l-1">
                <input type="checkbox" name="" id="">
            </div>

            <div class="cart__img col l-4">
                <img src="${e.img}" alt="">
            </div>

            <div class="cart__content col l-5">
                <div class="row">

                    <div class="cart__name col l-12">${e.name}</div>

                    <div class="cart__description col l-12">

                        <div class="cart__price">${e.price}</div>

                        <div class="cart__quantity">
                            <i class="fa-solid fa-plus"></i>
                            <div class="cart__num">${e.quantity}</div>
                            <i class="fa-solid fa-minus"></i>
                        </div>

                    </div>

                </div>
            </div>

            <div class="cart__btnDelete col l-2">
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
            //tách chuỗi thành số và đơn vị
            price = e.price.split(" ");
            //thay . giữu các số để thành chuỗi số
            price[0] = price[0].replace(/\./g, "");
            total += +price[0] * e.quantity;
        });

        price = total + " VND";
        price = price.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

        cartFooter.innerHTML = `
      <div class="cartFooter__container">
        <div class="cart__icon">
        <input type="checkbox" name="" id=""> <span>All</span>
        </div>
        <div class="cart__totalPrice">Total: <span>${price}</span></div>
        </div>
        <div class="cart__btn">
        <button>
          <a href="/Cart">Order</a>
        </button>
      </div>  
  `;

        //check list
        let checkInput = document.querySelectorAll(
                '.cart__icon input[type ="checkbox"]'
                );
        checkInput[checkInput.length - 1].onclick = () => {
            if (checkInput[checkInput.length - 1].checked) {
                checkInput.forEach((e) => {
                    e.checked = true;
                });
            } else {
                checkInput.forEach((e) => {
                    e.checked = false;
                });
            }
        };
    } else {
        cartFooter.innerHTML = `
      <img class="img__EmptyCart" src="./asset/img/img_all/img_cart/emptyCart.png" alt="">
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
        let remove = e.querySelector(".cart__btnDelete button");
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
