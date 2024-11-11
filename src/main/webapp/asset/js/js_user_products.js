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

// Open - close notification

let userItem = document.querySelectorAll('.user__item');
let notificationBtn = userItem[0].querySelectorAll('.user__link')[0];
let notificationPopup = document.querySelector('.notification__popup');

show(notificationBtn, notificationPopup);

//show hide Function
/**
 * Click : object that click
 * Effect : object that show or hide
 */
function show(Click, Effect) {

    Click.onclick = () => {

        if (Effect.style.display == '' || Effect.style.display == 'none') {
            Effect.style.display = 'block';
        } else {
            Effect.style.display = 'none';
        }

    }

    //check if onclick != element before click or child of element
    document.onclick = (event) => {
        if (event.target != document.querySelector('#button_next') && !userItem[0].contains(event.target)) {
            Effect.style.display = 'none';
        }
    }

}

let buyBtn = document.querySelector('.buy__btn');


buyBtn.onclick = () => {
    let productContainer = document.querySelector('.product__container');

    let id = productContainer.querySelector("input[type=hidden]").value;
    let img = productContainer.querySelector(".product__img img").src;
    let name = productContainer.querySelector(".product__name").textContent;
    let price = productContainer.querySelector(".product__price").textContent;
    let quantity = productContainer.querySelector(".product__num").textContent;

    //Check local Storage exist
    if (JSON.parse(localStorage.getItem("productList"))) {
        list = JSON.parse(localStorage.getItem("productList"));
    }

    addItem(id, img, name, price, quantity, true);

    //add to local Storage
    localStorage.setItem("productList", JSON.stringify(list));

    window.location.href = "/Payment";
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

function toggleButtons(element) {
    const parent = element.closest('.reply-content');
    const editButton = parent.querySelector('.edit-btn');
    const deleteButton = parent.querySelector('.delete-btn');
    const editIcon = parent.querySelector('.edit-cmt');

   
    editButton.style.display = 'block'; 
    deleteButton.style.display = 'block'; 
    editIcon.style.display = 'none'; 
}
function enableEdit(element) {
    const parent = element.closest('.comment__user');
    const editButton = parent.querySelector('.edit-btn');
    const saveButton = parent.querySelector('.save');
    const cmtContent = parent.querySelector('.cmt__user-content');


    const currentContent = cmtContent.innerText;



    cmtContent.readOnly = false;
    cmtContent.focus();
   
    saveButton.style.display = 'block';
    editButton.style.display = 'none';
}

function saveEdit(element) {
    const parent = element.closest('.comment__user');
    const cmtContent = parent.querySelector('.cmt__user-content');
    const saveButton = parent.querySelector('.save');
    const editButton = parent.querySelector('.edit-btn');
    const input = cmtContent.querySelector('.edit-input');

   
    const updatedContent = input.value;
    cmtContent.innerHTML = updatedContent;

    
    saveButton.style.display = 'none';
    editButton.style.display = 'block';
}

