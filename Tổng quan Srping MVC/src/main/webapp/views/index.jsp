<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>currency converter</title>
</head>
<body>
<form method="post" action="/converter">
  <table>
    <tr>
      <td><label>Rate :</> </label></td>
      <td><input type="text" name="rate" placeholder="Rate" value="23000"><br></td>
    </tr>
    <tr>
      <td><label>USD :</label></td>
      <td> <input type="text" name="usd" placeholder="USD" value="0"><br></td>
    </tr>
   <tr><td><input type="submit" id="submit" value="Converter"></td></tr>
  </table>
  </form>
<h1>${result1}</h1>
</body>
</html>