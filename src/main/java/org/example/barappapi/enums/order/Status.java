package org.example.barappapi.enums.order;

public enum Status {
    ORDERED(0),
    PREPARATION(1),
    FINISH(2);

    public final Integer status;

    private Status(Integer status) {
        this.status = status;
    }
}
