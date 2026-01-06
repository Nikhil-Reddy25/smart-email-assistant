# 🚀 Smart Email Assistant

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18.0+-blue.svg)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.0+-007ACC.svg)](https://www.typescriptlang.org/)

AI-powered email reply generator with Spring Boot backend, React frontend, and Chrome extension for Gmail integration. Generate professional email responses using Google Gemini AI.

## ✨ Features

- 🤖 **AI-Powered Responses**: Generate intelligent email replies using Google Gemini AI
- 🎨 **Multiple Tones**: Choose from professional, casual, friendly, or custom tones
- 🌐 **Web Application**: Standalone React web app for generating email responses
- 🔌 **Gmail Integration**: Chrome extension that adds an "AI Reply" button directly in Gmail
- ⚡ **Fast & Efficient**: Spring Boot backend with WebFlux for reactive processing
- 📋 **Copy to Clipboard**: One-click copy functionality for generated responses

## 🏗️ Project Structure

```
smart-email-assistant/
├── backend/                    # Spring Boot REST API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/email/writer/
│   │   │   │   ├── EmailWriterApplication.java
│   │   │   │   ├── controller/
│   │   │   │   │   └── EmailGeneratorController.java
│   │   │   │   ├── service/
│   │   │   │   │   └── EmailGeneratorService.java
│   │   │   │   ├── dto/
│   │   │   │   │   └── EmailRequest.java
│   │   │   │   └── config/
│   │   │   │       └── CorsConfig.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   └── pom.xml
├── frontend/                   # React Web Application
│   ├── src/
│   │   ├── App.tsx
│   │   ├── components/
│   │   │   └── EmailGenerator.tsx
│   │   └── index.tsx
│   ├── package.json
│   └── tsconfig.json
└── chrome-extension/           # Gmail Chrome Extension
    ├── manifest.json
    ├── content.js
    └── content.css
```

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Node.js 18+ and npm
- Maven 3.6+
- Google Gemini API Key ([Get it here](https://ai.google.dev/))

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Nikhil-Reddy25/smart-email-assistant.git
   cd smart-email-assistant/backend
   ```

2. **Set up environment variables**
   
   Create a file for environment variables or set them in your IDE:
   ```properties
   GEMINI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent
   GEMINI_API_KEY=your_api_key_here
   ```

3. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   Backend will start on `http://localhost:8080`

### Frontend Setup

1. **Navigate to frontend directory**
   ```bash
   cd ../frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start the development server**
   ```bash
   npm start
   ```

   Frontend will open on `http://localhost:3000`

### Chrome Extension Setup

1. **Open Chrome and navigate to**
   ```
   chrome://extensions/
   ```

2. **Enable "Developer mode"** (toggle in top right)

3. **Click "Load unpacked"** and select the `chrome-extension` folder

4. **Open Gmail** - You'll see the "AI Reply" button when composing or replying to emails!

## 📖 API Documentation

### Generate Email Reply

**Endpoint:** `POST /api/email/generate`

**Request Body:**
```json
{
  "emailContent": "Original email content here",
  "tone": "professional"  // Options: professional, casual, friendly, or empty
}
```

**Response:**
```json
{
  "reply": "Generated AI response..."
}
```

**Example using curl:**
```bash
curl -X POST http://localhost:8080/api/email/generate \
  -H "Content-Type: application/json" \
  -d '{
    "emailContent": "Hi, I wanted to follow up on our meeting last week.",
    "tone": "professional"
  }'
```

## 🔧 Configuration

### Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
server.port=8080
spring.application.name=Smart Email Assistant

# Gemini API Configuration (use environment variables)
gemini.api.url=${GEMINI_API_URL}
gemini.api.key=${GEMINI_API_KEY}

# CORS Configuration
cors.allowed.origins=http://localhost:3000,chrome-extension://*
```

### Frontend Configuration

Edit `frontend/src/config.ts` to point to your backend:

```typescript
export const API_BASE_URL = 'http://localhost:8080';
```

## 🎯 Usage

### Web Application

1. Open the web application at `http://localhost:3000`
2. Paste the email content you want to reply to
3. Select a tone (Professional, Casual, Friendly, or None)
4. Click "Generate Reply"
5. Copy the generated response to your clipboard

### Chrome Extension in Gmail

1. Open Gmail and click on any email
2. Click "Reply" or "Compose"
3. Look for the **"AI Reply"** button in the toolbar
4. Click it to automatically generate and insert an AI response
5. Edit if needed and send!

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.2.0** - Java framework
- **Spring WebFlux** - Reactive web client for API calls
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management

### Frontend
- **React 18+** - UI library
- **TypeScript** - Type safety
- **Material-UI (MUI)** - Component library
- **Axios** - HTTP client
- **Vite** - Build tool

### Chrome Extension
- **Vanilla JavaScript** - No framework needed
- **Chrome Extension Manifest V3** - Latest standard
- **Content Scripts** - DOM manipulation
- **Gmail DOM Integration** - Seamless UI injection

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## 📦 Deployment

### Backend Deployment

**Build JAR:**
```bash
mvn clean package
java -jar target/email-writer-sb-1.0.0.jar
```

**Deploy to:**
- Heroku
- AWS Elastic Beanstalk
- Google Cloud Run
- Docker container

### Frontend Deployment

**Build for production:**
```bash
npm run build
```

**Deploy to:**
- Vercel
- Netlify
- GitHub Pages
- AWS S3 + CloudFront

### Chrome Extension Publishing

1. Zip the `chrome-extension` folder
2. Go to [Chrome Web Store Developer Dashboard](https://chrome.google.com/webstore/devconsole)
3. Upload your extension
4. Fill in the required details
5. Submit for review

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Google Gemini AI](https://ai.google.dev/) for the AI capabilities
- [Spring Boot](https://spring.io/projects/spring-boot) for the excellent backend framework
- [React](https://reactjs.org/) for the powerful frontend library
- [Material-UI](https://mui.com/) for beautiful components

## 📧 Contact

**Nikhil Reddy** - [GitHub Profile](https://github.com/Nikhil-Reddy25)

Project Link: [https://github.com/Nikhil-Reddy25/smart-email-assistant](https://github.com/Nikhil-Reddy25/smart-email-assistant)

## 🎥 Tutorial

For a complete walkthrough, watch the tutorial: [YouTube Link](https://www.youtube.com/watch?v=hpiO9CKkKfU)

---

⭐ If you find this project helpful, please give it a star!

💼 **Perfect for your portfolio and resume!** This project demonstrates:
- Full-stack development skills
- AI/ML integration
- REST API design
- Chrome extension development
- Modern web technologies
- Production-ready code quality
