//check list
let checkInput = document.querySelectorAll('.cart__icon input[type ="checkbox"]');

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

//Order to cart


let productItem = document.querySelectorAll('.product__item');

let list = [];

//Add to local Storage
productItem.forEach((e) => {
  let orderBtn = (e.querySelector('.product__btn i'));

  orderBtn.onclick = () => {
    let id = e.querySelector('input').value;
    let img = e.querySelector('img').src
    let name = e.querySelector('.product__name').textContent;
    let price = e.querySelector('.product__price').textContent;

    //Check local Storage exist
    if (JSON.parse(localStorage.getItem('productList'))) {
      list = JSON.parse(localStorage.getItem('productList'));
    }

    //add to list
    let currItem = list.find(p => p.id === id);
    if (currItem) {
      currItem.quantity += 1;
    } else {
      addItem(id, img, name, price, 1);
    }
    //add to local Storage
    localStorage.setItem('productList', JSON.stringify(list));

    //Update cart
    updateCartList();


  }
});

function addItem(id, img, name, price, quantity) {
  const item = { id, img, name, price, quantity };
  list.push(item);
}


//Update cart
function updateCartList() {

  let cartList = document.querySelector('.cart__list');
  let out = "";
  var i = 1;

  if (JSON.parse(localStorage.getItem('productList'))) {
    list = JSON.parse(localStorage.getItem('productList'));

    //Num of Order
    document.querySelector('.list__quantity').innerHTML = list.length;


    //Update Cart
    list.forEach(e => {
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
  var total = 0;
  var price;
  list.forEach(e => {
    price = e.price.split(' ');
    total += +(price[0]) * e.quantity;
  })

  price = total + "000 VND"
  document.querySelector('.cart__totalPrice').innerHTML = price.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

}

updateCartList();


function updateCartQuantity() {
  let cartItem = document.querySelectorAll('.cart__item');
  list = JSON.parse(localStorage.getItem('productList'));

  cartItem.forEach((e) => {
    let increase = e.querySelector('.fa-plus');
    let decrease = e.querySelector('.fa-minus');
    let remove = e.querySelector('.cart__btnDelete button');
    let id = e.querySelector('input[type="hidden"]').value;

    increase.onclick = () => {
      let currItem = list.find(e => e.id === id);
      currItem.quantity += 1;
      //update to local Storage
      localStorage.setItem('productList', JSON.stringify(list));
      updateCartList();
    };

    decrease.onclick = () => {
      let currItem = list.find(e => e.id === id);
      if (currItem.quantity <= 1) {
        list = list.filter(e => e.id !== id)
      } else {
        currItem.quantity -= 1;
      }
      //update to local Storage
      localStorage.setItem('productList', JSON.stringify(list));
      updateCartList();
    };

    remove.onclick = () => {
      list = list.filter(e => e.id !== id)
      //update to local Storage
      localStorage.setItem('productList', JSON.stringify(list));
      updateCartList();
    };
  })

}

