# TabIt — Smart Bill Splitting App

**Split bills with friends and groups, effortlessly — with OCR-powered receipt scanning.**

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4-brightgreen?logo=springboot)
![React Native](https://img.shields.io/badge/React%20Native-Expo-blue?logo=react)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-green?logo=mongodb)
![TypeScript](https://img.shields.io/badge/TypeScript-blue?logo=typescript)

🔗 **Live Backend:** [https://tabit-1.onrender.com](https://tabit-1.onrender.com)

---

## Overview

TabIt is a full-stack bill-splitting application inspired by Splitwise. Users can create groups, add friends, split expenses (manually or by scanning a receipt with OCR), track real-time balances, and settle up — all backed by a custom debt-simplification algorithm that minimizes the number of transactions needed to clear everyone's dues.

---

## Key Features

- 🔐 **JWT-based Authentication** — secure signup/login with Spring Security
- 👥 **Groups & Friends** — split expenses within a group or directly with a friend
- 📸 **OCR Receipt Scanning** — snap a photo of a bill and auto-extract items, amount, and title using Tesseract OCR
- 🧮 **Debt Simplification Algorithm** — a custom greedy algorithm that minimizes the total number of settlement transactions in a group (similar to the classic "minimum cash flow" problem)
- ☁️ **Cloud Image Uploads** — profile photos and UPI payment QR codes stored via Cloudinary
- 💰 **Real-time Balance Tracking** — see exactly who owes whom, per group and per friend
- 📋 **Activity Feed** — a live log of group creations, expenses added, and settlements made
- ✅ **Settlements** — mark debts as paid and view payment QR codes directly in-app

---

## Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Java 21, Spring Boot 4, Spring Security (JWT), Spring Data MongoDB |
| **Database** | MongoDB Atlas |
| **OCR** | Tesseract (Tess4J) |
| **Image Storage** | Cloudinary |
| **Frontend** | React Native, Expo Router, TypeScript |
| **Networking** | Axios |
| **Deployment** | Render (backend, via Docker), EAS Build (Android APK) |

---

## Project Structure

Tabit/
├── tabit-backend/ # Spring Boot REST API
│ ├── src/main/java/com/tabit/tabitbackend/
│ │ ├── controller/ # REST endpoints
│ │ ├── service/ # Business logic
│ │ ├── model/ # MongoDB documents
│ │ ├── repository/ # Data access layer
│ │ ├── dto/ # Request/response objects
│ │ ├── security/ # JWT auth & filters
│ │ ├── config/ # Security, CORS, Cloudinary config
│ │ └── exception/ # Global error handling
│ ├── tessdata/ # Tesseract OCR language data
│ └── Dockerfile
│
└── tabit-app/ # React Native (Expo) mobile app
├── app/ # Screens (file-based routing)
├── components/ # Reusable UI components
├── context/ # Auth context
├── services/ # API client (Axios)
└── constants/ # Theme & config


---

## The Debt Simplification Algorithm

The core of TabIt's split logic lives in `SplitCalculationService.java`. Rather than tracking every individual transaction between every pair of users, the app:

1. Computes each user's **net balance** in a group (total paid − total owed)
2. Splits users into **creditors** (owed money) and **debtors** (owe money), sorted by amount
3. Uses a **greedy two-pointer approach** — matching the largest creditor with the largest debtor, settling the smaller of the two amounts, and moving the pointer for whichever balance hits zero first

This minimizes the total number of settlement transactions in a group — instead of 5 people generating 10+ individual debts, the algorithm reduces it to the smallest possible number of "who pays whom" transactions.

---

## Getting Started

### Prerequisites

- Java 21
- Node.js (LTS)
- MongoDB Atlas account (free tier)
- Cloudinary account (free tier)
- Expo Go app (for testing on a physical device)

### Backend Setup

```bash
cd tabit-backend
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Fill in your MongoDB URI, JWT secret, and Cloudinary credentials
./mvnw spring-boot:run
```

The backend runs on `http://localhost:8080` by default.

### Frontend Setup

```bash
cd tabit-app
npm install
# Update constants/config.ts with your backend URL (local IP or deployed URL)
npx expo start
```

Scan the QR code with Expo Go on your phone to run the app.

---

## API Endpoints (selected)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/signup` | Register a new user |
| POST | `/api/auth/login` | Authenticate and receive a JWT |
| GET | `/api/groups` | Get all groups for the current user |
| POST | `/api/groups` | Create a new group |
| POST | `/api/bills` | Create a new bill/expense |
| GET | `/api/bills/mine` | Get all bills (group + direct) for the current user |
| GET | `/api/bills/group/{groupId}/settlements` | Get settlement breakdown for a group |
| POST | `/api/ocr/scan` | Scan a receipt image and extract items/total |
| POST | `/api/upload/image` | Upload an image (avatar/QR code) to Cloudinary |
| GET | `/api/activities/me` | Get the current user's activity feed |

---

## Screenshots

<!-- Add screenshots here -->
<!-- ![Dashboard](./screenshots/dashboard.png) -->
<!-- ![Split Summary](./screenshots/split-summary.png) -->

---

## Future Improvements

- Push notifications for new expenses and reminders
- Multi-currency support
- Web dashboard version
- Recurring/scheduled expenses
- Export expense history as PDF/CSV

---

## Author

**Kiran Chaudhary**  
📧 [your email here]  
🔗 [LinkedIn](#) · [GitHub](https://github.com/kiranchaudhary18)

---

*Built as a self-directed project to strengthen full-stack development skills — Java/Spring Boot backend, React Native frontend, real-world integrations (MongoDB Atlas, Cloudinary, Tesseract OCR), and cloud deployment.*
