<BR>yolanda<br>
<!DOCTYPE html>
 
<html>
    <head>
        <title>Echo Chamber</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
       
<!--        <div>
            <input type="text" id="messageinput"/>
        </div>-->
	<BR>
	<div>
	    Face&nbsp; <INPUT id="face"  />
	</div>
	<BR>
	<div>
	    Finger&nbsp; <INPUT id="finger"  />
	</div>
	<BR>
	<div>
	    Taco&nbsp; <INPUT id="taco"  />
	</div>
	<BR>
	<div>
	    How Many&nbsp; <INPUT id="howMany"  />
	</div>
	<BR>
	<div>
	    Is that too many &nbsp; <INPUT id="isThatTooMany"  />
	</div>
	<BR><BR><BR>
	
        <div>
            <button type="button" onclick="openSocket();" >Open</button>
            <button type="button" onclick="send();" >Send</button>
            <button type="button" onclick="closeSocket();" >Close</button>
	    <TEXT id="messages"/>
        </div>
        <!-- Server responses get written here -->
	
	<BR><BR>
	<div>
	    Face&nbsp; <INPUT id="face2" readonly />
	</div>
	<BR>
	<div>
	    Finger&nbsp; <INPUT id="finger2" readonly />
	</div>
	<BR>
	<div>
	    Taco&nbsp; <INPUT id="taco2" readonly />
	</div>
	<BR>
	<div>
	    How Many&nbsp; <INPUT id="howMany2" readonly />
	</div>
	<BR>
	<div>
	    Is that too many &nbsp; <INPUT id="isThatTooMany2" readonly />
	</div>
	<BR><BR><BR>
       
        <!-- Script to utilise the WebSocket -->
        <script type="text/javascript">
                       
            var webSocket;
            var messages = document.getElementById("messages");
           
           
            function openSocket(){
                // Ensures only one connection is open at a time
                if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                   writeResponse("WebSocket is already opened.");
                    return;
                }
                // Create a new instance of the websocket
                webSocket = new WebSocket("ws:" + location.host  + "/BrianWebSockets/echo");
                /**
                 * Binds functions to the listeners for the websocket.
                 */
                webSocket.onopen = function(event){
                    // For reasons I can't determine, onopen gets called twice
                    // and the first time event.data is undefined.
                    // Leave a comment if you know the answer.
                    if(event.data === undefined)
                        return;
 
                    writeResponse(event.data);
                };
 
                webSocket.onmessage = function(event){
                    writeResponse(event.data);
                };
 
                webSocket.onclose = function(event){
                    writeResponse("Connection closed");
                };
            }
           
            /**
             * Sends the value of the text input to the server
             */
            function send(){
//                var text = document.getElementById("messageinput").value;
                var face = document.getElementById("face").value;
                var finger = document.getElementById("finger").value;
                var taco = document.getElementById("taco").value;
                var howMany = document.getElementById("howMany").value;
                var ittm = document.getElementById("isThatTooMany").value;
		
		var yorkie = '{"face":"' + face
			+ '","finger":"' + finger 
			+ '","taco":"' + taco 
			+ '","howMany":' + howMany
			+ ',"isThatTooMany":' + ittm + '}';
		
		//{"face":"furry","finger":"none","taco":"love","howMany":22,"isThatTooMany":true}
		//{"face":"aa","finger":"bb","taco":"cccc","howMany":234","isThatTooMany":"false"}
                webSocket.send(yorkie);
            }
           
            function closeSocket(){
                webSocket.close();
            }
 
            function writeResponse(text){
                messages.innerHTML += "<br/>" + text;
		
		var obj = JSON.parse(text);
		document.getElementById("face").value = "";
		document.getElementById("finger").value = "";
		document.getElementById("taco").value = "";
		document.getElementById("howMany").value = "";
		document.getElementById("isThatTooMany").value = "";
		
		document.getElementById("face2").value = obj.face;
		document.getElementById("finger2").value = obj.finger;
		document.getElementById("taco2").value = obj.taco;
		document.getElementById("howMany2").value = obj.howMany;
		document.getElementById("isThatTooMany2").value = obj.isThatTooMany;
            }
           
        </script>
       
    </body>
</html>