package com.github.marcelkoopman.reactive.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * Hello world!
 *
 */
public class App {

	private final ActorSystem actorSystem = ActorSystem.create("ReactiveAkka");
	private final Inbox inbox = Inbox.create(actorSystem);

	public void start(final UploadMessage uploadMsg) {
		final ActorRef uploadActor = actorSystem.actorOf(Props.create(Upload.class));
		inbox.send(uploadActor, uploadMsg);
	}
	
	public void stop() {
		final Future<Terminated> future = actorSystem.terminate();
		try {
			Await.result(future, new Timeout(Duration.create(5, "seconds")).duration());
			System.err.println("ActorSystem terminated!");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
