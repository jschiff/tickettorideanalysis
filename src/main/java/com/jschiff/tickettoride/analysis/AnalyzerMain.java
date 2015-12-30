package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.Game;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class AnalyzerMain {
  private static class ConnectionIterable implements Iterable<ConnectionData> {
    private final Iterable<CSVRecord> iterable;

    ConnectionIterable(Iterable<CSVRecord> iterable) {
      this.iterable = iterable;
    }

    @Override
    public Iterator<ConnectionData> iterator() {
      Iterator<CSVRecord> iterator = iterable.iterator();

      return new Iterator<ConnectionData>() {
        @Override
        public boolean hasNext() {
          return iterator.hasNext();
        }

        @Override
        public ConnectionData next() {
          CSVRecord record = iterator.next();
          ConnectionData data = new ConnectionData();
          data.setColor(record.get("Color"));
          data.setDestinationCity(record.get("Destination"));
          data.setLength(Integer.parseInt(record.get("Length")));
          data.setOriginCity(record.get("Origin"));
          return data;
        }
      };
    }
  }

  private static class RouteIterable implements Iterable<RouteData> {
    private final Iterable<CSVRecord> iterable;

    RouteIterable(Iterable<CSVRecord> iterable) {
      this.iterable = iterable;
    }

    @Override
    public Iterator<RouteData> iterator() {
      Iterator<CSVRecord> iterator = iterable.iterator();

      return new Iterator<RouteData>() {
        @Override
        public boolean hasNext() {
          return iterator.hasNext();
        }

        @Override
        public RouteData next() {
          CSVRecord record = iterator.next();
          RouteData data = new RouteData();
          data.setReward(Integer.parseInt(record.get("Points")));
          data.setDestinationCity(record.get("Destination"));
          data.setOriginCity(record.get("Origin"));
          return data;
        }
      };
    }
  }

  private static CSVFormat connectionFormat() {
    return CSVFormat.DEFAULT
        .withHeader("Origin", "Destination", "Length", "Color")
        .withRecordSeparator('\n')
        .withDelimiter(',');
  }

  public static void main(String[] args) throws ParseException, IOException {
    CommandLine cl = new DefaultParser().parse(options(), args);
    String connectionFile = cl.getOptionValue("c", "connections.csv");
    String routeFile = cl.getOptionValue("r", "routes.csv");

    CSVParser connectionParser = new CSVParser(new FileReader(connectionFile), connectionFormat());
    CSVParser routeParser = new CSVParser(new FileReader(routeFile), routeFormat());

    Game game = new Initializer().initializeGame(
        new ConnectionIterable(connectionParser),
        new RouteIterable(routeParser));
    Analysis analyze = new Analyzer(game).analyze();
  }

  private static Options options() {
    return new Options()
        .addOption("c", "Connections csv file")
        .addOption("r", "Routes csv file");
  }

  private static CSVFormat routeFormat() {
    return CSVFormat.DEFAULT
        .withHeader("Origin", "Destination", "Points")
        .withRecordSeparator('\n')
        .withDelimiter(',');
  }
}
