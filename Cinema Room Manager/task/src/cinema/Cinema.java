package cinema;
import java.util.Scanner;

public class Cinema {

    static int currentIncome = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void printingCinema(int rows, int seats, char[][] cinema) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        for (int i = 0; i < cinema.length; i++) {
            System.out.println();
            System.out.print(i + 1);
            for (int j = 0; j < cinema[1].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
        }
        System.out.println();
    }

    public static char[][] buyTicket(int rows, int seats, int seatsNumber, char[][] cinema) {
        int ticketPrice = 0;
        int fontPrice = 10;
        int backPrice = 8;

        // calculation the ticket price
        System.out.println("Enter a row number:");
        int seatRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumInRow = scanner.nextInt();

        if (seatRow > rows || seatNumInRow > seats || seatRow <= 0 || seatNumInRow <= 0) {
            System.out.println("Wrong input!");
        } else if (cinema[seatRow - 1][seatNumInRow - 1] != 'B') {
            if (seatsNumber <= 60 || seatRow <= rows / 2) {
                ticketPrice = fontPrice;
            } else {
                ticketPrice = backPrice;
            }
            System.out.println("Ticket price: $" + ticketPrice);

            currentIncome += ticketPrice;

            // printing the seating arrangement
            cinema[seatRow - 1][seatNumInRow - 1] = 'B';
            printingCinema(rows, seats, cinema);
        } else {
            System.out.println("That ticket has already been purchased!");
            buyTicket(rows, seats, seatsNumber, cinema);
        }
        return cinema;
    }

    public static void countStatistics(int seatsNumber, int purchasedTickets, int totalIncome) {
        double percentage = purchasedTickets * 1.0 / seatsNumber * 100;
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.printf("Current income: $%d\n" +
                "Total income: $%d\n", currentIncome, totalIncome);
    }

    public static void main(String[] args) {
        int seatsNumber = 0;
        int fontPrice = 10;
        int backPrice = 8;
        int purchasedTickets = 0;
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        seatsNumber = rows * seats;
        int totalIncome = 0;
        if (seatsNumber <= 60) {
            totalIncome = fontPrice * rows * seats;
        } else {
            totalIncome = fontPrice * (rows / 2 * seats) + backPrice * (rows - rows / 2) * seats;
        }

        // Creating Cinema map
        char[][] cinema = new char[rows][seats];
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[1].length; j++) {
                    cinema[i][j] = 'S';
            }
        }
        // Main menu
        int userOption = -1;
        while (userOption != 0) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            userOption = scanner.nextInt();
            switch (userOption) {
                case 1:
                    printingCinema(rows, seats, cinema);
                    break;
                case 2:
                    cinema = buyTicket(rows, seats, seatsNumber, cinema);
                    ++purchasedTickets;
                    break;
                case 3:
                    countStatistics(seatsNumber, purchasedTickets, totalIncome);
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}


