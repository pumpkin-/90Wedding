package com.wedding.secretary.networks.domain;

import java.io.Serializable;

/**
 * Created by hmy on 2015/10/30.
 */
public class HttpData implements Serializable {

   private String json;
   private HttpParams httpParams;

    public HttpData() {
    }

    public HttpData(String json, HttpParams httpParams) {
        this.json = json;
        this.httpParams = httpParams;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public HttpParams getHttpParams() {
        return httpParams;
    }

    public void setHttpParams(HttpParams httpParams) {
        this.httpParams = httpParams;
    }
}
