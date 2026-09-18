package Assign;

class LibraryMember {

    // Instance fields:
    // Every library member has their own name,
    // memberId and booksIssued.
    String name;
    String memberId;
    int booksIssued;

    // Static fields:
    // These belong to the library as a whole.
    static String libraryName = "SRM Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println(
                "Total members: " + memberCount
        );
    }
}
