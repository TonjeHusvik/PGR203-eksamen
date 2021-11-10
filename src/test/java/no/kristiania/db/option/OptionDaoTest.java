package no.kristiania.db.option;

import no.kristiania.TestData;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionDaoTest {
    private OptionDao dao = new OptionDao(TestData.testDataSource());

    @Test
    void shouldRetrieveSavedOption() throws SQLException {
        Option option = exampleOption();
        dao.saveOption(option);
        assertThat(dao.retrieveOption(option.getOptionId()))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(option)
        ;
    }

    @Test
    void shouldListAllPeople() throws SQLException {
        Option option = exampleOption();
        dao.saveOption(option);

        Option anotherOption = exampleOption();
        dao.saveOption(anotherOption);

        assertThat(dao.listAllOption())
                .extracting((Option::getOptionId))
                .contains(option.getOptionId(), anotherOption.getOptionId());
    }

    public static Option exampleOption() {
        Option option = new Option();
        option.setOptionName(TestData.pickOne("Bra", "Dårlig", "Okei"));
        option.setQuestionId(TestData.pickOneInteger(1, 2, 3, 4));
        return option;
    }

}
