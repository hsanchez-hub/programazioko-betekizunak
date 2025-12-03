package renfe_array;

public class Route {
    public String name;
    public String[] station;
    public int[] Time;

    //The next is an empty constructor
    Route() {
    }

    //The next is a constructor that creates a route using a number
    //So there are 6 routes that creates, this will create a different route dependng which number is entered from 0 to 5
    Route(int number) {
        String[][] stat = { {"1 - Figueras","1 - Valencia","1 - Barcelona","1 - Lleida","1 - Zaragoza","1 - Madrid"},               //1
                {"2 - Irun","2 - Donostia","2 - Pamplona","2 - Logroño","2 - Burgos","2 - Madrid"},                     //2
                {"3 - Murcia","3 - Alicante","3 - Valencia","3 - Cuenca","3 - Aranjuez","3 - Madrid"},                  //3
                {"4 - Cádiz","4 - Sevilla","4 - Huelva","4 - Mérida","4 - Toledo","4 - Madrid"},                        //4
                {"5 - Málaga","5 - Granada","5 - Linares-Baeza","5 - Manzanares","5 - Ciudad Real","5 - Madrid"},       //5
                {"6 - A Coruña","6 - Santiago de Compostela","6 - Ourense","6 - León","6 - Valladolid","6 - Madrid"}};  //6

        //This was just the station and route display.
        //The next lines will let us choose a route, on origin and a destination
        this.name = "route " + number;
        this.station = new String[6];

        for(int i = 0; i < 6; ++i) {
            this.station[i] = stat[number][i];
        }

        this.Time = new int[6];

        for(int i = 0; i < 6; ++i) {
            this.Time[i] = i * 10;
        }

    }

    //The next method will create an array created of routes and will give that value
    Route[] createRoutes() {
        Route[] routes = new Route[6];

        for(int i = 0; i < 6; ++i) {
            routes[i] = new Route(i);
        }

        return routes;
    }
}
