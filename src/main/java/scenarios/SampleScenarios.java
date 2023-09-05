package scenarios;

import endpoints.ui.SampleURLs;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.http.HttpDsl;

import static io.gatling.javaapi.core.CoreDsl.exec;

public final class SampleScenarios {
    private SampleScenarios() {
    }
    /**
     * Scenario for opening any Computer.
     *
     * @return
     */
    public static ChainBuilder scnOpenAnyComputer() {
        return exec(HttpDsl.flushHttpCache())
                .exec(HttpDsl.flushCookieJar())
                .exec(SampleURLs.launchApplication())
                .pause(5, 7)
                .exec(SampleURLs.openAnyComputer())
                .pause(5 , 7);
    }
}
