package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {


    public GridPane gridPane;
    private boolean isPlayerX = true; // Track the current player

    @FXML private Button button00;
    @FXML private Button button01;
    @FXML private Button button02;
    @FXML private Button button10;
    @FXML private Button button11;
    @FXML private Button button12;
    @FXML private Button button20;
    @FXML private Button button21;
    @FXML private Button button22;

    private Button[][] buttons;

    @FXML
    public void initialize() {
        // Initialize the 3x3 button grid
        buttons = new Button[][]{
                {button00, button01, button02},
                {button10, button11, button12},
                {button20, button21, button22}
        };

        // Add event handlers to buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int r = row, c = col;
                buttons[row][col].setOnAction(e -> handleButtonClick(buttons[r][c]));
            }
        }
    }

    private void handleButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(isPlayerX ? "X" : "O");
            button.setDisable(true); // Disable the button after a move
            if (checkWin()) {
                System.out.println((isPlayerX ? "X" : "O") + " wins!");
                disableAllButtons();
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
            }
            isPlayerX = !isPlayerX; // Switch turns
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().isEmpty()) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().isEmpty()) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().isEmpty()) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}