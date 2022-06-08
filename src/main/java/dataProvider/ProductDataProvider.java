package dataProvider;

import base.data.ProductData;
import base.data.UserData;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.HashMap;
import java.util.stream.Stream;

import static base.data.ProductData.generationData;
import static utils.GeneratorValue.getRandomChar;
import static utils.GeneratorValue.getRandomNumber;

public class ProductDataProvider {
    public static class PositiveTestAsAdmin implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(generationData("qwertyuioplkjhgfdsaz", "sadsa", 533),
                            "Проверка максимальной длины поля name"),
                    Arguments.of(generationData("qwe", "softEng", 533),
                            "Проверка минимальной длины поля name"),
                    Arguments.of(generationData("asdsa", "qweaghjuy5trgfvbnhjy", 533),
                            "Проверка максимальной длины поля description"),
                    Arguments.of(generationData("sadasf", "3fq", 533),
                            "Проверка минимальной длины поля description"),
                    Arguments.of(generationData("test" + (1 + (int) (Math.random() * 6)), "Lesson",
                            533), "Проверка максимальной длины поля price"),
                    Arguments.of(generationData("test" + (1 + (int) (Math.random() * 6)), "Lesson",
                            1), "Проверка минимальной длины поля price"),
                    Arguments.of(generationData("test" + (1 + (int) (Math.random() * 6)), "Lesson",
                            null), "Проверка создания документа без необязательного поля price")
            );
        }
    }

    public static class PositiveTestAsUser implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(generationData("qwertyuioplkjhgfdsaz", "sadsa", 533),
                            "Проверка максимальной длины поля name", UserData.generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                                    "QWERty!123", getRandomChar() + "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(generationData("qwe", "softEng", 533),
                            "Проверка минимальной длины поля name", UserData.generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                                    "QWERty!123", getRandomChar() + "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(generationData("asdsa", "qweaghjuy5trgfvbnhjy", 533),
                            "Проверка максимальной длины поля description", UserData.generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                                    "QWERty!123", getRandomChar() + "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(generationData("sadasf", "3fq", 533),
                            "Проверка минимальной длины поля description", UserData.generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                                    "QWERty!123", getRandomChar() + "test" + (1 + (int) (Math.random() * 10000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(generationData("test" + (1 + (int) (Math.random() * 6)), "Lesson",
                            533), "Проверка максимальной длины поля price", UserData.generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                            "QWERty!123", getRandomChar() + "test" + (1 + (int) (Math.random() * 10000)) +
                                    "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(generationData("test" + (1 + (int) (Math.random() * 6)), "Lesson",
                            1), "Проверка минимальной длины поля price", UserData.generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                            "QWERty!123", getRandomChar() + "test" + (1 + (int) (Math.random() * 10000)) +
                                    "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(generationData("test" + (1 + (int) (Math.random() * 6)), "Lesson",
                            null), "Проверка создания документа без необязательного поля price", UserData.generationData("testUserRandom12" + (1000 + (int) (Math.random() * 8000)),
                            "QWERty!123", getRandomChar() + "test" + (1 + (int) (Math.random() * 10000)) +
                                    "@gmail.com", "FirstName", "LastName", "user"))
            );
        }
    }

    public static class NegativeTestAdmin implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new ProductData().setDescription("softEng").setName("Lessonasdasdsafasfsag").setPrice(533),
                            "Проверка создания с 21 символом для поля name", new HashMap<String, String>() {{
                                put("name", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName("аа").setPrice(533),
                            "Проверка создания с 2 символами для поля name", new HashMap<String, String>() {{
                                put("name", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName("bb").setPrice(533),
                            "Проверка создания с 2 символами для поля name", new HashMap<String, String>() {{
                                put("name", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName(12325).setPrice(533),
                            "Проверка некорректного типа значения поля name", new HashMap<String, String>() {{
                                put("name", "?");
                            }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName(null).setPrice(533),
                            "Проверка обязательного ввода в поле name", new HashMap<String, String>() {{
                                put("name", "Field is mandatory.");
                            }}),
                    Arguments.of(new ProductData().setDescription(null).setName("asdfg").setPrice(533),
                            "Проверка обязательного ввода в поле description", new HashMap<String, String>() {{
                                put("description", "Field is mandatory.");
                            }}),
                    Arguments.of(new ProductData().setDescription("asfaasfasfasasfaasfas").setName("asdfg").setPrice(533),
                            "Проверка создания с 21 символом для поля description", new HashMap<String, String>() {{
                                put("description", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(new ProductData().setDescription("аа").setName("asdfg").setPrice(533),
                            "Проверка создания с 2 символами для поля description", new HashMap<String, String>() {{
                                put("description", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(new ProductData().setDescription("bb").setName("asdfg").setPrice(533),
                            "Проверка создания с 2 символами для поля description", new HashMap<String, String>() {{
                                put("description", "Length must be between 3 and 20 characters.");
                            }}),
                    Arguments.of(new ProductData().setDescription(533.56).setName("sdfsd").setPrice(533),
                            "Проверка некорректного типа значения поля description", new HashMap<String, String>() {{
                                put("description", "?");
                            }}),

                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(10000000),
                            "Проверка больше 7 чисел для поля price", new HashMap<String, String>() {{
                                put("price", "Maximum number of integral digits is 7, maximum number of fractional digits is 2");
                            }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(1.984),
                            "Проверка больше двух чисел после запятой для поля price", new HashMap<String, String>() {{
                                put("price", "Maximum number of integral digits is 7, maximum number of fractional digits is 2");
                            }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(-100),
                            "Проверка отрицательного числа в поле price", new HashMap<String, String>() {{
                                put("price", "Value must be positive");
                            }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(0),
                            "Проверка числа 0 в поле price", new HashMap<String, String>() {{
                                put("price", "Value must be positive");
                            }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice("sdfsd"),
                            "Проверка некорректного типа значения поля price", new HashMap<String, String>() {{
                                put("price", "Cannot deserialize value of type 'double' from String");
                            }})
            );
        }
    }

    public static class NegativeTestUser implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(generationData("Lessonasdasdsafaaaa" + getRandomNumber(10,90),"softEng",533),
                            "Проверка создания с 21 символом для поля name", new HashMap<String, String>() {{
                                put("name", "Length must be between 3 and 20 characters.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("softEng").setName("аа").setPrice(533),
                            "Проверка создания с 2 символами для поля name", new HashMap<String, String>() {{
                                put("name", "Length must be between 3 and 20 characters.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("softEng").setName("bb").setPrice(533),
                            "Проверка создания с 2 символами для поля name", new HashMap<String, String>() {{
                                put("name", "Length must be between 3 and 20 characters.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("softEng").setName(12325).setPrice(533),
                            "Проверка некорректного типа значения поля name", new HashMap<String, String>() {{
                                put("name", "?");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("softEng").setName(null).setPrice(533),
                            "Проверка обязательного ввода в поле name", new HashMap<String, String>() {{
                                put("name", "Field is mandatory.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription(null).setName("asdfg").setPrice(533),
                            "Проверка обязательного ввода в поле description", new HashMap<String, String>() {{
                                put("description", "Field is mandatory.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("asfaasfasfasasfaasfas").setName("asdfg").setPrice(533),
                            "Проверка создания с 21 символом для поля description", new HashMap<String, String>() {{
                                put("description", "Length must be between 3 and 20 characters.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("аа").setName("asdfg").setPrice(533),
                            "Проверка создания с 2 символами для поля description", new HashMap<String, String>() {{
                                put("description", "Length must be between 3 and 20 characters.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("bb").setName("asdfg").setPrice(533),
                            "Проверка создания с 2 символами для поля description", new HashMap<String, String>() {{
                                put("description", "Length must be between 3 and 20 characters.");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription(533.56).setName("sdfsd").setPrice(533),
                            "Проверка некорректного типа значения поля description", new HashMap<String, String>() {{
                                put("description", "?");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),

                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(10000000),
                            "Проверка больше 7 чисел для поля price", new HashMap<String, String>() {{
                                put("price", "Maximum number of integral digits is 7, maximum number of fractional digits is 2");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(1.984),
                            "Проверка больше двух чисел после запятой для поля price", new HashMap<String, String>() {{
                                put("price", "Maximum number of integral digits is 7, maximum number of fractional digits is 2");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(-100),
                            "Проверка отрицательного числа в поле price", new HashMap<String, String>() {{
                                put("price", "Value must be positive");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(0),
                            "Проверка числа 0 в поле price", new HashMap<String, String>() {{
                                put("price", "Value must be positive");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user")),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice("sdfsd"),
                            "Проверка некорректного типа значения поля price", new HashMap<String, String>() {{
                                put("price", "Cannot deserialize value of type 'double' from String");
                            }}, UserData.generationData("testUserRandom" + (1 + (int) (Math.random() * 80000)),
                                    "QWERty!123", getRandomChar() + "qwerty" + (1 + (int) (Math.random() * 100000)) +
                                            "@gmail.com", "FirstName", "LastName", "user"))
            );
        }
    }
}
