package dio.sudoku;

import java.util.Scanner;

public class Sudoku {
    private static final int SIZE = 9;
    private int[][] board;

    public Sudoku() {
        board = new int[SIZE][SIZE];
    }

    public void initializeBoard(String[] args) {
        for (String arg : args) {
            String[] parts = arg.split(";");
            int row = Integer.parseInt(parts[0].split(",")[0]);
            int col = Integer.parseInt(parts[0].split(",")[1]);
            int num = Integer.parseInt(parts[1].split(",")[0]);
            boolean filled = Boolean.parseBoolean(parts[1].split(",")[1]);

            if (filled) {
                board[row][col] = num;
            }
        }
    }

    public void printBoard() {
        for (int[] r : board) {
            for (int n : r) System.out.print(n + " ");
            System.out.println();
        }
        System.out.println();
    }

    public boolean isValidMove(int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num || board[x][col] == num || 
                board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean placeNumber(int row, int col, int num) {
        if (isValidMove(row, col, num)) {
            board[row][col] = num;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sudoku sudoku = new Sudoku();

        // Inicializa o tabuleiro com os argumentos
        sudoku.initializeBoard(args);

        while (true) {
            sudoku.printBoard();
            System.out.println("Insira a linha, coluna e o número (ou -1 para sair): ");
            int row = scanner.nextInt();
            if (row == -1) break;
            int col = scanner.nextInt();
            int num = scanner.nextInt();

            if (sudoku.placeNumber(row, col, num)) {
                System.out.println("Número colocado com sucesso.");
            } else {
                System.out.println("Movimento inválido. Tente novamente.");
            }
        }

        scanner.close();
    }
}