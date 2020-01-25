import com.bridgelabz.LeagueAnalyser;
import com.bridgelabz.exception.LeagueAnalyserException;
import com.bridgelabz.model.BatsMan;
import com.bridgelabz.model.Bowler;
import com.bridgelabz.model.IplCSVDao;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class CricketLeagueAnalyserTest {

    private String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPLMOSTRUNS.csv";

    private String WRONG_IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPLMOSTRUNS1.csv";

    private String WRONG_HEADER_CSV_FILE_PATH = "./src/test/resources/WrongHeader.csv";

    private String WRONG_DELIMITER_CSV_FILE_PATH= "./src/test/resources/WrongIplDelimiter.csv";

    private String IPL_MOST_WICKET_CSV_FILE_PATH="./src/test/resources/wicket.csv";

    @Test
    public void whenGivenCSVFileRecordOfMostRuns_ShouldReturnProperData() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        int noOFRecords = leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,
                                                        IPL_MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100,noOFRecords);
    }

    @Test
    public void whenGivenWrongCSVFilePath_ShouldThrowAnalyserException() {
        try {

            LeagueAnalyser leageAnalyser = new LeagueAnalyser();
            int noOFRecords = leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,
                                                        WRONG_IPL_MOST_RUNS_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e) {
           Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.NO_CSV_FILE);
        }
    }

    @Test
    public void whenGivenCSVFile_WithWrongHeader_SholdThrowAnalyserException() {
        try {

            LeagueAnalyser leageAnalyser = new LeagueAnalyser();
            int noOFRecords = leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,
                                                            WRONG_HEADER_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e){

            Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);

        }
    }

    @Test
    public void whenGivenIplMostRunsCSV_WithWrongDelimiter_ShouldThrowException() {
        try {

            LeagueAnalyser leageAnalyser = new LeagueAnalyser();
            int noOFRecords = leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.
                                                        BATSMAN,WRONG_DELIMITER_CSV_FILE_PATH);

        }catch (LeagueAnalyserException e){

            Assert.assertEquals(e.type,LeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
       }
    }

    @Test
    public void whenGivenIplMostRunsCSVFile_ShouldReturnSortedDataBasedOnAverage() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,
                                            IPL_MOST_RUNS_CSV_FILE_PATH);
        List<BatsMan> sortedData = leageAnalyser.sortBaseOnAverage();
        BatsMan bestAvgBatsMan = sortedData.get(0);
        Assert.assertEquals("MS Dhoni",bestAvgBatsMan.getPlayerName());

    }

    @Test
    public void whenGivenIplMostRunsCsvData_ShouldReturnTopStrickingRates() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,
                                                    IPL_MOST_RUNS_CSV_FILE_PATH);
        List<BatsMan> sortedData = leageAnalyser.getTopStrikingRates();
        Assert.assertEquals("Ishant Sharma",sortedData.get(0).getPlayerName());

    }

    @Test
    public void whenGivenIplMostRunsCsvData_ShouldSortDataBasedOnSixesAndFours() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,
                                    IPL_MOST_RUNS_CSV_FILE_PATH);
        List<BatsMan> sortedData = leageAnalyser.sortBaseOnSixesAndFours();
        Assert.assertEquals("Andre Russell",sortedData.get(0).getPlayerName());

    }

    @Test
    public void whenGivenIplMostRunsCsvData_ShouldSortDataByStrikingRateAndSixesAndFour() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,
                                    IPL_MOST_RUNS_CSV_FILE_PATH);
        List<BatsMan> batsManList = leageAnalyser.sortByStrickingSixAndFour();

        String playerBestStrikingSixFour = batsManList.get(0).getPlayerName();
        Assert.assertEquals("Andre Russell",playerBestStrikingSixFour);

    }

    @Test
    public void whenGivenIplMostRunsCsvData_ShouldSortDataByAverageAndStrikingRate() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,IPL_MOST_RUNS_CSV_FILE_PATH);
        List<BatsMan> batsManList = leageAnalyser.sortByAverageAndStrikingRate();
        batsManList.forEach(System.out::println);
        Assert.assertEquals("MS Dhoni",batsManList.get(0).getPlayerName());

    }

    @Test
    public void whenGivenIplMostRunsCsvData_SholdSortDataBaseOnRunsAndAverage() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BATSMAN,IPL_MOST_RUNS_CSV_FILE_PATH);
        List<BatsMan> batsManList = leageAnalyser.sortByRunsAndAverage();
        Assert.assertEquals("David Warner",batsManList.get(0).getPlayerName());

    }

    @Test
    public void whenGivenIplMostWicketCsvData_ShouldReturnProperData() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        int size = leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BOWLER,
                                                    IPL_MOST_WICKET_CSV_FILE_PATH);
        Assert.assertEquals(99,size);
    }

    @Test
    public void whenGivenIplMostWicketCsvData_ShouldReturnSortedDataByBowlingAvg() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BOWLER,
                                        IPL_MOST_WICKET_CSV_FILE_PATH);
        List<Bowler> bowlersData = leageAnalyser.SortByBowlingAvg();
        Assert.assertEquals("Krishnappa Gowtham",bowlersData.get(0).getPlayerName());

    }

    @Test
    public void whenGivenIplMostWicketCsvData_ShouldReturnSortedDataByBowlingStrikingRate() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BOWLER,
                IPL_MOST_WICKET_CSV_FILE_PATH);
        List<Bowler> list = leageAnalyser.sortBaseOnBowlingStrikeRate();
        String topAvgBowler = list.get(0).getPlayerName();
        Assert.assertEquals("Krishnappa Gowtham",topAvgBowler);

    }

    @Test()
    public void whenGivenIplMostWicketCsvData_ShouldReturnSortedDataBasedOnEconomyRate() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BOWLER ,
                                        IPL_MOST_WICKET_CSV_FILE_PATH);
        double bestEconomyRates = leageAnalyser.sortBaseOnEconomyRate().get(0).getEconomeyRate();
        Assert.assertEquals(13.5,bestEconomyRates,0.0);

    }

    @Test()
    public void whenGivenIplMostWicketCsvData_ShouldReturnSortedDataBasedOnStrikingRateAndWickets() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BOWLER ,
                                        IPL_MOST_WICKET_CSV_FILE_PATH);
        List<Bowler> sortedData = leageAnalyser.sortBaseOnStrikingRateAndWicket();
        String bowlerWithSRWk = sortedData.get(0).getPlayerName();
        Assert.assertEquals("Krishnappa Gowtham",bowlerWithSRWk);

    }
    
    
    @Test()
    public void whenGivenIplMostWicketCsvData_ShouldReturnSortedDataBasedOnStrikingRateAndBowlingAvg() {

        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.BOWLER ,
                IPL_MOST_WICKET_CSV_FILE_PATH);
        List<Bowler> sortedData = leageAnalyser.sortBaseOnStrikingRateAndBowlingAvg();
        String bowlerWithSRAvg = sortedData.get(0).getPlayerName();
        Assert.assertEquals("Krishnappa Gowtham",bowlerWithSRAvg);

    }

    @Test
    public void whenGivenIplMostWicketCsvData_ShouldReturnSortedDataBaseOnBowlingAvgAndBatingAvg() {
        LeagueAnalyser leageAnalyser = new LeagueAnalyser();
        int noOFRecords = leageAnalyser.loadCSVData(LeagueAnalyser.CsvFileType.LEAGUE,
                IPL_MOST_RUNS_CSV_FILE_PATH,IPL_MOST_WICKET_CSV_FILE_PATH);
        List<IplCSVDao> SortedDataByBlAvgBtAvg = leageAnalyser.sortBaseOnBatingAvgBowlingAvg();
        bestBlAvgBtAvg = s
        Assert.assertEquals("Krishnappa Gowtham");
    }
}
