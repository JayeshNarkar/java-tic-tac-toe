import javax.swing.*; //used for the actual gui interface
import java.awt.*; // used for setting the layout of the gui
import java.awt.event.*; // used for the action listener
import java.util.ArrayList; // used for the array list
import java.util.Random; // used for the random number generator
import java.util.List; // used for the list

public class MicroProject {
    private char currentSymbol;
    private JButton[] buttons;
    private Timer timer;
    private JFrame frame;

    public MicroProject() {
        currentSymbol = 'X';
        buttons = new JButton[9];
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botMove();
                checkForWin();
                switchSymbol();
                enableButtons();
                frame.setTitle("Tic Tac Toe");
                timer.stop();
            }
        });
    }

    public void startGame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        frame.add(panel, BorderLayout.CENTER);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            Dimension buttonDimension = new Dimension(300, 300);
            buttons[i].setPreferredSize(buttonDimension);
            buttons[i].setText("");
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), buttons[i].getFont().getStyle(),
                    buttons[i].getFont().getSize() * 2));
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();
                    buttonClicked.setText(String.valueOf(currentSymbol));
                    buttonClicked.setEnabled(false);
                    checkForWin();
                    switchSymbol();
                    if (currentSymbol == 'O') {
                        disableButtons();
                        frame.setTitle("Tic Tac Toe (bot is thinking)");
                        timer.start();
                    }
                }
            });
            panel.add(buttons[i]);
        }

        frame.pack();
        frame.setVisible(true);
    }

    private void switchSymbol() {
        currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
    }

    private void checkForWin() {
        for (int i = 0; i < 9; i += 3)
            if (checkLine(i, i + 1, i + 2))
                endGame(buttons[i].getText());
        for (int i = 0; i < 3; ++i)
            if (checkLine(i, i + 3, i + 6))
                endGame(buttons[i].getText());
        if (checkLine(0, 4, 8) || checkLine(2, 4, 6))
            endGame(buttons[4].getText());
    }

    private boolean checkLine(int a, int b, int c) {
        return buttons[a].getText().equals(buttons[b].getText()) &&
                buttons[b].getText().equals(buttons[c].getText()) &&
                !buttons[a].getText().equals("");
    }

    private void endGame(String winner) {
        JOptionPane.showMessageDialog(null,
                String.format("%s wins", winner),
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void botMove() {
        Random random = new Random();
        List<JButton> availableButtons = new ArrayList<>();

        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                availableButtons.add(button);
            }
        }

        if (!availableButtons.isEmpty()) {
            JButton selectedButton = availableButtons.get(random.nextInt(availableButtons.size()));
            selectedButton.setText(String.valueOf(currentSymbol));
            selectedButton.setEnabled(false);
        }
    }

    private void disableButtons() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                button.setEnabled(false);
            }
        }
    }

    private void enableButtons() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                button.setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        new MicroProject().startGame();
    }
}