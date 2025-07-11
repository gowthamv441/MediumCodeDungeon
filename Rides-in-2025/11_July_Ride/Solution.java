class Solution {

    private void printSubset(
        int[] nums,
        List<Integer> subset,
        int idx,
        List<List<Integer>> result
    ) {
        /*
         * [LEARNING]
         * It's important to add a new ArrayList to result.
         * Adding just `subset` passes a reference, which causes future changes to reflect on all added lists.
         */
        result.add(new ArrayList<>(subset));

        for (int i = idx; i < nums.length; i++) {
            subset.add(nums[i]);
            printSubset(nums, subset, i + 1, result);
            subset.remove(subset.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        printSubset(nums, new ArrayList<>(), 0, result);
        return result;
    }
}
