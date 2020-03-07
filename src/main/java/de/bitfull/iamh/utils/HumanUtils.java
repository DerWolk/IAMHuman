package de.bitfull.iamh.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.bitfull.iamh.pojo.HumanInput;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class HumanUtils {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final PasswordGenerator generator = new PasswordGenerator();


    public static HumanInput readJson(String resourcePath) throws IOException {
        return GSON.fromJson(Files.readString(Paths.get(resourcePath).toAbsolutePath(), StandardCharsets.UTF_8), HumanInput.class);
    }

    public static String generatePassword(int length) {
        return generator.generatePassword(length, List.of(
                new CharacterRule(EnglishCharacterData.Digit, 2),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1)
        ));
    }

    public static String generatePassword(int length, CharacterRule... rules) {
        return generator.generatePassword(length, Arrays.asList(rules));
    }
}
