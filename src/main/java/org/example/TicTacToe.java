package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TicTacToe implements ActionListener {
    private JFrame frame = new JFrame("Tic Tac Toe");
    private JPanel mainPanel;

    private List<JButton> buttons = new ArrayList<>();

    private Color backGroundColor = new Color(32, 45, 59);
    private Color winColor = new Color(106, 163, 80);

    private char currentPlayer;
    private boolean isGameRunning = false;
    private String gameType;

    // Assets
    private Font font = new Font("Poppins", Font.PLAIN, 25);
    private final int BUTTON_WIDTH = 250;
    private final int BUTTON_HEIGTH = 50;

    public TicTacToe() {
        // Frame
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        mainPanel = createPanel("home_page");

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createPanel(String type) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(backGroundColor);

        // Title
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(font);
        titleLabel.setForeground(Color.WHITE);

        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.gridy = 0;
        titleGbc.insets = new Insets(0, 0, 20, 0);
        panel.add(titleLabel, titleGbc);

        switch (type) {
            case "home_page" -> {
                // Single player button
                JButton singlePlayerButton = new JButton("SinglePlayer");;
                singlePlayerButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
                singlePlayerButton.setFont(font);
                singlePlayerButton.setFocusable(false);
                singlePlayerButton.setBackground(backGroundColor);
                singlePlayerButton.setForeground(Color.WHITE);
                singlePlayerButton.setFocusPainted(true);
                singlePlayerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                singlePlayerButton.addActionListener(e -> {
                    onClick("singleplayer");
                });

                GridBagConstraints singlePlayerButtonGbc = new GridBagConstraints();
                singlePlayerButtonGbc.gridx = 0;
                singlePlayerButtonGbc.gridy = 1;
                singlePlayerButtonGbc.insets = new Insets(20, 0, 0, BUTTON_WIDTH + 10);

                // Multiplayer button
                JButton multiPlayerButton = new JButton("MultiPlayer");;
                multiPlayerButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
                multiPlayerButton.setFont(font);
                multiPlayerButton.setFocusable(false);
                multiPlayerButton.setBackground(backGroundColor);
                multiPlayerButton.setForeground(Color.WHITE);
                multiPlayerButton.setFocusPainted(true);
                multiPlayerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                multiPlayerButton.addActionListener(e -> {
                    onClick("multiplayer");
                });

                GridBagConstraints multiPlayerButtonGbc = new GridBagConstraints();
                multiPlayerButtonGbc.gridx = 0;
                multiPlayerButtonGbc.gridy = 1;
                multiPlayerButtonGbc.insets = new Insets(20, BUTTON_WIDTH + 10, 0, 0);

                panel.add(titleLabel, titleGbc);
                panel.add(singlePlayerButton, singlePlayerButtonGbc);
                panel.add(multiPlayerButton, multiPlayerButtonGbc);

                break;
            }
            case "game_page" -> {
                setCurrentPlayer('X');
                setGameRunning(true);

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

                panel.add(buttonPanel, buttonGbc);

                // Back button
                JButton backButton = new JButton("Back");
                backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
                backButton.setFont(font);
                backButton.setFocusable(false);
                backButton.setBackground(backGroundColor);
                backButton.setForeground(Color.WHITE);
                backButton.setFocusPainted(true);
                backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                backButton.addActionListener(e -> {
                    onClick("back_to_home");
                });

                GridBagConstraints backButtonGbc = new GridBagConstraints();
                backButtonGbc.gridx = 0;
                backButtonGbc.gridy = 2;
                backButtonGbc.insets = new Insets(25, 0, 0, BUTTON_WIDTH + 10);

                // Reset button
                JButton resetButton = new JButton("Reset");
                resetButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
                resetButton.setFont(font);
                resetButton.setFocusable(false);
                resetButton.setBackground(backGroundColor);
                resetButton.setForeground(Color.WHITE);
                resetButton.setFocusPainted(true);
                resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                resetButton.addActionListener(e -> {
                    onClick("reset_game");
                });

                GridBagConstraints resetButtonGbc = new GridBagConstraints();
                resetButtonGbc.gridx = 0;
                resetButtonGbc.gridy = 2;
                resetButtonGbc.insets = new Insets(25, BUTTON_WIDTH + 10, 0, 0);

                panel.add(backButton, backButtonGbc);
                panel.add(resetButton, resetButtonGbc);

                break;
            }
        }

        return panel;
    }

//    private JPanel createHomePage() {
//        JPanel homePanel = new JPanel(new GridBagLayout());
//        homePanel.setBackground(backGroundColor);
//
//
//        // Title
//        JLabel titleLabel = new JLabel("Tic Tac Toe");
//        titleLabel.setFont(font);
//        titleLabel.setForeground(Color.WHITE);
//
//        GridBagConstraints titleGbc = new GridBagConstraints();
//        titleGbc.gridx = 0;
//        titleGbc.gridy = 0;
//        titleGbc.insets = new Insets(0, 0, 20, 0);
//
//        // SinglePlayer button
//
//
//        homePanel.add(titleLabel, titleGbc);
//        homePanel.add(singlePlayerButton, singlePlayerButtonGbc);
//        homePanel.add(multiPlayerButton, multiPlayerButtonGbc);
//
//        return homePanel;
//    }

//    private JPanel createMultiPlayer() {
//        setCurrentPlayer('X');
//        setGameRunning(true);
//
//        // MultiPlayer panel
//        JPanel multiPlayerPanel = new JPanel(new GridBagLayout());
//        multiPlayerPanel.setBackground(backGroundColor);
//
//        // Title
//        JLabel titleLabel = new JLabel("Tic Tac Toe");
//        titleLabel.setFont(font);
//        titleLabel.setForeground(Color.WHITE);
//
//        GridBagConstraints titleGbc = new GridBagConstraints();
//        titleGbc.gridx = 0;
//        titleGbc.gridy = 0;
//        titleGbc.insets = new Insets(0, 0, 20, 0);
//        multiPlayerPanel.add(titleLabel, titleGbc);
//
//        // Buttons
//        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
//        buttonPanel.setPreferredSize(new Dimension(300, 300));
//
//        GridBagConstraints buttonGbc = new GridBagConstraints();
//        buttonGbc.gridx = 0;
//        buttonGbc.gridy = 1;
//
//        for (int i = 0; i < 9; ++i) {
//            JButton button = new JButton();
//            button.setFont(font);
//            button.setFocusable(false);
//            button.setBackground(backGroundColor);
//            button.setForeground(Color.WHITE);
//            button.setFocusPainted(true);
//            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            button.addActionListener(this);
//            buttons.add(button);
//            buttonPanel.add(button);
//        }
//
//        multiPlayerPanel.add(buttonPanel, buttonGbc);
//
//        // Back button
//        JButton backButton = new JButton("Back");
//        backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
//        backButton.setFont(font);
//        backButton.setFocusable(false);
//        backButton.setBackground(backGroundColor);
//        backButton.setForeground(Color.WHITE);
//        backButton.setFocusPainted(true);
//        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        backButton.addActionListener(e -> {
//            onClick("back_to_home");
//        });
//
//        GridBagConstraints backButtonGbc = new GridBagConstraints();
//        backButtonGbc.gridx = 0;
//        backButtonGbc.gridy = 2;
//        backButtonGbc.insets = new Insets(25, 0, 0, BUTTON_WIDTH + 10);
//
//        // Reset button
//        JButton resetButton = new JButton("Reset");
//        resetButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
//        resetButton.setFont(font);
//        resetButton.setFocusable(false);
//        resetButton.setBackground(backGroundColor);
//        resetButton.setForeground(Color.WHITE);
//        resetButton.setFocusPainted(true);
//        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        resetButton.addActionListener(e -> {
//            onClick("reset_game");
//        });
//
//        GridBagConstraints resetButtonGbc = new GridBagConstraints();
//        resetButtonGbc.gridx = 0;
//        resetButtonGbc.gridy = 2;
//        resetButtonGbc.insets = new Insets(25, BUTTON_WIDTH + 10, 0, 0);
//
//
//        multiPlayerPanel.add(backButton, backButtonGbc);
//        multiPlayerPanel.add(resetButton, resetButtonGbc);
//
//        return multiPlayerPanel;
//    }
//
//    private JPanel createSinglePlayer() {
//        setCurrentPlayer('X');
//        setGameRunning(true);
//
//        // SinglePlayer panel
//        JPanel singlePlayerPanel = new JPanel(new GridBagLayout());
//        singlePlayerPanel.setBackground(backGroundColor);
//
//        // Title
//        JLabel titleLabel = new JLabel("Tic Tac Toe");
//        titleLabel.setFont(font);
//        titleLabel.setForeground(Color.WHITE);
//
//        GridBagConstraints titleGbc = new GridBagConstraints();
//        titleGbc.gridx = 0;
//        titleGbc.gridy = 0;
//        titleGbc.insets = new Insets(0, 0, 20, 0);
//        singlePlayerPanel.add(titleLabel, titleGbc);
//
//        // Buttons
//        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
//        buttonPanel.setPreferredSize(new Dimension(300, 300));
//
//        GridBagConstraints buttonGbc = new GridBagConstraints();
//        buttonGbc.gridx = 0;
//        buttonGbc.gridy = 1;
//
//        for (int i = 0; i < 9; ++i) {
//            JButton button = new JButton();
//            button.setFont(font);
//            button.setFocusable(false);
//            button.setBackground(backGroundColor);
//            button.setForeground(Color.WHITE);
//            button.setFocusPainted(true);
//            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            button.addActionListener(this);
//            buttons.add(button);
//            buttonPanel.add(button);
//        }
//
//        singlePlayerPanel.add(buttonPanel, buttonGbc);
//
//        // Back button
//        JButton backButton = new JButton("Back");
//        backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
//        backButton.setFont(font);
//        backButton.setFocusable(false);
//        backButton.setBackground(backGroundColor);
//        backButton.setForeground(Color.WHITE);
//        backButton.setFocusPainted(true);
//        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        backButton.addActionListener(e -> {
//            onClick("back_to_home");
//        });
//
//        GridBagConstraints backButtonGbc = new GridBagConstraints();
//        backButtonGbc.gridx = 0;
//        backButtonGbc.gridy = 2;
//        backButtonGbc.insets = new Insets(25, 0, 0, BUTTON_WIDTH + 10);
//
//        // Reset button
//        JButton resetButton = new JButton("Reset");
//        resetButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGTH));
//        resetButton.setFont(font);
//        resetButton.setFocusable(false);
//        resetButton.setBackground(backGroundColor);
//        resetButton.setForeground(Color.WHITE);
//        resetButton.setFocusPainted(true);
//        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        resetButton.addActionListener(e -> {
//            onClick("reset_game");
//        });
//
//        GridBagConstraints resetButtonGbc = new GridBagConstraints();
//        resetButtonGbc.gridx = 0;
//        resetButtonGbc.gridy = 2;
//        resetButtonGbc.insets = new Insets(25, BUTTON_WIDTH + 10, 0, 0);
//
//
//        singlePlayerPanel.add(backButton, backButtonGbc);
//        singlePlayerPanel.add(resetButton, resetButtonGbc);
//
//        return singlePlayerPanel;
//    }

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

    private void onClick(String type) {
        switch (type) {
            case "back_to_home" -> {
                mainPanel = createPanel("home_page");
                frame.setContentPane(mainPanel);
                frame.revalidate();
                frame.repaint();
                break;
            }
            case "reset_game" -> {
                for (JButton button : buttons) {
                    button.setText("");
                    button.setBackground(backGroundColor);
                    setGameRunning(true);
                }
                setCurrentPlayer('X');
                break;
            }
            case "multiplayer" -> {
                buttons.clear();
                mainPanel = createPanel("game_page");
                frame.setContentPane(mainPanel);
                frame.revalidate();
                frame.repaint();
                gameType = "multiplayer";
                break;
            }
            case "singleplayer" -> {
                buttons.clear();
                mainPanel = createPanel("game_page");
                frame.setContentPane(mainPanel);
                frame.revalidate();
                frame.repaint();
                gameType = "singleplayer";
                break;
            }
        }
    }

    private int calculateNextMove() {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < buttons.size(); ++i) {
            if (Objects.equals(buttons.get(i).getText(), "")) {
                tempList.add(i);
            }
        }

        Random random = new Random();
        return tempList.get(random.nextInt(tempList.size()));
    }

    private void checkGameEnd() {
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

    private void setBoardValue(JButton button) {
        button.setText(String.valueOf(currentPlayer));
        button.setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);

        checkGameEnd();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameRunning) { return; }
        if (Objects.equals(gameType, "singleplayer") && currentPlayer != 'X') { return; }

        for (JButton button : buttons) {
            if (e.getSource() == button) {
                if (Objects.equals(button.getText(), "")) {
                    setBoardValue(button);

                    if (Objects.equals(gameType, "singleplayer") && isGameRunning) {
                        currentPlayer = 'O';
                        setBoardValue(buttons.get(calculateNextMove()));
                    }
                }
            }
        }
    }
}