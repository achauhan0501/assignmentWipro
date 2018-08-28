package com.example.abhinayvarma.task.network;

public class RestClient {


        private static RestClient instance = null;
        private static ApiContract classifiedApiContract = null;

        //To avoid further initialization.
        private RestClient() {
        }

        public static RestClient getInstance() {
            initialize();
            return instance;
        }

        public static ApiContract getClient() {
            initialize();
            return classifiedApiContract;
        }

        private static void initialize() {
            if (instance == null || classifiedApiContract == null) {
                instance = new RestClient();
                classifiedApiContract = RestServiceGenerator.Companion.createRestApiService(ApiContract.class,"https://dl.dropboxusercontent.com/");
            }
        }
    }

