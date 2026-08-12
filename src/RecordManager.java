import java.io.*;
import java.util.*;

public class RecordManager {
    private final String filename;

    public RecordManager(String filename) {
        this.filename = filename;
    }

    private List<Book> loadAllRecords() {
        List<Book> records = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) return records;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Book book = Book.fromFileFormat(line);
                    if (book != null) records.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println(" [EROARE] La citirea din fisier: " + e.getMessage());
        }
        return records;
    }

    private void saveAllRecords(List<Book> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : records) {
                writer.write(book.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(" [EROARE] La salvarea in fisier: " + e.getMessage());
        }
    }

    public boolean idExists(int id) {
        return loadAllRecords().stream().anyMatch(b -> b.getId() == id);
    }

    public void addRecord(Book book) {
        List<Book> records = loadAllRecords();
        records.add(book);
        saveAllRecords(records);
        System.out.println(" [SUCCESS] Inregistrarea a fost salvata!");
    }

    public void displayAllRecords() {
        List<Book> records = loadAllRecords();
        if (records.isEmpty()) {
            System.out.println(" [INFO] Baza de date este goala.");
            return;
        }

        System.out.println("\n" + "-".repeat(71));
        System.out.printf("%-8s %-30s %-25s %-8s%n", "ID", "Titlu", "Autor", "An");
        System.out.println("-".repeat(71));
        records.forEach(Book::displayRow);
        System.out.println("-".repeat(71));
    }

    public void searchRecordByID(int id) {
        for (Book book : loadAllRecords()) {
            if (book.getId() == id) {
                System.out.println("\n [REZULTAT CAUTARE]");
                System.out.println(" ID:     " + book.getId());
                System.out.println(" Titlu:  " + book.getTitle());
                System.out.println(" Autor:  " + book.getAuthor());
                System.out.println(" An:     " + book.getYear());
                return;
            }
        }
        System.out.println(" [EROARE] ID-ul " + id + " nu a fost gasit.");
    }

    public void deleteRecord(int id) {
        List<Book> records = loadAllRecords();
        boolean removed = records.removeIf(b -> b.getId() == id);

        if (removed) {
            saveAllRecords(records);
            System.out.println(" [SUCCESS] Inregistrarea a fost starsa.");
        } else {
            System.out.println(" [EROARE] ID-ul specficat nu exista.");
        }
    }

    public void updateRecord(int id, Scanner scanner) {
        List<Book> records = loadAllRecords();
        boolean found = false;

        for (Book book : records) {
            if (book.getId() == id) {
                found = true;
                System.out.println("\nEditare carte (ID: " + id + ")");

                System.out.print("Titlu nou (Apasa Enter pentru a pastra '" + book.getTitle() + "'): ");
                String title = scanner.nextLine();
                if (!title.trim().isEmpty()) book.setTitle(title);

                System.out.print("Autor nou (Apasa Enter pentru a pastra '" + book.getAuthor() + "'): ");
                String author = scanner.nextLine();
                if (!author.trim().isEmpty()) book.setAuthor(author);

                System.out.print("An nou (Introdu 0 pentru a pastra '" + book.getYear() + "'): ");
                if (scanner.hasNextInt()) {
                    int year = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if (year > 0) book.setYear(year);
                }
                break;
            }
        }

        if (found) {
            saveAllRecords(records);
            System.out.println(" [SUCCESS] Inregistrarea a fost actualizata!");
        } else {
            System.out.println(" [EROARE] ID-ul specificat nu exista.");
        }
    }

    public void sortRecords(int criteria) {
        List<Book> records = loadAllRecords();
        if (criteria == 1) {
            records.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        } else if (criteria == 2) {
            records.sort(Comparator.comparingInt(Book::getYear));
        }

        saveAllRecords(records);
        System.out.println(" [SUCCESS] Datele au fost sortate si salvate.");
    }

    public void exportToCSV(String csvFilename) {
        List<Book> records = loadAllRecords();
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilename))) {
            writer.println("ID,Title,Author,Year");
            for (Book b : records) {
                writer.printf("%d,\"%s\",\"%s\",%d%n", b.getId(), b.getTitle(), b.getAuthor(), b.getYear());
            }
            System.out.println(" [SUCCESS] Datele au fost exportate in '" + csvFilename + "'!");
        } catch (IOException e) {
            System.out.println(" [EROARE] Nu s-a putut crea fisierul CSV.");
        }
    }
}