package com.tabcorp.betting;

import com.google.inject.Injector;
import com.tabcorp.betting.exceptions.BettingHostException;
import com.tabcorp.betting.io.CSVReader;
import com.tabcorp.betting.win.Win;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;

import static com.google.inject.Guice.createInjector;
import static org.slf4j.LoggerFactory.getLogger;

public class BettingHost {
    private static final Logger LOGGER = getLogger(BettingHost.class);
    public static final String NEW_LINE = "\n";

    public void placeBet(final String readPath) {
        final Injector injector = createInjector();
        final CSVReader reader = new CSVReader(injector);
        LOGGER.info("Reading input from " + readPath);
        final Win win = reader.read(readPath);
        LOGGER.info("Writing output to console");
        System.out.println(NEW_LINE);
        System.out.println(win.toString());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new BettingHostException("Input arguments are wrong. " +
                    "Correct format is: ./gradlew run -Dexec.args=\"input.csv\"");
        }
        final String readPath = args[0];
        BasicConfigurator.configure();
        final BettingHost bettingHost = new BettingHost();
        bettingHost.placeBet(readPath);
    }
}

