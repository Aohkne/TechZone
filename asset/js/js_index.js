//slider
let sliderList = document.querySelector('.slider-list');
let sliderItem = document.querySelectorAll('.slider-item');
let sliderDot = document.querySelectorAll('.slider-dot li');
let prev = document.getElementById('button_pre');
let next = document.getElementById('button_next');

let active = 0;
let lengthItems = sliderItem.length - 1;

// next btn
next.onclick = function () {
    if (active + 1 > lengthItems) {
        active = 0;
    } else {
        active++;
    }
    reloadSlider();
}

// prev btn
prev.onclick = function () {
    if (active - 1 < 0) {
        active = lengthItems;
    } else {
        active--;
    }
    reloadSlider();
}

// auto chuyển slider
let refeshSlider = setInterval(() => { next.click() }, 5000);

function reloadSlider() {
    let checkLeft = sliderItem[active].offsetLeft;
    sliderList.style.left = -checkLeft + 'px';

    //lấy dot đang được active (xoá và trở lại bth)
    let lastActiveDot = document.querySelector('.dot-item__active');
    lastActiveDot.classList.remove('dot-item__active');
    lastActiveDot.classList.add('dot-item');

    // lấy dot kế tiếp xoá và thêm active
    sliderDot[active].classList.remove('dot-item');
    sliderDot[active].classList.add('dot-item__active');

    //xoá thời gian đếm auto chuyển slider và gọi lại để đếm lại từ đầu
    clearInterval(refeshSlider);
    refeshSlider = setInterval(() => { next.click() }, 5000)
}

sliderDot.forEach((li, key) => {
    li.addEventListener('click', function () {
        active = key;
        reloadSlider();
    })
})


// Count time
var tmpSecond;
var tmpMin;
setInterval(() => {

    let hour = document.querySelectorAll('.sale__counter span')[0];
    let min = document.querySelectorAll('.sale__counter span')[1];
    let second = document.querySelectorAll('.sale__counter span')[2];

    if (second.textContent > 0) {
        //second
        tmpSecond = second.textContent - 1;
        second.textContent = (tmpSecond < 10) ? '0' + tmpSecond : tmpSecond;
    } else if (second.textContent <= 0 && min.textContent > 0) {
        second.textContent = 60;
        //min
        min.textContent = (min.textContent < 10) ? '0' + (min.textContent - 1) : min.textContent - 1
    } else if (second.textContent <= 0 && min.textContent <= 0) {
        // hour
        second.textContent = 60;
        min.textContent = 60;
        hour.textContent = (hour.textContent < 10) ? '0' + (hour.textContent - 1) : hour.textContent - 1;
    } else {
        //reset
        second.textContent = 25;
        min.textContent = 30;
        hour.textContent = '0' + 1;
    }


}, 1000)


//Scroll Sale Product
const saleList = document.querySelector('.sale__list');

let isDown = false;
let startX;
let scrollLeft;

saleList.addEventListener('mousedown', (e) => {
    isDown = true;
    saleList.classList.add('active');

    startX = e.pageX - saleList.offsetLeft;
    scrollLeft = saleList.scrollLeft;
});

saleList.addEventListener('mouseleave', () => {
    isDown = false;
    saleList.classList.remove('active');
});

saleList.addEventListener('mouseup', () => {
    isDown = false;
    saleList.classList.remove('active');
});

saleList.addEventListener('mousemove', (e) => {
    //stop when dont grabbing
    if (!isDown) return;
    e.preventDefault();
    const x = e.pageX - saleList.offsetLeft;
    const walk = (x - startX) * 1;
    saleList.scrollLeft = scrollLeft - walk;
});
