import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UniTestExampleTest {

    private UniTestExample uniTestExample = new UniTestExample();

    public static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(1,1),
                Arguments.of(2,5),
                Arguments.of(6,12),
                Arguments.of(63,12)


        );
    }

    public static Stream<Arguments> provideArgumentsAndResults() {
        return Stream.of(
                Arguments.of(1,1,2),
                Arguments.of(2,5,7),
                Arguments.of(6,12,18),
                Arguments.of(63,12,75)


        );
    }


    @Test
    void shouldCorrectlyAddTwoIntegers() {
        int result = uniTestExample.add(1,1);
        assertEquals(2,result);

    }

    @ParameterizedTest
    @ValueSource(ints = {1,5,8})
    void shouldCorrectlyAddTowIntegers(int a) {
        int result = uniTestExample.add(a,1);
        assertEquals(a+1,result);
    }

    @ParameterizedTest
    @MethodSource(value = "provideArguments")
    void shouldCorrectlyAddTwoIntegers(int a,int b){
        int result = uniTestExample.add(a,b);
        assertEquals(a+b,result);
    }


    @ParameterizedTest
    @MethodSource(value = "provideArgumentsAndResults")
    void shouldAddTwoIntegers(int a, int b, int expectedResult) {
        int result = uniTestExample.add(a,b);
        assertEquals(expectedResult,result);
    }
}