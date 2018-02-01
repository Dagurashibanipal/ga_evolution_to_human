import java.util.HashMap;

public class Organism {

    //mapping of the bodypart to the number of bodyparts of this type of this organism
    //bodyparts != null
    private HashMap<String,Integer> bodyparts;
    //how close the number of bodyparts is to the number of bodyparts of the desired organism, relative to the maximum of bodyparts possible
    //fitness >= 0
    private int fitness = 0;


    public Organism() {
        bodyparts = new HashMap<>();
    }

    /**
     * initialises the organism with the same bodyparts as the desired organism, set to random amounts
     * @param solution number of individuals to evolve
     */
    public Organism(Organism solution) {
        bodyparts = new HashMap<>();
        for (String bodypart : solution.getOrganism().keySet()) {
            setGene(bodypart, (int) (Math.random()*100) % 17);
        }
    }

    /**
     * updates the fitness of this organism
     * @return the fitness of
     */
    public int getFitness() {
        return fitness = Algorithm.getFitness(this);
    }

    /**
     * @return returns the bodypart map of this organism
     */
    public HashMap<String, Integer> getOrganism() {
        return bodyparts;
    }

    /**
     * @param bodypart name of the bodypart
     * @return the amount of bodyparts of this type the organism has
     */
    public int getGene(String bodypart) {
        return bodyparts.get(bodypart);
    }

    /**
     * @param bodypart name of the bodypart
     * @param amount amount of bodyparts of this bodypart the organism gets
     */
    public void setGene(String bodypart, int amount) {
        if (amount < 0 || amount > 16) {
            return;
        }
        bodyparts.put(bodypart, amount);
    }

    public String toString() {
        String string = "   ";
        for (String bodypart : bodyparts.keySet()) {
            string += bodypart + ": " + bodyparts.get(bodypart) + "\t";
        }
        return string;
    }
}