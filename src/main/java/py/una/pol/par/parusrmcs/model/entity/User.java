/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.parusrmcs.model.entity;

import py.una.pol.par.commons.entity.BaseEntity;

/**
 *
 * @author mauricio
 */
public class User extends BaseEntity<Integer> {
    
    private String lastname;
    private String email;
    private String loginName;
    private String passwd;
    private int clientType;
    
    public User() {
        super(0,"");
    }
    

    public User(Integer id,  String name , String lastname, String email, String loginName, String passwd, int clientType) {
        super(id, name);
        this.lastname = lastname;
        this.loginName = loginName;
        this.email = email;
        this.passwd = passwd;
        this.clientType = clientType;
    }

    public User(String lastname, String loginName, String passwd, int clientType) {
        this.lastname = lastname;
        this.loginName = loginName;
        this.passwd = passwd;
        this.clientType = clientType;
    }
    
    public User(Integer id, String nombre) {
        super(id, nombre);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }
    
    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return new StringBuilder("{id: ").append(this.getId()).append(", name: ")
                .append(this.getName()).append(", lastname: ").append(lastname)
                .append(", email: ").append(email)
                .append(", loginName: ").append(loginName)
                .append(", passwd: ").append(passwd)
                .append(", clientType: ").append(clientType).append("}").toString();
    }
}
