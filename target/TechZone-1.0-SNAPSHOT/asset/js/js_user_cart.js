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

            //tách chuỗi thành số và đơn vị
            price = e.price.split(" ");
            //thay . giữu các số để thành chuỗi số
            price[0] = price[0].replace(/\./g, "");
            total = +price[0] * e.quantity;

            total += " VND";
            total = total.replace(/\B(?=(\d{3})+(?!\d))/g, ".");
            out += `
      <div class="cart__item col l-12">
        <div class="cart_content">
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
          <div class="cart__voucher">
              <img src="${e.voucher.img}" alt="">
              <span>${e.voucher.voucher}</span>
          </div>


      </div>
  `;
        });
        cartList.innerHTML = out;
        updateCartQuantity();
        updateVoucher();
        checkQuantityVoucher();
    }

    //Update total
    let cartFooter = document.querySelector(".cart__footer");

    if (list.length > 0) {
        var total = 0;
        var price;
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
        <div class="cart__total">
            Total:
            <span>${price}</span>
        </div>

        <div class="cart__btn">
            <button>Order Now</button>
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

// update Voucher
function updateVoucher() {
    let cartVoucher = document.querySelectorAll(".cart__voucher");
    let voucherWrapper = document.querySelector(".voucher_wrapper");
    let voucherItem = document.querySelectorAll(".voucher__item");
    list = JSON.parse(localStorage.getItem("productList"));

    cartVoucher.forEach(
            (e, index) =>
        (e.onclick = () => {
            // open voucher
            voucherWrapper.style.display = "block";

            // Add Voucher to local storage
            let voucherBtn = document.querySelector(".voucher__btn button");

            // Check voucher is check
            voucherItem.forEach((item) => {
                if (
                        item.querySelector('input[type="hidden"]').value ==
                        list[index].voucher.id
                        ) {
                    item.querySelector('input[type="radio"]').checked = true;
                }
            });

            voucherBtn.onclick = () => {
                voucherItem.forEach((item) => {
                    let checked = item.querySelector('input[type="radio"]').checked;

                    if (checked) {
                        // get data
                        let id = item.querySelector('input[type="hidden"]').value;
                        let img = item.querySelector(".voucher__itemImg img").src;
                        let voucher = item
                                .querySelector(".voucher__name span")
                                .textContent.trim();

                        list[index].voucher.id = id;
                        list[index].voucher.img = img;
                        list[index].voucher.voucher = voucher;

                        //add to local Storage
                        localStorage.setItem("productList", JSON.stringify(list));

                        // Update cart
                        updateCartList();
                    }
                    voucherWrapper.style.display = "none";
                });
            };
        })
    );

    document.onclick = (e) => {
        if (e.target == voucherWrapper) {
            voucherWrapper.style.display = "none";
        }
    };
}

function checkQuantityVoucher() {
    let voucherItem = document.querySelectorAll(".voucher__item");

    // Storge quantiy voucher of list
    let dataVoucher = [];
    voucherItem.forEach((item, index) => {
        let id = item.querySelector('input[type="hidden"]').value;
        addDataVoucher(id, 0, dataVoucher);
    });

    list = JSON.parse(localStorage.getItem("productList"));

    voucherItem.forEach((item, index) => {
        let id = item.querySelector('input[type="hidden"]').value;
        let currItem = list.find((p) => p.voucher.id === id);

        if (currItem) {
            dataVoucher.forEach((e) => {
                if (e.id == id) {
                    e.quantity += 1;
                }
            });
        }
    });

    // render quantity of voucher
    let prevCheck;
    dataVoucher.forEach((e, index) => {
        let voucherQuantity =
                voucherItem[index].querySelector(".voucher__quantity");

        console.log(voucherQuantity);

        let quan = +voucherQuantity.textContent.substring(1) - e.quantity;

        if (quan < 0) {
            quan = 0;
        }
        voucherQuantity.innerHTML = "x" + quan;
    });

    //disable voucher
    voucherItem.forEach((item, index) => {
        let voucherQuantity = voucherItem[index]
                .querySelector(".voucher__quantity")
                .textContent.substring(1);

        if (+voucherQuantity <= 0) {
            item.classList.add("disabled");
        } else {
            item.classList.remove("disabled");
        }
    });

    console.log(dataVoucher);
}

function addDataVoucher(id, quantity, data) {
    const item = {id, quantity};
    data.push(item);
}
