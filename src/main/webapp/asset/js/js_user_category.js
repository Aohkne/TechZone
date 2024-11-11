const pages = document.querySelectorAll('.page-num');
const content = document.getElementById('content');
let currentPage = 1;

// Hàm cập nhật nội dung dựa trên số trang
function updateContent(page) {
    content.innerHTML = `Trang ${page}: Nội dung hiển thị ở đây.`;
}

// Xử lý sự kiện khi nhấn vào số trang
pages.forEach(page => {
    page.addEventListener('click', function (e) {
        e.preventDefault();
        currentPage = parseInt(this.getAttribute('data-page'));
        pages.forEach(p => p.classList.remove('active'));  // Bỏ class active
        this.classList.add('active');  // Đặt class active vào trang được nhấn
        updateContent(currentPage);  // Cập nhật nội dung
    });
});

//// Xử lý sự kiện khi nhấn nút trước và sau
//document.getElementById('prev').addEventListener('click', function (e) {
//    e.preventDefault();
//    if (currentPage > 1) {
//        currentPage--;
//        updatePagination();
//    }
//});
//
//document.getElementById('next').addEventListener('click', function (e) {
//    e.preventDefault();
//    if (currentPage < pages.length) {
//        currentPage++;
//        updatePagination();
//    }
//});

// Hàm cập nhật trạng thái pagination khi chuyển trang
function updatePagination() {
    pages.forEach(p => p.classList.remove('active'));
    document.querySelector(`.page-num[data-page="${currentPage}"]`).classList.add('active');
    updateContent(currentPage);
}

document.getElementById("popular").addEventListener("click", function () {
    var dropdown = document.getElementById("popular-list");
    // Kiểm tra nếu dropdown đang ẩn thì hiển thị, nếu đang hiện thì ẩn đi
    if (dropdown.style.display === "none" || dropdown.style.display === "") {
        dropdown.style.display = "block";
    } else {
        dropdown.style.display = "none";
    }
});

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


