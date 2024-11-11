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