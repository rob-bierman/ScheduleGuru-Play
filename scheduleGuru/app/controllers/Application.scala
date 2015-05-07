package controllers

import play.api._
import play.api.db._
import play.api.mvc._
import play.api.Play.current

object Application extends Controller {
  //val mydb = new MyDatabase(DB.getConnection())

  def welcome = Action {
    Ok(views.html.welcome())
  }

  def login = Action {
    Ok(views.html.login())
  }

  def aboutUs = Action {
    Ok(views.html.aboutUs())
  }
  
  def contact = Action {
    Ok(views.html.contact())
  }
  
  def build2 = Action{
    Ok(views.html.build2())
  }
  
  def build3 = Action{
    var majorClasses = Array(Array("1","2","3","4","5","6","7","8","9","10","11"),Array("1","2","3","4","5","6","7","8","9","10","11"))
    var counter = 0;
    var checkBoxID = "chckBox" + counter.toString()
    val tableHTML = for (a <- majorClasses) yield{
        <tr>
            <td><input type="checkbox" onclick="testCheckBoxes(this)" name="majorclasses" id={ a(2) + " " + a(3) + " " + a(5) + " " + a(6) + " " + a(7) }></input></td>
            <td>{ a(2) }</td>
            <td>{ a(3) }</td>
            <td>{ a(1) }</td>
            <td>{ a(5) }</td>
            <td>{ a(6) + "-" + a(7) } </td>
            <td>{ a(10) }</td>
        </tr>
    }
    Ok(views.html.build3(tableHTML, mydb))
  }
  
  def build4 = Action{
    val possibleCCClasses = Array(Array("1","2","3","4","5","6","7","8","9","10","11"),Array("1","2","3","4","5","6","7","8","9","10","11"))
    val ccTableHTML = possibleCCClasses.map(
        a => 
        <tr>
            <td> <input type="checkbox" onclick="testCheckBoxes(this)" name="ccclasses" id={ a(2) + " " + a(3) + " " + a(5) + " " + a(6) + " " + a(7) }></input></td>
            <td>{ a(2) }</td>
            <td>{ a(3) }</td>
            <td>{ a(1) }</td>
            <td>{ a(5) }</td>
            <td>{ a(6) + "-" + a(7) } </td>
            <td>{ a(10) }</td>
        </tr>
    )
    Ok(views.html.build4(ccTableHTML, mydb))
  }
  
  def build5 = Action{
    Ok(views.html.build5(mydb))
  }
}
