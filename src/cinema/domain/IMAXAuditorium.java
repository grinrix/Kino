package cinema.domain;

public class IMAXAuditorium extends Auditorium{
    public IMAXAuditorium(String name, int rows, int seatsPerRow) {
        super(name, rows, seatsPerRow); // przekazanie danych do konstruktora nadklasy
    }

    @Override
    public String getDescription() {
        return "Sala IMAX – powiększony ekran, dźwięk przestrzenny i najwyższa jakość obrazu.";
    }
}
