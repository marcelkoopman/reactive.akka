package com.github.marcelkoopman.reactive.akka;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CsvUpload extends UntypedActor {

	private final LoggingAdapter log = Logging.getLogger(this);

	@Override
	public void onReceive(final Object msg) throws Exception {
		if (msg instanceof File) {
			final File inputFile = (File) msg;
			final Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(new FileReader(inputFile));
			final List<Map<String, String>> result = new ArrayList<>();
			for (final CSVRecord record : records) {
				final Map<String, String> map = record.toMap();
				result.add(map);
			}
			getSender().tell("Parsed: "+result.size(), getSelf());
		} else {
			log.error("unhandled: "+msg);
			unhandled(msg);
		}
	}

	@Override
	public void postStop() throws Exception {
		super.postStop();
	}

}
