import com.bridgelabz.LeagueAnalyser;
import com.bridgelabz.exception.LeagueAnalyserException;
import com.bridgelabz.model.IplCSVDao;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class CricketLeagueAnalyserTest {

    private String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPLMOSTRUNS.csv";

    private String WRONG_IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPLMOSTRUNS1.csv";

    private String WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/WrongHeader.csv";

    private String WRONG_DELIMITER_CSV_FILE_PATH= "./src/test/resources/WrongIplDelimiter.csv";

    @Test
    public void whenGivenCSVFileRecordOfMostRuns_ShouldReturnProperData() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        int noOFRecords = leageAnalyser.loadMostRunData(IPL_MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100,noOFRecords);
    }

    @Test
    public void whenGivenWrongCSVFilePath_ShouldThrowAnalyserException() {
        try {

            LeagueAnalyser leageAnalyser = new LeagueAnalyser();
            int noOFRecords = leageAnalyser.loadMostRunData(WRONG_IPL_MOST_RUNS_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e) {
           Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.NO_CSV_FILE);
        }
    }

    @Test
    public void whenGivenCSVFile_WithWrongHeader_SholdThrowAnalyserException() {
        try {

            LeagueAnalyser leageAnalyser = new LeagueAnalyser();
            int noOFRecords = leageAnalyser.loadMostRunData(WRONG_HEADER_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e){

            Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);

        }
    }

    @Test
    public void whenGivenIplMostRunsCSV_WithWrongDelimiter_ShouldThrowException() {
        try {

            LeagueAnalyser leageAnalyser = new LeagueAnalyser();
            int noOFRecords = leageAnalyser.loadMostRunData(WRONG_DELIMITER_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e){

            Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
       }
    }

    @Test
    public void whenGivenIplMostRunsCSVFile_ShouldReturnSortedDataBasedOnAverage() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadMostRunData(IPL_MOST_RUNS_CSV_FILE_PATH);
        List<IplCSVDao> sortedData = leageAnalyser.sortBaseOnAverage();
        IplCSVDao csvDao = sortedData.get(0);
        Assert.assertEquals("MS Dhoni",csvDao.getPlayerName());

    }

    @Test
    public void whenGivenIplMostRunsCsvData_ShouldReturnTopStrickingRates() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadMostRunData(IPL_MOST_RUNS_CSV_FILE_PATH);
        List<IplCSVDao> sortedData = leageAnalyser.getTopStrikingRates();
        Assert.assertEquals("Ishant Sharma",sortedData.get(0).getPlayerName());

    }

    @Test
    public void whenGivenIplMostRunsCsvData_ShouldSortDataBasedOnSixesAndFours() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadMostRunData(IPL_MOST_RUNS_CSV_FILE_PATH);
        List<IplCSVDao> sortedData =leageAnalyser.sortBaseOnSixesAndFours();
        System.out.println("sixes and four");
        sortedData.forEach(System.out::println);
        Assert.assertEquals("Andre Russell",sortedData.get(0).getPlayerName());

    }

    @Test
    public void whenGivenIplMostRunsCsvData_ShouldSortDataByStrikingRateAndSixesAndFour() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadMostRunData(IPL_MOST_RUNS_CSV_FILE_PATH);
        List<IplCSVDao> list = leageAnalyser.sortByStrickingSixAndFour();
        String playerBestStrikingSixFour = list.get(0).getPlayerName();
        Assert.assertEquals("Andre Russell",playerBestStrikingSixFour);

    }
}
