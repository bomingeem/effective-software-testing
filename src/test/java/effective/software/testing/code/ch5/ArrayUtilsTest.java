package effective.software.testing.code.ch5;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.*;

class ArrayUtilsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testIndexOf(int[] array, int valueToFind, int startIndex, int expectedResult) {
        int result = ArrayUtils.indexOf(array, valueToFind, startIndex);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> testCases() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};

        /**
         * T1: array null
         * T2: array 요소 하나, array에 valueToFind가 있는 경우
         * T3: array 요소 하나, array에 valueToFind가 없는 경우
         * T4: startIndex가 음수, array에 값이 있는 경우
         * T5: startIndex가 array의 범위 밖에 있는 경우
         * T6: array 요소 다수, array에 valueToFind가 있으며, startIndex가 valueToFind 이후인 경우
         * T7: array 요소 다수, array에 valueToFind가 있으며, startIndex가 valueToFind 이전인 경우
         * T8: array 요소 다수, array에 valueToFind가 있으며, startIndex가 valueToFind의 정확한 위치인 경우
         * T9: array 요소 다수, array에 valueToFind가 여러개 있으며, startIndex가 valueToFind 이전인 경우
         * T10: array 요소 다수, array에 valueToFind가 여러개 있으며, 그 중 하나가 startIndex 이전에 있는 경우
         * T11: array 요소 다수, array에 valueToFind가 없는 경우
         */
        return Stream.of(
                of(null, 1, 1, -1),
                of(new int[]{1}, 1, 0, 0),
                of(new int[]{1}, 2, 0, -1),
                of(array, 1, 10, -1),
                of(array, 2, -1, 1),
                of(array, 4, 6, -1),
                of(array, 4, 1, 3),
                of(array, 4, 3, 3),
                of(array, 4, 1, 3),
                of(array, 4, 4, 5),
                of(array, 8, 0, -1)
        );
    }

    /**
     * @param numbers: 무작위 정수 리스트, 크기는 100이며 -1000에서 1000 사이의 값을 포함
     * @param value: 리스트에 추가할 임의의 정수, 1001에서 2000 범위의 값을 생성하여 값이 리스트에 존재하지 않도록 보장
     * @param indexToAddElement: 추가할 무작위 요소의 인덱스를 나타내는 임의의 정수, 인덱스의 범위는 0에서 99
     * @param startIndex: 메서드가 검색을 시작할 인덱스를 나타내는 임의의 정수, 0에서 99 사이의 임의의 숫자
     */
    @Property
    void indexOfShouldFindFirstValue(@ForAll @Size(value = 100)List<@IntRange(min = -1000, max = 1000) Integer> numbers,
                                     @ForAll @IntRange(min = 1001, max = 2000) int value,
                                     @ForAll @IntRange(max = 99) int indexToAddElement,
                                     @ForAll @IntRange(max = 99) int startIndex) {
        int[] array = convertListToArray(numbers);
        int expectedIndex = indexToAddElement >= startIndex ? indexToAddElement : -1;

        Assertions.assertThat(ArrayUtils.indexOf(array, value, startIndex))
                .isEqualTo(expectedIndex);
    }

    private int[] convertListToArray(List<Integer> numbers) {
        return numbers.stream().mapToInt(x -> x).toArray();
    }
}
