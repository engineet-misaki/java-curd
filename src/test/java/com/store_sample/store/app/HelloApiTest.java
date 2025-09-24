package com.store_sample.store.app;

import java.net.URL;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private DataSource dataSource;

  @ParameterizedTest
  @MethodSource("helloTestProvider")
  public void helloText(String queryString, String expectedBody, String dbPath) throws Exception {

    // TODO 共通処理科
    IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
    URL givenUrl = this.getClass().getResource("/hello/hello/" + dbPath + "/given/");
    databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
    databaseTester.onSetup();

    mockMvc.perform(
            MockMvcRequestBuilders.get("/hello" + queryString)
                // 非推奨だがテストコードのため利用を許可
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody,
            result.getResponse().getContentAsString(),
            false));

    var actualDataSet = databaseTester.getConnection().createDataSet();
    var actualTestTable = actualDataSet.getTable("test");
    URL expectedUrl = this.getClass().getResource("/hello/hello/" + dbPath + "/expected/");
    var expectedDataSet = new CsvURLDataSet(expectedUrl);
    var expectedTestTable = expectedDataSet.getTable("test");
    Assertion.assertEquals(expectedTestTable, actualTestTable);
  }

  private static Stream<Arguments> helloTestProvider() {
    return Stream.of(
        Arguments.arguments(
            "",
            """
                {
                  "message": "Hello, world!"
                }
                """,
            "default"
        ),

        Arguments.arguments(
            "?name=techpit",
            """
                {
                  "message": "Hello, techpit"
                }
                """,
            "techpit")
    );
  }
}
