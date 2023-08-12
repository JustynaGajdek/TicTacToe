package TicTacToe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    public char currentPlayer;
    char[][] board = new char[3][3];


    public void initializeBoard() {
//        Pierwszy sposób
//        for (int row = 0; row < board.length; row++){
//            for (int col = 0; col < board[row].length; col++)
//                board[row][col] = '-';
//        }
        for (char[] chars : board) { // wersja pętli 'for-each'
            Arrays.fill(chars, '-');
        }
    }

    public void displayBoard() {
        System.out.println("Plansza gry:");
        for (char[] chars : board) {
            for (char aChar : chars)
                if (aChar == 'X') {
                    System.out.print("\033[32m" + aChar + "\033[0m");
                } else if (aChar == '0') {
                    System.out.print("\033[34m" + aChar + "\033[0m");
                } else System.out.print("\033[31m" + aChar + "\033[0m");
            System.out.println();
        }
    }

    public int[] getMove() {
        // odpytywać gracza o ruch, dopóki nie poda prawidłowych wartości
        // można skorzystać z parsowania Integer.parseInt() // NumberFormatException
        // przechwycić błąd za pomocą try-catch
        int[] move = new int[2];
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Podaj wiersz (1-" + board.length + "):");
            String inputRow = sc.nextLine();
            System.out.print("Podaj kolumnę (1-" + board[0].length + "):");
            String inputCol = sc.nextLine();
            try {
                int row = Integer.parseInt(inputRow) - 1;
                int col = Integer.parseInt(inputCol) - 1;
                if (isValidMove(row, col)) {
                    move[0] = row;
                    move[1] = col;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Podałeś wartości nie będące liczbami, poproszę podaj liczbę");
            }
        }
        return move;
    }

    boolean isValidMove(int row, int col) {
        if (row < 0 || row > board.length || col < 0 || col > board[0].length) {
            System.out.println("Niepoprawny ruch, spróbuj ponownie");
            return false;
        } else if (board[row][col] != '-') {
            System.out.println("Pole jest już zajęte");
            return false;
        } else return true;
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    public boolean checkForWin() {
        return checkForRows() || checkForColumns() || checkDiagonal();
    }

    boolean checkForRows() {
        for (char[] chars : board) {
            if (chars[0] != '-') {
                char first = chars[0];
                boolean isWin = true;
                for (int col = 1; col < board.length; col++) {
                    if (chars[col] != first) {
                        isWin = false;
                        break;
                    }
                }
                if (isWin) return true;
            }
        }
        return false;
    }

    boolean checkForColumns() {
        int size = board.length;
        for (int col = 0; col < size; col++) {
            if (board[0][col] != '-') {
                boolean isWin = true;
                for (int row = 1; row < size; row++){
                    if (board[row][col] != board[0][col]){
                        isWin = false;
                        break;
                    }
                }
                if (isWin) return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        return (board[0][0] != '-' && board[0][0] == board[1][1] && board[0][0] == board[2][2])
                || (board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0]);
    }

    public boolean checkForTie() {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == '-') return false;
            }
        }
        return true;
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == 'X' ? '0' : 'X';
    }
}
