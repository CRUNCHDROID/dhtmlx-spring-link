package com.mylaensys.dhtmlx.adapter;


import com.google.appengine.api.urlfetch.*;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Locale;

public class Proxy extends AbstractAdapter  implements Adapter {

    HttpServletRequest request;
    HTTPResponse response;

    public Proxy(HttpServletRequest request) {
        this.request = request;
    }

    public void get(String url,String key) throws Exception {
        invoke(url,HTTPMethod.GET,key);
    }

    private void invoke(String url,HTTPMethod method,String key) throws Exception {
        URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();

        HTTPRequest req = new HTTPRequest(new URL(url), method);
        req.addHeader(new HTTPHeader("docklet-key", key ));

        for (int i = 0; i < 3; i++) {
            try {
                this.response = fetcher.fetch(req);
                return;
            } catch (SocketTimeoutException e) {
                log.severe("Got timeout (" + i + ") retry : " + e.getMessage());
            }
        }
    }

    public String serialize(Locale locale) {
        String content = "";
        if( response != null ) {
            content = new String(response.getContent());
            //log.info( content );
        }
        return content;
    }
}
