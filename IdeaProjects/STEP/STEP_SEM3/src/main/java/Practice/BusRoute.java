package Practice;

class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {

        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }

        int result =
                this.routeCode.compareToIgnoreCase(other.routeCode);

        if (result != 0) {
            return result;
        }

        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = new BusRoute[routes.length];

        for (int i = 0; i < routes.length; i++) {
            result[i] = routes[i];
        }

        for (int i = 0; i < result.length - 1; i++) {

            int min = i;

            for (int j = i + 1; j < result.length; j++) {

                if (result[j].compareTo(result[min]) < 0) {
                    min = j;
                }
            }

            BusRoute temp = result[i];
            result[i] = result[min];
            result[min] = temp;
        }

        return result;
    }

    public String getRouteCode() {
        return routeCode;
    }
}
