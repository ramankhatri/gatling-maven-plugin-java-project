package endpoints.ui;

import static helpers.BaseHelpers.PROJECT_BASE_DIRECTORY;
import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;

// TODO : add some useful code here as below are some sample URLs.
public final class SampleURLs {
    private SampleURLs() {
    }
    private static final int STATUS_CREATED = 201;
    private static final int STATUS_OK = 200;
    /**
     * Launch the Application.
     *
     * @return
     */
    public static ChainBuilder launchApplication() {
        return exec(
            http("launch-application")
                .get("https://computer-database.gatling.io/")
                .check(status().is(STATUS_OK))
        );
    }
    /**
     * Open any Computer.
     *
     * @return
     */
    public static ChainBuilder openAnyComputer() {
        return exec(
                http("open-any-computer")
                        .get("https://computer-database.gatling.io/computers/381")
                        .check(status().is(STATUS_OK))
        );
    }
}
