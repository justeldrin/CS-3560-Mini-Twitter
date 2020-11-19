package application;

import java.util.*;

//Observer Pattern subject
public abstract class Subject extends Entry{

	private List<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers(User user)
	{
		for (Observer observer: observers)
		{
			observer.update(user);
		}
	}
	
}
