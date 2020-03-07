package de.bitfull.iamh;

import de.bitfull.iamh.pojo.HumanInput;
import de.bitfull.iamh.utils.HumanUtils;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IAMHumanTest {


    @Test
    public void test() {
        System.out.println(Paths.get("src/main/java/resources/input.json").toAbsolutePath());
        String s = HumanUtils.generatePassword(8);
        System.out.println(s);
    }

    @Test
    public void parsing() throws Exception {

        HumanInput humanInput = HumanUtils.readJson("src/test/resources/input.json");
        assertThat(humanInput).isNotNull().hasNoNullFieldsOrProperties();

        IAMHuman mockedHuman = mock(IAMHuman.class);
        PowerMockito.whenNew(IAMHuman.class).withAnyArguments().thenReturn(mockedHuman);

        String function = ">>randompw(8)";
        when(mockedHuman.checkFunction(function)).thenCallRealMethod();
        String generatedPW = mockedHuman.checkFunction(function);
        assertThat(generatedPW).doesNotContain(">>").doesNotContain(")");
        assertThat(generatedPW.length()).isEqualTo(8);

        function = ">>randomnumber(1-10)";
        when(mockedHuman.checkFunction(function)).thenCallRealMethod();
        String generatedNumber = mockedHuman.checkFunction(function);
        assertThat(Integer.parseInt(generatedNumber)).isBetween(1,10);
    }
}
