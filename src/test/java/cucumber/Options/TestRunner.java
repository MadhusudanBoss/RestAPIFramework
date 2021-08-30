package cucumber.Options;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features", 
		  glue= {"stepDefinitions"},
		  tags= {"@AddPlace,@DeletePlace"},
		  plugin= "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
	
// Hooks in Cucumber is nothing but annotation like in TestNg. if we want to run specific test scenario's just give an annotation like @AddPlace and the same in TestRunner file as tags="@AddPlace"
// Here we are running only @DeletePlace tag and it do not have place_id so it will fail, hence we created Hooks.java so that before method has the code to get the place_id.
	// if we wnt to run tests from maven cmd line then use mvn test -Dcucumber.options="--tags @AddPlace" . IN jenkins we have given the paramter so in place of AddPLace just give as "$tag"
	//mvn test -Dcucumber.options="--tags @SmokeTesting --tags @RegressionTesting"
	// if we want to ignore any tags then use ~@AddPlace  example: tags= {"@SmokeTesting","~@RegressionTesting"}
	
	// AND conidtion in tagging is tags={"@Regression","@Smoke"}
	//OR condition in tagging is tags={"Smoke,Regression"} , example2:  tags= {"@AddPlace or @DeletePlace"},
	// to run jenkins on different port number :  tags= {"@AddPlace,@DeletePlace"},
	// Steps to generate Cucumber report.
	// 1: First we should add the plugin as plugin= "json:target/jsonReports/cucumber-report.json", and save the file.
	// goto url say https://github.com/damianszczepanik/maven-cucumber-reporting and copy paste the entire code and remove the <class file path> code and give version value to maven-cucumber-reporting plugin and
	// save this and goto cmd prompt where we have pom.xml and run it as mvn test verify, we see that jsonReports folder gets created and it has cucumber-json.report.
	// Here when tests are running successfully, we see that jsonReports folder gets created and also we will see that cucumber-html-reports folder created. Its a plugin. if we open that
	// we see the overview-features.html file, just open this in editor and see the report. Its awesome.
	
	// while configuring mavan commands in jenkins as mvn test verify -Dcucumber.options="--tags @AddPlace",
	// here we get error because, we are giving maven command, so we have to give it as test verify -Dcucumber.options="--tags @AddPlace.
	// then it accepts.
}
