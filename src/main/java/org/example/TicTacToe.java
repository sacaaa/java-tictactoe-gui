package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicTacToe implements ActionListener {
    private JFrame frame = new JFrame("Tic Tac Toe");
    private List<JButton> buttons = new ArrayList<>();
    private Color backGroundColor = new Color(32, 45, 59);
    private Color winColor = new Color(106, 163, 80);
    private char currentPlayer;
    private boolean isGameRunning = false;

    public TicTacToe() {
        // Assets
        Font font = new Font("Poppins", Font.PLAIN, 25);

        // Frame
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backGroundColor);

        // Title
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(font);
        titleLabel.setForeground(new Color(255, 255, 255));

        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.gridy = 0;
        titleGbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(titleLabel, titleGbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.setPreferredSize(new Dimension(300, 300));

        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 1;

        for (int i = 0; i < 9; ++i) {
            JButton button = new JButton();
            button.setFont(font);
            button.setFocusable(false);
            button.setBackground(backGroundColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(true);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.addActionListener(this);
            buttons.add(button);
            buttonPanel.add(button);
        }

        mainPanel.add(buttonPanel, buttonGbc);

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 50));
        resetButton.setFont(font);
        resetButton.setFocusable(false);
        resetButton.setBackground(backGroundColor);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(true);
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resetButton.addActionListener(e -> {
            resetGame();
        });

        GridBagConstraints resetButtonGbc = new GridBagConstraints();
        resetButtonGbc.gridx = 0;
        resetButtonGbc.gridy = 2;
        resetButtonGbc.insets = new Insets(20, 0, 0, 0);
        mainPanel.add(resetButton, resetButtonGbc);

        // Show
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        setCurrentPlayer('X');
        setGameRunning(true);
    }

    private void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setGameRunning(boolean gameRunning) {
        this.isGameRunning = gameRunning;
    }

    private void switchPlayer() {
        if (currentPlayer == 'X') {
            setCurrentPlayer('O');
        } else {
            setCurrentPlayer('X');
        }
    }

    private boolean checkForWinner() {
        String _currentPlayer = String.valueOf(currentPlayer);

        if (Objects.equals(buttons.get(0).getText(), _currentPlayer) && Objects.equals(buttons.get(1).getText(), _currentPlayer) && Objects.equals(buttons.get(2).getText(), _currentPlayer)) {
            // Row 1
            for (int i = 0; i < 3; ++i) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        } else if (Objects.equals(buttons.get(3).getText(), _currentPlayer) && Objects.equals(buttons.get(4).getText(), _currentPlayer) && Objects.equals(buttons.get(5).getText(), _currentPlayer)) {
            // Row 2
            for (int i = 3; i < 6; ++i) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        } else if (Objects.equals(buttons.get(6).getText(), _currentPlayer) && Objects.equals(buttons.get(7).getText(), _currentPlayer) && Objects.equals(buttons.get(8).getText(), _currentPlayer)) {
            // Row 3
            for (int i = 6; i < 9; ++i) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        } else if (Objects.equals(buttons.get(0).getText(), _currentPlayer) && Objects.equals(buttons.get(3).getText(), _currentPlayer) && Objects.equals(buttons.get(6).getText(), _currentPlayer)) {
            // Col 1
            for (int i = 0; i < 7; i+=3) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        } else if (Objects.equals(buttons.get(1).getText(), _currentPlayer) && Objects.equals(buttons.get(4).getText(), _currentPlayer) && Objects.equals(buttons.get(7).getText(), _currentPlayer)) {
            // Col 2
            for (int i = 1; i < 8; i+=3) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        } else if (Objects.equals(buttons.get(2).getText(), _currentPlayer) && Objects.equals(buttons.get(5).getText(), _currentPlayer) && Objects.equals(buttons.get(8).getText(), _currentPlayer)) {
            // Col 3
            for (int i = 2; i < 9; i+=3) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        } else if (Objects.equals(buttons.get(0).getText(), _currentPlayer) && Objects.equals(buttons.get(4).getText(), _currentPlayer) && Objects.equals(buttons.get(8).getText(), _currentPlayer)) {
            // Diagonal right
            for (int i = 0; i < 9; i+=4) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        } else if (Objects.equals(buttons.get(6).getText(), _currentPlayer) && Objects.equals(buttons.get(4).getText(), _currentPlayer) && Objects.equals(buttons.get(2).getText(), _currentPlayer)) {
            // Diagonal left
            for (int i = 2; i < 7; i+=2) {
                buttons.get(i).setBackground(winColor);
            }

            return true;
        }

        return false; // No winner yet
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText() == "") {
                return false;
            }
        }

        return true;
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setBackground(backGroundColor);
            setGameRunning(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameRunning) { return; }

        for (JButton button : buttons) {
            if (e.getSource() == button) {
                if (button.getText() == "") {
                    button.setText(String.valueOf(currentPlayer));

                    if (checkForWinner()) {
                        JOptionPane.showMessageDialog(frame, "Winner is: " + currentPlayer);
                        setGameRunning(false);
                    } else if (isBoardFull()) {
                        JOptionPane.showMessageDialog(frame, "Draw.");
                        setGameRunning(false);
                    } else {
                        switchPlayer();
                    }
                }
            }
        }
    }
}
