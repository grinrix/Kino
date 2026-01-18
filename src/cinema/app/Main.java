package cinema.app;

import cinema.data.ShowList;
import cinema.domain.*;
import cinema.service.TicketService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // dane
        List<Show> repertuar = ShowList.loadShows();
        TicketService ticketService = new TicketService();

        // GUI
        JFrame frame = new JFrame("KINO");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel);

        // Nagłówek
        JLabel titleLabel = new JLabel("KINO");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);

        // plakat
        JLabel posterLabel = new JLabel();
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        posterLabel.setPreferredSize(new Dimension(300, 400));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(posterLabel);

        // wybór filmu
        JComboBox<String> movieSelector = new JComboBox<>();
        for (Show s : repertuar) {
            movieSelector.addItem(s.getMovieTitle() + " (" + s.getStartTime() + ")");
        }
        movieSelector.setMaximumSize(new Dimension(400, 30));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(movieSelector);


        // rezerwacja
        JButton bookButton = new JButton("Zarezerwuj");
        bookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(bookButton);

        // dla plakatów
        ActionListener updatePoster = e -> {
            int idx = movieSelector.getSelectedIndex();
            Show selectedShow = repertuar.get(idx);

            String path = selectedShow.getImagePath();
            File imgFile = new File(path);

            if (imgFile.exists()) {
                // skalowanie plakatu
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage();
                Image newImg = img.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
                posterLabel.setIcon(new ImageIcon(newImg));
                posterLabel.setText("");
            } else {
                posterLabel.setIcon(null);
                posterLabel.setText("Brak plakatu: " + path);
                posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        movieSelector.addActionListener(updatePoster);
        updatePoster.actionPerformed(null); //ładuje pierwszy plakat na start

        // wybieranie miejsca
        bookButton.addActionListener(e -> {
            int idx = movieSelector.getSelectedIndex();
            Show selectedShow = repertuar.get(idx);

            JFrame seatsFrame = new JFrame("Wybierz miejsce: " + selectedShow.getMovieTitle()); //pojawi się w nowym oknie
            seatsFrame.setSize(600, 400);
            JPanel seatsPanel = new JPanel();
            Auditorium aud = selectedShow.getAuditorium();
            seatsPanel.setLayout(new GridLayout(aud.getRows(), aud.getSeatsPerRow(), 5, 5));

            for (Seat seat : selectedShow.getSeats()) { // tworzenie przycisku dla każdego miejsca
                JButton seatButton = new JButton(seat.getRow() + "-" + seat.getNumber());

                if (!seat.isAvailable()) {
                    seatButton.setBackground(Color.RED); // zajęte
                    seatButton.setEnabled(false);
                } else {
                    seatButton.setBackground(Color.GREEN); // wolne
                }
                // klikanie w miejsca
                seatButton.addActionListener(ev -> {
                    boolean success = ticketService.reserveTicket(selectedShow, seat.getRow(), seat.getNumber());
                    if (success) {
                        seatButton.setBackground(Color.RED);
                        seatButton.setEnabled(false);
                        JOptionPane.showMessageDialog(seatsFrame, "Zarezerwowano miejsce!");
                    } else {
                        JOptionPane.showMessageDialog(seatsFrame, "Błąd rezerwacji!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                });
                seatsPanel.add(seatButton);
            }
            seatsFrame.add(seatsPanel);
            seatsFrame.setVisible(true);
        });
        frame.setVisible(true);
    }
}