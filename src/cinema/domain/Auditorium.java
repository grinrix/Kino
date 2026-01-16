package cinema.domain;

public abstract class Auditorium {
    private String name;
    private int rows;
    private int seatsPerRow;

    public Auditorium(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }
    public abstract String getDescription(); // opis dla standard oraz IMAX
}