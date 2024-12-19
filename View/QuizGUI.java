/*
Name: Devani, Zeel Jitendrabhai
NSID: vrp432
Student Number: 11387749
Course: cmpt270-01 2024 Fall
 */

package View;

import Model.Quiz;
import Model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The QuizGUI class represents the GUI for the quiz application.
 * It presents the questions and options in a window, allowing the user to answer and submit their responses.
 * After the user finishes the quiz, it displays their score.
 */

public class QuizGUI extends JFrame {
    private Quiz quiz;
    private Question question;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton submitButton;

    /**
     * Constructs a QuizGUI instance and initializes the GUI components.
     * This constructor accepts a Quiz object to load and present the quiz questions.
     *
     * @param quiz The Quiz object containing the questions for the quiz.
     */
    public QuizGUI(Quiz quiz) {
        this.quiz = quiz;
        setupGUI();
        loadNextQuestion();
    }

    /**
     * Sets up the components for GUI
     * It configures the layout, initializes labels, radio buttons, and sets up the submit button.
     */
    private void setupGUI() {
        setTitle("Quiz by vrp432");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sets the application icon
        setIconImage(new ImageIcon("src/View/icon.jpg").getImage());

        // Create and add the question label to the top of the window
        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        // Create the panel for the options and initialize radio buttons
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        // Add radio buttons to the panel and group them
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
            optionPanel.add(optionButtons[i]);
        }

        add(optionPanel, BorderLayout.CENTER);

        // Create the submit button and add an action listener to handle submissions
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitAnswer();
            }
        });

        add(submitButton, BorderLayout.SOUTH);
        setVisible(true);

    }

    /**
     * Loads the next question from the quiz and updates the GUI with the new question and options.
     * If there are no more questions, it will show the user's score.
     */
    private void loadNextQuestion() {
        // Get the next question
        if (quiz.hasNext()) {
            question = quiz.next();
            questionLabel.setText(question.getQuestionText());
            String[] options = question.getOptions();

            // Updates radio buttons with new options
            for (int i = 0; i < options.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setVisible(true);
            }

            // Hides the unused radio buttons if there are less than 4 options
            for (int i = options.length; i < optionButtons.length; i++) {
                optionButtons[i].setVisible(false);
            }
            buttonGroup.clearSelection();
        } else {
            showScore();
        }
    }

    /**
     * Submits the selected answer for the current question and loads the next question.
     * If no option is selected, it prompts the user to select a valid option.
     */
    private void submitAnswer() {
        int idx = -1;

        // Finds the selected option
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                idx = i;
                break;
            }
        }

        // If no option is selected, a warning appears!!!
        if (idx == -1){
            JOptionPane.showMessageDialog(this, "Please select valid option");
        }

        // Submit the answer and load the next question
        quiz.submitAnswer(question, optionButtons[idx].getText());
        loadNextQuestion();

    }

    /**
     * Displays a message showing the user's score at the end of the quiz.
     * The application will then exit.
     */
    private void showScore() {
        JOptionPane.showMessageDialog(this, "Quiz Finished! Your score is: " + quiz.getScore());
        System.exit(0);
    }

}