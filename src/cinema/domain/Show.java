package cinema.domain;
import java.util.List;

public class Show implements Exportable {
    private  String movieTitle;
    private  Auditorium auditorium;
    private  String startTime;
    private  List<Seat> seats;
    private  String imagePath; //dla plakatów do filmów
    public  Show(String movieTitle, Auditorium auditorium, String startTime, String imagePath) {
        this.movieTitle = movieTitle;
        this.auditorium = auditorium;
        this.startTime = startTime;
        this.imagePath = imagePath;
        this.seats=SeatMapService.generateSeats(auditorium); // miejsca są automatycznie wygenerowane podczas tworzenie show
    }

    public String getMovieTitle() {
        return movieTitle;
    }
    public Auditorium getAuditorium() {
        return auditorium;
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getImagePath() {
        return imagePath;
    }
    @Override
    public String toCsv() {
        long occupiedCount = seats.stream().filter(seat -> !seat.isAvailable()).count(); // ile jest zajętych
        double price = (auditorium instanceof IMAXAuditorium) ? 35.00 : 20.00; // IMAX jest droższy
        double revenue = occupiedCount * price;
        return String.format("%s,%s,%s,%d,%.2f",
                movieTitle,
                auditorium.getName(),
                startTime,
                occupiedCount,
                revenue
        );
    }
}