package cinema.service;

import cinema.domain.Auditorium;
import cinema.domain.Seat;
import java.util.ArrayList;
import java.util.List;

public class SeatMapService {

    public static List<Seat> generateSeats(Auditorium aud) {
        List<Seat> seats = new ArrayList<>();

        // PĘTLA 1: Rzędy (od 1 do liczby rzędów)
        for (int r = 1; r <= aud.getRows(); r++) {

            // PĘTLA 2: Miejsca (od 1 do liczby miejsc w rzędzie)
            // WAŻNE: Tutaj musi być n++ (nie r++!) i sprawdzamy n <= ...
            for (int n = 1; n <= aud.getSeatsPerRow(); n++) {
                seats.add(new Seat(r, n));
            }
        }
        return seats;
    }
}