<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container p-3 my-3 bg-dark text-white">
<img src="images/yong.jpeg">
<h3>MUSIC RECOMMENDATION</h3>
</div>
<div class="container">
<form action="MusicControllerServlet" class="was-validated" method="GET">
<input type="hidden" class="form-control" id="yj" name="song_Id"  value="<%=(String)session.getAttribute("user_Id")%>"/>
<input type="hidden" name="command" value="ADD"/>
			<div class="form-group">
				<label>Song Name:</label>
				<input type="text" class="form-control" id="yj1" placeholder="Enter firstname" name="song_Name" required/>
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
      		</div>
			<div class="form-group">
				<label>Singer:</label>
				<input type="text" class="form-control" id="yj2" placeholder="Enter lastname" name="song_Singer" required/>
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
      		<div class="form-group">
      			<label>Url:</label>
      			<input type="text" class="form-control" id="yj3" placeholder="Enter url" name="song_Url" required/>
      			<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
      		</div>	
      		</div>
			<span>
				<select name = "song_Year">
				<option>2020</option>
				<option>2010</option>
				<option>2000</option>
				<option>1990</option>
				<option>1980</option>
				</select>
			</span>
			<span>
				<select name = "song_Genre">
				<option>발라드</option>
				<option>랩/힙합</option>
				<option>댄스</option>
				<option>트로트</option>
				<option>해외</option>
				</select>
			</span>
			<span>
				<select name = "song_Mood">
				<option>신나는</option>
				<option>편안한</option>
				<option>조용한</option>
				<option>슬픈</option>
				<option>행복한</option>
				</select>
			</span>
    		<br><br>
				<button type="submit" class="btn btn-primary">save</button>
</form>
<a href ="MusicControllerServlet">Back to List</a>
</div>

</body>
</html>