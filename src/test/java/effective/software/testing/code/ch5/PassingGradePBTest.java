package effective.software.testing.code.ch5;

import net.jqwik.api.*;
import net.jqwik.api.constraints.FloatRange;

import static org.assertj.core.api.Assertions.*;
class PassingGradePBTest {

    private final PassingGrade passingGrade = new PassingGrade();

    /**
     * fail: 1.0(포함)에서 5.0(제외)까지의 범위에 있는 모든 수에 대해 프로그램은 false를 반환한다.
     */
    @Property
    void fail(@ForAll @FloatRange(min = 1f, max = 5f, maxIncluded = false) float grade) {
        assertThat(passingGrade.passed(grade)).isFalse();
    }

    /**
     * pass: 5.0(포함)에서 10.0(포함)까지의 범위에 있는 모든 수에 대해 프로그램은 true를 반환한다.
     */
    @Property
    void pass(@ForAll @FloatRange(min = 5f, max = 10f) float grade) {
        assertThat(passingGrade.passed(grade)).isTrue();
    }

    /**
     * invalid: 유효하지 않은 모든 등급(1.0 미만이거나 10.0 초과인 어떤 수)에 대해, 프로그램은 예외를 던진다.
     */
    @Property
    void invalid(@ForAll("invalidGrades") float grade) {
        assertThatThrownBy(() -> {
            passingGrade.passed(grade);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * 값을 제공하는 메서드
     * 메서드가 무작위 반환값을 만들도록 한다. (1.0보다 작은 실수 또는 10.0보다 큰 실수)
     */
    @Provide
    private Arbitrary<Float> invalidGrades() {
        return Arbitraries.oneOf(
                Arbitraries.floats().lessThan(1f),
                Arbitraries.floats().greaterThan(10f)
        );

    }

}
