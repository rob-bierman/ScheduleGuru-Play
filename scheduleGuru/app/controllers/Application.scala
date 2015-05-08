package controllers

import play.api._
import play.api.db._
import play.api.mvc._
import play.api.Play.current

object Application extends Controller {
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
  
  def build3(major: String) = Action{
    println("Found major to be: "+major)
    val conn = DB.getConnection()
    val tableHeader = Array("Name","Department","Number","Section","Prof","Building","RoomNum","Days","StartTime","EndTime","Note")
    var classList = List[Array[String]]()
    classList ::= tableHeader
    try{
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT * FROM AllClasses WHERE Department = \""+major+"\"")
      while (rs.next()) {
        val currClass = new Array[String](tableHeader.length)
        currClass(0) = rs.getString("Name")
        currClass(1) = rs.getString("Department")
        currClass(2) = rs.getString("Number")
        currClass(3) = rs.getString("Section")
        currClass(4) = rs.getString("Prof")
        currClass(5) = rs.getString("Building")
        currClass(6) = rs.getString("RoomNum")
        currClass(7) = rs.getString("Days")
        currClass(8) = rs.getString("StartTime")
        currClass(9) = rs.getString("EndTime")
        currClass(10) = rs.getString("Note")
        classList ::= currClass
      }
    } finally {
      conn.close()
    }
    var majorClasses = classList.reverse.toArray
    var counter = 0;
    var checkBoxID = "chckBox" + counter.toString()
    val tableHTML = for (a <- majorClasses) yield{
        <tr>
            <td><input type="checkbox" onclick="testCheckBoxes(this)" name="majorclasses" id={ a(2) + " " + a(3) + " " + a(5) + " " + a(6) + " " + a(7) }></input></td>
            {for(b <- a) yield{ <td>{ b }</td> }}
        </tr>
    }
    Ok(views.html.build3(tableHTML))
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
    Ok(views.html.build4(ccTableHTML))
  }
  
  def build5 = Action{
    Ok(views.html.build5())
  }
}
