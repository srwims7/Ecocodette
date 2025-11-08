// Smooth fade animation helper
function fadeIn(element) {
  element.style.opacity = 0;
  element.style.display = "block";
  let opacity = 0;
  const timer = setInterval(() => {
    if (opacity >= 1) clearInterval(timer);
    element.style.opacity = opacity;
    opacity += 0.1;
  }, 30);
}

document.addEventListener("DOMContentLoaded", () => {
  const btn = document.getElementById("loadBtn");
  const totalText = document.getElementById("total");
  const suggestionsList = document.getElementById("suggestions");

  btn.addEventListener("click", async () => {
    // Add loading animation
    btn.disabled = true;
    btn.textContent = "⏳ Loading routes...";
    totalText.textContent = "--";
    suggestionsList.innerHTML = "";

    try {
      // Fetch from backend
      const res = await fetch("http://localhost:8080/api/routes");
      const data = await res.json();

      // Fake AI delay for smoothness
      setTimeout(() => {
        totalText.textContent = data.totalEmissionsKg.toFixed(2) + " kg CO₂";
        fadeIn(totalText);

        data.suggestions.forEach((s) => {
          const li = document.createElement("li");
          li.textContent = s;
          fadeIn(li);
          suggestionsList.appendChild(li);
        });

        btn.textContent = "✅ Reload Data";
        btn.disabled = false;
      }, 700);
    } catch (error) {
      totalText.textContent = "⚠️ Backend not running";
      totalText.style.color = "#9e9e9e";
      btn.textContent = "Try Again";
      btn.disabled = false;
    }
  });
});
