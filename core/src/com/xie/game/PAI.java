package com.xie.game;

/**
 * Created by xie on 16/8/23.
 */
public enum PAI {
    W1(0),
    W2(1),
    W3(2),
    W4(3),
    W5(4),
    W6(5),
    W7(6),
    W8(7),
    W9(8),
    B1(9),
    B2(10),
    B3(11),
    B4(12),
    B5(13),
    B6(14),
    B7(15),
    B8(16),
    B9(17),
    T1(18),
    T2(19),
    T3(20),
    T4(21),
    T5(22),
    T6(23),
    T7(24),
    T8(25),
    T9(26),
    E(27),
    S(28),
    W(29),
    N(30),
    ZH(31),
    FA(32),
    BA(33);

    private int code;

    PAI(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
