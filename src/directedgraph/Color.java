package directedgraph;

public enum Color {
    RED ("red"),
    BLUE ("blue");

    private final String color;

    Color(String color) {

        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

    @Override
    public String toString() {
        return this.color;
    }
}
