import java.util.Scanner;

public class SearchMenu {
    public Route[] routes;

    public SearchMenu(Route[] routes) {
        this.routes = routes;
    }

    public static int showSearchMenu() {
        Scanner sc = new Scanner(System.in);
        boolean finish = false;
        int value = 0;

        while (!finish) {
            System.out.println("-------------------------------------------");
            System.out.println("- The Search Menu                         -");
            System.out.println("-------------------------------------------");
            System.out.println("| 1. Show all the trains routes           |");
            System.out.println("| 2. Search by date                       |");
            System.out.println("| 3. Search by time and date              |");
            System.out.println("| 4. Search by start and end stations     |");
            System.out.println("| 5. Search by station and date           |");
            System.out.println("| 6. Search using date, time and stations |");
            System.out.println("| Press 0 to exit the menu                |");
            System.out.println("-------------------------------------------");
            System.out.print("Enter here: ");

            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                String charac = input.substring(0, 1);
                String validValues = "0123456";
                if (validValues.contains(charac)) {
                    finish = true;
                    value = Integer.parseInt(charac);
                } else {
                    System.out.println("Invalid option. Please enter 0-6.");
                }
            }
        }
        return value;
    }

    public void showAllRoutes() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nThere are the following routes:");

        for (int i = 0; i < this.routes.length; ++i) {
            Route r = this.routes[i];
            System.out.println("The " + r.name);
            System.out.println("--------------------------------------------");

            for (int j = 0; j < r.station.length; ++j) {
                System.out.print("| " + j + ". " + r.station[j]);
                System.out.println(" /   09:" + r.Time[j]);
            }
        }

        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

    public int[] searchByDate() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------------");
            System.out.println("- Search by date          -");
            System.out.println("---------------------------");
            System.out.println("| Please enter the date in the following format: |");
            System.out.println("| month/day                                      |");
            System.out.println("-----------------------------------------------");
            System.out.print("Enter the date: ");

            String dateLine = sc.nextLine().trim();
            try {
                String[] dateLineS = dateLine.split("/");
                if (dateLineS.length == 2) {
                    int month = Integer.parseInt(dateLineS[0]);
                    int day = Integer.parseInt(dateLineS[1]);

                    if (month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                            System.out.println("Error: Invalid day for this month.");
                            continue;
                        }
                        if (month == 2 && day > 29) {
                            System.out.println("Error: February has maximum 29 days.");
                            continue;
                        }
                        int[] date = {month, day};
                        return date;
                    }
                }
                System.out.println("Error: Invalid format. Use month/day.");
            } catch (Exception e) {
                System.out.println("Error: Invalid number format.");
            }
        }
    }

    public int[] searchByTimeDate() {
        Scanner sc = new Scanner(System.in);
        int[] monthAndDay = this.searchByDate();
        int[] data = new int[4];
        data[0] = monthAndDay[0];
        data[1] = monthAndDay[1];

        while (true) {
            System.out.print("Enter time (HH:MM): ");
            String timeNoFormat = sc.nextLine().trim();
            try {
                String[] timeS = timeNoFormat.split(":");
                if (timeS.length == 2) {
                    int hour = Integer.parseInt(timeS[0]);
                    int minute = Integer.parseInt(timeS[1]);
                    if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
                        data[2] = hour;
                        data[3] = minute;
                        return data;
                    }
                }
                System.out.println("Invalid time format. Use HH:MM.");
            } catch (Exception e) {
                System.out.println("Invalid time format.");
            }
        }
    }

    public void searchByStation() {
        System.out.println("Search by station functionality (simplified)");
        System.out.println("Route 1: Figueras → Madrid");
        System.out.println("Route 2: Irun → Madrid");
        // More implementation needed
    }

    public void searchByStationDate() {
        searchByStation();
        searchByDate();
    }

    public void searchByStationDateTime() {
        searchByStation();
        searchByTimeDate();
    }

    public void searchEverything() {
        int searchValue = showSearchMenu();
        switch (searchValue) {
            case 1: showAllRoutes(); break;
            case 2:
                int[] dateVal = searchByDate();
                System.out.println("Date: " + dateVal[0] + "/" + dateVal[1]);
                break;
            case 3: searchByTimeDate(); break;
            case 4: searchByStation(); break;
            case 5: searchByStationDate(); break;
            case 6: searchByStationDateTime(); break;
        }
    }
}
