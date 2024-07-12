import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        assertEquals("Should return 1 for an empty array", 0, Solution.increasingMovableSubarrayCount(nums));
    }

    @Test
    public void testSingleElementArray() {
        int[] nums = {4};
        assertEquals("Should return 1 for an array with a single element", 1, Solution.increasingMovableSubarrayCount(nums));
    }

    @Test
    public void testIncreasingArray() {
        int[] nums = {1, 2, 3, 4};
        assertEquals("Should return 10 for an increasing array", 10, Solution.increasingMovableSubarrayCount(nums));
    }


    @Test
    public void testMixedArray() {
        int[] nums = {6,5,7,8};
        // The expected result depends on the implementation of isIncreasingArray.
        // Adjust the expected value according to how isIncreasingArray handles such cases.
        assertEquals("Should return a specific value for a mixed array", 7, Solution.increasingMovableSubarrayCount(nums));
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] nums = {8,7,6,6};
        // The expected result depends on how isIncreasingArray treats duplicate numbers.
        // Adjust the expected value accordingly.
        assertEquals("Should return a specific value for an array with duplicates", 3, Solution.increasingMovableSubarrayCount(nums));
    }

}
