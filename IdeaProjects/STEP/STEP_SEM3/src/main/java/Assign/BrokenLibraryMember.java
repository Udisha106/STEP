package Assign;

class BrokenLibraryMember {

    // These are wrongly static because each member
    // should have separate values.

    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name,
                        String memberId,
                        int booksIssued) {

        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}
