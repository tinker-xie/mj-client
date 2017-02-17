package com.xie.game;

/**
 * Created by xie on 16/8/23.
 */
public enum PAI {
    B1(0),
    B2(1),
    B3(2),
    B4(3),
    B5(4),
    B6(5),
    B7(6),
    B8(7),
    B9(8),
    T1(9),
    T2(10),
    T3(11),
    T4(12),
    T5(13),
    T6(14),
    T7(15),
    T8(16),
    T9(17),
    W1(18),
    W2(19),
    W3(20),
    W4(21),
    W5(22),
    W6(23),
    W7(24),
    W8(25),
    W9(26),
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
