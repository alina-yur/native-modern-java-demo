package dev.nipafx.demo.modern.operations;

import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import dev.nipafx.demo.modern.page.GitHubPage;
import dev.nipafx.demo.modern.page.Page;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static dev.nipafx.demo.modern.Util.asHTML;
import static dev.nipafx.demo.modern.Util.join;
import static java.lang.StringTemplate.RAW;
import static java.util.stream.Collectors.joining;

public class ResultServer {

	public static void serve(Page rootPage, Path serverDir) throws IOException {
		if (!Files.exists(serverDir))
			Files.createDirectory(serverDir);

		var html = asHTML(RAW."""
				<!DOCTYPE html>
				<html lang="en">
					<head>
						<meta charset="utf-8">
						<title>\{Pretty.pageName(rootPage)}</title>
						<link rel="stylesheet" href="style.css">
					</head>
					<body>
						<div class="container">
							\{pageTreeHtml(rootPage)}
						</div>
					</body>
				</html>
				""");
		Files.writeString(serverDir.resolve("index.html"), html.html());

		launchWebServer(serverDir);
	}

	private static void launchWebServer(Path serverDir) {
		System.out.println("Visit localhost:8080");
		// TODO: launch web server
		new Thread(() ->
				SimpleFileServer
						.createFileServer(
								new InetSocketAddress(8080),
								serverDir.toAbsolutePath(),
								OutputLevel.INFO)
						.start())
				.start();
	}

	private static String pageTreeHtml(Page rootPage) {
		var printedPages = new HashSet<Page>();
		return appendPageTreeHtml(printedPages, rootPage, 0);
	}

	private static String appendPageTreeHtml(Set<Page> printedPages, Page page, int level) {
		var pageHtml = pageHtml(page, printedPages.contains(page), level);
		if (printedPages.contains(page)) {
			printedPages.add(page);
			return pageHtml;
		} else {
			printedPages.add(page);
			var descendantsHtml = page instanceof GitHubPage ghPage
					? ghPage
							.links().stream()
							.map(linkedPage -> appendPageTreeHtml(printedPages, linkedPage, level + 1))
							.collect(joining("\n"))
					: "";
			return join(RAW."""
					\{pageHtml}
					\{descendantsHtml}
					""");
		}
	}

	private static String pageHtml(Page page, boolean reference, int level) {
		return join(RAW."""
				<div class="page level-\{level}">
					<a href="\{page.url().toString()}">\{Pretty.pageName(page)}</a>
					\{reference ? "<span class=\"ref\"></span>" : ""}
				</div>
				""");
	}

}
