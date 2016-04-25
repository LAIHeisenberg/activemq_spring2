<html>
<body>
<h2>Hello World!</h2>

<a href="test">click me</a>
<br />
<a href="queue/sender">queue send.</a>
<form id="messageForm" action="/queue/sender">
    <textarea name="message" autofocus="autofocus" ></textarea>
    <input type="submit" value="submit" />
</form>
<br />

<form action="/topic/sender">
    <textarea name="topicMessage"></textarea>
    <input type="submit" value="topic submit">
</form>
</body>
</html>
