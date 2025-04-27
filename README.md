# 📡 Java Multi-ServerClient.Client Chat Application

A simple yet powerful multi-client chat server built with Java sockets and multi-threading. ServerClient.Broadcast messages in real-time to all connected clients! 🚀

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Socket](https://img.shields.io/badge/Networking-Socket-orange)
![Multi-Threading](https://img.shields.io/badge/Concurrency-Multi--Threading-green)

## 🌟 Features

- **Real-time Messaging** 💬  
  Instantly send and receive messages across all connected clients.

- **Multi-ServerClient.Client Support** 👥  
  Handle multiple clients simultaneously using threads.

- **Simple Architecture** 🏗️  
  Clean server-client model with intuitive code structure.

- **Console Interface** ⌨️  
  Lightweight terminal-based interaction.

## 🛠️ How It Works

```mermaid
graph TD
  ServerClient.Server[ServerClient.Server] -->|Listens on Port 4444| ServerClient.Client1[ServerClient.Client 1]
  ServerClient.Server -->|Broadcasts Messages| ServerClient.Client2[ServerClient.Client 2]
  ServerClient.Server -->|Manages Connections| Client3[ServerClient.Client 3]
  ServerClient.Client1 -->|Sends Messages| ServerClient.Server
  ServerClient.Client2 -->|Sends Messages| ServerClient.Server
  Client3 -->|Sends Messages| ServerClient.Server
```

### Key Components
- **`ServerClient.Server`**: Manages client connections and message broadcasting.
- **`ServerClient.Client`**: Connects to server and handles I/O.
- **`ServerClient.Broadcast`**: Thread task to relay messages between clients.
- **`ServerClient.RelyMessage`**: Handles client-side message input.

## 🚀 Getting Started

### Prerequisites
- Java 17+ JDK
- Basic terminal knowledge

### Installation & Usage

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/java-chat-app.git
   cd java-chat-app
   ```

2. **Compile the Code**
   ```bash
   javac ServerClient.Server.java ServerClient.Client.java ServerClient.Broadcast.java ServerClient.RelyMessage.java
   ```

3. **Run the ServerClient.Server** (In separate terminal)
   ```bash
   java ServerClient.Server
   ```

4. **Launch Clients** (In new terminals)
   ```bash
   java ServerClient.Client
   ```

5. **Start Chatting!**  
   Type messages in any client terminal - they'll appear in all others!

## 🧩 Code Structure

| Class           | Description                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| `ServerClient.Server`        | Creates server socket, manages client connections and output streams       |
| `ServerClient.Client`        | Connects to server, handles message input/output                           |
| `ServerClient.Broadcast`     | ServerClient.Server-side thread that broadcasts messages to all clients (except sender) |
| `ServerClient.RelyMessage`   | ServerClient.Client-side thread that sends user input to server                         |

## 🧠 Technical Highlights

```java
// ServerClient.Server-side broadcasting logic (ServerClient.Broadcast.java)
for (int i = 0; i < out.size(); i++) {
    if (i == index) continue;  // Skip sender
    out.get(i).println(message);
}
```

```java
// ServerClient.Client message input handling (ServerClient.RelyMessage.java)
message = name + ": " + scanner.nextLine();
out.println(message);
```

## 🤝 Contributing

Pull requests welcome! For major changes, please open an issue first.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📜 License

Distributed under the MIT License. See `LICENSE` for more information [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT).

---

Made by PiccioHub | [![GitHub](https://img.shields.io/badge/GitHub-Profile-blue)](https://github.com/Piccio-Code)


