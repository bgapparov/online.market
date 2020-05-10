package edu.miu.cs545.group01.online.market.domain.enums;

public enum Role {
    ADMIN,
    SELLER,
    BUYER;

    public String toRoleString(){
        return "ROLE_"+this.toString();
    }
}
