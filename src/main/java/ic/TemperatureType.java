package ic;

public class TemperatureType {
    private int id;
    private String typeName;

    public TemperatureType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() { return id; }
    public String getTypeName() { return typeName; }

    @Override
    public String toString() { return typeName; } // shown in ComboBox
}
