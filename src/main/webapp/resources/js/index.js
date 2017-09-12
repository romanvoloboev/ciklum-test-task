var updMsg;

$(function () {
    loadMessages();
    var $body = $('body');

    $body.on('click', '.delete', function () {
        deleteMessage($(this));
    });

    $body.on('click', '.edit', function (e) {
        e.preventDefault();
        setUpdateData($(this));
    });

    $body.on('click', '.update', function (e) {
        e.preventDefault();
        updateMessage();
    });

    $body.on('click', '.create', function (e) {
        e.preventDefault();
        createMessage();
    });

    $body.on('click', '.add', function (e) {
        e.preventDefault();
        showModal(false);
    });

    $body.on('click', '.cancel', function (e) {
        e.preventDefault();
        hideModal();
    });

});

function updateMessage() {
    updMsg.userName = $('#username').val();
    updMsg.text = $('#message').val();

    $.ajax({
        type: 'PUT',
        url: '/message/' + updMsg.messageId,
        data: JSON.stringify(updMsg),
        contentType: 'application/json',
        success: function () {
            hideModal();
            loadMessages();
        },
        error: function (response) {
            showError(response.responseJSON.errorMessage);
        }
    });
}

function createMessage() {
    updMsg = {};
    updMsg.userName = $('#username').val();
    updMsg.text = $('#message').val();

    $.ajax({
        type: 'POST',
        url: '/message/',
        data: JSON.stringify(updMsg),
        contentType: 'application/json',
        success: function () {
            hideModal();
            loadMessages();
        },
        error: function (response) {
            showError(response.responseJSON.errorMessage);
        }
    });
}

function loadMessages() {
    $('table tbody').empty();
    $.ajax({
        type: "GET",
        url: "/message",
        dataType: "json",

        success: function (response) {
            var html = '';
            if (response.length !== 0) {
                $.each(response, function (i, obj) {
                    html += '<tr>' +
                        '<td class="msg" data-label="Message text">' + obj.text + '</td>' +
                        '<td class="usr" data-label="Username">' + obj.userName + '</td>' +
                        '<td data-label="Action">' +
                        '<button class="edit" data-message-id=' + obj.messageId + '>Edit</button> ' +
                        '<button class="delete" data-message-id=' + obj.messageId + '>Delete</button>' +
                        '</td>' +
                        '</tr>';
                });
            } else {
                html = '<tr><td colspan="3">There are no messages yet :(</td></tr>';
            }
            $('table').append(html);
        }
    });
}

function deleteMessage(obj) {
    var id = obj.attr('data-message-id');
    $.ajax({
        type: "DELETE",
        url: "/message/" + id,
        success: function () {
            loadMessages();
        }
    });
}

function hideModal() {
    $('#back').fadeOut(100);
    $('#modalWindow').hide();
}

function showModal(isForUpdate) {
    var modalHeader;
    var btnCaption;
    var $error = $('#error');
    var $saveOrUpdateBtn = $('#saveOrUpdateBtn');
    var $username = $('#username');

    $error.text('');
    $error.hide();

    if (isForUpdate) {
        modalHeader = 'Update message';
        btnCaption = 'Update';
        $saveOrUpdateBtn.attr('class', 'update');
        $username .prop('disabled', true);
    } else {
        modalHeader = 'Create message';
        btnCaption = 'Create';
        $saveOrUpdateBtn.attr('class', 'create');
        $username .removeAttr('disabled');

        $username .val('');
        $('#message').val('');
    }

    $saveOrUpdateBtn.text(btnCaption);
    $('.form-row h4').text(modalHeader);
    $('#back').fadeIn(100);
    $('#modalWindow').show();
}

function setUpdateData(obj) {
    var id = obj.attr('data-message-id');
    var msg = obj.closest("tr").find(".msg").text();
    var usr = obj.closest("tr").find(".usr").text();

    updMsg = {};
    updMsg.messageId = id;
    $('#username').val(usr);
    $('#message').val(msg);
    showModal(true);
}

function showError(msg) {
    var $error = $('#error');
    $error.text(msg);
    $error.show();
}




