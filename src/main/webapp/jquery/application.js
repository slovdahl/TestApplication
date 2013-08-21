$(function() {
    "use strict";

    var content = $('#content');
    var counter = $('#counter');
    var socket = $.atmosphere;
    var subSocket;
    var myId;
    var messagesReceived = 0;
    var request;
    var pingScheduler;

    $('#connectButton').click(function() {
        myId = prompt('Give ID');
        if (myId === undefined || myId === null) {
            return;
        }
        request = {
            url: 'http://127.0.0.1:8080/TestApplication/test',
            contentType: "application/json",
            enableProtocol: true,
            trackMessageLength: true,
            enableXDR: true,
            readResponseHeaders: false,
            logLevel: 'debug',
            transport: $('input[name=transport]:checked').val()
        };
        request.onOpen = function(response) {
            addMessage('Connected using ' + response.transport, 'black', new Date());
            pingScheduler = setInterval(sendKeepAlive, 5000);
        };

        request.onMessage = function(response) {
            if (response.responseBody !== undefined) {
                addMessage($.trim(response.responseBody), 'black', new Date());
                messagesReceived++;
                updateMessagesReceived();
            }
        };

        request.onClose = function(response) {
            addMessage('Connection closed: ' + myId, 'red', new Date());
            clearInterval(pingScheduler);
        };

        request.onError = function(response) {
            addMessage('ERROR: Problem with the socket: ' + response.toSource(), 'red', new Date());
            clearInterval(pingScheduler);
        };

        request.onReconnect = function(request, response) {
            addMessage('Reconnecting to server..', 'red', new Date());
        };

        request.onTransportFailure = function(errorMsg, request) {
            addMessage('Transport failure: ' + errorMsg, 'red', new Date());
        };
        addMessage('Connecting..', 'black', new Date());
        subSocket = socket.subscribe(request);
        $(this).attr("disabled", true);
    });

    $('#disconnectButton').click(function() {
        socket.unsubscribe();
        $('#connectButton').attr("disabled", false);
    });

    function addMessage(message, color, datetime) {
        content.append('<p><span style="color:' + color + '">' +
                formatTimestamp(datetime) + ': ' + message + '</span></p>');
    }

    function updateMessagesReceived() {
        counter.html(messagesReceived + ' (' + formatTimestamp(new Date()) + ')');
    }

    function formatTimestamp(datetime) {
        return (datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
                + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes()) + ':'
                + (datetime.getSeconds() < 10 ? '0' + datetime.getSeconds() : datetime.getSeconds());
    }

    function sendKeepAlive() {
        addMessage("Sending PING", 'black', new Date());
        subSocket.push(JSON.stringify({message: "PING"}));
    }
});