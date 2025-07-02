# 🧠 Quiz Platform

A Java-based interactive Quiz Application with GUI using Swing, designed for users to sign up, log in, take quizzes, and view scores. It supports admin question management and real-time scoring. Built with Java and connected to a Supabase database.

---

## 🚀 Features

- 👤 User authentication (Sign Up / Login)
- 📚 Multiple quizzes with questions stored in a database
- ✅ MCQ support using JRadioButtons
- 🎯 Real-time score calculation and result display
- 🛠️ Admin panel to manage and update questions
- 🧩 Modular and clean code structure
- ☁️ Supabase used for cloud-based backend and database
- 🏆 Leaderboard 
- ⏱️ Timer for quiz

---

## 🛠️ Tech Stack

- **Frontend**: Java Swing (GUI)
- **Backend**: JDBC with Supabase PostgreSQL
- **Language**: Java
- **Database**: Supabase (PostgreSQL)
- **Version Control**: Git & GitHub

---

## 🚦 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/HetviThakkar-025/Quiz-Platform.git
   cd Quiz-Platform
   ```

2. **Open in your Java IDE** (Eclipse, IntelliJ, or NetBeans)

3. **Connect to your Supabase DB**
   - Update `SupabaseConfig.java` with your Supabase `URL`, `API key`, and `credentials`.

4. **Run the Application**
   - Start with `UserLogin.java` or `Main.java`

---

## 🔐 Environment Setup

Create a file named `.env` or use constants in your config file:

```java
// Example
public static final String SUPABASE_URL = "https://your-project.supabase.co";
public static final String API_KEY = "your-api-key";
```

---

## 📂 Project Structure

```
📦 Quiz-Platform
├── 📁 assets/
├── 📁 database/
├── 📁 panels/
├── 📁 users/
├── 📄 Main.java
├── 📄 UserLogin.java
└── ...
```

---

## 🧪 Future Enhancements

- [ ] Multiple quiz categories
- [ ] Role-based access for admin and user

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repo
2. Create a branch (`git checkout -b feature-name`)
3. Commit changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-name`)
5. Create a pull request

---

## 📜 License

This project is open-source under the [MIT License](LICENSE).

---

## 🙋‍♀️ Author

**Hetvi Thakkar**  
📧 [Contact on GitHub](https://github.com/HetviThakkar-025)

---

## 🏷️ Badges

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white)
![Platform](https://img.shields.io/badge/DesktopApp-Swing-blueviolet?style=for-the-badge)
