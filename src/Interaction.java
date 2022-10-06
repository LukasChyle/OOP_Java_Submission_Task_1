import javax.swing.*;

/*
Interaction is where all the messages and options with the user is made.

The program is running in a while-true loop in the class constructor,
a JOptionPane.showOptionDialog combined to a switch gives the user options to different tasks.

Object of the class Registry is also created in the constructor, where all the plants will be stored in a list.

Methods: addPlant, removePlant, printRation and printPlantsInfo.
 */
public class Interaction {

    private final Registry registry;
    boolean run;

    public Interaction() {

        registry = new Registry();
        run = true;

        while (run) {
            String[] options = {"Add plant", "Remove Plant", "Get plant ration", "Print list"};
            int choice = JOptionPane.showOptionDialog(null, "Choose next task", "Options",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);

            switch (choice) {
                case -1 -> run = false;
                case 0 -> addPlant();
                case 1 -> removePlant();
                case 2 -> printRation();
                case 3 -> printPlantsInfo();
            }
        }
        System.out.println("Exit");
    }

    // conversation with the user to create a new plant with unique name, a height and type.
    public void addPlant() {
        String name = JOptionPane.showInputDialog(null, "Enter name of the new plant");
        if (name == null) {
            return;
        } else if (registry.getPlant(name) != null) {
            JOptionPane.showMessageDialog(null, "Error: Plant named " + name + " already exists");
            return;
        }

        double height;
        String heightInput = JOptionPane.showInputDialog(null, "Height of the plant in meters?");
        if (heightInput == null) {
            return;
        }
        try {
            height = Double.parseDouble(heightInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: " + heightInput + ", is not a number");
            return;
        }

        String[] options = {PlantType.CACTUS.shape, PlantType.CARNIVOROUS.shape, PlantType.PALM.shape};
        int choice = JOptionPane.showOptionDialog(null, "Type of plant?", "Options",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);
        if (choice == -1) {
            return;
        }

        try {
            boolean added = switch (choice) {
                case 0 -> registry.addPlant(name, height, PlantType.CACTUS);
                case 1 -> registry.addPlant(name, height, PlantType.CARNIVOROUS);
                case 2 -> registry.addPlant(name, height, PlantType.PALM);
                default -> false;
            };
            if (!added) {
                JOptionPane.showMessageDialog(null, "Plant named " + name + " already exists");
            } else {
                registry.savePlantsToFile();
                JOptionPane.showMessageDialog(null, name + " was added");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e + ", plant was not added");
        }
    }

    // conversation with the user to remove a plant with a specific name if found.
    public void removePlant() {
        String name = JOptionPane.showInputDialog(null, "Enter name of the plant to remove");
        if (name != null) {
            boolean removed = registry.removePlant(name);
            if (removed) {
                registry.savePlantsToFile();
                JOptionPane.showMessageDialog(null, name + " was removed");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Plant " + name + " don't exists");
            }
        }
    }

    // conversation with the user to get the food ration for a specific plant, if found by name.
    public void printRation() {
        String name = JOptionPane.showInputDialog(null, "Enter name of the plant to feed");
        if (name != null) {
            String ration = registry.getRation(name);
            if (ration == null) {
                JOptionPane.showMessageDialog(null, "Error: Plant " + name + " don't exists");
            } else {
                JOptionPane.showMessageDialog(null, ration);
            }
        }
    }

    // prints information about all the existing plants
    public void printPlantsInfo() {
        String plants = registry.getPlantsInfo();
        if (plants == null) {
            JOptionPane.showMessageDialog(null, "There is no plants in the database");
        } else {
            JOptionPane.showMessageDialog(null, plants);
        }
    }
}