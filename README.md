# Modern Java in Action

A repository for my live-coding talk [Modern Java in Action](https://nipafx.dev/talk-java-action).

## Next

String templates:
* change output in `GitHubCrawl::main` to:
	```java
	System.out.println(join(RAW."""

			---

			\{Statistician.evaluate(rootPage)}

			\{Pretty.pageList(rootPage)}

			"""));
	```
* add info in `ResultServer::pageHtml`:
	```java
	return join(RAW."""
			<div class="page level-\{level}">
				<a href="\{page.url().toString()}">\{Pretty.pageName(page)}</a>
				\{reference ? "<span class=\"ref\"></span>" : ""}
			</div>
			""");
	```
* add `Util::asHTML` and use in `ResultServer::serve`:
	```java
	public static Document asHTML(StringTemplate stringTemplate) {
		return Jsoup.parse(stringTemplate.interpolate());
	}
	```
