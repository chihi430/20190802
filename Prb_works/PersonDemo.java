package lineage;

public class PersonDemo {
private static final String newline = "\n";
	
	private Person fred;
	private Person doug;
	private Person peter;
	private Person sam;
	private Person susie;
	private Person julie;
	private Person ted;
	
	private StringBuilder testOutput = new StringBuilder();
	
	private void appendTextLine(String text) {
		testOutput.append(text + newline);
	}
	
    /**
     * Runs tests of the Person class using a sample family. The result should be:
     * <p>
     * <pre>
     *    --- Tests of the childOf method --------------------
     *    Doug is a child of Fred
     *    Peter is a child of Fred
     *    Sam is a child of Doug
     *    Julie is a child of Peter
     *    Ted is a child of Sam
     *    --- Tests of the siblingOf method --------------------
     *    Doug is a sibling of Peter
     *    Sam is a sibling of Susie
     *    --- Tests of the brotherOf method --------------------
     *    Doug is a brother of Peter
     *    Sam is a brother of Susie
     *    --- Tests of the sisterOf method --------------------
     *    Susie is a sister of Sam
     * </pre>
     */
	public static void main(String[] args) {
		PersonDemo myFamily = new PersonDemo();
		
		myFamily.setUp();
		myFamily.testChildOf();
		myFamily.testSiblingOf();
		myFamily.testBrotherOf();
		myFamily.testSisterOf();
		
		System.out.println(myFamily.getTestOutput());
	}

	/**
	 * Returns the test result in string format.
	 */
	public String getTestOutput() {
		return testOutput.toString();
	}
	
    /**
     * Sets up Person objects representing my family members and their paternal relations.
     */
	public void setUp() {
		fred = new Person();
		fred.setName("Fred");
		fred.setGender(Gender.MALE);
		
		doug = new Person();
		doug.setName("Doug");
		doug.setGender(Gender.MALE);
		
		peter = new Person();
		peter.setName("Peter");
		peter.setGender(Gender.MALE);
		
		sam = new Person();
		sam.setName("Sam");
		sam.setGender(Gender.MALE);
		
		susie = new Person();
		susie.setName("Susie");
		susie.setGender(Gender.FEMALE);
		
		julie = new Person();
		julie.setName("Julie");
		julie.setGender(Gender.FEMALE);

		ted = new Person();
		ted.setName("Ted");
		ted.setGender(Gender.MALE);

		doug.setFather(fred);
		peter.setFather(fred);
		sam.setFather(doug);
		susie.setFather(doug);
		julie.setFather(peter);
		ted.setFather(sam);
	}
	
    /**
     * Tests the childOf method.
     */
	public void testChildOf() {
		appendTextLine("--- Tests of the childOf method --------------------");
		
		if ( fred.childOf(fred) )
			appendTextLine("error: " + fred.getName() + " is NOT a child of himself");
		if ( doug.childOf(doug) )
			appendTextLine("error: " + doug.getName() + " is NoT a child of himself");
		if ( fred.childOf(doug) )
			appendTextLine("error: " + fred.getName() + " is NOT a child of " + doug.getName());
		if ( doug.childOf(sam) )
			appendTextLine("error: " + doug.getName() + " is NOT a child of " + sam.getName());
		if ( fred.childOf(sam) )
			appendTextLine("error: " + fred.getName() + " is NOT a child of " + sam.getName());
		if ( doug.childOf(peter) )
			appendTextLine("error: " + doug.getName() + " is NOT a child of " + peter.getName());
		

		if ( doug.childOf(fred) )
			appendTextLine(doug.getName() + " is a child of " + fred.getName());
		else
			appendTextLine("error: " + doug.getName() + " should be a child of " + fred.getName());

		if ( peter.childOf(fred) )
			appendTextLine(peter.getName() + " is a child of " + fred.getName());
		else
			appendTextLine("error: " + peter.getName() + " should be a child of " + fred.getName());

		if ( sam.childOf(doug) )
			appendTextLine(sam.getName() + " is a child of " + doug.getName());
		else
			appendTextLine("error: " + sam.getName() + " should be a child of " + fred.getName());

		if ( julie.childOf(peter) )
			appendTextLine(julie.getName() + " is a child of " + peter.getName());
		else
			appendTextLine("error: " + julie.getName() + " should be a child of " + peter.getName());

		if ( ted.childOf(sam) )
			appendTextLine(ted.getName() + " is a child of " + sam.getName());
		else
			appendTextLine("error: " + ted.getName() + " should be a child of " + sam.getName());
	}
	
    /**
     * Tests the siblingOf method.
     */
	public void testSiblingOf() {
		appendTextLine("--- Tests of the siblingOf method --------------------");

		if ( fred.siblingOf(fred) )
			appendTextLine("error: " + fred.getName() + " is NOT a sibling of himself");
		if ( doug.siblingOf(doug) )
			appendTextLine("error: " + doug.getName() + " is NoT a sibling of himself");
		if ( fred.siblingOf(doug) )
			appendTextLine("error: " + fred.getName() + " is NOT a sibling of " + doug.getName());
		if ( doug.siblingOf(fred) )
			appendTextLine("error: " + doug.getName() + " is NOT a sibling of " + fred.getName());
		if ( doug.siblingOf(julie) )
			appendTextLine("error: " + doug.getName() + " is NOT a sibling of " + julie.getName());
		if ( sam.siblingOf(fred) )
			appendTextLine("error: " + sam.getName() + " is NOT a sibling of " + fred.getName());
		if ( sam.siblingOf(peter) )
			appendTextLine("error: " + sam.getName() + " is NOT a sibling of " + peter.getName());
		if ( sam.siblingOf(julie) )
			appendTextLine("error: " + sam.getName() + " is NOT a sibling of " + julie.getName());
		
		if ( doug.siblingOf(peter) )
			appendTextLine(doug.getName() + " is a sibling of " + peter.getName());
		else
			appendTextLine("error: " + doug.getName() + " should be a sibling of " + peter.getName());

		if ( sam.siblingOf(susie) )
			appendTextLine(sam.getName() + " is a sibling of " + susie.getName());
		else
			appendTextLine("error: " + sam.getName() + " should be a sibling of " + susie.getName());
	}
	
    /**
     * Tests the brotherOf method.
     */
	public void testBrotherOf() {
		appendTextLine("--- Tests of the brotherOf method --------------------");

		if ( fred.brotherOf(fred) )
			appendTextLine("error: " + fred.getName() + " is NOT a brother of himself");
		if ( doug.brotherOf(doug) )
			appendTextLine("error: " + doug.getName() + " is NoT a brother of himself");
		if ( fred.brotherOf(doug) )
			appendTextLine("error: " + fred.getName() + " is NOT a brother of " + doug.getName());
		if ( doug.brotherOf(fred) )
			appendTextLine("error: " + doug.getName() + " is NOT a brother of " + fred.getName());
		if ( doug.brotherOf(julie) )
			appendTextLine("error: " + doug.getName() + " is NOT a brother of " + julie.getName());
		if ( sam.brotherOf(fred) )
			appendTextLine("error: " + sam.getName() + " is NOT a brother of " + fred.getName());
		if ( sam.brotherOf(peter) )
			appendTextLine("error: " + sam.getName() + " is NOT a brother of " + peter.getName());
		if ( sam.brotherOf(julie) )
			appendTextLine("error: " + sam.getName() + " is NOT a brother of " + julie.getName());
		if ( susie.brotherOf(sam) )
			appendTextLine("error: " + susie.getName() + " is NOT a brother of " + sam.getName());
		
		if ( doug.brotherOf(peter) )
			appendTextLine(doug.getName() + " is a brother of " + peter.getName());
		else
			appendTextLine("error: " + doug.getName() + " should be a brother of " + peter.getName());

		if ( sam.brotherOf(susie) )
			appendTextLine(sam.getName() + " is a brother of " + susie.getName());
		else
			appendTextLine("error: " + sam.getName() + " should be a brother of " + susie.getName());
		
	}
	
    /**
     * Tests the sisterOf method.
     */
	public void testSisterOf() {
		appendTextLine("--- Tests of the sisterOf method --------------------");

		if ( fred.sisterOf(fred) )
			appendTextLine("error: " + fred.getName() + " is NOT a sister of himself");
		if ( doug.sisterOf(doug) )
			appendTextLine("error: " + doug.getName() + " is NoT a sister of himself");
		if ( fred.sisterOf(doug) )
			appendTextLine("error: " + fred.getName() + " is NOT a sister of " + doug.getName());
		if ( doug.sisterOf(fred) )
			appendTextLine("error: " + doug.getName() + " is NOT a sister of " + fred.getName());
		if ( doug.sisterOf(julie) )
			appendTextLine("error: " + doug.getName() + " is NOT a sister of " + julie.getName());
		if ( sam.sisterOf(fred) )
			appendTextLine("error: " + sam.getName() + " is NOT a sister of " + fred.getName());
		if ( sam.sisterOf(peter) )
			appendTextLine("error: " + sam.getName() + " is NOT a sister of " + peter.getName());
		if ( sam.sisterOf(julie) )
			appendTextLine("error: " + sam.getName() + " is NOT a sister of " + julie.getName());
		if ( sam.sisterOf(susie) )
			appendTextLine("error: " + sam.getName() + " is NOT a sister of " + susie.getName());
		if ( doug.sisterOf(peter) )
			appendTextLine("error: " + doug.getName() + " is NOT a sister of " + peter.getName());
		
		if ( susie.sisterOf(sam) )
			appendTextLine(susie.getName() + " is a sister of " + sam.getName());
		else
			appendTextLine("error: " + susie.getName() + " should be a sister of " + sam.getName());
	}

}
