public class Cactus extends Plant {

    public Cactus(String name, double height) {
        super(name, height, PlantType.CACTUS);
    }

    @Override
    public String getRation() {
        return getPlantType().baseNutrientAmount + (getPlantType().scalableNutrientAmount * getHeight()) +
                " metric liters of " + getPlantType().nutrient.shape;
    }

    @Override
    public String getInfo() {
        return "\nName: " + getName() + ", " + getPlantType().shape +
                ", Height: " + getHeight() + " m, Nutrient: " + getPlantType().nutrient.shape;
    }
}
