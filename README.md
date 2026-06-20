# AudioGPT

AudioGPT is a Spring Boot application that leverages local LLM models (via Ollama) to provide intelligent audio/speaker recommendations and comparisons. Built with Spring AI, PostgreSQL, and modern Java practices.

## Features

- **Speaker Recommendations**: Get AI-powered recommendations based on your preferences
- **Speaker Comparison**: Compare multiple speakers and get detailed analysis
- **Speaker Management**: Browse and search through a database of speakers
- **Local AI Model**: Uses Ollama with Qwen3:8B model for privacy-first inference
- **REST API**: Comprehensive REST endpoints for easy integration
- **Database Persistence**: PostgreSQL integration for data management

## Tech Stack

- **Java 21** - Latest LTS version
- **Spring Boot 4.1.0** - Web framework
- **Spring AI 2.0.0-RC2** - AI/ML integration with Ollama
- **Spring Data JPA** - ORM and database abstraction
- **PostgreSQL** - Relational database
- **Lombok** - Reduce boilerplate code
- **Gradle** - Build automation

## Prerequisites

Before you begin, ensure you have the following installed:

- Java 21 or higher
- Gradle (or use the included Gradle wrapper)
- PostgreSQL 12 or higher
- Ollama with Qwen3:8B model ([Install Ollama](https://ollama.ai))
