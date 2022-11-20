package com.kh0ma.playground.acceptance.test.util;

import com.kh0ma.playground.acceptance.test.example.api.client.ApiClient;
import com.kh0ma.playground.acceptance.test.example.api.client.api.BooksApi;

public class ApiFactory {
    public static BooksApi getBookApi(ApiClient apiClient) {
        return apiClient.buildClient(BooksApi.class);
    }
}
