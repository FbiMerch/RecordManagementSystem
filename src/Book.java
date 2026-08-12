public class Book {
    private int id;
    private String title;
    private String author;
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getters și Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }

    // Formatare pentru fișier local
    public String toFileFormat() {
        return id + "|" + title + "|" + author + "|" + year;
    }

    public static Book fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 4) {
            return new Book(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    parts[2],
                    Integer.parseInt(parts[3])
            );
        }
        return null;
    }

    public void displayRow() {
        String shortTitle = title.length() > 27 ? title.substring(0, 24) + "..." : title;
        String shortAuthor = author.length() > 22 ? author.substring(0, 19) + "..." : author;
        System.out.printf("%-8d %-30s %-25s %-8d%n", id, shortTitle, shortAuthor, year);
    }
}