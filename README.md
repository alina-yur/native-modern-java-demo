# Modern Java in Action

A repository for my live-coding talk [Modern Java in Action](https://nipafx.dev/talk-java-action).

Each step is its own commit.
Check them out to see here what needs to be done.


## Modern Java in Action Meets GraalVM 🚀

Build a native app (this will leverage the [Native Maven Plugin](https://graalvm.github.io/native-build-tools/latest/maven-plugin.html)):

```shell
mvn -Pnative package
```

Run the native app and pass the github page you want to crawl and the preferred crawl depth, e.g.:

```shell
./target/native-modern-java "https://github.com/oracle/graal/issues/7626" 5
```