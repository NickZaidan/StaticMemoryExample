//Ignore this, its just the python equivalance of "import x" to let us use ArrayLists 
import java.util.ArrayList;


//README:
//Author: Nicholas Zaidan
//This is to show the importance of static variables, public/private variables and getters & setter functions
//Read through the code real quick, I documented it as best I could so you'd understand each part so hopefully nothing in particular confuses you
//After you've ran it and compared to see if you understand it, try uncommenting some of the code at the end of the main function and you'll be able to see the results
//Additionally, after you've done all that try removing the static keyword from the arraylist. You'll see that they no longer share the same memoryspace, and each object has its own arraylist.
//One work around to this, is to make an arraylist in the main function instead of the object class, this might be intuitively more easy for you to understand, but this is just another way of doing it. There will be cases in the future where this won't apply, and you'll have to do it through static instead so its good to learn.
//Hope this helps explain the importance of these concepts


public class Knowledge{
	public static void main(String[] args){
		//Creating of first student and printing its static arraylist
		UniStudent u1 = new UniStudent("Steve");
		System.out.println("u1 printing:");
		u1.printAllInfo();

		//Adding more users that add itself to the static arraylist shared between all objects
		System.out.println("Adding students u2, u3, u4");
		UniStudent u2 = new UniStudent("Greg"); 
		UniStudent u3 = new UniStudent("Bill");
		UniStudent u4 = new UniStudent("Big Bill");
		
		//Printing the arraylists information through u1
		System.out.println("u1 printing");
		u1.printAllInfo();

		//Printing the arraylists information through u2
		System.out.println("u2 printing");
		u2.printAllInfo();
		
		//Changing the university of u2
		System.out.println("Changing the uni of u2 to UTS");
		u2.setUni("UTS");

		//Printing the arraylists information through u1 & u3 to show they are stil sharing the same static space
		System.out.println("u1 & u3 printing");
		u1.printAllInfo();
		u3.printAllInfo();

		//Not accessing the the u2 elemeent through the arraylist, but instead through the object itself. You can see that it still has the correct variable value
		System.out.println("Using getter for u2 to see uni name");
		System.out.println(u2.getUni());
		
		/*
		//Some commented code for you to see the effect
		
		//u2.uni = "Red Hot Chili Peppers"; //When running this you'll see it fail since it cant directly access the uni string since its private
		
		
		//Showing what happens when you try to add a incorrect value to the setter. This would be impossible/difficult if we weren't using private variables and public getters & setters
		int result = u2.setUni("Red Hot Chili Peppers");
		//Return result of trying to add penis as university is incorrect error number 1 (0 implies successful, -1 implies failed and error 1 implies worked correctly but incorrect input
		System.out.println("return from set uni setter function: " +  result);
		*/
		
	}
}


class UniStudent{
	
	static ArrayList<UniStudent> students = new ArrayList<UniStudent>(); //List of all students	
	private String name; //Student name
	private String uni; //Uni name

	//Constructor
	public UniStudent(String name){
		this.name = name; //Setting its name to itself
		this.uni =  "USYD"; //Setting default university to be USYD
		students.add(this); //This may confuse you a tad, but the ArrayList is an array (list as you might call it) that doesn't store primitive data types like ints(it can but this one doesn't), but instead UniStudent objects. This line is adding itself to the list to be shared with all over students. 
	}
	
	//Prints list of all students contained in the object class
	public void printAllInfo(){
		for(int i = 0; i < students.size(); i++){
			students.get(i).printInfo(); //Can probably safely ignore this if you want. Its just calling the printinfo() function on each element in the arraylist
		}
		System.out.println(""); //Giving a blankline after
	}

	//Print individual info
	public void printInfo(){
		System.out.println("Element of Array: " + getIndexOfItself()  + " | Name: " + getName() + " | Uni: " + getUni()); //Just printing out all the information of itself
	}


	//GETTERS (these should all make sense to you)
	public String getUni(){
		return this.uni;
	}

	public String getName(){
		return this.name;
	}
	
	public ArrayList<UniStudent> getStudentList(){
		return this.students;
	}

	//This function might confuse you a bit
	public int getIndexOfItself(){
		for(int i = 0; i < students.size(); i++){
			if(students.get(i) == this){ //Essentially going through each element of the arraylist, and if the element at the index is itself, it just returns the index. Otherwise returns -1 if it cannot find itself. This is possible through the "this" keyword
				return i;
			}
		}
		return -1; //Returning -1 if it cannot find itself
	}

	//SETTERS
	public int setName(String names){
		int index = getIndexOfItself(); //Tries to find its index to update its value
		
		if(index == -1){ //If for some reason the UniStudent doesnt exist in the list, this gives us a safeway to exit the function whilst giving us and error message and not crashing
			System.out.println("Error, doesn't exist");
			return -1; //Returning -1 to indicate that it failed
		}
	       	else{
			students.get(index).name = names;  //Changing its name through the arraylist
			return 0; //Returning 0 to indicate it was sucessful
		}
	}

	public int setUni(String uniName){
		//Same initial business as before
		int index = getIndexOfItself();
		if(index == -1){
			System.out.println("Error, doesn't exist");
			return -1;
		}
		else{
			//You can see here that we are testing the variable before allowing it to change. This way people can not directly change the value, but instead have to go through the correct channels of the setter function. To see this in action uncomment the Some of the lower lines of code in the main function and you can see that 1. You cannot directly change the value, and 2. if you try to change it to an incorrect value, the setter will tell you its incorrect and thus saving the integrity of the list.
			if(uniName.equals("USYD") || uniName.equals("UTS") || uniName.equals("UNSW")){
				students.get(index).uni = uniName;
				return 0; //Returns 0 if successful
			}
			else{
				System.out.println("University " + "\"" + uniName + "\""  + " does not exist");
				return 1; //Returns 1 if operation was sucessful but the value was not, therefore not adding it
			}
		}
	}
}
