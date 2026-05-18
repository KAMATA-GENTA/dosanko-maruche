const treasures = document.querySelectorAll(".treasure");
const message = document.getElementById("message");

const resultInput = document.getElementById("result");
const selectedBoxInput = document.getElementById("selectedBox");
const correctBoxInput = document.getElementById("correctBox");
const treasureForm = document.getElementById("treasureForm");

const correctIndex = Math.floor(Math.random() * 3);

treasures.forEach((treasure) => {
  treasure.addEventListener("click", () => {
    const selectedIndex = Number(treasure.dataset.index);
    const isHit = selectedIndex === correctIndex;

    resultInput.value = isHit ? "hit" : "miss";
    selectedBoxInput.value = selectedIndex;
    correctBoxInput.value = correctIndex;

    if (isHit) {
      treasure.textContent = "💎";
      treasure.classList.add("hit");
      message.textContent = "宝物を見つけた！送料無料！";
    } else {
      treasure.textContent = "💨";
      treasure.classList.add("miss");
      message.textContent = "ざんねん…送料はそのまま！";
    }

    treasures.forEach((box) => {
      box.disabled = true;
    });

    setTimeout(() => {
      treasureForm.submit();
    }, 1500);
  });
});