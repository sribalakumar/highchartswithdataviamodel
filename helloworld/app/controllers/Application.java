package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
	
	public static void sayHello(String myName){
	if(validation.hasErrors()}
	{ flash.error("oops, please enter your name!");
	index();
	}
	render(myName);
	}

}