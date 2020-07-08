package twin;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwinCharsTest {

    @Test
    void checkThatSumOfGroupsIsCalculatedCorrect() {
        //Given
        String text1 = "SPD-University";
        String text2 = "JAvaJava";

        //Action
        int actualText1 = new TwinChars(text1).calcDifference();
        int actualText2 = new TwinChars(text2).calcDifference();

        //Assert
        assertEquals(actualText1, 34);
        assertEquals(actualText2, 95);
    }

    @Test
    void checkThatOutputIsAsExpected() {
        //Given
        String text1 = "SPD-University";
        String text2 = "JAvaJava";

        String outputForText1 = "The difference between amounts is 34\n" +
                "Odd group\n" +
                "'S' 83\n" +
                "'U' 85\n" +
                "'E' 69\n" +
                "'I' 73\n" +
                "'Y' 89\n" +
                "'-' 45\n" +
                "Even group\n" +
                "'P' 80\n" +
                "'R' 82\n" +
                "'D' 68\n" +
                "'T' 84\n" +
                "'V' 86\n" +
                "'N' 78\n";

        String outputForText2 = "The difference between amounts is 95\n" +
                "Odd group\n" +
                "'A' 65\n" +
                "Even group\n" +
                "'V' 86\n" +
                "'J' 74\n";

        //Action
        String actualForText1 = null;
        String actualForText2 = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             PrintStream mem = new PrintStream(bos)) {

            PrintStream tmp = System.out;
            System.setOut(mem);

            new TwinChars(text1).printInfo();
            actualForText1 = new String(bos.toByteArray());
            bos.reset();
            new TwinChars(text2).printInfo();
            actualForText2 = new String(bos.toByteArray());

            System.setOut(tmp);
        } catch (IOException ignore) {
        }

        //Assert
        assertEquals(outputForText1, actualForText1);
        assertEquals(outputForText2, actualForText2);

    }

    @Test
    void checkThatRegisterIsIgnoredAndSumOfGroupsIsEquals() {
        //Given
        String text1 = "JavaJava";
        String text2 = "JAVAjava";

        //Action
        int actualText1 = new TwinChars(text1).calcDifference();
        int actualText2 = new TwinChars(text2).calcDifference();

        //Assert
        assertEquals(actualText1, actualText2);
    }

    @Test
    void checkForUniqueCharacter() {
        //Given
        String text1 = "JavaJavaJavaJavaJava";
        String text2 = "JAVAjava";

        //Action
        int actualText1 = new TwinChars(text1).calcDifference();
        int actualText2 = new TwinChars(text2).calcDifference();

        //Assert
        assertEquals(actualText1, actualText2);
    }
}