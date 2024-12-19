package Model;

import java.util.List;

/**
 * The mcqType class represents a multiple choice question in the quiz.
 * It extends the Question class and includes a list of options and the correct answer.
 * The class provides methods to check if a given answer is correct and to retrieve the available options.
 */
public class mcqType extends Question {
    private List<String> options;
    private String correctAnswer;

    /**
     * Constructs a mcq with the specified question text,
     * a list of options, and the correct answer.
     *
     * @param questionText The text of the question.
     * @param options The list of answer options for the question.
     * @param correctAnswer The correct answer for the question.
     */
    public mcqType(String questionText, List<String> options, String correctAnswer) {
        super(questionText);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Returns the index of the correct answer in the options list.
     * If the correct answer is found, the method returns the index.
     * If not found, it returns -1 (should not happen with properly formed questions).
     *
     * @return The index of the correct answer, or -1 if not found.
     */
    // Method to get the correct answer index
    private int getCorrectAnswerIndex() {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).trim().equalsIgnoreCase(correctAnswer.trim())) {
                return i;
            }
        }
        return -1; // Return -1 if not found (should not happen in a question which follows provided format)
    }

    /**
     * Checks if the provided answer is correct for this mcq question.
     * The answer can be provided either as an index (e.g., "1" or "2.") or as a direct text answer.
     *
     * @param answer The answer provided by the user, either as an index or a string.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean isCorrect(String answer) {
        // Try to interpret the answer as an index (like "2" or "2.")
        try {
            int selectedAnswerIndex = Integer.parseInt(answer.split("\\.")[0].trim()) - 1;
            return selectedAnswerIndex == getCorrectAnswerIndex();
        } catch (NumberFormatException e) {
            // If it's not a number, compare directly to the correct answer text
            return correctAnswer.trim().equalsIgnoreCase(answer.trim());
        }
    }

    /**
     * Returns the available options for this mcq question.
     * The options are returned as an array of strings.
     *
     * @return A string array containing the options for the question.
     */
    public String[] getOptions() {
        return options.toArray(new String[0]);
    }
}
