/*
Name: Devani, Zeel Jitendrabhai
NSID: vrp432
Student Number: 11387749
Course: cmpt270-01 2024 Fall
 */


package Model;

/**
 * The Question class is an abstract class which represents a general question in the quiz.
 * It defines the essential properties and methods that any specific question type (e.g., multiple choice, true/false) must implement.
 * The class includes the question text and provides methods to retrieve the question text, check if an answer is correct, and get the available options.
 */
public abstract class Question {
    protected String questionText;

    /**
     * Constructs a question with the specified question text.
     * This constructor is used by subclasses to initialize the question's text.
     *
     * @param questionText The text of the question.
     */
    public Question(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Returns the text of the question.
     *
     * @return The question text.
     */
    public String getQuestionText() {
        return questionText;
    }


    /**
     * Abstract method to check whether the user's answer is correct.
     * Subclasses must implement this method to define how the answer is validated for the specific question type.
     *
     * @param userAnswer The user's answer to the question.
     * @return true if the answer is correct, false otherwise.
     */
    public abstract boolean isCorrect(String userAnswer);

    /**
     * Abstract method to return the available options for answering the question.
     * Subclasses must implement this method to provide the answer choices for the specific question type.
     *
     * @return An array of answer options for the question.
     */
    public abstract String[] getOptions();
}
