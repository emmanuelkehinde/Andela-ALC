package com.kehinde.alc.models;

import android.os.Bundle;

import com.kehinde.alc.data.Constants;

/**
 * Created by kehinde on 3/7/17.
 */
public class Developer {

    private String login;//username
    private String avatar_url;
    private String html_url;

    public Developer() {

    }

    public Developer(String login, String avatar_url, String html_url) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
    }

    public Developer(Bundle arguments) {
        if (arguments!=null) {
            this.login = arguments.getString(Constants.LOGIN);
            this.avatar_url = arguments.getString(Constants.AVARTAR_URL);
            this.html_url = arguments.getString(Constants.HTML_URL);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public Bundle toBundle() {
        Bundle bundle=new Bundle();
        bundle.putString(Constants.LOGIN,login);
        bundle.putString(Constants.AVARTAR_URL,avatar_url);
        bundle.putString(Constants.HTML_URL,html_url);
        return bundle;
    }
}
