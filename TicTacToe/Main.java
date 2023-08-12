package TicTacToe;

public class Main {

    public static void main(String[] args) {

        /** Inicjalizacja planszy znakiem '-'
         *   ---
         *   ---
         *   ---
         *
         *   - ustawienie gracza początkowego (np:  w zmiennej currentPlayer typu char)
         *   - rozpoczęcie gry i pobranie ruchu od gracza
         *   - wykonanie ruchu (sprawdzenie, czy ruch jest możliwy, czy pole nie jest zajęte przez 'X' lub 'O',
         *     czy podana wartość jest w zakresie)
         *   - sprawdzenie, czy gra została zakończona (gry jeden gracz wygrał, lub gdy jest remis)
         *     sprawdzamy po przekątnych, kolumny, wiersze.
         *   - jeśli można wykonać kolejny ruch to znaczy, że zmieniamy gracza.
         *   Kolorki dla konsoli :https://gist.github.com/BD103/dced59ec590161b23710dd2d05ad0720
         */

        TicTacToe game = new TicTacToe();
        game.initializeBoard();
        boolean gameInProgres = true;
        game.currentPlayer = 'X';
        while (gameInProgres) {
            // wyświetlenie planszy
            game.displayBoard();
//            gameInProgres=false;
            // pobieramy ruch od użytkownika
            int[] move = game.getMove();
//            // musimy odzwierciedlić ruch na planszy
            game.makeMove(move[0], move[1]);
//            // wyświetlenie planszy
            game.displayBoard();
            if (game.checkForWin()) {
                System.out.println("Gratulacje dla gracza '" + game.currentPlayer + "'");
                gameInProgres = false;
            } else if (game.checkForTie()) {
                System.out.println("mamy remis");
                gameInProgres = false;
            } else game.changePlayer();
        }
    }
}
