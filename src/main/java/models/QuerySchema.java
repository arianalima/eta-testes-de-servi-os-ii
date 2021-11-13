package models;

import org.json.simple.JSONObject;

public class QuerySchema {

    public static String getLanguageAsJson(String code){
        String query = String.format("{\r\n  language(code: \"%s\") {\r\n    code\r\n    name\r\n    native\r\n    rtl\r\n  }\r\n}", code);
        JSONObject languageJsonQuery = new JSONObject();
        languageJsonQuery.put("query", query);
        languageJsonQuery.put("variables","{}");
        return languageJsonQuery.toJSONString();
    }

}
