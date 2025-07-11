# 🔐 Stream Cipher

> Lightweight Java implementation of a stream cipher encryption system.

🧪 This project demonstrates simple symmetric encryption using a custom stream cipher written in Java.  
It includes a basic GUI (Swing-based) for testing and visualizing the encryption/decryption process in real time.

---

## ✨ Features

- 📡 Custom stream cipher logic
- 🧬 Real-time text encryption and decryption
- 🖥️ Minimal Swing GUI interface
- 🗜️ Byte-level data manipulation
- 🔁 Symmetric key usage

---

## 🚀 Getting Started

### Prerequisites

- Java 8+ (tested on JDK 17)
- IntelliJ IDEA (recommended, but not required)

### Run locally

## 🧠 How It Works

Stream ciphers are a form of symmetric encryption that operate on plaintext **one byte (or bit) at a time**, combining it with a **keystream** using a reversible operation — typically the **XOR** (exclusive OR).

This project demonstrates that principle through a custom-built cipher using Java.

### 🔑 Core Principles

1. **Keystream generation**:
   - A pseudo-random sequence of bytes is generated from a user-provided seed (key).
   - This sequence is not truly random, but deterministic — the same seed will always produce the same sequence.
   - In this implementation, the seed is used to initialize a `Random` object from Java's standard library.

2. **Encryption (encoding)**:
   - Each character of the plaintext is converted to a byte.
   - That byte is then XOR-ed (`^`) with the next byte in the keystream.
   - The result is an encrypted byte that appears random but can be reversed.

3. **Decryption (decoding)**:
   - The same keystream is re-generated using the same seed/key.
   - Each encrypted byte is XOR-ed again with the same keystream byte — thanks to XOR's symmetric property:
     ```
     (plaintext ^ key) ^ key = plaintext
     ```

### 💡 Example

Assume we want to encrypt letter `A`, which is byte `65`, using a keystream byte `42`.

65 (A) ^ 42 = 107 → encrypted byte
107 ^ 42 = 65 → original byte (A)


Simple and elegant.

---

### 🧬 Summary

- ✔️ Lightweight and fast (no heavy crypto libraries)
- ✔️ Fully symmetric (same function for encryption and decryption)
- ✔️ Suitable for small-scale learning, demos, and experimental encryption

⚠️ **Note**: This implementation is for educational purposes only.  
It does **not** provide strong cryptographic guarantees and should **not** be used for securing sensitive data in real-world applications.

