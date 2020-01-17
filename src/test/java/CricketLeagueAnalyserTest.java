import com.bridgelabz.LeageAnalyser;
import com.bridgelabz.exception.LeagueAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPLMOSTRUNS.csv";

    private String WRONG_IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPLMOSTRUNS1.csv";

    private String WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/WrongHeader.csv";

    private String WRONG_DELIMITER_CSV_FILE_PATH= "./src/test/resources/WrongIplDelimiter.csv";

    @Test
    public void whenGivenCSVFileRecordOfMostRuns_ShouldReturnProperData() {

        LeageAnalyser leageAnalyser = new LeageAnalyser();
        int noOFRecords = leageAnalyser.loadMostRunData(IPL_MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100,noOFRecords);
    }

    @Test
    public void whenGivenWrongCSVFilePath_ShouldThrowAnalyserException() {
        try {

            LeageAnalyser leageAnalyser = new LeageAnalyser();
            int noOFRecords = leageAnalyser.loadMostRunData(WRONG_IPL_MOST_RUNS_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e) {
           Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.NO_CSV_FILE);
        }
    }

    @Test
    public void whenGivenCSVFile_WithWrongHeader_SholdThrowAnalyserException() {
        try {

            LeageAnalyser leageAnalyser = new LeageAnalyser();
            int noOFRecords = leageAnalyser.loadMostRunData(WRONG_HEADER_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e){

            Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
//            Assert.assertEquals();
        }
    }

    @Test
    public void whenGivenIplMostRunsCSV_WithWrongDelimiter_ShouldThrowException() {
        try {

            LeageAnalyser leageAnalyser = new LeageAnalyser();
            int noOFRecords = leageAnalyser.loadMostRunData(WRONG_HEADER_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e){

            Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
       }
    }

}
