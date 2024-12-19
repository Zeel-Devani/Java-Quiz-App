
package Controller;

import Model.Quiz;
import Model.Question;
import Model.mcqType;
import Model.trueFalseType;
import View.QuizGUI;
import java.util.*;
import java.io.*;

/**
 * The Application class is responsible for managing the quiz game.
 * It allows the user to choose between running the quiz in a console or a GUI mode.
 * The class loads quiz data from two text files (for multiple-choice and true/false questions),
 * presents the questions to the user, and calculates the score at the end of the quiz.
 */
public class Application {
    private Quiz quiz;

    /**
     * Constructs an Application instance with the provided file paths for MCQ and True/False questions.
     * This constructor loads questions from the specified files and initializes the quiz.
     *
     * @param mcqFile         The file path for the multiple-choice questions.
     * @param trueFalseFile   The file path for the true/false questions.
     */
    public Application(String mcqFile, String trueFalseFile) {
        List<Question> questions = loadQuestions(mcqFile, trueFalseFile);
        this.quiz = new Quiz(questions);
    }

    /**
     * Loads questions from the provided multiple-choice and true/false question files.
     * The questions are parsed from the files and added to a list.
     *
     * @param mcqFile         The file path for the multiple-choice questions.
     * @param trueFalseFile   The file path for the true/false questions.
     * @return A list of Question objects loaded from the given files.
     */
    private List<Question> loadQuestions(String mcqFile, String trueFalseFile) {
        List<Question> questions = new ArrayList<>();
        try {
            // Loads MCQ type questions
            Scanner mcqScan = new Scanner(new File(mcqFile));
            while (mcqScan.hasNextLine()) {
                String line = mcqScan.nextLine();
                String[] parts = line.split(";");
                String questionText = parts[0];
                List<String> options = Arrays.asList(parts[1].split(","));
                String answer = parts[2];
                questions.add(new mcqType(questionText, options, answer));
            }

            mcqScan.close();

            // Loads TrueFalse type of questions
            Scanner trueFalseScan = new Scanner(new File(trueFalseFile));
            while (trueFalseScan.hasNextLine()) {
                String line = trueFalseScan.nextLine();
                String[] parts = line.split(";");
                String questionText = parts[0];
                boolean answer = Boolean.parseBoolean(parts[1]);
                questions.add(new trueFalseType(questionText, answer));
            }
            trueFalseScan.close();

        }
        catch (FileNotFoundException e) {
            System.err.println("Error reading file: "+ e.getMessage()); // I changed this for better error handling
        }
        return questions;
    }

    /**
     * Starts the quiz in the console mode. The user is prompted to answer each question, and their choices are submitted.
     * At the end of the quiz, the user's score is displayed.
     */
    public void startQuiz(){
        Scanner sc = new Scanner(System.in);
        while (quiz.hasNext()){
            Question question = quiz.next();
            System.out.println("Question: "+ question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.println("Enter your choice: ");
            String choice = sc.nextLine();
            quiz.submitAnswer(question, choice);
        }

        System.out.println("Thank you for using Quiz!");
        System.out.println("Your score is: " + quiz.getScore());
        sc.close();
    }


    /**
     * Starts the quiz in the GUI mode. This opens a graphical user interface for the quiz.
     */
    public void startQuizGUI(){
        new QuizGUI(quiz);
    }
    /**
     * The main method that acts as the entry point for the application.
     * It prompts the user to choose between console or GUI mode and starts the quiz accordingly.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose mode of quiz:");
        System.out.println("1. Console");
        System.out.println("2. GUI");

        String choice = scanner.nextLine();

        Application app = new Application("src/Data/mcq.txt", "src/Data/trueFalse.txt");

        if (choice.equals("1")) {
            app.startQuiz();
        } else if (choice.equals("2")) {
            app.startQuizGUI();
        } else {
            System.out.println("Invalid choice. Please restart and select 1 or 2.");
        }

        scanner.close();
    }

}
