package cinema.domain;

public class Seat implements Bookable {
    private final int row;
    private final int number;
    private boolean isOccupied;

    public Seat(int row, int number) {
        if (row < 1 || number < 1) {
            throw new IllegalArgumentException("BŁĄD: RZĄD I NUMER MIEJSCA NIE MOŻE BYĆ UJEMNĄ LICZBĄ");
        }
        this.row = row;
        this.number = number;
        this.isOccupied = false;
    }

    @Override
    public boolean book() {
        if (isOccupied) {
            return false; // jest już zajęte
        }
        isOccupied = true;
        return true;
    }

    @Override
    public boolean cancel() {
        if (!isOccupied) {
            return false; // jest wolne
        }
        isOccupied = false;
        return true;
    }

    @Override
    public boolean isAvailable() {
        return !isOccupied;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return isOccupied ? "[X]" : "[ ]";
        // [X] - zajęte
        // [ ] - wolne
    }
}