public class Test {
    //solution organism
    private static Organism solution = new Organism();

    public static void main(String [] args) {

        //you may add new bodyparts and change the desired amounts of bodyparts
        solution.setGene("heads", 1);
        solution.setGene("arms", 2);
        solution.setGene("legs", 2);
        solution.setGene("wings", 0);
        Algorithm.setSolution(solution);

        //you may experiment with the numbers
        testingBatch(500, 10, false);
        //testingBatch(500, 1, true);
    }

    /**
     * runs the algorithm according to the input parameters and provides output
     * @param populationSize the size of the population, the bigger the size, the faster the solution is reached
     * @param batchAmount how many batches the user wants to test
     * @param individualOutput if true, information about every generation of the batch will be printed out
     */
    public static void testingBatch(int populationSize, int batchAmount, boolean individualOutput) {
        int generationCounter = 0;
        for (int i = 0; i < batchAmount; i++) {
            Population population = new Population(populationSize);
            int generationCount = 0;
            while (population.getFittest().getFitness() < Algorithm.getMaxFitness()) {
                if (individualOutput) {
                    System.out.println("Generation: " + generationCount);
                    System.out.println("Fittest: " + population.getFittest().getFitness() + "/" + Algorithm.getMaxFitness());
                    System.out.println("AverageFitness: " + Algorithm.getAverageFitness(population));
                    System.out.println();
                }
                population = Algorithm.evolvePopulation(population);
                generationCount++;
            }
            generationCounter += generationCount;

            System.out.println("-------------------------------------------Batch" + i);
            System.out.println("Solution - Batch " + i + ":");
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness()  + "/" + Algorithm.getMaxFitness());
            System.out.println("Genes:");
            System.out.println(population.getFittest());
        }
        System.out.println("-------------------------------------------");
        System.out.println("Average amount of generations to reach solution: " + generationCounter/(double) batchAmount);
    }
}