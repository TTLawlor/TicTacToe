import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> compPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameDisplay = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printDisplay(gameDisplay);

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter position choice (1-9)");
            int playerPos = scanner.nextInt();
            while(playerPositions.contains(playerPos) || compPositions.contains(playerPos)){
                System.out.println("position taken, please choose another");
                playerPos = scanner.nextInt();
            }
            placePiece(gameDisplay, playerPos, "player");

            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int compPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(compPos) || compPositions.contains(compPos)){
                compPos = rand.nextInt(9) + 1;
            }
            placePiece(gameDisplay, compPos, "computer");
            printDisplay(gameDisplay);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
            }
        }


    public static void printDisplay(char[][] gameDisplay) {
        for (char[] row : gameDisplay) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameDisplay, int pos, String user) {
        char symbol = 'X';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        }
        if (user.equals("computer")) {
            symbol = 'O';
            compPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameDisplay[0][0] = symbol;
                break;
            case 2:
                gameDisplay[0][2] = symbol;
                break;
            case 3:
                gameDisplay[0][4] = symbol;
                break;
            case 4:
                gameDisplay[2][0] = symbol;
                break;
            case 5:
                gameDisplay[2][2] = symbol;
                break;
            case 6:
                gameDisplay[2][4] = symbol;
                break;
            case 7:
                gameDisplay[4][0] = symbol;
                break;
            case 8:
                gameDisplay[4][2] = symbol;
                break;
            case 9:
                gameDisplay[4][4] = symbol;
                break;
        }
    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(diag1);
        winning.add(diag2);

        for(List l : winning){
            if(playerPositions.containsAll(l)){
                return "Congratulations you won!";
            }else if(compPositions.containsAll(l)){
                return "you lost";
            }else if(playerPositions.size() + compPositions.size() == 9) {
                return "no winner :((";
            }
        }
        return "";
    }
}
