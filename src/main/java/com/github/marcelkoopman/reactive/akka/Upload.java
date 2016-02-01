package com.github.marcelkoopman.reactive.akka;

import java.io.File;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Upload extends UntypedActor {
	private final LoggingAdapter log = Logging.getLogger(this);

	private int count = 0;
	
	@Override
	public void onReceive(final Object msg) throws Exception {
		if (msg instanceof UploadMessage) {

			final ActorRef routerActor = getContext().actorOf(Props.create(ContentBasedRouter.class));

			final UploadMessage uploadMessage = (UploadMessage) msg;
			final List<File> files = uploadMessage.getFiles();

			for (final File file : files) {
				routerActor.tell(file, getSelf());
			}
		} else if (msg instanceof String) {
			log.info("Result: " + msg + " " +(++count));
		} else {
			log.error("unhandled: "+msg);
			unhandled(msg);
		}

	}

}
