import implementations.clients.ActionsTest;
import implementations.clients.mocked.BasicEmployeeActionsTest;
import implementations.clients.mocked.MockedActionTest;
import implementations.generator.FlightSignatureGeneratorTest;
import implementations.generator.GeneratorTest;
import implementations.repository.MemoryRepositoryTest;
import implementations.repository.RepositoryTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Categories.IncludeCategory({RepositoryTest.class, GeneratorTest.class, ActionsTest.class, MockedActionTest.class})
@Suite.SuiteClasses({
        implementations.clients.BasicEmployeeActionsTest.class,
        BasicEmployeeActionsTest.class,
        FlightSignatureGeneratorTest.class,
        MemoryRepositoryTest.class
})

public class AllTestsSuite {

}