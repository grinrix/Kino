package cinema.service;

import cinema.domain.Bookable;
import cinema.domain.Exportable;
import cinema.domain.Seat;
import cinema.domain.Show;

public class TicketService implements Bookable{

    public boolean reserveTicket(Show show, int row, int seatNumber) {

        Seat seat = show.getSeats().stream()
                .filter(s -> s.getRow() == row && s.getNumber() == seatNumber)
                .findFirst()
                .orElse(null);


        if (seat == null) {
            System.out.println("Takie miejsce nie istnieje.");
            return false;
        }

        if(seat.book()){System.out.println("Rezerwacja zakończona sukcesem."); return true;}else {System.out.println("To miejsce jest już zajęte"); return false;}


    }
}
