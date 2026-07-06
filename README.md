# AudioGPT

AudioGPT is a Spring Boot application that recommends and compares speakers using a hybrid AI architecture. Instead of relying entirely on an LLM, the system combines PostgreSQL, Spring AI, vector search, and deterministic Java business logic to deliver faster, more accurate, and explainable recommendations.

## Features

Built AI-powered speaker recommendation and comparison engines using Spring AI and Ollama.
Designed a hybrid recommendation pipeline where PostgreSQL performs structured filtering, Java applies business-specific ranking logic, and the LLM focuses solely on generating natural-language explanations.
Reduced recommendation latency by approximately 67% (90s → 30s) and comparison latency by approximately 41% (37s → 22s) by moving deterministic decision-making from the LLM into the application layer.
Externalized prompts using a reusable Prompt Provider architecture for maintainability and versioning.
Implemented semantic search using pgvector and Spring AI VectorStore to retrieve speakers based on meaning rather than keyword matching.
Built a document indexing pipeline that converts speaker metadata into embeddings for efficient similarity search.
Designed modular backend components following clean architecture principles, including Recommendation Engine, Comparison Engine, Semantic Search Service, Prompt Provider, and Embedding Indexer.
Developed RESTful APIs with Spring Boot, Spring Data JPA, and PostgreSQL, using DTOs, mappers, and layered service architecture.
Leveraged local LLMs through Ollama, enabling fully local inference without dependency on external AI APIs.

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
