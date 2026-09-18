package Assign;

class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;


    // No-argument constructor
    public LibraryMemberBean() {
        this(null, null);
    }


    // Name-only constructor
    public LibraryMemberBean(String name) {
        this(null, name);
    }


    // Main constructor
    public LibraryMemberBean(
            String membershipId,
            String name) {

        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
        this.securityAnswer = null;
    }


    public String getMembershipId() {
        return membershipId;
    }


    public void setMembershipId(String id) {

        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public boolean isPremiumMember() {
        return premiumMember;
    }


    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }


    // Write-only property
    public void setSecurityAnswer(String answer) {

        if (answer == null) {
            securityAnswer = null;
        } else {

            // Deterministic one-way transformation
            securityAnswer =
                    Integer.toHexString(answer.hashCode());
        }
    }
}
