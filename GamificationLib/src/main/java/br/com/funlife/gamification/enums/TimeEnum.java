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
public enum TimeEnum {
    MINUTES(1),HOURS(2),DAY(3),WEEK(4),MONTH(5),YEAR(6);
    
    private final int code;

    private TimeEnum(int code) {
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
