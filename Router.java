/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.lab1;

/**
 *
 * @author Stuardo
 */
public class Router extends Equipo{
    
    private String ip;
    private AccessPoint ap = null;
    private Switch sw = null;

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the ap
     */
    public AccessPoint getAp() {
        return ap;
    }

    /**
     * @param ap the ap to set
     */
    public void setAp(AccessPoint ap) {
        this.ap = ap;
    }

    /**
     * @return the sw
     */
    public Switch getSw() {
        return sw;
    }

    /**
     * @param sw the sw to set
     */
    public void setSw(Switch sw) {
        this.sw = sw;
    }
    
}
