package ic;

public class Temperature {
    private int id;
    private String originalType;
    private double originalDegree;
    private boolean extreme;
    private String convertedType;
    private double convertedDegree;

    public Temperature(String originalType, double originalDegree, boolean extreme, String convertedType, double convertedDegree) {
        this.originalType = originalType;
        this.originalDegree = originalDegree;
        this.extreme = extreme;
        this.convertedType = convertedType;
        this.convertedDegree = convertedDegree;
        
    }

    public Temperature(int id, String originalType, double originalDegree, boolean extreme, String convertedType, double convertedDegree) {
        this.id = id;
        this.originalType = originalType;
        this.originalDegree = originalDegree;
        this.extreme = extreme;
        this.convertedType = convertedType;
        this.convertedDegree = convertedDegree;
        
    }

    public int getId() { return id; }
    public String getOriginalType() { return originalType; }
    public double getOriginalDegree() { return originalDegree; }
    public boolean getExtreme() { return extreme; }
    public String getConvertedType() { return convertedType; }
    public double getConvertedDegree() { return convertedDegree; }
}
