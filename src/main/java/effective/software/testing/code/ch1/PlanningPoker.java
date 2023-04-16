package effective.software.testing.code.ch1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlanningPoker {
    /**
     * 입력: Estimate 목록. 각 항목은 개발자의 이름과 추정값을 포함한다.
     * 출력: String 목록. 가장 낮은 추정값과 가장 높은 추정값을 제시한 개발자의 이름을 포함해야 한다.
     */
    public List<String> identifyExtremes(List<Estimate> estimates) {

        if (estimates == null) {
            throw new IllegalArgumentException("estimates cannot be null");
        }

        if (estimates.size() <= 1) {
            throw new IllegalArgumentException("there has to be more than 1 estimate in the list");
        }

        Estimate lowestEstimate = null;
        Estimate highestEstimate = null;

        for (Estimate estimate : estimates) {
            if (highestEstimate == null || estimate.getEstimate() > highestEstimate.getEstimate()) {
                highestEstimate = estimate;
            }
            if (lowestEstimate == null || estimate.getEstimate() < lowestEstimate.getEstimate()) {
                lowestEstimate = estimate;
            }
        }

        if (lowestEstimate.equals(highestEstimate)) {
            return Collections.emptyList();
        }

        return Arrays.asList(
                lowestEstimate.getDeveloper(),
                highestEstimate.getDeveloper()
        );
    }
}
