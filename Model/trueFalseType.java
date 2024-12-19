package Model;


/**
 * The trueFalseType class represents a trueFalse question in the quiz.
 * It extends the Question class and includes a boolean value to store the correct answer.
 * The class provides methods to check if a given answer is correct and to retrieve the options for the question.
 */
public class trueFalseType extends Question {
    private boolean correctAnswer;


    /**
     * Constructs a trueFalse question with the specified question text and the correct answer.
     *
     * @param questionText The text of the question.
     * @param correctAnswer The correct answer for the question (true or false).
     */
    public trueFalseType(String questionText, boolean correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    /**
     * Checks if the provided answer is correct for this trueFalse question.
     * The answer can be provided as either a number ("1" for true, "2" for false)
     * or as a string ("true" or "false").
     *
     * @param answer The answer provided by the user, either as a number or a boolean string.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean isCorrect(String answer) {
        // Try to interpret the answer as an index (1 or 2)
        try {
            int selectedAnswerIndex = Integer.parseInt(answer.trim());
            if (selectedAnswerIndex == 1) {
                return correctAnswer;  // "true" is correct if answer is true
            } else if (selectedAnswerIndex == 2) {
                return !correctAnswer; // "false" is correct if answer is false
            }
        } catch (NumberFormatException e) {
            // If it's not a number, directly compare with the boolean answer
            return Boolean.parseBoolean(answer.trim().toLowerCase()) == correctAnswer;
        }
        return false;
    }

    /**
     * Returns the available options for the trueFalse question.
     * The options are always true and false.
     *
     * @return A string array containing the two options: "true" and "false".
     */
    public String[] getOptions() {
        return new String[]{"true", "false"};
    }
}
