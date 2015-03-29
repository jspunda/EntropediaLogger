package util;


public class Patterns {

	public static final String ITEMVALUEPATTERN = ".*class=\"IT\">(\\S*)";
	public static final String ITEMNAMEPATTERN = ".*class=\"PageSubject\">Material: (.*)<";
	
	public static final String TEAMPATTERN = "\\[Team\\] \\[\\w*\\] (.*) received (.*) \\((\\d*)\\)";
	
	private static final String SYSTEMPREFIX = "\\[System\\] \\[] ";  
	public static final String SHOTHITPATTERN = SYSTEMPREFIX + "You inflicted (\\d*.\\d)";
	public static final String SHOTMISSPATTERN = SYSTEMPREFIX + "You missed.";
	public static final String SHOTEVADEPATTERN = SYSTEMPREFIX + "Target evaded attack.";
	public static final String SHOTCRITPATTERN = SYSTEMPREFIX + "Critical hit \\D*(\\d*.\\d)";
	public static final String DEATHPATTERN = SYSTEMPREFIX + "You were killed";
	public static final String DMGTAKENPATTERN = SYSTEMPREFIX + "You take (\\d*.\\d)";
	
	public static final String LOGGERPREFIX = "\\[Logger\\] ";
	
	public static final String[] ALLGAMEPATTERNS = {
		TEAMPATTERN, SHOTHITPATTERN, SHOTMISSPATTERN,
		SHOTEVADEPATTERN, DMGTAKENPATTERN, SHOTCRITPATTERN,
		DEATHPATTERN
	};
	
	public static final String[] ALLSITEPATTERNS = {
		ITEMVALUEPATTERN, ITEMNAMEPATTERN
	};
}
