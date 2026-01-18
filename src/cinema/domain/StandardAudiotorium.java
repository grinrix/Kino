package cinema.domain;

public class StandardAudiotorium {
    public StandardAudiotorium(String name, int rows, int seatsPerRow) {
        super(name, rows, seatsPerRow); // przekazanie danych do konstruktora nadklasy
    }

    @Override
    public String getDescription() {
        return "Sala standardowa – podstawowy system dźwięku i obrazu.";
    }
}
