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
public enum CompletionActionEnum {
    COMPLETE_ALL_TYPE(1),COMPLETE_ANY_TYPE(2),COMPLETE_ORDER_TYPE(3),COMPLETE_AT_LEAST_TYPE(4);
    
    private final int code;

    private CompletionActionEnum(int code) {
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
