package it.alisa.companydirector.model;

public enum ErrorCDStrings {
    E_9999_UNKNOWN_ERROR("Неизвестная ошибка, код 9999"),
    E_0001_UNKNOWN_ERROR("Пользователь неавторизован, код 0001");

    private final String errorMsg;

    ErrorCDStrings(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
