/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.funlife.gamification.enums;

/**
 *
 * @author Diógenes Firmiano
 */
public enum LimitAmountRestrictionEnum {
    LIMIT_FROM_PLAYER(1),LIMIT_FROM_TEAM(2),LIMIT_FROM_APP(3);
    
    private final int code;

    private LimitAmountRestrictionEnum(int code) {
        this.code = code;
    }

    public int toInt() {
        return code;
    }

    @Override
    public String toString() {
        //only override toString, if the returned value has a meaning for the
        //human viewing this value 
        return String.valueOf(code);
    }

}
