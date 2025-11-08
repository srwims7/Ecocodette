// Smooth fade animation helper with easing
function fadeIn(element) {
  element.style.opacity = 0;
  element.style.display = "block";
  let opacity = 0;
  const timer = setInterval(() => {
    if (opacity >= 1) clearInterval(timer);
    opacity += 0.05;
    element.style.opacity = opacity;
  }, 25);
}

// Wait until DOM ready
document.addEventListener("DOMContentLoaded", () => {
  const btn = document.getElementById("loadBtn");
  const totalText = document.getElementById("total");
  const suggestionsList = document.getElementById("suggestions");

  btn.addEventListener("click", async () => {
    btn.disabled = true;
    btn.textContent = "🌿 Calculating routes...";
    btn.style.backgroundColor = "#FFD6C3"; // soft peach tone
    totalText.textContent = "--";
    totalText.style.color = "#F4A896"; // subtle peach accent
    suggestionsList.innerHTML = "";

    try {
      // Fetch from backend
      const res = await fetch("http://localhost:8080/api/routes");
      const data = await res.json();

      // Delay for smooth visual flow
      setTimeout(() => {
        totalText.textContent = data.totalEmissionsKg.toFixed(2) + " kg CO₂";
        fadeIn(totalText);

        data.suggestions.forEach((s) => {
          const li = document.createElement("li");
          li.textContent = s;
          li.style.transition = "all 0.6s ease";
          li.style.color = "#3A3A3A";
          li.style.marginTop = "0.5rem";
          li.style.background = "rgba(255, 235, 228, 0.6)";
          li.style.borderRadius = "12px";
          li.style.padding = "8px 14px";
          li.style.backdropFilter = "blur(4px)";
          fadeIn(li);
          suggestionsList.appendChild(li);
        });

        btn.textContent = "🔁 Recalculate";
        btn.style.backgroundColor = "#F4A896"; // peach highlight
        btn.disabled = false;
      }, 700);
    } catch (error) {
      totalText.textContent = "⚠️ Backend not running";
      totalText.style.color = "#9e9e9e";
      btn.textContent = "Try Again";
      btn.style.backgroundColor = "#FFC2A1";
      btn.disabled = false;
    }
  });
});
