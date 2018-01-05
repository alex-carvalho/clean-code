package com.ac.example.cleanCode.utis;

/**
 * @author Alex Carvalho
 */
public class WebBrowser {

    private BrowserName name;
    private Integer majorVersion;

    public WebBrowser(BrowserName name, int majorVersion) {
        this.name = name;
        this.majorVersion = majorVersion;
    }

    public BrowserName getName() {
        return name;
    }

    public void setName(BrowserName name) {
        this.name = name;
    }

    public Integer getMajorVersion() {
        return majorVersion;
    }


    public enum BrowserName {
        UNKNOWN,
        INTERNET_EXPLORER,
        FIREFOX,
        CHROME,
        OPERA,
        SAFARI
    }
}
