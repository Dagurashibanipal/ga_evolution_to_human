import java.util.ArrayList;

public class Population {

    //ArrayList of organisms which will take part in the simulated evolution process
    private ArrayList organisms;

    /**
     * initialises populationSize amount of organisms with the same bodyparts as the desired organism
     * @param populationSize number of organisms to evolve
     */
    public Population(int populationSize) {
        organisms = new ArrayList<Organism>();
        for (int i = 0; i < populationSize; i++) {
            organisms.add(new Organism(Algorithm.getSolution()));
        }
    }

    /**
     * @return the ArrayList consisting of the organisms
     */
    public ArrayList getPopulation() {
        return organisms;
    }

    /**
     * @param index of the organism in the organism ArrayList
     * @return the desired organism
     */
    public Organism getOrganism(int index) {
        if (!(organisms.get(index) instanceof Organism)) {
            System.err.println("Error in getOrganism: Object not instance of Organism");
            return null;
        }
        return (Organism) organisms.get(index);
    }

    /**
     * @return the fittest organism of this population (for closer description see Algorithm.getFitness())
     */
    public Organism getFittest() {
        Organism fittest = (Organism) organisms.get(0);
        for (int i = 0; i < organisms.size(); i++) {
            if (fittest.getFitness() <= getOrganism(i).getFitness()) {
                fittest = getOrganism(i);
            }
        }
        return fittest;
    }

    @Override
    public String toString() {
        String string = "";

        for (int i = 0; i < getPopulation().size(); i++) {
            string += "\n" + getPopulation().get(i).toString() + "\n";
        }

        return string;
    }
}
