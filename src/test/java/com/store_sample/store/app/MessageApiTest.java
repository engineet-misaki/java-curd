package com.store_sample.store.app;

import java.util.Optional;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.csv.CsvURLDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private DataSource dataSource;

  @ParameterizedTest
  @MethodSource("createTestProvider")
  public void createTest(int channelId, String requestBody, String expectedBody, String dbPath)
      throws Exception {

    IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
    var givenUrl = this.getClass().getResource("/messages/create/" + dbPath + "/given/");
    databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
    databaseTester.onSetup();

    mockMvc.perform(
            MockMvcRequestBuilders.post("/channels/" + channelId + "/message")
                .content(requestBody) // contentでリクエストボディを設定する
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> JSONAssert.assertEquals(
            expectedBody, result.getResponse().getContentAsString(),
            new CustomComparator(
                // 余分なJSON項目が含まれていてもOKとする
                JSONCompareMode.LENIENT,
                // idが所定のフォーマットであることを検証する
                new Customization("id",
                    new RegularExpressionValueMatcher<>("\\d{12}-[a-zA-Z0-9\\-]{36}")),
                // timestampは検証対象外とする(どんな値でもOK)
                new Customization("timestamp", (o1, o2) -> true)))
        );

    // 比較時のソートに使用するカラムを指定する。
    String[] sortBy = {"channel_id", "username", "text"};
    // 比較対象外にするカラムを指定する。
    String[] excludeColumns = {"id", "timestamp"};

    var actualDataSet = databaseTester.getConnection().createDataSet();
    // 比較対象外のカラムを除外後、ソートする。
    var actualMessagesTable = new SortedTable(
        DefaultColumnFilter.excludedColumnsTable(
            actualDataSet.getTable("messages"),
            excludeColumns
        ), sortBy);

    var expectedUri = this.getClass().getResource("/messages/create/" + dbPath + "/expected/");
    var expectedDataSet = new CsvURLDataSet(expectedUri);
    // 比較対象外のカラムを除外後、ソートする。
    var expectedMessagesTable = new SortedTable(
        DefaultColumnFilter.excludedColumnsTable(
            expectedDataSet.getTable("messages"),
            excludeColumns
        ), sortBy);
    Assertion.assertEquals(expectedMessagesTable, actualMessagesTable);
  }


  @ParameterizedTest
  @MethodSource("findTestProvider")
  public void findTest(int channelId, Optional<String> searchWord, String expectedBody,
      String dbPath) throws Exception {

    IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
    var givenUrl = this.getClass().getResource("/messages/find/" + dbPath + "/given/");
    databaseTester.setDataSet(new CsvURLDataSet(givenUrl));
    databaseTester.onSetup();

    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    searchWord.ifPresent(w -> params.add("searchWord", w));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/channels/" + channelId + "/message")
                .params(params)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((result) -> {
              JSONAssert.assertEquals(
                  expectedBody, result.getResponse().getContentAsString(), false
              );
            }
        );

    var actualDataSet = databaseTester.getConnection().createDataSet();
    var actualChannelsTable = actualDataSet.getTable("messages");

    var expectedUri = this.getClass().getResource("/messages/find/" + dbPath + "/given/");
    var expectedDataSet = new CsvURLDataSet(expectedUri);
    var expectedMessagesTable = expectedDataSet.getTable("messages");
    Assertion.assertEquals(expectedMessagesTable, actualChannelsTable);

  }


  private static Stream<Arguments> createTestProvider() {
    return Stream.of(
        Arguments.arguments(
            1,
            """
                {
                  "text": "APIリクエストしたメッセージ"
                }
                """,
            """
                {
                  "id": "202210151201-8097c0d2-ddc7-f02a-9dbf-29dfcd646d2b",
                  "channelId": 1,
                  "text": "APIリクエストしたメッセージ",
                  "username": "guest",
                  "timestamp": "2022-10-15 12:01:00"
                }
                """,
            "success"));
  }


  private static Stream<Arguments> findTestProvider() {
    return Stream.of(
        Arguments.arguments(
            // DBに存在しないチャンネルID
            3,
            // メッセージ本文の検索条件なし
            Optional.empty(),
            // 検索結果なし
            "[]",
            // DBのディレクトリ
            "base"),

        Arguments.arguments(
            // DBに存在するチャンネルID
            1,
            // メッセージ本文の検索条件なし
            Optional.empty(),
            // 検索結果なし
            """
                [
                  {
                    "id": "202210151200-8097c0d2-ddc7-f02a-9dbf-29dfcd646d2b",
                    "channelId": 1,
                    "text": "今日のランチは焼き鳥",
                    "username": "ユーザー",
                    "timestamp": "2022-10-15T12:00:00"
                  },
                  {
                    "id": "202210151201-9f207c7a-16f5-80a0-a81f-5b216b9da38f",
                    "channelId": 1,
                    "text": "明日のランチは唐揚げ",
                    "username": "ユーザー",
                    "timestamp": "2022-10-15T12:01:00"
                  },
                  {
                    "id": "202210151202-469ffa6c-fcbf-7d95-cc8a-e1a4ba9a5b66",
                    "channelId": 1,
                    "text": "明日のディナーはよだれ鶏",
                    "username": "ユーザー",
                    "timestamp": "2022-10-15T12:02:00"
                  }
                ]
                """,
            // DBのディレクトリ
            "base"),
        Arguments.arguments(
            // DBに存在しないチャンネルID
            3,
            // DBに存在しないメッセージ文言
            Optional.of("朝食"),
            // 検索結果なし
            "[]",
            // DBのディレクトリ
            "base"),

        Arguments.arguments(
            // DBに存在するチャンネルID
            1,
            // DBに存在しないメッセージ文言
            Optional.of("朝食"),
            // 検索結果なし
            "[]",
            // DBのディレクトリ
            "base"),

        Arguments.arguments(
            // DBに存在しないチャンネルID
            3,
            // DBに存在するメッセージ文言
            Optional.of("ランチ"),
            // 検索結果なし
            "[]",
            // DBのディレクトリ
            "base"),
        Arguments.arguments(
            // DBに存在するチャンネルID
            1,
            // DBのメッセージ本文に部分一致する文言
            Optional.of("ランチ"),
            // 条件に合致するメッセージ
            """
                  [
                    {
                      "id": "202210151200-8097c0d2-ddc7-f02a-9dbf-29dfcd646d2b",
                      "channelId": 1,
                      "text": "今日のランチは焼き鳥",
                      "username": "ユーザー",
                      "timestamp": "2022-10-15T12:00:00"
                    },
                    {
                      "id": "202210151201-9f207c7a-16f5-80a0-a81f-5b216b9da38f",
                      "channelId": 1,
                      "text": "明日のランチは唐揚げ",
                      "username": "ユーザー",
                      "timestamp": "2022-10-15T12:01:00"
                    }
                  ]
                """
            ,
            // DBのディレクトリ
            "base")

    );
  }

}
