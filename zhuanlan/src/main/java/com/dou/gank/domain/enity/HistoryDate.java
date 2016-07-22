package com.dou.gank.domain.enity;

/**
 * Created by mac on 16/6/20.
 */
public class HistoryDate {
    private boolean error;
    private String[] results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String[] getResults() {
        return results;
    }

    public void setResults(String[] results) {
        this.results = results;
    }
}
