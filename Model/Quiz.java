
package Model;

import java.util.Collections;
import java.util.List;

/**
 * The Quiz class manages the collection of questions and the scoring system for the quiz.
 * It provides methods to navigate through questions, check answers, and track the user's score.
 * The class is initialized with a list of questions, shuffles them, and limits the quiz to 10 questions.
 */
public class Quiz {
    private List<Question> questions;
    private int questionIDX = 0;
    private int score = 0;

    /**
     * Constructs a Quiz instance with a list of questions.
     * The questions are shuffled and the list is limited to the first 10 questions.
     *
     * @param questions A list of questions to be included in the quiz.
     */
    public Quiz(List<Question> questions) {
        this.questions = questions;
        Collections.shuffle(this.questions);
        if (this.questions.size() > 1) {
            this.questions = this.questions.subList(0, 10); // Limit to 10 question per quiz
        }
    }

    /**
     * Checks if there are more questions available in the quiz.
     *
     * @return true if there are more questions to be asked, false otherwise.
     */
    public boolean hasNext() {
        return this.questionIDX < this.questions.size();
    }

    /**
     * Returns the next question in the quiz, if available.
     * The method increments the question index after retrieving the question.
     *
     * @return The next Question object or null if there are no more questions.
     */
    public Question next() {
        if (hasNext()) {
            return this.questions.get(this.questionIDX++);
        }
        return null;
    }

    /**
     * Submits the user's answer to the current question.
     * If the answer is correct, the user's score is incremented.
     *
     * @param question The question that the user is answering.
     * @param answer   The user's selected answer.
     */
    public void submitAnswer(Question question, String answer) {
        if (question.isCorrect(answer)) {
            score++;
        }
    }

    /**
     * Returns the current score of the user.
     *
     * @return The user's score.
     */
    public int getScore() {
        return score;
    }


    /** NOT IN USE!!
     * Only here for future use.
     * Returns the current index of the question being asked.
     *
     * @return The index of the next question.
     */
    public int getQuestionIDX() {
        return questionIDX;
    }

    /** NOT IN USE!!!
     * Only here for future use.
     * Returns the total number of questions in the quiz.
     *
     * @return The total number of questions in the quiz.
     */
    public int getTotalQuestions() {
        return this.questions.size();
    }


}
