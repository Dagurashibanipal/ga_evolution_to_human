public class Algorithm {
    //how much of the new individual after crossover should (on average) be from the first parent, rest from the second
    private static double uniformRate = 0.5;
    //rate at which random individuals should mutate to a possibly entirely different bodypart count
    private static double mutationRate = 0.015;
    //population to be filled with new, evolved individuals
    private static Population newGeneration;
    //organism depicting what we want to reach through "evolution"
    static private Organism solution;

    /**
     * main method of this class, first selects the fittest of the previous generation, then fills with crossover and mutation till the normal populationsize is reached
     * @param population population of the previous generation, which we want to evolve
     * @return returns the evolved population
     */
    public static Population evolvePopulation(Population population) {
        int populationSize = population.getPopulation().size();
        newGeneration = new Population(0);
        selection(population);

        while (newGeneration.getPopulation().size() < populationSize) {
            int index1 = (int) (Math.random()*100) % populationSize;
            int index2 = (int) (Math.random()*100) % populationSize;
            Organism parent1 = population.getOrganism(index1);
            Organism parent2 = population.getOrganism(index2);
            if (Math.random() <= mutationRate) {
                mutate(parent1);
            } else {
                newGeneration.getPopulation().add(crossover(parent1, parent2));
            }
        }
    return newGeneration;
}

    /**
     * eliminates a part of the population, with "weaker" organisms having a higher chance to be eliminated
     * @param population specifies the population to select from (previous generation)
     */
    private static void selection(Population population) {
        for (int i = 0; i < population.getPopulation().size(); i++) {
            if ((population.getOrganism(i).getFitness() / (double) getMaxFitness()) > Math.random()) {
                newGeneration.getPopulation().add(population.getOrganism(i));
            }
        }
    }

    /**
     * creates a new organism consisting of parts of infividual1 and individual2
     * @param individual1 first parent of the new organism
     * @param individual2 second parent of the new prganism
     * @return returns the new organism created through crossover
     */
    private static Organism crossover(Organism individual1, Organism individual2) {
        Organism child = new Organism(solution);
        for (String bodypart : child.getOrganism().keySet()) {
            if (Math.random() <= uniformRate) {
                child.setGene(bodypart, individual1.getGene(bodypart));
            } else {
                child.setGene(bodypart, individual2.getGene(bodypart));
            }
        }
        return child;
    }

    /**
     * changes the given organism to consist of entirely random amounts of bodyparts
     * @param individual the organism to be mutated
     */
    private static void mutate(Organism individual) {
        for (String bodypart : individual.getOrganism().keySet()) {
            int newAmount = (int) ((Math.random() * 100) % 17);
            individual.getOrganism().put(bodypart, newAmount);
        }
    }

    /**
     * change the target to evolve to
     * @param individual organism we want to evolve to
     */
    public static void setSolution(Organism individual) {
        solution = individual;
    }

    /**
     * retrieve the solution organism
     * @return the solution organism
     */
    public static Organism getSolution() {
        return solution;
    }

    /**
     * calculates a made-up definition of fitness by adding the differences of bodypart amounts between the given organism and the solution organism
     * @param individual the organism to compute the fitness level for
     * @return the fitness level of the organism as positive integer
     */
    static int getFitness(Organism individual) {
        int fitness = 0;
        for (String bodypart : individual.getOrganism().keySet()) {
            int helper = individual.getGene(bodypart) - solution.getGene(bodypart);
            if (helper<0) {
                helper *= -1;
            }
            helper = 16 - helper;
            fitness += helper;
        }
        return fitness;
    }

    /**
     * calculates the average fitness of the organisms of the given population
     * @param population the population to get the average fitness for
     * @return the average fitness as real number
     */
    static double getAverageFitness(Population population) {
        int counter = 0;
        for (int i = 0; i < population.getPopulation().size(); i++) {
            counter += getFitness((Organism) population.getPopulation().get(i));
        }
        return counter/population.getPopulation().size();
    }

    /**
     * @return the fitness of the solution organism - the fitness level the organisms try to reach
     */
    static int getMaxFitness() {
        int maxFitness = getFitness(solution);
        return maxFitness;
    }
}
