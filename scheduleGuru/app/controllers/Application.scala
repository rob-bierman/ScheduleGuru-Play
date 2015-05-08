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
    val tableHeader = Array(" ","Department","Number","Name","Days","StartTime","EndTime","Prof")
    var classList = List[Array[String]]()
    try{
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT * FROM AllClasses WHERE Department = \""+major+"\"")
      while (rs.next()) {
        val currClass = new Array[String](7)
        currClass(0) = rs.getString("Department")
        currClass(1) = rs.getString("Number")
        currClass(2) = rs.getString("Name")
        currClass(3) = rs.getString("Days")
        currClass(4) = rs.getString("StartTime")
        currClass(5) = rs.getString("EndTime")
        currClass(6) = rs.getString("Prof")
        classList ::= currClass
      }
    } finally {
      conn.close()
    }
    var majorClasses = classList.reverse.toArray
    var counter = 0;
    var checkBoxID = "chckBox" + counter.toString()
    val tableHTML = {
      <tbody>
        <tr> {for (a <- tableHeader) yield { <th> {a} </th>}} </tr>
        {for (a <- majorClasses) yield{
            <tr>
                <td><input type="checkbox" onclick="testCheckBoxes(this)" name="majorclasses" id={ a(0) + " " + a(1) + " " + a(3) + " " + a(4) + " " + a(5) }></input></td>
                {for(b <-0 until a.length) yield{ <td>{ a(b) }</td> }}
            </tr>
        }}
      </tbody>
    }
    Ok(views.html.build3(tableHTML))
  }
  
  def build4(_mwf:String,_tr:String,earliest:String,latest:String) = Action{
    var daysString = ""
    val mwf = if(_mwf == "None") "" else _mwf
    val tr = if(_tr == "None") "" else _tr
    val days = mwf+tr
    val notDays = "MWFTR".filter(!days.contains(_))
    if(days != ""){
        daysString += " AND ("
        for (c <- 0 until days.length) {
          if(c == 0) daysString += " Days LIKE \"%" + days(c) + "%\""
          else daysString += " OR Days LIKE \"%" + days(c) + "%\""
        }
        daysString += ")"
    }
    if(notDays != ""){
        daysString += " AND NOT ("
        for (c <- 0 until notDays.length) {
          if(c == 0) daysString += " Days LIKE \"%" + notDays(c) + "%\""
          else daysString += " OR Days LIKE \"%" + notDays(c) + "%\""
        }
        daysString += ")"
    }
    if(days == "mwftr" || days == "mtwrf"){
      daysString = ""
    }
    val sT = if(earliest == "No Preference") "00:00:00" else earliest
    val eT = if(latest == "No Preference") "23:59:59" else latest
    
    println("Days are "+mwf+" "+tr+" earliest is "+earliest+" latest"+latest)
    
    println("Select * from AllClasses WHERE StartTime >= \""+sT+"\" AND EndTime <= \""+eT+"\" AND CC = 1" + daysString)
    val conn = DB.getConnection()
    val tableHeader = Array(" ","Department","Number","Name","Days","StartTime","EndTime","Prof")
    var classList = List[Array[String]]()
    try{
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("Select * from AllClasses WHERE StartTime > \""+sT+"\" AND EndTime < \""+eT+"\" AND CC = 1" + daysString)
      while (rs.next()) {
        val currClass = new Array[String](7)
        currClass(0) = rs.getString("Department")
        currClass(1) = rs.getString("Number")
        currClass(2) = rs.getString("Name")
        currClass(3) = rs.getString("Days")
        currClass(4) = rs.getString("StartTime")
        currClass(5) = rs.getString("EndTime")
        currClass(6) = rs.getString("Prof")
        classList ::= currClass
      }
    } finally {
      conn.close()
    }
    var ccClasses = classList.reverse.toArray
    val tableHTML = {
      <tbody>
        <tr> {for (a <- tableHeader) yield { <th> {a} </th>}} </tr>
        {for (a <- ccClasses) yield{
            <tr>
                <td><input type="checkbox" onclick="testCheckBoxes(this)" name="ccClasses" id={ a(0) + " " + a(1) + " " + a(3) + " " + a(4) + " " + a(5) }></input></td>
                {for(b <-0 until a.length) yield{ <td>{ a(b) }</td> }}
            </tr>
        }}
      </tbody>
    }

    Ok(views.html.build4(tableHTML))
  }
  
  def build5 = Action{
    Ok(views.html.build5())
  }
}
