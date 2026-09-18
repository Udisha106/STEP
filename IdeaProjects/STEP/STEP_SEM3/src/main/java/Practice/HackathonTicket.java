package Practice;

public class HackathonTicket extends EventTicket {

    private String teamName;

    public HackathonTicket(
            String attendeeId,
            double basePrice,
            String teamName) {

        super(attendeeId, basePrice);

        this.teamName = teamName;
    }

    @Override
    public void printTicket() {

        System.out.print(
                "Hackathon Ticket | Team: "
                        + teamName
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}
