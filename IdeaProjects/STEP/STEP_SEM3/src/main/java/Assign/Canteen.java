package Assign;

class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode,
                   String canteenName,
                   int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {

        // Higher trust score comes first
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        // Compare codes ignoring case
        int result =
                this.canteenCode.compareToIgnoreCase(
                        other.canteenCode
                );

        if (result != 0) {
            return result;
        }

        // Shorter name comes first
        return this.canteenName.length()
                - other.canteenName.length();
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = new Canteen[canteens.length];

        // Copy array
        for (int i = 0; i < canteens.length; i++) {
            result[i] = canteens[i];
        }

        // Selection sort
        for (int i = 0; i < result.length - 1; i++) {

            int best = i;

            for (int j = i + 1; j < result.length; j++) {

                if (result[j].compareTo(result[best]) < 0) {
                    best = j;
                }
            }

            Canteen temp = result[i];
            result[i] = result[best];
            result[best] = temp;
        }

        return result;
    }

    public String getCanteenCode() {
        return canteenCode;
    }
}
