package com.github.marcelkoopman.reactive.akka;

import java.io.File;
import java.util.List;

public class UploadMessage {
	private final List<File> files;

	public UploadMessage(final List<File> files) {
		this.files = files;
	}

	public List<File> getFiles() {
		return this.files;
	}
}
