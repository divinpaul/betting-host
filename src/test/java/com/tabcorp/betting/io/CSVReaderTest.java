package com.tabcorp.betting.io;

import com.google.inject.Injector;
import com.tabcorp.betting.Bet;
import com.tabcorp.betting.Result;
import com.tabcorp.betting.exceptions.BettingHostException;
import com.tabcorp.betting.win.Win;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.inject.Guice.createInjector;
import static com.tabcorp.betting.BetType.WIN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CSVReaderTest {

    public static final String INPUT_FILE_PATH = "src/test/resources/sample_input.csv";
    public static final String INVALID_INPUT_FILE_PATH = "src/test/resources/invalid_input.csv";
    private CSVReader csvReader;
    private Injector injector;

    @Before
    public void setUp() {
        injector = createInjector();
        csvReader = new CSVReader(injector);
    }

    @Test
    public void shouldReadFromFileAndReturnTheEmployeeList() {
        final Win expected = new Win();
        expected.setBets(newArrayList(buildBet()));
        expected.setResult(buildResult());
        final Win actual = csvReader.read(INPUT_FILE_PATH);
        assertThat(actual.getWinner(), is(expected.getWinner()));
    }

    @Test(expected = BettingHostException.class)
    public void shouldThrowExceptionForInvalidInputData() {
        csvReader.read(INVALID_INPUT_FILE_PATH);
    }

    private Bet buildBet() {
        Bet bet = new Bet();
        bet.setType(WIN.getValue());
        bet.setEntity(1);
        bet.setAmount(3);
        return bet;
    }

    private Result buildResult() {
        final Result result = new Result();
        result.setWinner(2);
        result.setRunnerUp(3);
        result.setThird(1);
        return result;
    }
}