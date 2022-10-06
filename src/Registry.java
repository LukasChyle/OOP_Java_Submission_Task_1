import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Registry is where the list of plants are stored,
with methods to edit the list and communicate with objects in the list

Methods: addPlant, removePlant, getRation, getAllPlants, getPlantsInfo, getPlant
and getSizeOfPlants(test purpose)
 */

public class Registry {

    private final List<Plant> plants;
    private java.util.Date date;
    private final SimpleDateFormat sdf;
    private final String fileName;

    public Registry() {
        plants = new ArrayList<>();
        sdf = new SimpleDateFormat("HH:mm:ss");
        fileName = "plants.txt";
        fetchPlantsFromFile();
    }

    // adds a plant if name don't exist, rethrows exception if name is blank or height is negative or zero.
    public boolean addPlant(String name, double height, PlantType type) throws IllegalArgumentException {
        if (getPlant(name) == null) {
            switch (type) {
                case CACTUS -> plants.add(new Cactus(name, height));
                case CARNIVOROUS -> plants.add(new CarnivorousPlant(name, height));
                case PALM -> plants.add(new Palm(name, height));
            }
            return true;
        }
        return false;
    }

    // if a plant with given name exists, it is removed.
    public boolean removePlant(String name) {
        Plant p = getPlant(name.trim());
        if (p == null) {
            return false;
        } else {
            plants.remove(p);
            return true;
        }
    }

    // returns the interface method getRation from object, if plant with given name exists.
    public String getRation(String name) {
        Plant p = getPlant(name);
        if (p == null) {
            return null;
        } else {
            return p.getRation();
        }
    }

    // retrieves strings from all the plants interface method getInfo, and returns them as one string.
    public String getPlantsInfo() {
        List<Informable> infoList = new ArrayList<>(plants);
        String s = "";
        for (Informable i : infoList) {
            s += i.getInfo() + "\n";
        }
        if (s.isBlank()) {
            return null;
        }
        return s;
    }

    // checks if a plant with given name exists, and returns it if found.
    public Plant getPlant(String name) {
        for (Plant p : plants) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    // makes a string with all the plants inside and then stores them to a file, overwrites the old one.
    public void savePlantsToFile() {
        String fileString = "";
        for (Plant p : plants) {
            String name = p.getName();
            String height = String.valueOf(p.getHeight());
            String type = String.valueOf(p.getPlantType());
            fileString += name + " " + height + " " + type + "\n";
        }
        date = new java.util.Date(); //updates the timeStamp
        FileOutputStream newFile = null;
        try {
            newFile = new FileOutputStream(fileName, false);
            byte[] stringInBytes = fileString.getBytes();
            newFile.write(stringInBytes);

            System.out.println(sdf.format(date) + " update got saved to " + fileName);
        } catch (IOException e) {
            System.out.println(sdf.format(date) + "Error: list of plants didn't get saved to " + fileName + "\n" + e);
        } finally {
            try {
                if (newFile != null) {
                    newFile.close();
                }
            } catch (IOException e) {
                System.out.println(sdf.format(date) + " " + fileName + " did not get closed\n" + e);
            }
        }
    }

    // fetches all the plants from the plants.txt and stores them in the list plants.
    private void fetchPlantsFromFile() {
        Scanner fileScan = null;
        try {
            fileScan = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + fileName + " was not found\n" + e);
        }

        if (!(fileScan == null)) {
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();

                if (!line.isBlank()) {
                    date = new java.util.Date(); //updates the timeStamp

                    String typeString = line.substring(line.lastIndexOf(" ")).trim();
                    line = line.substring(0, line.lastIndexOf(" "));
                    String heightString = line.substring(line.lastIndexOf(" ")).trim();
                    String name = line.substring(0, line.lastIndexOf(" ")).trim();

                    double height = Double.parseDouble(heightString);
                    PlantType type = PlantType.valueOf(typeString);

                    addPlant(name, height, type);
                    System.out.println(sdf.format(date) + " " + name + " " + height + " " + type.shape +
                            " got fetched from the " + fileName);
                }
            }
        }
    }

    //implemented to test purpose.
    public int getSizeOfPlants() {
        return plants.size();
    }
}
