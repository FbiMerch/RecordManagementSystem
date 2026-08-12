import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RecordManager manager = new RecordManager("books.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("       RECORD MANAGEMENT SYSTEM (Java)   ");
            System.out.println("=========================================");
            System.out.println(" 1. Adauga inregistrare");
            System.out.println(" 2. Afiseaza toate inregistrarile");
            System.out.println(" 3. Cauta dupa ID");
            System.out.println(" 4. Editeaza inregistrare");
            System.out.println(" 5. Sterge inregistrare");
            System.out.println(" 6. Sorteaza inregistrari");
            System.out.println(" 7. Exporta in format CSV");
            System.out.println(" 0. Iesire");
            System.out.println("=========================================");
            System.out.print("Alege o optiune: ");

            if (!scanner.hasNextInt()) {
                System.out.println(" [EROARE] Te rog introdu un numar!");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // curățare buffer

            switch (choice) {
                case 1:
                    System.out.print("Introdu ID unic: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (manager.idExists(id)) {
                        System.out.println(" [EROARE] Exista deja o carte cu acest ID!");
                        break;
                    }

                    System.out.print("Introdu Titlu: ");
                    String title = scanner.nextLine();
                    System.out.print("Introdu Autor: ");
                    String author = scanner.nextLine();
                    System.out.print("Introdu Anul publicarii: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    manager.addRecord(new Book(id, title, author, year));
                    break;

                case 2:
                    manager.displayAllRecords();
                    break;

                case 3:
                    System.out.print("Introdu ID cautat: ");
                    int searchId = scanner.nextInt();
                    manager.searchRecordByID(searchId);
                    break;

                case 4:
                    System.out.print("Introdu ID-ul de editat: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    manager.updateRecord(updateId, scanner);
                    break;

                case 5:
                    System.out.print("Introdu ID-ul de sters: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteRecord(deleteId);
                    break;

                case 6:
                    System.out.print("Sorteaza dupa: 1. Titlu | 2. An publicare: ");
                    int crit = scanner.nextInt();
                    manager.sortRecords(crit);
                    break;

                case 7:
                    manager.exportToCSV("exported_books.csv");
                    break;

                case 0:
                    System.out.println("\n La revedere!");
                    return;

                default:
                    System.out.println(" [EROARE] Optiune invalida.");
            }
        }
    }
}