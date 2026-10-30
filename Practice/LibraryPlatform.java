import java.util.*;

interface Reservable {
    void reserve(String member);
}

interface Downloadable {
    void download();
}

abstract class Resource implements Comparable<Resource> {
    protected String id;
    protected String type;

    public Resource(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Resource o) {
        return this.id.compareTo(o.id);
    }
}

class Book extends Resource implements Reservable {
    private boolean reserved = false;
    private String reservedBy = null;

    public Book(String id) {
        super(id, "Book");
    }

    @Override
    public void reserve(String member) {
        this.reserved = true;
        this.reservedBy = member;
        System.out.println(id + " reserved for " + member);
    }
}

class EBook extends Resource implements Reservable, Downloadable {
    private boolean reserved = false;

    public EBook(String id) {
        super(id, "EBook");
    }

    @Override
    public void reserve(String member) {
        this.reserved = true;
        System.out.println(id + " reserved for " + member);
    }

    @Override
    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class LibraryPlatform {
    public static void main(String[] args) {
        List<Resource> library = new ArrayList<>();

        addResource(library, new Book("B1"));
        addResource(library, new Book("B1"));
        addResource(library, new EBook("E1"));

        reserveResource(library, "B1", "M1");
        downloadResource(library, "B1");
        downloadResource(library, "E1");
        findResource(library, "E1");
    }

    private static void addResource(List<Resource> library, Resource r) {
        int idx = Collections.binarySearch(library, r);
        if (idx >= 0) {
            System.out.println("duplicate rejected");
        } else {
            library.add(-idx - 1, r);
            System.out.println(r.getId() + " added");
        }
    }

    private static void reserveResource(List<Resource> library, String id, String member) {
        Resource r = findById(library, id);
        if (r != null) {
            if (r instanceof Reservable) {
                ((Reservable) r).reserve(member);
            } else {
                System.out.println(id + " rejected: reserve unsupported");
            }
        }
    }

    private static void downloadResource(List<Resource> library, String id) {
        Resource r = findById(library, id);
        if (r != null) {
            if (r instanceof Downloadable) {
                ((Downloadable) r).download();
            } else {
                System.out.println(id + " rejected: download unsupported");
            }
        }
    }

    private static Resource findById(List<Resource> library, String id) {
        int left = 0, right = library.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = library.get(mid).getId().compareTo(id);
            if (cmp == 0) return library.get(mid);
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    private static void findResource(List<Resource> library, String id) {
        int left = 0, right = library.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = library.get(mid).getId().compareTo(id);
            if (cmp == 0) {
                System.out.println(id + " found at index " + mid);
                return;
            }
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        System.out.println(id + " not found");
    }
}