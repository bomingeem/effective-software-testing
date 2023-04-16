package effective.software.testing.code.ch1;

import lombok.Getter;

@Getter
public class Estimate {
    private String developer;
    private int estimate;

    public Estimate(String developer, int estimate) {
        this.developer = developer;
        this.estimate = estimate;
    }
}
