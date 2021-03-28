package me.capthua.advancedjava.week2;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Question4Client01 {

    public static void main(String[] args) {
        System.out.println(get("http://localhost:8801",null,null));
    }

    private static String get(String url, Map<String,Object> params,Map<String,String> headers){
        String result=null;
        try {
            result=Request.Get(url).execute().returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
