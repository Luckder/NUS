import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class BonusTest {

    @Test
    public void BonusTest1() {
        int[] jobSizes = {1, 3, 5, 7, 9, 11, 10, 8, 6, 4};
        int processors = 3;

        System.out.println(Arrays.deepToString(Bonus.greedy(jobSizes, processors)));
        System.out.println(LoadBalancing.findLoad(jobSizes, processors));
    }
}
