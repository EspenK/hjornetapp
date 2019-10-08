package me.kverna.hjornetapp;

public class HjornetApi {
    public static final String API_URL = "http://pckvalvi-kanten.uials.no:8080/api/";

    public static final String AUTH_LOGIN = API_URL + "auth/login";
    public static final String AUTH_CREATE = API_URL + "auth/create";
    public static final String AUTH_CURRENT_USER = API_URL + "auth/currentuser";
    public static final String AUTH_LOGOUT= API_URL + "auth/logout";
    public static final String AUTH_ADD_ROLE = API_URL + "auth/addrole";
    public static final String AUTH_REMOVE_ROLE = API_URL + "auth/removerole";
    public static final String AUTH_CHANGE_PASSWORD = API_URL + "auth/changepassword";

    public static final String ITEM_CREATE = API_URL + "item/create";
    public static final String ITEM_IMAGE = API_URL + "item/image";
    public static final String ITEM_ALL = API_URL + "item/all";
    public static final String ITEM_BUY = API_URL + "item/buy";
}