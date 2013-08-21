<!DOCTYPE html>

<html>
    <head>
        <script type="text/javascript" src="jquery/jquery-1.9.0.js"></script>
        <script type="text/javascript" src="jquery/jquery.atmosphere.js"></script>
        <script type="text/javascript" src="jquery/application.js"></script>

        <meta charset="utf-8">
        <title>Test page</title>

        <style>
            * {
                font-family: tahoma;
                font-size: 12px;
                padding: 0px;
                margin: 0px;
            }

            p {
                line-height: 18px;
            }

            div {
                width: 500px;
                margin-left: auto;
                margin-right: auto;
            }

            #message_counter {
                padding: 5px;
                background: #ddd;
                border-radius: 5px;
                border: 1px solid #CCC;
                margin-top: 10px;
            }

            #content {
                padding: 5px;
                background: #ddd;
                border-radius: 5px;
                border: 1px solid #CCC;
                margin-top: 10px;
            }

            #control {
                padding: 5px;
                background: #ddd;
                border-radius: 5px;
                border: 1px solid #CCC;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div id="message_counter">Messages received: <span id="counter">0</span></div>
        <div id="content"></div>
        <div id="control">
            <input type="radio" name="transport" value="websocket" checked="checked" />WebSocket<br />
            <input type="radio" name="transport" value="sse" />SSE<br />
            <input type="radio" name="transport" value="long-polling" />Long-polling<br />
            <input type="button" id="connectButton" value="Connect"/>
            <input type="button" id="disconnectButton" value="Disconnect"/>
        </div>
    </body>
</html>