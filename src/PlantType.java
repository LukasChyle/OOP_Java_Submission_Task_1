public enum PlantType {

    CACTUS(0.02, 0, NutrientType.MINERALWATER, "Cactus"),
    CARNIVOROUS(0.1, 0.2, NutrientType.PROTEINDRINK, "Carnivorous Plant"),
    PALM(0, 0.5, NutrientType.TAPWATER, "Palm");

    public final double baseNutrientAmount;
    public final double scalableNutrientAmount;
    public final NutrientType nutrient;
    public final String shape;

    PlantType(double base, double scalable,NutrientType nutrient, String shape) {
        baseNutrientAmount = base;
        scalableNutrientAmount = scalable;
        this.nutrient = nutrient;
        this.shape = shape;
    }
}
