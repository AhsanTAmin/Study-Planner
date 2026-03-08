# AI-inspired Smart Study Planner

This project was developed for **SWE7302 Advanced Software Development** as part of a Master's degree in Artificial Intelligence.

## Overview
The AI-inspired Smart Study Planner is a Java-based academic planning system designed to help students manage, prioritise, and schedule study tasks. The system supports modular task handling, interchangeable planning strategies, and an explainable recommendation component.

## Features
- Import study tasks from CSV input
- Create task objects through a factory-based design
- Save and load tasks through a DAO abstraction
- Generate study plans using multiple scheduling strategies
- Produce AI-inspired explainable task recommendations
- Build adaptive study plans based on recommendation scores

## Design Patterns Used
- **Factory Method** - centralises task creation
- **Facade** - coordinates the main planning workflow
- **Adapter** - converts CSV task data into domain objects
- **Strategy** - supports interchangeable scheduling algorithms
- **DAO** - separates persistence logic from business logic

## Project Structure
- `domain` - core entities such as Task, StudySession, and StudyPlan
- `factory` - object creation logic
- `strategy` - scheduling algorithms
- `dao` - persistence abstractions and implementations
- `adapter` - task import logic
- `facade` - workflow coordination
- `intelligence` - explainable recommendation and scoring logic

## Build Instructions
Run the following command to compile and test the project:

```bash
mvn clean test
