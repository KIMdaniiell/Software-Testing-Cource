package readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.load("application.conf");
    }

    String URL = config.getString("mainPageURL");

    String USER_LOGIN = config.getString("user.login");
    String USER_PASSWORD = config.getString("user.password");
    String USER_INCORRECT_PASSWORD = config.getString("user.incorrectPassword");
    String USER_MAIL_DOMAIN = config.getString("user.mainDomain");
    String USER_LINK = config.getString("user.link");
    String USER_FULL_NAME = config.getString("user.fullName");

    String QUERY_TEXT = config.getString("query.text");
    String QUERY_TEXT_TRIMMED = config.getString("query.textTrimmed");
    String QUERY_CATEGORY = config.getString("query.categoryFromShortList");
    String QUERY_CATEGORY_FROM_FULL_LIST = config.getString("query.queryCategoryFromFullList");

    String QUESTION_LINK = config.getString("question.link");
    String QUESTION_TITLE = config.getString("question.own.title");
    String QUESTION_TITLE_SPACES = config.getString("question.own.titleSpaces");
    String QUESTION_TITLE_TABS = config.getString("question.own.titleTabs");
    String QUESTION_TITLE_NEWLINE = config.getString("question.own.titleNewLine");
    String QUESTION_TEXT = config.getString("question.own.text");
    Integer QUESTION_OWN_ID = config.getInt("question.own.id");
    String QUESTION_OWN_CATEGORY = config.getString("question.own.category");
    String QUESTION_OWN_SUBCATEGORY = config.getString("question.own.subCategory");
    Integer QUESTION_OUTER_ID = config.getInt("question.outer.id");

    String QUESTION_EDIT_TEXT = config.getString("question.edit.text");

    String QUESTION_ADD_TEXT = config.getString("question.add.text");

    String RESPONSE_TEXT = config.getString("response.text");
}
