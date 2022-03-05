## Cv03

```http
GET http://localhost:8080/form
```

```html
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Simple form</title>
</head>
<body>
<h2>Simple form</h2>
<form method="post" action="/form">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value=""><br>
    <label for="age">Age:</label><br>
    <input type="number" id="age" name="age" value=""><br><br>
    <input type="submit" value="Submit"/>
    <input type="reset" value="Reset"/>
</form>
</body>
</html>
```

```http
GET http://localhost:8080/form?name=Nikola&age=22
```

```html
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Simple form</title>
</head>
<body>
<h2>Simple form</h2>
<form method="post" action="/form">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="Nikola"><br>
    <label for="age">Age:</label><br>
    <input type="number" id="age" name="age" value="22"><br><br>
    <input type="submit" value="Submit"/>
    <input type="reset" value="Reset"/>
</form>
</body>
</html>
```

```http
POST http://localhost:8080/form?name=Nikola&age=22
```

```html
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Greeting</title>
</head>
<body>
<p>Hello, Nikola!</p>
<p>You are 22 years old.</p>
<a href="/form">Submit another user</a>
<a href="/users">See all users</a>
</body>
</html>
```

```http
GET http://localhost:8080/users
```

```html
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h2>Your users</h2>
<table>
    <tr>
        <td>Nikola</td>
        <td>22</td>
    </tr>
</table>
<h2>All users</h2>
<table>
    <tr>
        <td>Nikola</td>
        <td>22</td>
    </tr>
</table>
</body>
</html>
```
## Cv04
