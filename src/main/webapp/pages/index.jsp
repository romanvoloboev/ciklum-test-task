<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/index.css"/>
    <script type="text/javascript" src="resources/js/libs/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="resources/js/index.js"></script>
</head>
<body>
<table>
    <thead>
    <tr>
        <th scope="col">Message text</th>
        <th scope="col">Username</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<div class="footer">
    <button class="add">Add new message</button>
</div>

<div id="back">
    <div id="modalWindow">
        <form>
            <div class="form-row">
                <h4></h4>
            </div>
            <div class="form-row">
                <label for="username">Username</label>
                <input id="username"/>
            </div>
            <div class="form-row">
                <label for="message">Message text</label>
                <textarea id="message"></textarea>
            </div>
            <div class="form-row buttons">
                <button id="saveOrUpdateBtn" class="update">Save</button>
                <button class="cancel">Cancel</button>
            </div>
            <div id="error" class="form-row error"></div>
        </form>
    </div>
</div>
</body>
</html>
