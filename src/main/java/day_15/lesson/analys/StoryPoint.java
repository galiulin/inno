package com.pervushow.analys;

public class StoryPoint {
    private Client client;
    private int summ;
    private int delay;
    private boolean isFault;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isFault() {
        return isFault;
    }

    public void setFault(boolean fault) {
        isFault = fault;
    }

    public StoryPoint(Client client, int summ, int delay, boolean isFault) {

        this.client = client;
        this.summ = summ;
        this.delay = delay;
        this.isFault = isFault;
    }
}
