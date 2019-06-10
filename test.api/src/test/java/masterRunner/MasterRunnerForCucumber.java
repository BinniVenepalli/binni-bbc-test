package masterRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources",
        glue = "StepDefs",
		dryRun = false,
		strict = false,
		monochrome = true,
		tags 	= {"@test"},
        plugin = {
                    "pretty",
                    "html:target/cucumber",
                } 
        )
public class MasterRunnerForCucumber 
{
	@org.junit.BeforeClass
	public static void beforeClass() {
		System.out.println("*************************************************");		
	}
	
	@org.junit.AfterClass
	public static void afterClass() {
		System.out.println("*************************************************");		
	}
}
