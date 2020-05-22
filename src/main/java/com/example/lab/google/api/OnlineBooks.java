package com.example.lab.google.api;

import com.example.lab.domain.Book;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.lab.google.api.JsonReader.readJsonFromUrl;

public class OnlineBooks {
    private ArrayList<Book> onlineBooks = new ArrayList<Book>();

    private String keyword;
    private String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:";
    private String token = "&key=" + API_TOKEN.getTOKEN();

    private int MAX_BOOKS = 100;

    public OnlineBooks(String keyword){
        this.keyword = keyword.replace( " ", "%20");    //'%20' == ' '
    }


    private void getBooksWithKeywords() {
        JSONObject json = null;
        try {
            json = readJsonFromUrl(url + keyword + token);
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(OnlineBooks.class);
            logger.warn("Json response error");
        }


        if (json == null) {
            onlineBooks.add(new Book("Error", "Error", "Error", "#"));
        } else {
            try {
                JSONArray list = json.getJSONArray("items");

                String title, author, genre, link;

                for (int i = 0; i < list.length() && i < MAX_BOOKS; i++) {
                    title = list.getJSONObject(i).getJSONObject("volumeInfo").getString("title");
                    author = list.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors").get(0).toString();
                    try {
                        genre = list.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("categories").get(0).toString();
                    } catch (JSONException e) {
                        genre = "<none>";
                    }
                    try{
                        link = list.getJSONObject(i).getJSONObject("volumeInfo").getString("previewLink");
                    } catch (JSONException e){
                        link = "#";
                    }

                    Book tmpBook = new Book(title, author, genre, link);

                    onlineBooks.add(tmpBook);


                }
            } catch (JSONException e) {
                Logger logger = LoggerFactory.getLogger(OnlineBooks.class);
                logger.warn("No book found");
            }


        }
    }

    public ArrayList<Book> getOnlineBooks(){
        getBooksWithKeywords();
        if (onlineBooks.isEmpty())
            return null;
        return onlineBooks;
    }
    public void setMAX_BOOKS(int MAX_BOOKS) {
        this.MAX_BOOKS = MAX_BOOKS;
}
}
