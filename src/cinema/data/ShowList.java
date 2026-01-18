package cinema.data;
import cinema.domain.Auditorium;
import cinema.domain.IMAXAuditorium;
import cinema.domain.Show;
import cinema.domain.StandardAudiotorium;
import java.util.ArrayList;
import java.util.List;

public class ShowList {
    public static List<Show> loadShows() {
        List<Show> shows = new ArrayList<>();
        Auditorium salaMala = new StandardAudiotorium("Sala Kameralna", 4, 6);
        Auditorium salaImax = new IMAXAuditorium("IMAX 3D", 6, 10);

        // SEANSE
        shows.add(new Show("Five Nights at Freddy's 2", salaImax, "20:00", "images/FNAF.jpg"));
        shows.add(new Show("Minecraft Movie", salaMala, "09:30", "images/Minecraft.jpg"));
        shows.add(new Show("F1: The Movie", salaMala, "21:37", "images/F1.jpg"));

        return shows;
    }
}