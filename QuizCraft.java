package com.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
	String questionText;
	String[] options;
	char correctAnswer;

	public Question(String questionText, String[] options, char correctAnswer) {
		this.questionText = questionText;
		this.options = options;
		this.correctAnswer = correctAnswer;
	}

	public boolean isCorrect(char answer) {
		return Character.toUpperCase(answer) == correctAnswer;
	}

	public void display() {
		System.out.println("\n" + questionText);
		for (String option : options) {
			System.out.println(option);
		}
	}
}

class Quiz {
	private List<Question> questions = new ArrayList<>();
	private int score = 0;

	public void loadQuestions(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\\|");
				String questionText = parts[0];
				String[] options = { parts[1], parts[2], parts[3], parts[4] };
				char correctAnswer = parts[5].charAt(0);

				questions.add(new Question(questionText, options, correctAnswer));
			}

		} catch (IOException e) {
			System.out.println("Error loading questions.");
			e.printStackTrace();
		}
	}

	public void start() {
		Scanner sc = new Scanner(System.in);

		for (Question q : questions) {
			q.display();

			System.out.print("Enter your answer (A/B/C/D): ");
			char userAnswer;

			try {
				userAnswer = sc.next().charAt(0);

				if (q.isCorrect(userAnswer)) {
					System.out.println("Correct!");
					score++;
				} else {
					System.out.println("Wrong! Correct answer: " + q.correctAnswer);
				}

			} catch (Exception e) {
				System.out.println("Invalid input. Skipping question.");
			}
		}

		System.out.println("\nQuiz Completed!");
		System.out.println("Your Score: " + score + "/" + questions.size());
	}
}

public class QuizCraft {
	public static void main(String[] args) {
		Quiz quiz = new Quiz();

		quiz.loadQuestions("C:\\Users\\hp\\MK\\questions.txt");//path
		quiz.start();
	}
}
