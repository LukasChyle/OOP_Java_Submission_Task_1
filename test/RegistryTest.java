import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegistryTest {

    /*
          for these tests to work the file with saved plants have to be empty.
     */
    public Registry registry = new Registry();

    @Test
    void addPlant() {

        assertTrue(registry.addPlant("Swingy", 7, PlantType.PALM)); // type PALM
        assert (registry.getSizeOfPlants() == 1 && registry.getSizeOfPlants() != 0);

        assertFalse(registry.addPlant("Swingy", 7, PlantType.PALM)); // name already exists, not added
        assert (registry.getSizeOfPlants() == 1 && registry.getSizeOfPlants() != 2);

        assertTrue(registry.addPlant("Rex", 3, PlantType.CARNIVOROUS)); // type CARNIVOROUS
        assert (registry.getSizeOfPlants() == 2 && registry.getSizeOfPlants() != 1);

        assertTrue(registry.addPlant("Sticky", 0.5, PlantType.CACTUS)); // type CACTUS
        assert (registry.getSizeOfPlants() == 3 && registry.getSizeOfPlants() != 2);
    }

    @Test
    void NegativeHeightShouldReturnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    registry.addPlant("Sticky", -4, PlantType.CACTUS);
                });
    }

    @Test
    void BlankNameShouldReturnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    registry.addPlant("   ", 7, PlantType.CACTUS);
                });
    }

    @Test
    void removePlant() {

        registry.addPlant("Swingy",  7, PlantType.CACTUS);

        assertFalse(registry.removePlant("Flex")); // name don't exist
        assert (registry.getSizeOfPlants() == 1 && registry.getSizeOfPlants() != 0);

        assertTrue(registry.removePlant("Swingy")); // removed
        assert (registry.getSizeOfPlants() == 0 && registry.getSizeOfPlants() != 1);
    }
}