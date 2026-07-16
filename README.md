# AudioGPT

AudioGPT is a Spring Boot application that recommends and compares speakers using a hybrid AI architecture. Instead of
relying entirely on an LLM, the system combines PostgreSQL, Spring AI, vector search, and deterministic Java business
logic to deliver faster, more accurate, and explainable recommendations.

## What's new (Latest features)

- Vector embeddings and semantic search: speaker metadata is transformed into vector embeddings and stored in a vector
  index (pgvector). This enables semantic similarity search — retrieving speakers by meaning rather than keyword match.
- Embedding indexer & VectorStore: a pipeline that builds and refreshes embeddings for speakers, and exposes a
  VectorStore for fast nearest-neighbor retrieval.
- Improved recommendation pipeline: structured filtering in PostgreSQL + vector-based retrieval + Java business
  ranking → higher relevance and lower latency.
- Local LLM integration: prompts remain focused on natural-language explanations while deterministic logic is handled by
  the app for reproducibility and speed.

## Features

- Built AI-powered speaker recommendation and comparison engines using Spring AI and Ollama.
- Designed a hybrid recommendation pipeline where PostgreSQL performs structured filtering, Java applies
  business-specific ranking logic, and the LLM focuses solely on generating natural-language explanations.
- Semantic search using vector embeddings and pgvector for meaning-based retrieval of speakers.
- An embedding indexer that converts speaker metadata into embeddings for efficient similarity search and fast
  retrieval.
- Externalized prompts using a reusable Prompt Provider architecture for maintainability and versioning.
- Implemented semantic search using pgvector and Spring AI VectorStore to retrieve speakers based on meaning rather than
  keyword matching.
- Built a document indexing pipeline that converts speaker metadata into embeddings for efficient similarity search.
- Designed modular backend components following clean architecture principles, including Recommendation Engine,
  Comparison Engine, Semantic Search Service, Prompt Provider, and Embedding Indexer.
- Developed RESTful APIs with Spring Boot, Spring Data JPA, and PostgreSQL, using DTOs, mappers, and layered service
  architecture.
- Leveraged local LLMs through Ollama, enabling fully local inference without dependency on external AI APIs.

## Tech Stack

- **Java 21** - Latest LTS version
- **Spring Boot 4.1.0** - Web framework
- **Spring AI 2.0.0-RC2** - AI/ML integration with Ollama
- **Spring Data JPA** - ORM and database abstraction
- **PostgreSQL** - Relational database (with pgvector extension for vector search)
- **Lombok** - Reduce boilerplate code
- **Gradle** - Build automation

## Prerequisites

Before you begin, ensure you have the following installed:

- Java 21 or higher
- Gradle (or use the included Gradle wrapper)
- PostgreSQL 12 or higher (install and enable the `pgvector` extension for vector storage)
- Ollama with Qwen3:8B model ([Install Ollama](https://ollama.ai))

## Notes

- The embedding indexer and vector-based retrieval drastically improve search relevance for similarity-based queries (e.g., "find speakers that sound like X").
- If you deploy to a managed database or containerized environment, ensure the `pgvector` extension and the appropriate database tuning are configured.