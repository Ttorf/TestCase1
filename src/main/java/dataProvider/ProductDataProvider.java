package dataProvider;

import base.data.ProductData;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.HashMap;
import java.util.stream.Stream;

public class ProductDataProvider {
    public static class PositiveTest implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new ProductData().setDescription("sadsa").setName("qwertyuioplkjhgfdsaz").setPrice(533), "Проверка максимальной длины поля name"),
                    Arguments.of(new ProductData().setDescription("softEng").setName("qwe").setPrice(533), "Проверка минимальной длины поля name"),
                    Arguments.of(new ProductData().setDescription("qweaghjuy5trgfvbnhjy").setName("asdsa").setPrice(533), "Проверка максимальной длины поля description"),
                    Arguments.of(new ProductData().setDescription("3fq").setName("sadasf").setPrice(533), "Проверка минимальной длины поля description"),
                    Arguments.of(new ProductData().setDescription("Lesson").setName("test" + (1 + (int) (Math.random() * 6))).setPrice(1000000), "Проверка максимальной длины поля number"),
                    Arguments.of(new ProductData().setDescription("Lesson").setName("test" + (1 + (int) (Math.random() * 6))).setPrice(1), "Проверка минимальной длины поля number"),
                    Arguments.of(new ProductData().setDescription("Lesson").setName("test" + (1 + (int) (Math.random() * 6))), "Проверка создания документа без необязательного поля number")
            );
        }
    }

    public static class NegativeTest implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new ProductData().setDescription("softEng").setName("Lessonasdasdsafasfsag").setPrice(533), "Проверка создания с 21 символом для поля name", new HashMap<String, String>() {{
                        put("name", "Length must be between 3 and 20 characters.");
                    }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName("аа").setPrice(533), "Проверка создания с 2 символами для поля name", new HashMap<String, String>() {{
                        put("name", "Length must be between 3 and 20 characters.");
                    }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName("bb").setPrice(533), "Проверка создания с 2 символами для поля name", new HashMap<String, String>() {{
                        put("name", "Length must be between 3 and 20 characters.");
                    }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName(12325).setPrice(533), "Проверка некорректного типа значения поля name", new HashMap<String, String>() {{
                        put("name", "?");
                    }}),
                    Arguments.of(new ProductData().setDescription("softEng").setName(null).setPrice(533), "Проверка обязательного ввода в поле name", new HashMap<String, String>() {{
                        put("name", "Field is mandatory.");
                    }}),
                    Arguments.of(new ProductData().setDescription(null).setName("asdfg").setPrice(533), "Проверка обязательного ввода в поле description", new HashMap<String, String>() {{
                        put("description", "Field is mandatory.");
                    }}),
                    Arguments.of(new ProductData().setDescription("asfaasfasfasasfaasfas").setName("asdfg").setPrice(533), "Проверка создания с 21 символом для поля description", new HashMap<String, String>() {{
                        put("description", "Length must be between 3 and 20 characters.");
                    }}),
                    Arguments.of(new ProductData().setDescription("аа").setName("asdfg").setPrice(533), "Проверка создания с 2 символами для поля description", new HashMap<String, String>() {{
                        put("description", "Length must be between 3 and 20 characters.");
                    }}),
                    Arguments.of(new ProductData().setDescription("bb").setName("asdfg").setPrice(533), "Проверка создания с 2 символами для поля description", new HashMap<String, String>() {{
                        put("description", "Length must be between 3 and 20 characters.");
                    }}),
                    Arguments.of(new ProductData().setDescription(533.56).setName("sdfsd").setPrice(533), "Проверка некорректного типа значения поля description", new HashMap<String, String>() {{
                        put("description", "?");
                    }}),

                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(10000000), "Проверка больше 7 чисел для поля price", new HashMap<String, String>() {{
                        put("price", "Maximum number of integral digits is 7, maximum number of fractional digits is 2");
                    }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(1.984), "Проверка больше двух чисел после запятой для поля price", new HashMap<String, String>() {{
                        put("price", "Maximum number of integral digits is 7, maximum number of fractional digits is 2");
                    }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(-100), "Проверка отрицательного числа в поле price", new HashMap<String, String>() {{
                        put("price", "Value must be positive");
                    }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice(0), "Проверка числа 0 в поле price", new HashMap<String, String>() {{
                        put("price", "Value must be positive");
                    }}),
                    Arguments.of(new ProductData().setDescription("dsgdsg").setName("sdfsd").setPrice("sdfsd"), "Проверка некорректного типа значения поля price", new HashMap<String, String>() {{
                        put("price", "Cannot deserialize value of type 'double' from String");
                    }})
            );
        }
    }
}
