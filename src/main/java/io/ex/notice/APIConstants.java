package io.ex.notice;

public class APIConstants {

    /**
     * The default timeout is 30 seconds.
     */
    public static final long TIMEOUT = 1000 * 30;
    /**
     * All requests and responses are application/json content type and follow typical HTTP response status codes for success and failure.
     */
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String ACCEPT = "Accept";

    public static final String COOKIE = "Cookie";

    public static final String LOCALE = "locale=";

//    public static final MediaType JSON = MediaType.parse(ContentTypeEnum.APPLICATION_JSON.contentType());

//    public static final Charset UTF_8 = Charset.forName(CharsetEnum.UTF_8.charset());

    public static final String QUESTION = "?";

    public static final String EMPTY = "";



    public static final String timestamp_key = "timestamp";

    public static final String CHARGE_METHOD = "charge";
    public static final String WITHDRAW_SUCCESS_METHOD = "withdrawSuccess";
    public static final String WITHDRAW_CONFIRM_METHOD = "withdrawConfirm";

    public static final String METHOD = "method";
}
