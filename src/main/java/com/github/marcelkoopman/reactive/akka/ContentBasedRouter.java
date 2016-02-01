package com.github.marcelkoopman.reactive.akka;

import java.io.File;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ContentBasedRouter extends UntypedActor {

	private final LoggingAdapter log = Logging.getLogger(this);

	@Override
	public void onReceive(final Object msg) throws Exception {
		if (msg instanceof File) {
			final ActorRef csvUpload = getContext().actorOf(Props.create(CsvUpload.class));
			csvUpload.forward(msg, getContext());
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
