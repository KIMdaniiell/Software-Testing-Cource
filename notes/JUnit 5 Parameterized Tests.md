# JUnit 5 - Аннотация @ParameterizedTest
\- используется для запуска одного теста **несколько раз**, но с **разными входными данными**.

**pom.xml**
```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>${junit-version}</version>
    <scope>test</scope>
</dependency>
```

```
  @ParameterizedTest(name = "{index} - {0} is a palindrome")
  @ValueSource(strings = { "12321", "pop" })
  void testPalindrome(String word) {
      assertTrue(isPalindrome(word));
  }
```

##  Источники тестовых аргументов
+ **@ValueSource**
    + предоставляет **`примитивы` и `строки`** указанные в массиве
    ```
      @ParameterizedTest
      @ValueSource(ints = { 1, 2, 3 })
      void testMethod(int argument) {
          //test code
      }
    ```
    
+ **@NullSource**
    + предоставляет **`null`**
    ```
      @ParameterizedTest
      @NullSource
      void testMethodNullSource(Integer argument) {
          assertTrue(argument == null);
      }
    ```
    
+ **@EmptySource**
    + предоставляет **пустой**
        + `String`
        + `List`
        + `Set`
        + `Map`
        + `Массив` (примитивов или объектов)
    ```
      @ParameterizedTest
      @EmptySource
      void testMethodEmptySource(String argument) {
          assertTrue(StringUtils.isEmpty(argument));
      }
    ```
    
+ **@NullAndEmptySource**
    + предоставляет сначала **`null`**, а затем **`empty`**
    ```
      @ParameterizedTest
      @NullAndEmptySource
      void testMethodNullAndEmptySource(String argument) {
          assertTrue(StringUtils.isEmpty(argument));
      }
    ```
    
+ **@EnumSource**
    + предоставляет каждый элемент **`перечисления`** поочереди
    ```
      enum Direction {
          EAST, WEST, NORTH, SOUTH
      }
      
      @ParameterizedTest
      @EnumSource(Direction.class)
      void testWithEnumSource(Direction d) {
          assertNotNull(d);
      }
    ```

+ **@MethodSource**
    + использует фабричный метод
    ```
      @ParameterizedTest
      @MethodSource("argsProviderFactory")
      void testWithMethodSource(String argument) {
          assertNotNull(argument);
      }
      
      static Stream<String> argsProviderFactory() {
          return Stream.of("alex", "brian");
      }
    ```

    Если мы явно не предоставим имя фабричного метода через **@MethodSource**, JUnit будет искать фабричный метод,
    имя которого по умолчанию совпадает с именем текущего метода с аннотацией **@ParameterizedTest**.

    Поэтому, в примере, если мы не предоставим имя метода *argsProviderFactory* в аннотации **@MethodSource**,
    Junit будет искать имя метода *testWithMethodSource* с возвращаемым типом Stream<String>.
    ```
      @ParameterizedTest
      @MethodSource
      void testWithMethodSource(String argument) {
          assertNotNull(argument);
      }
      
      static Stream<String> testWithMethodSource() {
          return Stream.of("alex", "brian");
      }
    ```

    Также поддерживаются потоки для примитивных типов (DoubleStream, IntStream и LongStream).
    ```
      @ParameterizedTest
      @MethodSource("argsProviderFactory")
      void testWithMethodSource(int argument) {
          assertNotEquals(9, argument);
      }
      
      static IntStream argsProviderFactory() {
          return IntStream.range(0, 10);
      }
    ```

+ **@CsvSource**
    ```
      @ParameterizedTest
      @CsvSource(value = {
          "alex, 30",
          "brian, 35",
          "charles, 40"
      }, ignoreLeadingAndTrailingWhitespace = true)
      void testWithCsvSource(String name, int age) {
          assertNotNull(name);
          assertTrue(age > 0);
      }
    ```

    **ignoreLeadingAndTrailingWhitespace** указывает на то, что Junit должен принимать или игнорировать пробелы в CSV строках

+ **@CsvFileSource**
    ```
      @ParameterizedTest
      @CsvFileSource(resources = "employeeData.csv", numLinesToSkip = 0)
      void testWithCsvFileSource(String name, int age) {
          assertNotNull(name);
          assertTrue(age > 0);
      }
    ```

    строка, начинающаяся с символа **`#`**, будет интерпретироваться как **комментарий и игнорироваться**

+ **@ArgumentsSource**

    Аннотацию **@ArgumentsSource** можно использовать для указания настраиваемого многоразового поставщика аргументов **`ArgumentsProvider`**.
    ```
      @ParameterizedTest(name = "{index} - {0} is older than 40")
      @ArgumentsSource(EmployeesArgumentsProvider.class)
      void isEmployeeAgeGreaterThan40(Employee e) {
          assertTrue(Period.between(e.getDob(), LocalDate.now()).get(ChronoUnit.YEARS) > 40);
      }
      
      class EmployeesArgumentsProvider implements ArgumentsProvider {
          @Override
          public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
              return Stream.of(
                Arguments.of(new Employee(1, "Alex", LocalDate.of(1980, 2, 3))),
                Arguments.of(new Employee(2, "Brian", LocalDate.of(1979, 2, 3))),
                Arguments.of(new Employee(3, "Charles", LocalDate.of(1978, 2, 3)))
              );
          }
      }
    ```

## Параметризованные тесты с несколькими аргументами
+ **@CsvSource**
+ **ArgumentsSource**

    В примере мы передаем три аргумента метода тестирования testArgumentsSource(), типов:
    + Employee
    + LocalDate
    + enum константы типа Direction
    
    ```
      @ParameterizedTest
      @ArgumentsSource(EmployeesArgumentsProvider.class)
      void testArgumentsSource(Employee e, LocalDate date, Direction d) {
          assertTrue(Period.between(e.getDob(), LocalDate.now()).get(ChronoUnit.YEARS) > 40);
          assertNotNull(date);
          assertNotNull(d);
      }
      
      class EmployeesArgumentsProvider implements ArgumentsProvider {
          @Override
          public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
              return Stream.of(
                Arguments.of(new Employee(1, "Alex", 
                        LocalDate.of(1980, 2, 3)), LocalDate.now(), Direction.EAST),
                Arguments.of(new Employee(2, "Brian", 
                        LocalDate.of(1979, 2, 3)), LocalDate.now(), Direction.NORTH),
                Arguments.of(new Employee(3, "Charles", 
                        LocalDate.of(1978, 2, 3)), LocalDate.now(), Direction.SOUTH)
              );
          }
    ```
