import java.util.Scanner;

public class TicTacToe {

    // Spielfeld-Matrix und aktueller Spieler
    private static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        printBoard(); // Spielfeld anzeigen

        while (true) {
            makeMove(); // Zug machen
            printBoard(); // Spielfeld anzeigen

            // Überprüfe Gewinner oder Unentschieden
            if (checkWinner() || isBoardFull()) {
                break;
            }

            switchPlayer(); // Spieler wechseln
        }
    }

    // Spielfeld anzeigen
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Zug machen
    private static void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.print("Spieler " + currentPlayer + ", geben Sie Zeile (1-3) und Spalte (1-3) ein: ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ');

        board[row][col] = currentPlayer; // Zug ins Spielfeld eintragen
        scanner.close(); // Scanner schließen, um Resource-Leak zu vermeiden
    }

    // Spieler wechseln
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Überprüfe Gewinner
    private static boolean checkWinner() {
        // Überprüfe Reihen und Spalten
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                System.out.println("Spieler " + currentPlayer + " gewinnt!");
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                System.out.println("Spieler " + currentPlayer + " gewinnt!");
                return true;
            }
        }

        // Überprüfe Diagonalen
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            System.out.println("Spieler " + currentPlayer + " gewinnt!");
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            System.out.println("Spieler " + currentPlayer + " gewinnt!");
            return true;
        }

        return false; // Kein Gewinner
    }

    // Überprüfe, ob das Spielfeld voll ist (Unentschieden)
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Spielfeld ist nicht voll
                }
            }
        }
        System.out.println("Unentschieden! Das Spiel endet unentschieden.");
        return true; // Spielfeld ist voll
    }
}
