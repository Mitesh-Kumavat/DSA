package Array;

public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        // Step 1: Check feasibility
        // If total gas < total cost → impossible to complete the circuit
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        // If overall gas is not enough, no starting point will work
        if (totalGas < totalCost)
            return -1;

        // Step 2: Greedy traversal to find valid start
        int start = 0; // potential starting index
        int currentGas = 0; // gas balance while traveling

        for (int i = 0; i < gas.length; i++) {

            // fill the gas at current station and subtract travel cost to move to next station
            currentGas += gas[i] - cost[i];

            // If we run out of gas before reaching next station:
            // It means all stations from 'start' to 'i' cannot be valid.
            if (currentGas < 0) {
                start = i + 1; // shift start to next index
                currentGas = 0; // reset balance
            }
        }

        // After the global check, 'start' is guaranteed to be the answer
        return start;
    }

    public static void main(String[] args) {
        int[] gas = { 1, 2, 3, 4, 5 };
        int[] cost = { 3, 4, 5, 1, 2 };

        int startIndex = canCompleteCircuit(gas, cost);
        System.out.println("Starting gas station index: " + startIndex); // Output: 3
        // Explanation: Starting at index 3 allows completing the circuit.
        // Dry run:
        // Start at index 3: gas = 4, cost to next = 1 → remaining gas = 3
        // Move to index 4: gas = 3 + 5 = 8, cost to next = 2 → remaining gas = 6
        // Move to index 0: gas = 6 + 1 = 7, cost to next = 3 → remaining gas = 4
        // Move to index 1: gas = 4 + 2 = 6, cost to next = 4 → remaining gas = 2
        // Move to index 2: gas = 2 + 3 = 5, cost to next = 5 → remaining gas = 0
        // Successfully returned to index 3 with 0 gas left.
    }
}
