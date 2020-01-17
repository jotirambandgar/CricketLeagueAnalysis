import com.bridgelabz.LeageAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {
    private String IPL_MOST_RUNS_CSV_FILE_PATH="./src/test/resources/IPLMOSTRUNS.csv";

    @Test
    public void whenGivenCSVFileRecordOfMostRuns_ShouldReturnProperData() {
        LeageAnalyser leageAnalyser = new LeageAnalyser();
        int noOFRecords = leageAnalyser.loadMostRunData(IPL_MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(101,noOFRecords);
    }


}
