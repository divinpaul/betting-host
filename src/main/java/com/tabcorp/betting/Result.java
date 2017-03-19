package com.tabcorp.betting;

public class Result {
    private Integer winner;
    private Integer runnerUp;
    private Integer third;

    public Integer getWinner() {
        return winner;
    }

    public Integer getRunnerUp() {
        return runnerUp;
    }

    public Integer getThird() {
        return third;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public void setRunnerUp(Integer runnerUp) {
        this.runnerUp = runnerUp;
    }

    public void setThird(Integer third) {
        this.third = third;
    }
}
