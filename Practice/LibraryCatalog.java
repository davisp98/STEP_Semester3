import java.util.List;

public class LibraryCatalog {
    public static class Book {
        String isbn;
        String title;

        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    public static String findBook(List<Book> catalog, String targetIsbn) {
        if (catalog == null || catalog.isEmpty()) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = catalog.get(mid).isbn.compareTo(targetIsbn);

            if (comparison == 0) {
                return catalog.get(mid).title;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<Book> catalog = List.of(
            new Book("0001112223", "Introduction to Algebra"),
            new Book("0002223334", "Beginning Python"),
            new Book("0003334445", "Classic Mythology"),
            new Book("0004445556", "Data and Society"),
            new Book("0005556667", "European History")
        );

        System.out.println(findBook(catalog, "0003334445"));
        System.out.println(findBook(catalog, "0009998887"));
    }
}