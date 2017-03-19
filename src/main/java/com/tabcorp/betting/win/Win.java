package com.tabcorp.betting.win;

import com.tabcorp.betting.Bet;
import com.tabcorp.betting.Result;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.tabcorp.betting.BetType.WIN;
import static java.lang.String.format;

public class Win {
    private List<Bet> bets = newArrayList();
    private Result result;


    public void setBets(final List<Bet> bets) {
        this.bets = bets;
    }

    public void setResult(final Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return WIN.getValue() + ":" + getWinner() + ":" + "$" + getYield();
    }

    public Integer getWinner() {
        return result.getWinner();
    }

    public Double getYield() {
        final Double poolAmount = getAvailableAmount();
        final Integer winnersAmount = getWinnersAmount();
        return Double.valueOf(format("%.2f", (poolAmount / winnersAmount)));
    }

    private Double getAvailableAmount() {
        final Integer total = getTotal();
        return total - (double) total * 15 / 100;
    }

    private Integer getWinnersAmount() {
        Integer winnersAmount = 0;
        for (final Bet bet : bets) {
            if (isWinner(bet)) {
                winnersAmount += bet.getAmount();
            }
        }
        return winnersAmount == 0 ? 1 : winnersAmount;
    }

    private Integer getTotal() {
        Integer total = 0;
        for (final Bet bet : bets) {
            total += bet.getAmount();
        }
        return total;
    }

    private boolean isWinner(Bet bet) {
        return bet.getEntity() == getWinner();
    }
}
