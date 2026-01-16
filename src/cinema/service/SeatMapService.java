package cinema.service;
import cinema.domain.Auditorium;
import java.util.ArrayList;
import java.util.List;

public class SeatMapService {
    public static List<Seat> generateSeats(Auditorium auditorium) {
        List<Seat> seats = new ArrayList<>();
        // robimy pętle do stworzenia listy miejsc
        for (int n = 1; n <=auditorium.getRows(); n++) {
            for(int m = 1; n <=auditorium.getSeatsPerRow(); m++) {
                seats.add(new Seat(n,m));
            }
        }
        return  seats;
    }
}
