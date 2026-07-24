
# 📱 Cross-Platform Appium Mobile Automation Framework

A robust, cross-platform mobile automation testing framework for **Android** and **iOS** applications. Inspired by the *LinkedIn Learning course: Advanced Appium*, this repository provides a clean architecture for scalable mobile test automation.

---

## 🛠️ Tech Stack & Architecture

* **Language:** Java
* **Automation Framework:** Appium (Supports both Android & iOS)
* **Build Tool:** Gradle
* **Dependency Management:** Maven Central Repository (resolved via Gradle)
* **Design Pattern:** Centralized `BaseTest` Architecture

### Key Features
* **Centralized `BaseTest` Class:** Serves as the parent class for all `*Test.java` files. It encapsulates driver creation, capability injection, and session lifecycle management so individual test classes stay clean and focused.
* **Cross-Platform Readiness:** Easily switch execution between Android and iOS test targets.

---

## 🚀 Roadmap & Upcoming Features

- [x] Cross-platform test setup (Android & iOS)
- [x] Centralized Base Test initialization
- [ ] Parallel Test Execution support
- [ ] GitHub Actions CI/CD workflows with automated quality gating strategies

---

## 💻 Prerequisites

Before running tests, ensure you have the following installed on your machine:

1. **Java Development Kit (JDK 11+):** Set up `JAVA_HOME`.
2. **Node.js & npm:** Required to run the Appium server.
3. **Appium Server:** Install globally via npm:
   ```bash
   npm install -g appium

```

4. **Appium Drivers:**
* Android: `appium driver install uiautomator2`
* iOS: `appium driver install xcuitest`


5. **Platform SDKs:**
* **Android Studio** (with Android SDK & Emulators configured)
* **Xcode** (macOS only, for iOS Simulators & Command Line Tools)



---

## 🚀 Getting Started

Follow these steps to clone the repository and execute your first mobile test:

### 1. Clone the Repository

```bash
git clone [https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git)
cd YOUR_REPOSITORY_NAME

```

### 2. Start the Appium Server

In a separate terminal window, start the Appium server:

```bash
appium

```

### 3. Build the Project

Fetch dependencies and compile the code using the Gradle wrapper:

```bash
./gradlew build -x test

```

### 4. Configure & Run Your First Test

1. Launch your target emulator/simulator or connect a physical device.
2. Update the device capabilities (such as `deviceName`, `platformVersion`, and `app` path) inside `BaseTest` or your properties configuration file.
3. Execute the tests via Gradle:
```bash
./gradlew test

```



---

## 📂 Project Structure

```text
├── src
│   ├── main
│   │   └── java        # Helper utilities, drivers, and Page Objects
│   └── test
│       └── java
│           ├── base    # BaseTest.java (Driver setup & teardown)
│           └── tests   # Individual *Test.java test cases extending BaseTest
├── build.gradle        # Gradle configuration resolving Maven dependencies
└── README.md

```

---

## 🤝 Contributing

Contributions are welcome! If you'd like to improve the framework, add new test cases, or help implement the upcoming roadmap features:

1. **Fork** the project.
2. Create a new Feature Branch:
```bash
git checkout -b feature/AmazingFeature

```


3. Commit your changes:
```bash
git commit -m "Add some AmazingFeature"

```


4. Push to the branch:
```bash
git push origin feature/AmazingFeature

```


5. Open a **Pull Request**.

---

## 📜 Acknowledgments & Inspiration

* Inspired by the **Advanced Appium** course on LinkedIn Learning.

```

```
