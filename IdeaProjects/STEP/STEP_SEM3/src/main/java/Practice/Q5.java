package Practice;


import java.util.*;

interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

abstract class Resource {
    String id;

    Resource(String id) {
        this.id = id;
    }
}

class Book extends Resource implements Reservable {
    Book(String id) {
        super(id);
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }
}

class EBook extends Resource implements Reservable, Downloadable {
    EBook(String id) {
        super(id);
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class Q5 {
    static ArrayList<Resource> resources = new ArrayList<>();

    static void addResource(Resource r) {
        int index = findIndex(r.id);

        if (index >= 0) {
            System.out.println("duplicate rejected");
        } else {
            resources.add(r);
            resources.sort(Comparator.comparing(x -> x.id));
            System.out.println(r.id + " added");
        }
    }

    static int findIndex(String id) {
        int left = 0;
        int right = resources.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = resources.get(mid).id.compareTo(id);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    static void reserve(String id, String memberId) {
        int index = findIndex(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource r = resources.get(index);

        if (r instanceof Reservable) {
            ((Reservable) r).reserve(memberId);
        } else {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    static void download(String id) {
        int index = findIndex(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource r = resources.get(index);

        if (r instanceof Downloadable) {
            ((Downloadable) r).download();
        } else {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    static void find(String id) {
        int index = findIndex(id);

        if (index >= 0) {
            System.out.println(id + " found at index " + index);
        } else {
            System.out.println(id + " not found");
        }
    }

    public static void main(String[] args) {
        addResource(new Book("B1"));
        addResource(new Book("B1"));
        addResource(new EBook("E1"));

        reserve("B1", "M1");
        download("B1");
        download("E1");
        find("E1");
    }
}
