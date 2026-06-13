import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Book {
    String id;
    String title;
    boolean issued;

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
        this.issued = false;
    }

    @Override
    public String toString() {
        return id + " - " + title + (issued ? " [Issued]" : " [Available]");
    }
}

public class LibraryManagementSystemGUI extends JFrame {

    ArrayList<Book> books = new ArrayList<>();

    JTextArea displayArea;

    public LibraryManagementSystemGUI() {

        books.add(new Book("B101", "Java Programming"));
        books.add(new Book("B102", "Data Structures"));

        setTitle("Library Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("LIBRARY MANAGEMENT SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JButton addBook = new JButton("Add Book");
        JButton viewBooks = new JButton("View Books");
        JButton searchBook = new JButton("Search Book");
        JButton issueBook = new JButton("Issue Book");
        JButton returnBook = new JButton("Return Book");
        JButton exit = new JButton("Exit");

        buttonPanel.add(addBook);
        buttonPanel.add(viewBooks);
        buttonPanel.add(searchBook);
        buttonPanel.add(issueBook);
        buttonPanel.add(returnBook);
        buttonPanel.add(exit);

        panel.add(buttonPanel, BorderLayout.WEST);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        add(panel);

        addBook.addActionListener(e -> addBook());

        viewBooks.addActionListener(e -> viewBooks());

        searchBook.addActionListener(e -> searchBook());

        issueBook.addActionListener(e -> issueBook());

        returnBook.addActionListener(e -> returnBook());

        exit.addActionListener(e -> System.exit(0));
    }

    private void addBook() {

        String id = JOptionPane.showInputDialog(this, "Enter Book ID:");

        String title = JOptionPane.showInputDialog(this, "Enter Book Title:");

        if (id != null && title != null) {
            books.add(new Book(id, title));
            JOptionPane.showMessageDialog(this, "Book Added Successfully!");
        }
    }

    private void viewBooks() {

        displayArea.setText("");

        for (Book b : books) {
            displayArea.append(b + "\n");
        }
    }

    private void searchBook() {

        String search = JOptionPane.showInputDialog(this, "Enter Book Title:");

        displayArea.setText("");

        for (Book b : books) {
            if (b.title.toLowerCase().contains(search.toLowerCase())) {
                displayArea.append(b + "\n");
            }
        }
    }

    private void issueBook() {

        String id = JOptionPane.showInputDialog(this, "Enter Book ID:");

        for (Book b : books) {

            if (b.id.equalsIgnoreCase(id)) {

                if (!b.issued) {
                    b.issued = true;
                    JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Book Already Issued!");
                }

                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Book Not Found!");
    }

    private void returnBook() {

        String id = JOptionPane.showInputDialog(this, "Enter Book ID:");

        for (Book b : books) {

            if (b.id.equalsIgnoreCase(id)) {

                if (b.issued) {

                    b.issued = false;

                    JOptionPane.showMessageDialog(this,
                            "Book Returned Successfully!");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Book Was Not Issued!");
                }

                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Book Not Found!");
    }

    private static boolean login() {

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        Object[] message = {
                "Username:", username,
                "Password:", password
        };

        int option = JOptionPane.showConfirmDialog(
                null,
                message,
                "Library Login",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            String user = username.getText();
            String pass = new String(password.getPassword());

            return user.equals("admin") && pass.equals("admin");
        }

        return false;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            if (login()) {

                new LibraryManagementSystemGUI().setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Login!"
                );
            }
        });
    }
}
