package com.example.database.data;

public class Constants {
    public static final String BLANK_MESSAGE = "Данное поле должно быть заполнено";
    public static final String CHECK_CORRECT = "Проверьте правильность введенных данных";
    public static final String SURNAME_PATTERN = "[a-zA-z]{1,40}";
    public static final String NAME_PATTERN = "[a-zA-z]{1,20}";
    public static final String PATRONYMIC_PATTERN = "[a-zA-z]{1,40}";
    public static final String TABLE_NAME = "users";

    public static final String EMAIL_BLANK_MESSAGE = "Email не может быть пуст";
    public static final String CHECK_CORRECT_EMAIL = "Проверьте введенный вами email";
    public static final String BEFORE = "Before ";
    public static final String ARGS = "args: ";
    public static final String RESULT = "result";
    public static final String AFTER = "After: ";
    public static final String RETURNING_N = " returning: \\n";
    public static final String EXCEPTION = "ex";
    public static final String THROWING = " throwing:";
    public static final String WITH_ARGS_N = " with args: \\n";
    public static final String USERS_URL = "/users";
    public static final String EMAIL = "email";
    public static final int PAGE_SIZE = 10;

    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String BY_PAGE_1 = "?page=1";
    public static final String BY_PAGE_2 = "?page=2";
    public static final String BY_PAGE_3 = "?page=3";
}
