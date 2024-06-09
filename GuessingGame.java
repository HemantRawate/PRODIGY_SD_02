import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private int targetNumber;
    private int numberOfAttempts;

    public GuessingGame() {
        createView();

        setTitle("Guessing Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        initializeGame();
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel instructionsLabel = new JLabel("Guess the number between 1 and 100:");
        panel.add(instructionsLabel);

        guessInput = new JTextField();
        panel.add(guessInput);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonActionListener());
        panel.add(guessButton);

        feedbackLabel = new JLabel("Enter your guess and click 'Guess'");
        panel.add(feedbackLabel);

        attemptsLabel = new JLabel("Attempts: 0");
        panel.add(attemptsLabel);
    }

    private void initializeGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        numberOfAttempts = 0;
    }

    private class GuessButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessInput.getText());
                numberOfAttempts++;

                if (guess < targetNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (guess > targetNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Congratulations! You've guessed the number.");
                    guessButton.setEnabled(false);
                }

                attemptsLabel.setText("Attempts: " + numberOfAttempts);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GuessingGame().setVisible(true);
        });
    }
}
