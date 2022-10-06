public abstract class Plant implements Feedable, Informable {

    private final String name;
    private final double height;
    private final PlantType plantType;

    public Plant(String name, double height, PlantType plantType) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Invalid name");
        } else if (height <= 0){
            throw new IllegalArgumentException(height + " Invalid height");
        }
        this.height = height;
        this.name = name.trim();
        this.plantType = plantType;
    }

    public String getName() {
        return name;
    }

    protected double getHeight() {
        return height;
    }

    protected PlantType getPlantType() {
        return plantType;
    }
}
