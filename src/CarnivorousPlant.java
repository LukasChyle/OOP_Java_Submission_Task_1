public class CarnivorousPlant extends Plant {

    public CarnivorousPlant(String name, double height) {
        super(name, height, PlantType.CARNIVOROUS);
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