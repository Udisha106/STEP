package Practice;

public class PremiumWorkshopTicket extends WorkshopTicket {

    private double kitFee;

    public PremiumWorkshopTicket(
            String attendeeId,
            double basePrice,
            String track,
            double kitFee) {

        super(attendeeId, basePrice, track);

        this.kitFee = kitFee;
    }

    @Override
    public void printTicket() {

        System.out.print(
                "Premium Workshop Ticket | Track: "
                        + getTrack()
                        + " | Kit Fee: "
                        + kitFee
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}
