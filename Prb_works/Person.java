package lineage;

public class Person{	
	//---------------------------------------------------------
    // private fields to declare attributes
    //---------------------------------------------------------
	String name="unknowm";
	Gender gender;
	Person father;
    //---------------------------------------------------------
    // constructor
    //---------------------------------------------------------
    public Person(){}
    //---------------------------------------------------------
    // three public methods to get attribute values
    //---------------------------------------------------------
    public String getName() {return this.name;}
    public Gender getGender(){return this.gender;}
    public Person getFather(){return this.father;}
       //---------------------------------------------------------
    // three public methods to set attribute values
    //---------------------------------------------------------
    public void setName(java.lang.String newName){name=newName;}
    public void setGender(Gender knownGender){gender=knownGender;}
    public void setFather(Person knownFather){father=knownFather;}   
    //-------------------------------------------------------------
    // four public boolean methods to determine specific relations
    //-------------------------------------------------------------
    public boolean childOf(Person other){
    	if(father==other){
    	return true;	
    		}
    	else
    		return false;
     	}
    public boolean siblingOf(Person other){
    	if(getFather()==other.getFather()&&getName()!=other.getName()){
    		return true;
    	}
    	else
    		return false;
    }
    public boolean brotherOf(Person other){
    	if(getFather()==other.getFather()&&getName()!=other.getName()&&getGender()==gender.MALE){
    		return true;
    	}
    	else
    		return false;
    }
    public boolean sisterOf(Person other){
    	if(getFather()==other.getFather()&&getName()!=other.getName()&&getGender()==gender.FEMALE){
    		return true;
    	}
    	else
    		return false;
    }

}

   
   



