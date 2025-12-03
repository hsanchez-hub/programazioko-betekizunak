package renfe_array;

import java.util.Scanner;

public class SearchMenu {
    public Route[] routes;

    SearchMenu(Route[] routes) {
        this.routes = routes;
    }

    public static int showSearchMenu() {
        Scanner sc = new Scanner(System.in);
        boolean finish = false;
        int value = 0;

        while(!finish) {
            System.out.println("-------------------------------------------");
            System.out.println("- The Seacrh Menu                         -");
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
            String input = sc.next();
            String charac = "" + input.charAt(0);
            String ValidValues = "0123456";
            if (ValidValues.contains(charac)) {
                finish = true;
                value = Integer.parseInt(charac);
            }
        }

        return value;
    }

    void ShowAllRoutes() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nThere are the next routes:");

        for(int i = 0; i < this.routes.length; ++i) {
            Route var1 = this.routes[i];
            System.out.println("The " + var1.name);
            System.out.println("--------------------------------------------");

            for(int j = 0; j < this.routes[i].station.length; ++j) {
                System.out.print("| " + j + ". " + this.routes[i].station[j]);
                int[] var4 = this.routes[i].Time;
                System.out.println("/   09:" + var4[j]);
            }
        }

        System.out.println("Press any key to continue");
        sc.next();
    }

    int[] searchByDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("- Search by date          -");
        System.out.println("---------------------------");
        System.out.println("_______________________________________________");
        System.out.println("| Please enter the date in the next format:   |");
        System.out.println("| month/day                                   |");
        System.out.println("-----------------------------------------------");
        System.out.print("Enter the date: ");
        String dateLine = sc.next();
        String[] dateLineS = dateLine.split("/");
        int[] date = new int[2];
        date[0] = Integer.parseInt(dateLineS[0]);
        date[1] = Integer.parseInt(dateLineS[1]);
        if (date[0] <= 12 && date[0] >= 1 && date[1] <= 31 && date[1] >= 1) {
            if (date[0] == 2) {
                if (date[1] > 29) {
                    System.out.println("Error the enter value is not correct");
                    return this.searchByDate();
                } else {
                    return date;
                }
            }
            else if (date[0] != 4 && date[0] != 6 && date[0] != 9 && date[0] != 11) {
                if (date[1] > 31) {
                    System.out.println("Error the enter value is not correct");
                    return this.searchByDate();
                }
                else {
                    return date;
                }
            }
            else {
                    if(date[1] > 30) {
                        System.out.println("Error the enter value is not correct");
                        return this.searchByDate();
                    }
                else {
                    return date;
                    }
            }
        }
        else {
            System.out.println("Error, the enter data is not valid");
            return this.searchByDate();
        }
    }

    int[] searchByTimeDate() {
        Scanner sc = new Scanner(System.in);
        int[] monthAndDay = this.searchByDate();
        System.out.println();
        int[] data = new int[4];
        data[0] = monthAndDay[0];
        data[1] = monthAndDay[1];
        data[2] = 0;
        data[3] = 0;
        boolean finish = false;

        while(!finish) {
            System.out.println("Please enter the hours and time in the next format hours:minutes");
            System.out.print("Enter time: ");
            String timeNoFormat = sc.next();
            String[] timeS = timeNoFormat.split(":");
            int[] time = new int[]{Integer.parseInt(timeS[0]), Integer.parseInt(timeS[1])};
            if (time[0] < 24 && time[0] >= 0 && time[1] < 60 && time[1] >= 0) {
                finish = true;
                data[2] = time[0];
                data[3] = time[0];
            }
        }
        return data;
    }
    void searchByStation() {
        Scanner sc = new Scanner(System.in);
        int listnum1 = 0;
        int listnum2 = 0;
        int origin1;
        int origin2;
        int destination1;
        int destination2;
        float price;
        int[][] num = { {11,12,13,14,15,16},  //1
                {21,22,23,24,25,26},  //2
                {31,32,33,34,35,36},  //3
                {41,42,43,44,45,46},  //4
                {51,52,53,54,55,56},  //5
                {61,62,63,64,65,66}}; //6
        String[][] stat = { {"1 - Figueras","1 - Valencia","1 - Barcelona","1 - Lleida","1 - Zaragoza","1 - Madrid"},               //1
                {"2 - Irun","2 - Donostia","2 - Pamplona","2 - Logroño","2 - Burgos","2 - Madrid"},                     //2
                {"3 - Murcia","3 - Alicante","3 - Valencia","3 - Cuenca","3 - Aranjuez","3 - Madrid"},                  //3
                {"4 - Cádiz","4 - Sevilla","4 - Huelva","4 - Mérida","4 - Toledo","4 - Madrid"},                        //4
                {"5 - Málaga","5 - Granada","5 - Linares-Baeza","5 - Manzanares","5 - Ciudad Real","5 - Madrid"},       //5
                {"6 - A Coruña","6 - Santiago de Compostela","6 - Ourense","6 - León","6 - Valladolid","6 - Madrid"}};
        System.out.println("The routes are the next one's:");
        System.out.println("................");
        while (listnum1 <= 5){
            System.out.print(stat[listnum1][listnum2]);
            if (listnum2 >= 5){
                listnum2 = 0;
                listnum1 = listnum1 + 1;
                System.out.println();
                System.out.println("================================================================================================");
            }
            else {
                listnum2 = listnum2 + 1;
                System.out.print(" | ");
            }
        }
        //This was just the station and route display.
        //The next lines will let us choose a route, on origin and a destination

        System.out.println("These are the available options");
        System.out.println("columns = routes, rows = stations");
        System.out.println("The price will be determined by the distance between stations (min 2€): ");
        while (listnum1 <= 5){
            System.out.print(stat[listnum1][listnum2]);
            if (listnum2 >= 5){
                listnum2 = 0;
                listnum1 = listnum1 + 1;
                System.out.println();
                System.out.println("================================================================================================");
            }
            else {
                listnum2 = listnum2 + 1;
                System.out.print(" | ");
            }
        }

        System.out.println("Select route (1-6): ");
        int route = sc.nextInt() - 1;
        origin1 = route;
        destination1 = route;

        System.out.println("Insert origin (1-6): ");
        origin2 = sc.nextInt() - 1;
        System.out.println("Insert destination (1-6): ");
        destination2 = sc.nextInt() - 1;
        price = (num[origin1][origin2]) - (num[destination1][destination2]);

        if (price == 0) {
            System.out.println("That's the same station, try again");

            System.out.println("Select route: ");
            route = sc.nextInt();
            origin1 = route;
            destination1 = route;

            System.out.println("Insert origin (1-6): ");
            origin2 = sc.nextInt() - 1;
            System.out.println("Insert destination (1-6): ");
            destination2 = sc.nextInt() - 1;
            System.out.println();
            price = (num[origin1][origin2]) - (num[destination1][destination2]);
        }
        if (price < 0) {
            price = price * -1;
        }
        System.out.println();
        System.out.println("origin: " + stat[origin1][origin2]);
        System.out.println("destination: " + stat[destination1][destination2]);
        if (price < 2) {
            price = 2;
        }
        System.out.println(price);
    }
    void searchByStationDate() {
        searchByStation();
        searchByDate();
    }
    void searchByStationDateTime() {
        searchByStation();
        searchByTimeDate();
    }
    void searchEverything() {
        int searchValue = showSearchMenu();
        switch (searchValue) {
            case 1:
                ShowAllRoutes();
                break;
            case 2:
                int[] nvalue = searchByDate();
                System.out.println("The values are: " + nvalue[0] + "/" + nvalue[1]);
                break;
            case 3:
                searchByTimeDate();
                break;
            case 4:
                searchByStation();
                break;
            case 5:
                searchByStationDate();
                break;
            case 6:
                searchByStationDateTime();
        }
    }
}