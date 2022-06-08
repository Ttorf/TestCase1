package dataProvider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.HashMap;
import java.util.stream.Stream;

import static base.data.UserData.generationData;
import static utils.GeneratorValue.getRandomChar;
import static utils.GeneratorValue.getRandomNumber;

public class UserDataProvider {


    public static class PositiveValidationTest implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                                    "QWERty!123", "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка максимальной длины поля userName"),
                    Arguments.of(generationData(getRandomChar() + getRandomNumber(10, 88),
                                    "QWERty!123", "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка минимальной длины поля userName"),
                    Arguments.of(generationData(getRandomChar() + (10 + (int) (Math.random() * 80)),
                                    "QWEy!123", "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка минимальной длины поля password"),
                    Arguments.of(generationData(getRandomChar() + (10 + (int) (Math.random() * 80)),
                                    "QWEy!123ss1234567890Zxcvbnhgpp", "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка максимальной длины поля password"),
                    Arguments.of(generationData(getRandomChar() + (10 + (int) (Math.random() * 80)),
                                    "testRand!!Paswo" + getRandomChar() + +(100 + (int) (Math.random() * 800)),
                                    getRandomChar() + "@" + getRandomChar(), "FirstName", "LastName", "user"),
                            "Проверка минимальной длины поля email"),
                    Arguments.of(generationData(getRandomChar() + (10 + (int) (Math.random() * 80)),
                                    "testRand!!Paswo" + getRandomChar() + +(100 + (int) (Math.random() * 800)),
                                    "email@safasfasfasfsafasfsfsfs#s$sf^*ssafasfasfssafasfs112fsafassas"
                                            + getRandomNumber(100, 800), "FirstName", "LastName", "user"),
                            "Проверка максимальной длины поля email"),
                    Arguments.of(generationData("testUserRandom12" + (1000 + (int) (Math.random() * 800)),
                                    "QWERty!123", "test" + (1 + (int) (Math.random() * 800)) +
                                            "@gmail.com", "FirstName123FirstName123e123FirstName123e123FirstN",
                                    "LastName", "user"),
                            "Проверка максимальной длины поля firstName"),
                    Arguments.of(generationData("testUserRandom12" + (1000 + (int) (Math.random() * 800)),
                                    "QWERty!123", "test" + (1 + (int) (Math.random() * 800)) +
                                            "@gmail.com", "F", "LastName", "user"),
                            "Проверка минимальной длины длины поля firstName"),
                    Arguments.of(generationData("testUserRandom12" + (1000 + (int) (Math.random() * 800)),
                                    "QWERty!123", "test" + (1 + (int) (Math.random() * 800)) +
                                            "@gmail.com", "FirstName",
                                    "Lastname123!@#@#$%$#Lastname123!@#@#$%$#QWERTY3zxc", "user"),
                            "Проверка максимальной длины поля lastName"),
                    Arguments.of(generationData("testUserRandom12" + (1000 + (int) (Math.random() * 800)),
                                    "QWERty!123", "test" + (1 + (int) (Math.random() * 800)) +
                                            "@gmail.com", "firstName", "л", "user"),
                            "Проверка минимальной длины длины поля lastName"),
                    Arguments.of(generationData("testAdminRandom" + (1000 + (int) (Math.random() * 800)),
                                    "QWERty!123", "admin" + (1 + (int) (Math.random() * 800)) +
                                            "@gmail.com", "admin", "admin", "admin"),
                            "Проверка создания пользователя с ролью admin")
            );
        }

    }

    public static class NegativeTest implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(generationData("test123UserName1" + getRandomNumber(10000, 80000),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName", "LastName", "user")
                            , "Проверка создания с 21 символом для поля name", new HashMap<String, String>() {{
                                put("username", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "2qwer!@#$%^&**Q!;%:?*()_-=SАюж1",
                                    getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка создания с 31 символом для поля password", new HashMap<String, String>() {{
                                put("password", "Password must be no more than 30 characters in length.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "12345678p!",
                                    getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка создания с паролем без верхнего регистра", new HashMap<String, String>() {{
                                put("password", "Password must contain 1 or more uppercase characters.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "asdfghjQ!",
                                    getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка создания с паролем без цифр", new HashMap<String, String>() {{
                                put("password", "Password must contain 1 or more digit characters.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "asdfghjQ123",
                                    getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка создания с паролем без специальных символов", new HashMap<String, String>() {{
                                put("password", "Password must contain 1 or more special characters.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", "@gmail.com", "FirstName", "LastName",
                                    "user")
                            , "Проверка ввода некорректного email", new HashMap<String, String>() {{
                                put("email", "Must be valid email address.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", "email@", "FirstName", "LastName",
                                    "user")
                            , "Проверка ввода некорректного email", new HashMap<String, String>() {{
                                put("email", "Must be valid email address.");
                            }}
                    ),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123",
                                    "email@safasfasfasfsafasfsfsfs#s$sf^*ssafasfasfssafasfs112fsafassasf"
                                            + getRandomNumber(100, 800),
                                    "FirstName", "LastName", "user"),
                            "Проверка создания с 71 символом для email", new HashMap<String, String>() {{
                                put("email", "Must be valid email address.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "2QWERTYUIOP{}ASDFGHJKL:ZXCVB<>?!@#$%^&*()_+*?:%;№!1",
                                    "LastName", "user"),
                            "Проверка создания с 51 символом для поля firstName", new HashMap<String, String>() {{
                                put("firstName", "Length must be between 1 and 50 characters.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName",
                                    "2QWERTYUIOP{}ASDFGHJKL:ZXCVB<>?!@#$%^&*()_+*?:%;№!1", "user"),
                            "Проверка создания с 51 символом для поля firstName", new HashMap<String, String>() {{
                                put("lastName", "Length must be between 1 and 50 characters.");
                            }}),
                    Arguments.of(generationData(getRandomChar() + getRandomNumber(1, 9),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName", "LastName", "user")
                            , "Проверка создания логина длиной 3 символа", new HashMap<String, String>() {{
                                put("username", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "12345A!",
                                    getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName", "LastName", "user"),
                            "Проверка создания пароля длиной 7 символа", new HashMap<String, String>() {{
                                put("password", "Password must be 8 or more characters in length.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "",
                                    "LastName", "user"),
                            "Проверка создания с 0 символов для поля firstName", new HashMap<String, String>() {{
                                put("firstName", "Length must be between 1 and 50 characters.Field is mandatory.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName",
                                    "", "user"),
                            "Проверка создания с 0 символов для поля lastname", new HashMap<String, String>() {{
                                put("lastName", "Length must be between 1 and 50 characters.Field is mandatory.");
                            }}),
                    Arguments.of(generationData(null,
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName",
                                    "LastName", "user"),
                            "Проверка создания c null  в username", new HashMap<String, String>() {{
                                put("username", "Field is mandatory.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    null, getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName",
                                    "LastName", "user"),
                            "Проверка создания c null  в password", new HashMap<String, String>() {{
                                put("password", "Field is mandatory.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", null, "FirstName",
                                    "LastName", "user"),
                            "Проверка создания c null  в email", new HashMap<String, String>() {{
                                put("email", "Field is mandatory.");
                            }}),

                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", null,
                                    "LastName", "user"),
                            "Проверка создания c null  в firstName", new HashMap<String, String>() {{
                                put("firstName", "Field is mandatory.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName",
                                    null, "user"),
                            "Проверка создания c null  в lastName", new HashMap<String, String>() {{
                                put("lastName", "Field is mandatory.");
                            }}),
                    Arguments.of(generationData("testUserName" + getRandomNumber(1, 800),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@gmail.com", "FirstName",
                                    "LastName", null),
                            "Проверка создания c null  в role", new HashMap<String, String>() {{
                                put("role", "Field is mandatory.");
                            }})


            );
        }
    }

    public static class NegativeTestUser implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(generationData(getRandomChar() + "testUserName" + getRandomChar() + getRandomNumber(10, 80000),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@mail.com", "FirstName",
                                    "LastName", "user"),
                            "Проверка создания пользователя с ролью User из под логина с ролью User", generationData(getRandomChar() + "testUserName" + getRandomChar() + getRandomNumber(10, 80000),
                                    "QWERty!123", getRandomChar() + getRandomNumber(1, 800) +
                                            "@mail.com", "FirstName",
                                    "LastName", "user"))
            );
        }
    }
}
