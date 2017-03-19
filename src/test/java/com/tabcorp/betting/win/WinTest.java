package com.tabcorp.betting.win;

import com.tabcorp.betting.Bet;
import com.tabcorp.betting.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static com.tabcorp.betting.BetType.WIN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WinTest {

    @Test
    public void shouldReturnYieldAsZeroIfThereIsNoWinningBet() {
        Win win = new Win();
        win.setBets(newArrayList(buildBet(1, 5)));
        win.setResult(buildResult());
        assertThat(win.toString(), is("W:2:$4.25"));
    }

    @Test
    public void shouldReturnYieldIfThereIsOnlyOneBet() {
        Win win = new Win();
        win.setBets(newArrayList(buildBet(2, 5)));
        win.setResult(buildResult());
        assertThat(win.toString(), is("W:2:$0.85"));
    }

    @Test
    public void shouldReturnYieldForMultipleBets() {
        Win win = new Win();
        win.setBets(newArrayList(buildBet(2, 5), buildBet(1, 10)));
        win.setResult(buildResult());
        assertThat(win.toString(), is("W:2:$2.55"));
    }

    private Bet buildBet(final Integer entity, final Integer amount) {
        Bet bet = new Bet();
        bet.setType(WIN.getValue());
        bet.setEntity(entity);
        bet.setAmount(amount);
        return bet;
    }

    private Result buildResult() {
        final Result result = new Result();
        result.setWinner(2);
        result.setRunnerUp(1);
        result.setThird(3);
        return result;
    }
}