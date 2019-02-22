package bridgeandtorch;

public class SETTINGS{
    /**
     * Note: The length of starting and destination state
     * strings should be consistent with NUM_OF_PERSONS + 1
     */
    public static final int NUM_OF_PERSONS = 6;
    public static final Integer[] TIMES = new Integer[]{1,2, 3, 5,8,13};
    public static final String START_STATE = "0000000";
    public static final String DESTINATION_STATE = "1111111";
    public static final Algorithm ALGORITHM = Algorithm.A_STAR;
    public static final Heuristic HEURISTIC = Heuristic.AVERAGE;
}
