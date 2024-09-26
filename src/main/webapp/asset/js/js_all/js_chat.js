// open / close chat

let chatIcon = document.querySelector(".chat__container img");
let closeIcon = document.querySelector(".chat__title i");
let chatContent = document.querySelector(".chat__content");

chatIcon.onclick = () => {
  if (chatContent.style.display == "block") {
    chatContent.style.display = "none";
  } else {
    chatContent.style.display = "block";
  }
};

closeIcon.onclick = () => {
  chatContent.style.display = "none";
};
