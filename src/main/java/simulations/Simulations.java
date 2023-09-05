package simulations;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import scenarios.SampleScenarios;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class Simulations extends Simulation {
    private Map<String, ChainBuilder> scnMapping = new HashMap<>();
    /**
     * To resolve template.yaml file using Hogan, please make sure to use (getHoganConfig) in before hook.
     * Please make sure to import com.cvent.gatlingcommon.hogan.HoganConfig for using Hogan to resolve template.yaml
     * If <env>.yaml file does not exists at location src/main/java/config/ or project root directory/test_configs,
     * then getHoganConfig will check for the template.yaml file at same location and if it founds the template.yaml
     * file then it will resolve the template.yaml file and create a new <env>.yaml file.
     * If <env>.yaml already exists at location src/main/java/config/ or project root directory/test_configs,
     * then it will not resolve template.yaml file and will use the existing <env>.yaml file.
     */
    @Override
    public void before() {
        /*try {
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    {
        try {
            scnMapping.put("ContactSearchWithFirstName", SampleScenarios.scnOpenAnyComputer());

            ScenarioBuilder sampleScn = scenario("Open any Computer").exec(SampleScenarios.scnOpenAnyComputer());

            /**
             * All the load patterns are maintained in gatlingcommon library, and below setUp command can be used to
             * execute any type of available load pattern under gatlingcommon library. Below are the commands can be
             * used for executing any type of load test.
             *
             * Commands to select and run the load test type:
             *
             * |- To run <Iterative> load test type e.g.:
             * |----- mvn clean install -DLoadTestType=Iterative -DContactSearchWithFirstName=1 -DNoOfIteration=1
             * -DRampUpTime=1 -DTestingEnvironment=T2
             *
             * |- To run <StepUp> load test type e.g.:
             * |----- mvn clean install -DLoadTestType=StepUp -DContactSearchWithFirstName=6 -DVUsersPerStep=2
             * -DSteadyStatePerStep=15 -DRampUpTime=2 -DRampDownTime=2 -DTestingEnvironment=T2
             *
             * |- To run <Linear> or <Endurance> load test type e.g.:
             * |----- mvn clean install -DLoadTestType=LinearOrEndurance -DContactSearchWithFirstName=2 -DRampUpTime=2
             * -DLoadTestDuration=30 -DTestingEnvironment=T2
             *
             * |- To run <Throughput> load test type e.g.:
             * |----- mvn clean install -DLoadTestType=Throughput -DContactSearchWithFirstName=10 -DVUsersPerSecond=2
             * -DTargetRPS=1 -DLoadTestDuration=50 -DTestingEnvironment=T2
             *
             * |- To run <Custom> load test type e.g.:
             * |----- <JumpsInThroughputFromFirstToSecondTarget>:
             * |---------- mvn clean install -DLoadTestType=JumpsInThroughputFromFirstToSecondTarget
             * -DContactSearchWithFirstName=10 -DVUsersPerSecond=2 -DTargetRPS=1 -DSecondTargetRPS=2
             * -DLoadTestDuration=60 -DSteadyStateForFirstTargetThroughput=30 -DTestingEnvironment=T2
             *
             * |----- <MultiplyVUsersAtEveryStep>:
             * |---------- mvn clean install -DLoadTestType=MultiplyVUsersAtEveryStep -DLoadTestDuration=60
             * -DSteadyStatePerStep=10 -DContactSearchWithFirstName=2 -DMultiplyVUserByAtEveryStep=2 -DRampUpTime=2
             * -DRampUpTime=2 -DTestingEnvironment=T2
             *
             * |----- <StartWithConstantVUsersAndThenAddSpikeOfVUsersEveryStep>:
             * |---------- mvn clean install -DLoadTestType=StartWithConstantVUsersAndThenAddSpikeOfVUsersEveryStep
             * -DLoadTestDuration=60 -DSteadyStateForConstantVUsers=20 -DSteadyStateForSpikeVUsers=10
             * -DContactSearchWithFirstName=5 -DSpikeVUsers=2 -DRampUpTime=5 -DRampDownTime=5 -DTestingEnvironment=T2
             */

            /*this.setUp(GatlingLoadPattern.execute(GatlingLoadPattern.LOAD_TEST_TYPE, scnMapping))
                .protocols(BaseHelpers.httpProtocolWithInferHtmlResources);*/
            setUp(sampleScn.injectOpen(rampUsers(1).during(1)));
            /**
             * If any load pattern does not exists in gatlingcommon library then you can design and test the pattern
             * locally by commenting out the above statement. e.g: we have a requirement to inject given number of
             * users at once, below is the setUp statement
             * setUp(ScenariosLoadDistribution.scnDistribution.inject(atOnceUsers(1)))
             * Once the load pattern is designed and verified then please raise the PR in gatlingcommon library as
             * ultimately we need to maintain all the load patterns in gatlingcommon library.
             * Stash link for gatlingcommon: https://stash.cvent.net/projects/PLT/repos/gatlingcommon/browse
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
