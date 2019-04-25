/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Teeu Guima
 */
public class Request implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String tag;
    private Object params;
    
    
    public Request(String tag, Object params){
        this.setTag(tag);
        this.setParams(params);
    }
    
    public void setParams(Object params){
        this.params = params;
    }
    
    public Object getParams(){
        return this.params;
    }
    
    public void setTag(String tag){
        this.tag = tag;
    }
    
    public String getTag(){
        return this.tag;
    }
}
