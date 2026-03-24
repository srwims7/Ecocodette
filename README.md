# EcoCodette 🌿
**AI Route Emissions Tracker** — a full-stack web app that calculates the CO₂ footprint of your daily routes and suggests greener alternatives.

Built by the **Codettes**.

---

## What It Does

EcoCodette lets you log trips (origin, destination, vehicle type, distance) and instantly see:
- The **CO₂ emissions** for each route in kg
- A **running total** of all your emissions
- **AI-style suggestions** for reducing your carbon footprint based on your travel patterns

---

## Project Structure

```
Ecocodette/
├── ecoCodette/              # Spring Boot backend (Java)
│   ├── pom.xml
│   └── src/main/java/com/example/ecocodette/
│       ├── EcoCodetteApplication.java   # App entry point
│       ├── Route.java                   # Route data model
│       ├── RouteController.java         # REST API endpoints
│       └── RouteService.java            # CO₂ calculation logic
└── frontend/                # Vanilla HTML/CSS/JS frontend
    ├── index.html
    ├── style.css
    └── script.js
```

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 24, Spring Boot 3.5.7 |
| Validation | Spring Boot Starter Validation (Jakarta) |
| Build tool | Maven |
| Frontend | HTML, CSS, Vanilla JavaScript |
| API style | REST (JSON) |

---

## Prerequisites

- **Java 24+**
- **Maven** (or use the Maven wrapper if included)

---

## Running the App

### 1. Start the backend

```bash
cd ecoCodette
mvn spring-boot:run
```

The backend starts on **http://localhost:8080**. You'll see all registered routes printed to the console on startup.

### 2. Open the frontend

Once the backend is running, open `frontend/index.html` directly in your browser (double-click the file). No separate server needed.

Click **Load Data** — the app will seed three sample routes if none exist, then display your total emissions and suggestions.

---

## API Endpoints

Base URL: `http://localhost:8080/api/routes`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/routes` | Returns all stored routes |
| `POST` | `/api/routes` | Adds a new route and calculates its CO₂ |
| `GET` | `/api/routes/summary` | Returns total emissions + greener travel suggestions |
| `GET` | `/api/routes/test` | Health check — returns `"Controller is working!"` |

### POST /api/routes — Request body

```json
{
  "origin": "Home",
  "destination": "Office",
  "distance": 12.0,
  "vehicleType": "petrol"
}
```

All fields are required. `distance` must be 0 or greater.

### GET /api/routes/summary — Response example

```json
{
  "totalEmissionsKg": 4.04,
  "routesCount": 3,
  "suggestions": [
    "Consider electric transport for shorter trips to reduce emissions.",
    "For short trips under 5 km, walking or biking can eliminate emissions."
  ]
}
```

---

## CO₂ Emission Factors

Emissions are calculated as: `CO₂ (kg) = distance (km) × emission factor`

| Vehicle Type | Emission Factor |
|-------------|----------------|
| Electric | 0.05 kg/km |
| Petrol | 0.22 kg/km |
| Diesel | 0.25 kg/km |
| Unknown / not provided | 0.30 kg/km (fallback) |

---

## Suggestions Logic

The `/summary` endpoint generates tailored tips based on your actual routes:

- If any route uses a non-electric vehicle → suggests switching to electric for short trips
- If any route is under 5 km → suggests walking or biking
- If total emissions exceed 20 kg → suggests combining errands or using public transit
- If no routes are logged yet → prompts you to add routes
- If all choices are already low-emission → gives you a green thumbs-up 🎉

---

## Sample Seed Data

If no routes exist when you click Load Data, the frontend automatically seeds three example routes:

| Origin | Destination | Distance | Vehicle |
|--------|------------|----------|---------|
| Home | Office | 12 km | Petrol |
| Office | Gym | 4 km | Diesel |
| Gym | Home | 4 km | Electric |

---

## Notes

- Route data is stored **in-memory** — it resets each time the backend restarts. No database is connected.
- CORS is open (`*`) so the frontend can call the backend from any origin during development.
- Hot reload is enabled via Spring Boot DevTools — saving a Java file restarts the backend automatically.
