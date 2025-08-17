# FizzBuzz (Android) — Clean Architecture + MVI

> **Note:** A **Jetpack Compose** version will be implemented when there’s time, in a **separate repository**.

A small but production-grade Android app that solves **FizzBuzz** while showcasing **Clean Architecture** and the **MVI (Model–View–Intent)** pattern in Kotlin.  
The goal is to provide a clear, testable, and easily extensible codebase you can use as a template for real projects.

---

## Why this repo?

- **Pedagogical**: Demonstrates boundaries between `presentation`, `domain`, and `data`.
- **Scalable**: MVI with unidirectional data flow makes side-effects and UI states explicit.
- **Testable**: Business rules live in `domain` and are framework-free.
