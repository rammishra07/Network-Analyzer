# 🚀 Network Analyzer Project

A real-time **Network Traffic Monitoring System** that captures, analyzes, and visualizes packet data using a full-stack architecture.

---

## 📌 Overview

This project simulates real-world network monitoring by capturing packet-level data using Java sockets, processing it through a Spring Boot backend, storing it in PostgreSQL, and visualizing it on a dynamic React dashboard.

---

## 🛠 Tech Stack

| Layer            | Technology Used         |
| ---------------- | ----------------------- |
| 💻 Backend       | Spring Boot (Java)      |
| 🌐 Frontend      | React.js                |
| 🗄 Database      | PostgreSQL              |
| 🔌 Networking    | Java Socket Programming |
| 📊 Visualization | Chart.js / Recharts     |

---

## ⚡ Features

* 📡 Real-time packet capture using sockets
* 🔍 Protocol analysis (TCP / UDP)
* 🚨 Suspicious packet detection (based on size)
* 📊 Interactive dashboard with charts & tables
* 🔄 Live data updates (polling-based)
* 🔎 Search & filtering functionality

---

## 🏗 System Architecture

```
Test Client → Socket Server → Spring Boot API → PostgreSQL → React Dashboard
```


## ▶️ How to Run

### 🔹 1. Backend (Spring Boot)

```bash
cd backend
run DemoApplication.java
```

---

### 🔹 2. Frontend (React)

```bash
cd frontend
npm install
npm start
```

---

### 🔹 3. Test Client (Traffic Simulator)

```bash
cd test-client
javac TestClient.java
java TestClient
```

---

## 🌐 API Endpoints

| Method | Endpoint | Description       |
| ------ | -------- | ----------------- |
| GET    | /packets | Fetch all packets |
| POST   | /packets | Store packet data |

---

## 🧠 Key Learnings

* Building real-time systems using socket programming
* Designing REST APIs with Spring Boot
* Database integration with PostgreSQL
* Full-stack integration (Backend ↔ Frontend)
* Debugging multi-layer architecture

---

## 💡 Future Improvements

* ⚡ WebSocket integration (real-time push instead of polling)
* 🚨 Advanced intrusion detection system
* ☁️ Cloud deployment (AWS / Render / Vercel)
* 📈 Advanced analytics & reporting

---

## 👨‍💻 Author

**Ram Mishra**

---
