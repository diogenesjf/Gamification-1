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
public enum AttributeTypeEnum {
    STRING_TYPE(1),NUMBER_TYPE(2),BOOLEAN_TYPE(3),COORD_TYPE(4);
    
    private final int code;

    private AttributeTypeEnum(int code) {
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
