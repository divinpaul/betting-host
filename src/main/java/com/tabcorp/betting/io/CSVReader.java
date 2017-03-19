package com.tabcorp.betting.io;

import com.google.inject.Injector;
import com.tabcorp.betting.Bet;
import com.tabcorp.betting.Result;
import com.tabcorp.betting.exceptions.BettingHostException;
import com.tabcorp.betting.win.Win;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.valueOf;
import static org.slf4j.LoggerFactory.getLogger;

public class CSVReader {
    private static final Logger LOGGER = getLogger(CSVReader.class);
    private static final String RESULT = "Result";
    private Injector injector;

    public CSVReader(Injector injector) {
        this.injector = injector;
    }

    public Win read(String fileName) {
        String line = "";
        final List<Bet> bets = newArrayList();
        final Win win = injector.getInstance(Win.class);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (!line.trim().startsWith(RESULT) && (line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    bets.add(buildBet(line, fileName));
                }
            }
            win.setBets(bets);
            win.setResult(buildResult(line, fileName));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BettingHostException(e.getMessage(), e);
        }
        return win;
    }

    private Result buildResult(String line, String fileName) {
        final String[] input = line.trim().split(":");
        validateInput(input, fileName);
        final Result result = injector.getInstance(Result.class);
        result.setWinner(valueOf(input[1].trim()));
        result.setRunnerUp(valueOf(input[2].trim()));
        result.setThird(valueOf(input[3].trim()));
        return result;
    }

    private Bet buildBet(String line, String fileName) {
        final String[] input = line.split(":");
        validateInput(input, fileName);
        final Bet bet = injector.getInstance(Bet.class);
        bet.setType(input[1].trim());
        bet.setEntity(valueOf(input[2].trim()));
        bet.setAmount(valueOf(input[3].trim()));
        return bet;
    }

    private void validateInput(String[] input, String fileName) {
        if (input.length != 4) {
            throw new BettingHostException("The input format is wrong. Please verify in " + fileName);
        }
    }
}

