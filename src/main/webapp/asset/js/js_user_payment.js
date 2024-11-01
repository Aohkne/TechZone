let cashBtn = document.querySelectorAll(".rightContainer__feature .btn")[0];
let creditBtn = document.querySelectorAll(".rightContainer__feature .btn")[1];
let img = document.querySelector(".rightContainer__img img");

cashBtn.onclick = (e) => {
    let input = document.querySelector('.rightContainer__img input').value = 'cash';
    cashBtn.classList.toggle("active");
    creditBtn.classList.toggle("active");
    img.src = "./asset/img/img__payment/cash.png";
};

creditBtn.onclick = () => {
    let input = document.querySelector('.rightContainer__img input').value = 'credit';
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
        let productDetailIDList = [];
        let quantityList = [];
        let voucherDetailIDList = [];
        let priceList = [];
        list.forEach((e) => {
            var total = 0;
            var price;
            var voucher = 0;
            if (e.check) {
                if (e.voucher.id) {
                    voucher = e.voucher.voucher.split(" ");
                    voucher = voucher[1].replace("%", "");
                    voucherDetailIDList.push(e.voucher.id);
                } else {
                    voucherDetailIDList.push("0");
                }
                //Set order
                productDetailIDList.push(e.id);
                quantityList.push(e.quantity);
                price = e.price.split(" ");
                price = price[0].replaceAll(".", "");
                total += +price - ((voucher / 100) * price);
                priceList.push(total);
                price = total + " VND";
                price = price.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

                out += `
                <div class="leftContainer__item">
                  <div class="leftContainer__img">
                      <img src= ${e.img} />
                  </div>
                  <div class="leftContainer__content">
                      <div class="leftContainer__name">${e.name}</div>
                      <div class="leftContainer__description">description</div>
                  </div>
                  <div class="leftContainer__price"><span>${e.quantity} x </span>${price}</div>
              </div>
            `;
            }
        });
        //Get order
        out += `
            <input type="hidden" name="productDetailIDList" value="${productDetailIDList}" />
            <input type="hidden" name="quantityList" value="${quantityList}" />
            <input type="hidden" name="voucherDetailIDList" value="${voucherDetailIDList}" />
            <input type="hidden" name="priceList" value="${priceList}" />
        `;

        orderList.innerHTML = out;
    }

    //Update total
    let totalPrice = document.querySelector(".totalPrice__content");

    var total = 0;
    var price;
    var voucher = 0;
    list.forEach((e) => {
        if (e.check) {
            if (e.voucher.id) {
                voucher = e.voucher.voucher.split(" ");
                voucher = voucher[1].replace("%", "");
            }
            price = e.price.split(" ");
            price = price[0].replaceAll(".", "");
            price = +price - ((voucher / 100) * price);
            total += +price * e.quantity;
        }
    });

    price = total + " VND";
    price = price.replace(/\B(?=(\d{3})+(?!\d))/g, ".");

    totalPrice.textContent = price;
}

updateOrder();

//Delete Order

let home = document.querySelector('.message__btn');

home.onclick = () => {
    let list = JSON.parse(localStorage.getItem("productList"));

    if (list) {
        let newList = [];


        list.forEach((e) => {
            if (!e.check) {
                newList.push(e);
            }
        });

        localStorage.setItem("productList", JSON.stringify(newList));
    }
};