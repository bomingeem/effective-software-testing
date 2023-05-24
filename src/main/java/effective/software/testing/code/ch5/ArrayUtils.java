package effective.software.testing.code.ch5;

public class ArrayUtils {
    /**
     * @param array: 대상을 검색할 배열, 널일 수 있다.
     * @param valueToFind: 찾고자 하는 값
     * @param startIndex: 검색을 시작할 인덱스
     * @return
     */
    public static int indexOf(final int[] array, final int valueToFind, int startIndex) {
        if (array == null) {
            return -1;
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i=startIndex; i<array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
